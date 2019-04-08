/*
 * Filename: SettingsWindow.java
 * Creation date: 26.03.2019
 */

package ch.heigvd.sitr.gui.settings;

import javax.swing.*;

/**
 * Settings window class represents the first window that permit to set the simulation
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
public class SettingsWindow {
    // the only one instance of the class
    private static SettingsWindow instance;

    private JFrame frame;

    /**
     * static method of implementation as singleton
     *
     * @return the only one instance of the class
     */
    public static SettingsWindow getInstance() {
        if (instance == null) {
            instance = new SettingsWindow();
        }
        return instance;
    }

    /**
     * private constructor of the window
     * Create a frame and add a settings panel
     */
    private SettingsWindow() {
        frame = new JFrame("Paramétrage");
        JPanel settingsPanel = new SettingsPanel();
        frame.setContentPane(settingsPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Method to close the settings window
     */
    public void closeWindow() {
        frame.dispose();
    }
}
