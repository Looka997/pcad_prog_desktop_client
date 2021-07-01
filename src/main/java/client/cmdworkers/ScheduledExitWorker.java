package client.cmdworkers;

import javax.swing.*;
import java.net.InetAddress;

public class ScheduledExitWorker extends CommandWorker {

    private int sleeptime;
    public ScheduledExitWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, int sleeptime, InetAddress addr, int port) {
        super(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
        this.sleeptime = sleeptime;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        EnterWorker enterWorker = new EnterWorker(plate, brand, textArea, enterButton, exitButton, textField, addr, port, true);
        enterWorker.execute();
        if (!enterWorker.get())
            return false;
        Thread.sleep(sleeptime * 1000);
        ExitWorker exitWorker = new ExitWorker(plate, brand, textArea, enterButton, exitButton, textField, addr, port, true);
        exitWorker.execute();
        return exitWorker.get();
    }

    @Override
    protected void done() {
        enterButton.setEnabled(true);
        textField.setText("");
        textField.setEnabled(true);
    }
}
