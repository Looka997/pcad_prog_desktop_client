package client;


import common.Brands;
import common.Targa;
import common.Targhe;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.*;

public class ClientGUI {
    final static boolean RIGHT_TO_LEFT = false;
    private RemoteClient client;

    public ClientGUI() throws UnknownHostException {
        this(new RemoteClient(InetAddress.getLocalHost(), 8080, new Targhe().random(), Brands.random().name()));
    }

    public ClientGUI(RemoteClient Client){

    }

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton enterButton, exitButton;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;

        JTextArea textArea = new JTextArea(10,40);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(24,30,24,30);
        textArea.setEditable(false);
        pane.add(textArea, c);

        enterButton = new JButton("Enter");
        c.gridy = 1;
        c.gridx = 0;
        c.ipady = 10;   // y padding for both buttons
        c.ipadx = 7;    // x padding for both buttons
        c.insets = new Insets(0,16,0,8);
        pane.add(enterButton, c);

        JTextField textField = new JTextField(6);
        c.gridx = 1;
        c.insets = new Insets(0,8,0,16);
        pane.add(textField,c);

        exitButton = new JButton("Exit");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(16,16,16,8);
        pane.add(exitButton, c);

        enterButton.addActionListener(new EnterListener(enterButton, textField, exitButton));

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Desktop client GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
