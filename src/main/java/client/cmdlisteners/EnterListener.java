package client.cmdlisteners;

import client.cmdworkers.EnterWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
public class EnterListener extends CommandListener {

    private EnterWorker worker = null;
    public EnterListener(String plate, String brand, JButton enterButton, JButton exitButton, JTextField textField, JTextArea textArea, InetAddress addr, int port){
        super(plate, brand, enterButton, exitButton, textField, textArea, addr, port);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (worker != null)
            return;
        else{
            enterButton.setEnabled(false);
            textField.setEnabled(false);
            worker = new EnterWorker(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
            worker.execute();
            worker = null;
        }

    }
}
