package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterListener implements ActionListener {

    private JButton enterButton, exitButton;
    private JTextField textField;
    private EnterWorker worker = null;
    public EnterListener(JButton enterButton, JTextField textField, JButton exitButton){
        this.enterButton = enterButton;
        this.textField = textField;
        this.exitButton = exitButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (worker != null)
            return;
        String text = textField.getText();
//        worker = new EnterWorker(text);
    }
}
