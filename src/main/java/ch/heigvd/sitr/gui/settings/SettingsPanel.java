package ch.heigvd.sitr.gui.settings;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Settings Panel class represents the first window's panel. Offers settings options for the simulation
 */
class SettingsPanel extends JPanel {
    /**
     * Package-Private Constructor of the panel. Constructs all components for the settings window
     */
    SettingsPanel() {
        GridBagConstraints gbc;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null));

        final JLabel acronym = new JLabel();
        acronym.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 26));
        acronym.setHorizontalAlignment(0);
        acronym.setHorizontalTextPosition(0);
        acronym.setText("SiTR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(acronym, gbc);

        final JLabel title = new JLabel();
        title.setFont(new Font("default", Font.PLAIN, 18));
        title.setText("Simulation de Trafic Routier");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 50, 0, 50);
        this.add(title, gbc);

        final JLabel subtitle1 = new JLabel();
        subtitle1.setHorizontalAlignment(0);
        subtitle1.setText("Choix des paramètres du scénario");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        this.add(subtitle1, gbc);

        final JSeparator separator1 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(separator1, gbc);

        final JLabel scenarioLabel = new JLabel();
        scenarioLabel.setText("Scénario :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 30);
        this.add(scenarioLabel, gbc);

        final JComboBox scenarioSelector = new JComboBox();

        /********************************************************************/
        // appelle toString() de l'objet
        scenarioSelector.addItem("Croisement");
        scenarioSelector.addItem("Quartier");
        scenarioSelector.addItem("Ville");
        scenarioSelector.addItem("Planete");
        /********************************************************************/

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(scenarioSelector, gbc);

        final JSeparator separator2 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(separator2, gbc);

        /********************************************************************/
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(11);
        label1.setHorizontalTextPosition(4);
        label1.setText("Agressif :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        this.add(label1, gbc);

        final JSpinner controllerCount1 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 10, 10, 0);
        this.add(controllerCount1, gbc);

        final JLabel label2 = new JLabel();
        label2.setText("Passif :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        this.add(label2, gbc);

        final JSpinner controllerCount2 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 10, 10, 0);
        this.add(controllerCount2, gbc);

        final JLabel label3 = new JLabel();
        label3.setText("Autonome :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        this.add(label3, gbc);

        final JSpinner controllerCount3 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 10, 10, 0);
        this.add(controllerCount3, gbc);
        /********************************************************************/

        final JSeparator separator3 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(separator3, gbc);

        final JLabel subtitle2 = new JLabel();
        subtitle2.setHorizontalAlignment(0);
        subtitle2.setHorizontalTextPosition(0);
        subtitle2.setText("Comportement des véhicules lorsqu'ils arrivent à destination :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(subtitle2, gbc);

        final JComboBox behaviorSelector = new JComboBox();

        /********************************************************************/
        // appelle toString() de l'objet
        behaviorSelector.addItem("retourne au départ");
        behaviorSelector.addItem("refait le trajet");
        behaviorSelector.addItem("choisi un nouveau trajet");
        /********************************************************************/

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        gbc.ipadx = 50;
        this.add(behaviorSelector, gbc);

        final JSeparator separator4 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(separator4, gbc);

        final JButton startButton = new JButton("Lancer");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsWindow.getInstance().closeWindow();
                SimulationWindow.getInstance();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 3;
        gbc.ipadx = 50;
        this.add(startButton, gbc);
    }
}
