package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.Vehicle;

import javax.swing.*;
import java.awt.*;

public class SimulationWindow {
    private static SimulationWindow instance;

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
        panel.add(new MapPanel(), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new SimControlPanel(), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new CarControlPanel(new Vehicle(2,2)),gbc);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
