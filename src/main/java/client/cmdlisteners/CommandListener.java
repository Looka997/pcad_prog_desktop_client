package client.cmdlisteners;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public abstract class CommandListener implements ActionListener {
    JButton enterButton;
    JButton exitButton;
    JTextField textField;
    JTextArea textArea;
    final String plate;
    final String brand;
    final InetAddress addr;
    final int port;

    public CommandListener(String plate, String brand, JButton enterButton, JButton exitButton, JTextField textField, JTextArea textArea, InetAddress addr, int port) {
        this.enterButton = enterButton;
        this.textField = textField;
        this.exitButton = exitButton;
        this.plate = plate;
        this.brand = brand;
        this.addr = addr;
        this.port = port;
        this.textArea = textArea;
    }
}
