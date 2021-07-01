package client.cmdworkers;

import javax.swing.*;
import java.net.InetAddress;

public abstract class CommandWorker extends SwingWorker<Boolean, Void> {
    final InetAddress addr;
    final int port;
    final String plate;
    final String brand;
    final JTextArea textArea;
    final JButton enterButton;
    final JButton exitButton;
    final JTextField textField;

    public CommandWorker(String plate, String brand, JTextArea textArea, JButton enterButton, JButton exitButton, JTextField textField, InetAddress addr, int port){
        this.addr = addr;
        this.port = port;
        this.plate = plate;
        this.brand = brand;
        this.textArea = textArea;
        this.enterButton = enterButton;
        this.textField = textField;
        this.exitButton = exitButton;
    }
}
