package client;

import javax.swing.*;
import java.net.InetAddress;

public class EnterWorker extends SwingWorker<String, String> {
    private final InetAddress addr;
    private final int port;
    private final String plate, brand;

    public EnterWorker(String plate, String brand,InetAddress addr, int port) {
        this.addr = addr;
        this.port = port;
        this.plate = plate;
        this.brand = brand;
    }

    @Override
    protected String doInBackground() throws Exception {
        new RemoteClient(addr, port, plate, brand) {
            @Override
            public void run() {
                if (!park()) {
                    System.out.println("client " + plate + " could not park");
                    return;
                }
            }
        }.run();
        return null;
    }
}
