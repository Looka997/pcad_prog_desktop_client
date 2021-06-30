package client;

import javax.swing.*;
import java.awt.*;

public class RegisterWorker extends SwingWorker<Void, Boolean> {

    private Container pane;
    public RegisterWorker(Container pane) {
        this.pane = pane;
    }

    @Override
    protected Void doInBackground() throws Exception {

        JButton enterButton, exitButton;
        pane.removeAll();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        JTextArea textArea = new JTextArea(10, 40);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(24, 30, 24, 30);
        textArea.setEditable(false);
        pane.add(textArea, c);

        enterButton = new JButton("Enter");
        c.gridy = 1;
        c.gridx = 0;
        c.ipady = 10;   // y padding for both buttons
        c.ipadx = 7;    // x padding for both buttons
        c.insets = new Insets(0, 16, 0, 8);
        pane.add(enterButton, c);

        JTextField textField = new JTextField(6);
        c.gridx = 1;
        c.insets = new Insets(0, 8, 0, 16);
        pane.add(textField, c);

        exitButton = new JButton("Exit");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(16, 16, 16, 8);
        pane.add(exitButton, c);
        pane.requestFocusInWindow();
        pane.repaint();
        pane.validate();

        enterButton.addActionListener(new EnterListener(enterButton, textField, exitButton));
        return null;
    }
}
