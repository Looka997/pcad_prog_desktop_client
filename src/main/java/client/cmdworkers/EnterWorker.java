package client.cmdworkers;

import client.RemoteClient;

import javax.swing.*;
import java.net.InetAddress;

public class EnterWorker extends CommandWorker {

    private boolean enable = false;
    private boolean skipDone = false;

    public EnterWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port) {
        super(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
    }
    EnterWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port, boolean skipDone) {
        super(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
        this.skipDone = skipDone;
    }

    @Override
    protected Boolean doInBackground() throws Exception {

        new RemoteClient(addr, port, plate, brand) {
            @Override
            public void run() {
                if (!park()) {
                    textArea.append(plate + " could not park\n");
                    enable = true;
                    return;
                }
                textArea.append(plate + " parked successfully\n");
                return;
            }
        }.run();
        return !enable;
    }

    @Override
    protected void done() {
        if (skipDone)
            return;
        if (enable){
            enterButton.setEnabled(true);
            textField.setEnabled(true);
        }
        else
            exitButton.setEnabled(true);
    }
}
