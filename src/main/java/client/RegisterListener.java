package client;

import client.cmdlisteners.EnterListener;
import client.cmdlisteners.ExitListener;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.management.OperationsException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class RegisterListener implements ActionListener {
    private final JTextField plate;
    private final JComboBox brand;
    private final InetAddress addr;
    private final int port;

    public RegisterListener(JTextField plateTxt, JComboBox brandComboBox, InetAddress addr, int port) {
        plate = plateTxt;
        brand = brandComboBox;
        this.addr = addr;
        this.port = port;
    }

    private String getJsonResp() throws OperationsException {
        try {
            URL url = new URL("http://" + addr.getHostAddress() + ":4567/users/" + plate.getText());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String json = in.readLine();
            JSONTokener tokener = new JSONTokener(json);
            JSONObject jsonObject = new JSONObject(tokener);
            String res = (String) jsonObject.get("state");
            in.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new OperationsException("could not retrieve plate state");
    }

    private void disableButton(JButton enterButton, JButton exitButton){
        String state = "";
        try {
            state = getJsonResp();
        } catch (OperationsException e) {
            e.printStackTrace();
        }
        if (state.equals(""))
            return;
        if (state.equals("UNSET") || state.equals("USCITA"))
            exitButton.setEnabled(false);
        else if (state.equals("ENTRATA"))
            enterButton.setEnabled(false);
    }

    private void modifyGui(String pl_str, String br_str){
        Container pane = plate.getParent();
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
        disableButton(enterButton, exitButton);
        pane.repaint();
        pane.validate();

        enterButton.addActionListener(new EnterListener(pl_str, br_str, enterButton, exitButton,textField, textArea, addr, port));
        exitButton.addActionListener(new ExitListener(pl_str, br_str, enterButton, exitButton,textField, textArea, addr, port));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pl_str, br_str;
        pl_str = plate.getText();
        br_str = (String) brand.getSelectedItem();
        if (pl_str.equals("") || br_str.equals(""))
            return;
        modifyGui(pl_str, br_str);
    }
}
