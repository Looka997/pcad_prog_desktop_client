package client.cmdworkers;

import org.json.JSONObject;
import org.json.JSONTokener;

import javax.management.OperationsException;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public abstract class CommandWorker extends SwingWorker<Boolean, Void> {
    final InetAddress addr;
    final int port;
    final String plate;
    final String brand;
    final JTextArea textArea;
    final JButton enterButton;
    final JButton exitButton;
    final JTextField textField;

    public CommandWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port){
        this.addr = addr;
        this.port = port;
        this.plate = plate;
        this.brand = brand;
        this.textArea = textArea;
        this.enterButton = enterButton;
        this.textField = textField;
        this.exitButton = exitButton;
    }

    public String getJsonResp(String plate) throws OperationsException {
        try {
            URL url = new URL("http://" + addr.getHostAddress() + ":4567/users/" + plate);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String json = in.readLine();
            JSONTokener tokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(tokener);
            String res = (String) jsonObject.get("state");
            in.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new OperationsException("could not retrieve plate state");
    }

    public void refresh(){
        try {
            String state = getJsonResp(plate);
            if (state.equals("ENTRATA")) {
                exitButton.setEnabled(true);
                enterButton.setEnabled(false);
                textField.setEnabled(false);
                return;
            }
                exitButton.setEnabled(false);
                enterButton.setEnabled(true);
                textField.setEnabled(true);
        } catch (OperationsException ex) {
            ex.printStackTrace();
        }
    }
}
