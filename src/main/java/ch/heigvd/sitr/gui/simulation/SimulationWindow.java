package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.vehicle.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class SimulationWindow implements Displayer {
    private static SimulationWindow instance;

    private BufferedImage mapImage;
    private MapPanel mapPanel;

    public static SimulationWindow getInstance() {
        if(instance == null){
            instance = new SimulationWindow();
        }
        return instance;
    }

    private SimulationWindow() {
        JFrame frame = new JFrame("SiTR");

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
        panel.add(new CarControlPanel(new Vehicle(null,2.0, 2.0)),gbc);

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
        mapPanel.getGraphics().drawImage(mapImage,0,0, null);
        mapImage = (BufferedImage) mapPanel.createImage(getMapWidth(), getMapHeight());
    }
}
