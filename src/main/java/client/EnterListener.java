package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class EnterListener implements ActionListener {

    private JButton enterButton, exitButton;
    private JTextField textField;
    private EnterWorker worker = null;
    private final String plate, brand;
    private final InetAddress addr;
    private final int port;
    public EnterListener(String plate, String brand, JButton enterButton, JTextField textField, JButton exitButton, InetAddress addr, int port){
        this.enterButton = enterButton;
        this.textField = textField;
        this.exitButton = exitButton;
        this.plate = plate;
        this.brand = brand;
        this.addr = addr;
        this.port = port;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (worker != null)
            return;
        else{
            worker = new EnterWorker(plate, brand, addr, port);
            worker.execute();
        }

//        worker = new EnterWorker(text);
    }
}
