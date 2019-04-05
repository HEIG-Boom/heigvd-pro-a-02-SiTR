package ch.heigvd.sitr.gui.settings;

import javax.swing.*;

public class SettingsWindow {
    private static SettingsWindow instance;
    private JFrame frame;

    public static SettingsWindow getInstance() {
        if(instance == null){
            instance = new SettingsWindow();
        }
        return instance;
    }

    private SettingsWindow() {
        frame = new JFrame("Paramétrage");
        JPanel settingsPanel = new SettingsPanel();
        frame.setContentPane(settingsPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }

    public void closeWindow() {
        frame.dispose();
    }
}
