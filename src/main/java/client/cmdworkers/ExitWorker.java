package client.cmdworkers;

import client.RemoteClient;

import javax.swing.*;
import java.net.InetAddress;

public class ExitWorker extends CommandWorker {

    private boolean enable = false;

    public ExitWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port) {
        super(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
    }
    @Override
    protected String doInBackground() throws Exception {

        new RemoteClient(addr, port, plate, brand) {
            @Override
            public void run() {
                if (!unpark()) {
                    textArea.append(plate + " could not unpark. Something went wrong?\n");
                    return;
                }
                textArea.append(plate + " unparked successfully\n");
                enable = true;
                return;
            }
        }.run();
        return null;
    }

    @Override
    protected void done() {
        if (enable){
            enterButton.setEnabled(true);
            textField.setEnabled(true);
        } else
            exitButton.setEnabled(true);
    }
}
