package client.cmdworkers;

import client.RemoteClient;

import javax.swing.*;
import java.net.InetAddress;

public class ExitWorker extends CommandWorker {

    private boolean enable = false;
    private boolean skipDone = false;

    public ExitWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port) {
        super(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
    }
    ExitWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port, boolean skipDone) {
        super(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
        this.skipDone = skipDone;
    }
    @Override
    protected Boolean doInBackground() {

        new RemoteClient(addr, port, plate, brand) {
            @Override
            public void run() {
                if (!unpark()) {
                    textArea.append(plate + " could not unpark. Something went wrong?\n");
                    return;
                }
                textArea.append(plate + " unparked successfully\n");
                enable = true;
            }
        }.run();
        return enable;
    }

    @Override
    protected void done() {
        if (skipDone)
            return;
        if (enable){
            enterButton.setEnabled(true);
            textField.setEnabled(true);
        } else
            exitButton.setEnabled(true);
    }
}
