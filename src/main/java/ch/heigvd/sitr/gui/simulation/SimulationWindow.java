/*
 * Filename: SimulationWindow.java
 * Creation date: 28.03.2019
 */

package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.vehicle.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Simulation window class represent the main frame of the simulation. It contains a map panel, a simulation control
 * panel and a car control panel.
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
public class SimulationWindow implements Displayer {
    // the only one instance of the class
    private static SimulationWindow instance;

    private JFrame frame;
    private BufferedImage mapImage;

    private MapPanel mapPanel;

    /**
     * static method of implementation as singleton
     *
     * @return the only one instance of the class
     */
    public static SimulationWindow getInstance() {
        if (instance == null) {
            instance = new SimulationWindow();
        }
        return instance;
    }

    /**
     * private constructor of the window
     * Create a frame and add a map panel, a simulation control panel and a car control panel
     */
    private SimulationWindow() {
        frame = new JFrame("SiTR");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        mapPanel = new MapPanel();
        panel.add(mapPanel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new SimControlPanel(), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new CarControlPanel(new Vehicle(null, 2.0, 2.0)), gbc);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        mapImage = (BufferedImage) mapPanel.createImage(getMapWidth(), getMapHeight());
    }

    @Override
    public int getMapWidth() {
        return MapPanel.WIDTH;
    }

    @Override
    public int getMapHeight() {
        return MapPanel.HEIGHT;
    }

    @Override
    public Graphics2D getSimulationPane() {
        return (Graphics2D) mapImage.getGraphics();
    }

    @Override
    public void repaint() {
        mapPanel.getGraphics().drawImage(mapImage, 0, 0, null);
        mapImage = (BufferedImage) mapPanel.createImage(getMapWidth(), getMapHeight());
    }

    /**
     * Method to close the simulation window
     */
    public void closeWindow() {
        frame.dispose();
        instance = null;
    }
}
