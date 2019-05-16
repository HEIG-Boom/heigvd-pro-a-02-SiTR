/*
 * Filename: SimulationWindow.java
 * Creation date: 28.03.2019
 */

package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.gui.settings.*;
import lombok.Getter;

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
    private boolean backgroundChanged = false;
    private final BufferedImage backgroundMapImage;
    private BufferedImage foregroundMapImage;

    private MapPanel mapPanel;

    @Getter
    private final SimControlPanel simControlPanel;

    @Getter
    private final CarControlPanel carControlPanel;

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
        simControlPanel = new SimControlPanel();
        panel.add(simControlPanel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        carControlPanel = new CarControlPanel();
        panel.add(carControlPanel, gbc);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Create background image
        backgroundMapImage = (BufferedImage) mapPanel.createImage(getMapWidth(), getMapHeight());
        Graphics2D g = backgroundMapImage.createGraphics();
        g.setPaint(Color.decode(mapPanel.getBackgroundColor()));
        g.fillRect(0, 0, backgroundMapImage.getWidth(), backgroundMapImage.getHeight());

        foregroundMapImage = createFgMapImage();
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
        return (Graphics2D) foregroundMapImage.getGraphics();
    }

    @Override
    public Graphics2D getBackgroundSimulationPane() {
        // We assume that if we get background simulation pane, it's to draw something. So we
        // have to repaint the background.
        backgroundChanged = true;
        return (Graphics2D) backgroundMapImage.getGraphics();
    }

    @Override
    public void repaint() {
        if (backgroundChanged) {
            mapPanel.getGraphics().drawImage(backgroundMapImage, 0, 0, null);
            backgroundChanged = false;
        }
        mapPanel.getGraphics().drawImage(foregroundMapImage, 0, 0, null);
        foregroundMapImage = createFgMapImage();
    }

    /**
     * Method to close the simulation window
     */
    public void closeWindow() {
        // stop the main loop timer
        SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().stopLoop();

        // kill the simulation window
        frame.dispose();

        // reset the reference to the simulation window
        instance = null;
    }

    /**
     * This method creates the foreground image on which to draw vehicles
     *
     * @return The created foreground image.
     */
    private BufferedImage createFgMapImage() {
        BufferedImage tmp = (BufferedImage) mapPanel.createImage(getMapWidth(), getMapHeight());
        Graphics2D g = tmp.createGraphics();
        g.setComposite(AlphaComposite.SrcOver);
        g.drawImage(backgroundMapImage, 0, 0, null);
        return tmp;
    }
}
