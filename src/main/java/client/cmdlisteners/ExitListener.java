package client.cmdlisteners;

import client.cmdworkers.ExitWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;

public class ExitListener extends CommandListener {

    private ExitWorker worker = null;

    public ExitListener(String plate, String brand, JButton enterButton, JButton exitButton, JTextField textField, JTextArea textArea, InetAddress addr, int port){
        super(plate, brand, enterButton, exitButton, textField, textArea, addr, port);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (worker == null) {
            exitButton.setEnabled(false);
            worker = new ExitWorker(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
            worker.execute();
            worker = null;
        }
    }
}

