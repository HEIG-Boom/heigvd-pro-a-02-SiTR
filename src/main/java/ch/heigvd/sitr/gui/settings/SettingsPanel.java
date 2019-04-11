/*
 * Filename: SettingsPanel.java
 * Creation date: 26.03.2019
 */

package ch.heigvd.sitr.gui.settings;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.model.ScenarioType;
import ch.heigvd.sitr.model.Simulation;
import ch.heigvd.sitr.model.VehicleBehaviourType;
import ch.heigvd.sitr.model.VehicleControllerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Settings Panel class represents the first window's panel. Offers settings options for the simulation
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
class SettingsPanel extends JPanel {
    /**
     * Package-Private Constructor of the panel. Constructs all components for the settings window
     */
    SettingsPanel() {
        GridBagConstraints gbc;

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null));

        final JLabel acronym = new JLabel();
        acronym.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 26));
        acronym.setHorizontalAlignment(JLabel.CENTER);
        acronym.setHorizontalTextPosition(JLabel.CENTER);
        acronym.setText("SiTR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(acronym, gbc);

        final JLabel title = new JLabel();
        title.setFont(new Font("default", Font.PLAIN, 18));
        title.setText("Simulation de Trafic Routier");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 50, 0, 50);
        add(title, gbc);

        final JLabel subtitle1 = new JLabel();
        subtitle1.setHorizontalAlignment(JLabel.CENTER);
        subtitle1.setText("Choix des paramètres du scénario");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(subtitle1, gbc);

        final JSeparator separator1 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(separator1, gbc);

        final JLabel scenarioLabel = new JLabel();
        scenarioLabel.setText("Scénario :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 30);
        add(scenarioLabel, gbc);

        final JComboBox scenarioSelector = new JComboBox();

        /* Adding scenario to the scenario selector */

        for (ScenarioType st : ScenarioType.values()) {
            // call toString for each object
            scenarioSelector.addItem(st);
        }

        /*--------------------------------------*/

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scenarioSelector, gbc);

        final JSeparator separator2 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(separator2, gbc);

        /* Adding one line (label + spinner) for each controller */

        int baseY = gbc.gridy + 1;
        for (VehicleControllerType vc : VehicleControllerType.values()) {
            // adding label
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = baseY;
            gbc.insets = new Insets(0, 0, 10, 0);
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel(vc.toString()), gbc);

            // adding spinner (0 to 1000)
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = baseY;
            gbc.gridwidth = 2;
            gbc.ipadx = 50;
            gbc.insets = new Insets(0, 10, 10, 0);
            add(new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1)), gbc);

            baseY++;
        }

        /*--------------------------------------------------------------*/

        final JSeparator separator3 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = baseY++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(separator3, gbc);

        final JLabel subtitle2 = new JLabel();
        subtitle2.setHorizontalAlignment(JLabel.CENTER);
        subtitle2.setHorizontalTextPosition(JLabel.CENTER);
        subtitle2.setText("Comportement des véhicules lorsqu'ils arrivent à destination :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = baseY++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(subtitle2, gbc);

        final JComboBox behaviorSelector = new JComboBox();

        /* Adding behavior to the behavior selector */

        for (VehicleBehaviourType vb : VehicleBehaviourType.values()) {
            // call toString for each object
            behaviorSelector.addItem(vb);
        }

        /*--------------------------------------*/

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = baseY++;
        gbc.gridwidth = 3;
        gbc.ipadx = 50;
        add(behaviorSelector, gbc);

        final JSeparator separator4 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = baseY++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(separator4, gbc);

        final JButton startButton = new JButton("Lancer");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsWindow.getInstance().closeWindow();
//                SimulationWindow.getInstance();
                new Simulation(8).loop();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = baseY;
        gbc.gridwidth = 3;
        gbc.ipadx = 50;
        add(startButton, gbc);
    }
}
