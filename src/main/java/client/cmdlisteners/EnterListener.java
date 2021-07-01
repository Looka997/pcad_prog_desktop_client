package client.cmdlisteners;

import client.cmdworkers.EnterWorker;
import client.cmdworkers.ScheduledExitWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
public class EnterListener extends CommandListener {

    private EnterWorker enterWorker = null;
    private ScheduledExitWorker schedWorker = null;
    public EnterListener(String plate, String brand, JButton enterButton, JButton exitButton, JTextField textField, JTextArea textArea, InetAddress addr, int port){
        super(plate, brand, enterButton, exitButton, textField, textArea, addr, port);
    }

    private void create_execute_enter(){
        textField.setText("");
        enterWorker = new EnterWorker(plate, brand, textArea, enterButton, exitButton, textField, addr, port);
        enterWorker.execute();
        enterWorker = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (enterWorker != null && schedWorker != null)
            return;
        else{
            enterButton.setEnabled(false);
            textField.setEnabled(false);
            if (!textField.getText().equals("")){
                int sleeptime;
                try{
                    sleeptime = Integer.parseInt(textField.getText());
                }catch (NumberFormatException ex){
                    create_execute_enter();
                    return;
                }
                exitButton.setEnabled(false);
                schedWorker = new ScheduledExitWorker(plate, brand, textArea, enterButton, exitButton, textField, sleeptime, addr, port);
                schedWorker.execute();
                schedWorker = null;
            }else{
                create_execute_enter();
                return;
            }
        }

    }
}
