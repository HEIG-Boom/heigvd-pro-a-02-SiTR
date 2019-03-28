package ch.heigvd.sitr.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SettingsWindow2 {

    JFrame f;
    JPanel settingsPanel;

    SettingsWindow2(String title) {
        f = new JFrame(title);
        settingsPanel = new JPanel();

        settingsPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = c.gridy = 0;
        c.ipadx = c.ipady = 30;

        settingsPanel.add(new JButton("Cool"),c);


        f.getContentPane().add(settingsPanel);
        f.setSize(new Dimension(400,500));
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new SettingsWindow2("Paramétrage");
    }
}
