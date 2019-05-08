/*
 * Filename: SettingsWindow.java
 * Creation date: 26.03.2019
 */

package ch.heigvd.sitr.gui.settings;

import ch.heigvd.sitr.model.Scenario;
import ch.heigvd.sitr.model.VehicleBehaviour;
import ch.heigvd.sitr.model.VehicleControllerType;
import lombok.Getter;

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

    @Getter
    private SettingsPanel settingsPanel;

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
        settingsPanel = new SettingsPanel();
        frame.setContentPane(settingsPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        showWindow();
    }

    /**
     * Method to close the settings window
     */
    public void closeWindow() {
        frame.setVisible(false);
    }

    /**
     * Method to show the settings window.
     * Used to create a new simulation when clicking on the corresponding button in simulation window
     */
    public void showWindow() {
        frame.setVisible(true);
    }

    /**
     * Method used to get the selected scenario
     * @return the selected scenario
     */
    public Scenario getSelectedScenario() {
        return settingsPanel.getSelectedScenario();
    }

    public int getNumberOfController(VehicleControllerType vct) {
        return settingsPanel.getNumberOfController(vct);
    }

    /**
     * Method used to get the selected behaviour of the simulation
     * @return the selected behaviour
     */
    public VehicleBehaviour getSelectedBehaviour() {
        return settingsPanel.getSelectedBehaviour();
    }
}
