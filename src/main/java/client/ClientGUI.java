package client;


import common.Brands;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import javax.swing.*;

public class ClientGUI extends JFrame {
    final static boolean RIGHT_TO_LEFT = false;
    private final InetAddress addr;
    private final int port;

    public ClientGUI(InetAddress addr, int port){
        Objects.requireNonNull(addr);
        this.addr = addr;
        this.port = port;
    }

    public void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setPreferredSize(new Dimension(510, 450));
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;

        JLabel plateLabel = new JLabel("Targa:");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(24,12,8,12);
        c.ipady = 20;
        pane.add(plateLabel,c);

        JTextField plateTxt = new JTextField(12);
        c.gridx = 1;
        c.insets = new Insets(24,12,8,12);
        pane.add(plateTxt,c);

        JLabel brandLabel = new JLabel("Brand:");
        c.gridy = 1;
        c.gridx = 0;
        pane.add(brandLabel,c);

        JComboBox brandList = new JComboBox(Brands.allNames());
        c.gridx = 1;
        pane.add(brandList,c);

        JButton button = new JButton("Register");
        c.gridy = 2;
        c.gridx = 1;
        c.insets = new Insets(24,24,16,12);
        c.ipady = 15;
        c.ipadx = 10;
        pane.add(button, c);

        button.addActionListener(new RegisterListener(plateTxt,brandList, addr, port));
    }

    private void createAndShowGUI() {
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
        Runnable init = () -> {
            try {
                new ClientGUI(InetAddress.getLocalHost(), 8080).createAndShowGUI();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        };
        SwingUtilities.invokeLater(init);
    }
}
