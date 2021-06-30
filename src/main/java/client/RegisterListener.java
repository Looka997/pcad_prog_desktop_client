package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener {
    private JTextField plate;
    private JComboBox brand;

    public RegisterListener(JTextField plateTxt, JComboBox brandComboBox) {
        plate = plateTxt;
        brand = brandComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pl_str, br_str;
        pl_str = plate.getText();
        br_str = (String) brand.getSelectedItem();
        if (pl_str.equals("") || br_str.equals(""))
            return;
        RegisterWorker worker = new RegisterWorker(plate.getParent());
        worker.execute();
    }
}
