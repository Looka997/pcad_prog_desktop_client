package client;

import javax.swing.*;
import java.net.InetAddress;

public class EnterWorker extends SwingWorker<String, String> {
    private final InetAddress addr;
    private final int port;
    private final String plate, brand;
    private final JTextArea textArea;
    private final JButton enterButton;
    private final JTextField textField;
    private boolean enable = false;

    public EnterWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JTextField textField, InetAddress addr, int port) {
        this.addr = addr;
        this.port = port;
        this.plate = plate;
        this.brand = brand;
        this.textArea = textArea;
        this.enterButton = enterButton;
        this.textField = textField;
    }

    @Override
    protected String doInBackground() throws Exception {

        new RemoteClient(addr, port, plate, brand) {
            @Override
            public void run() {
                if (!park()) {
                    textArea.append(plate + " could not park\n");
                    enable = true;
                    return;
                }
                textArea.append(plate + " parked successfully");
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
        }
    }
}
