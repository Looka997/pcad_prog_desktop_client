package client;

import common.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class RemoteClient extends Client {
    private InetAddress address;
    private int port;

    public RemoteClient(InetAddress address, int port, Targa targa, String marca) {
        super(targa, marca);
        this.address = address;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    private boolean recvACK(Socket serverSocket){
        InputStream is = null;
        try {
            is = serverSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader inputReader= new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputReader);
        try {
            return reader.readLine().equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean helper(ContentMessage cm) throws IOException {
        HandshakeMessage hm = new HandshakeMessage(getTarga().getTarghe());
        Socket serverSocket = null;
        OutputStream output = null;
        boolean success;
        try {
            serverSocket = new Socket(getAddress(), getPort());
        } catch (IOException e) {
            return false;
        }
        output = serverSocket.getOutputStream();
        PrintWriter writer = new PrintWriter(output,true);
        writer.println(hm.toString());
        writer.println(cm.toString());
        success = recvACK(serverSocket);
        serverSocket.close();
        return success;
    }

    @Override
    public boolean park(){
        ContentMessage cm;
        cm = new ContentMessage(TipoRichiesta.ENTRATA, getTarga(), getMarca(), new Date());
        try {
            return helper(cm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unpark() {
        ContentMessage cm;
        cm = new ContentMessage(TipoRichiesta.USCITA, getTarga(), getMarca(), new Date());
        try {
            return helper(cm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
