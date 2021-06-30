package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
public class EnterListener implements ActionListener {

    private JButton enterButton, exitButton;
    private JTextField textField;
    private JTextArea textArea;
    private EnterWorker worker = null;
    private final String plate, brand;
    private final InetAddress addr;
    private final int port;
    public EnterListener(String plate, String brand, JButton enterButton, JTextField textField, JButton exitButton, JTextArea textArea, InetAddress addr, int port){
        this.enterButton = enterButton;
        this.textField = textField;
        this.exitButton = exitButton;
        this.plate = plate;
        this.brand = brand;
        this.addr = addr;
        this.port = port;
        this.textArea = textArea;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (worker != null)
            return;
        else{
            enterButton.setEnabled(false);
            textField.setEnabled(false);
            worker = new EnterWorker(plate, brand, textArea, enterButton, textField, addr, port);
            worker.execute();
            worker = null;
        }

//        worker = new EnterWorker(text);
    }
}
