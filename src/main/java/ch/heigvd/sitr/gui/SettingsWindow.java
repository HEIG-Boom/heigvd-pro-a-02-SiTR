package ch.heigvd.sitr.gui;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow {

    JFrame f;

    JPanel settingsPanel;

    private JLabel acronym;
    private JLabel title;
    private JLabel subtitle1;
    private JSeparator separator1;

    private JLabel scenarioLabel;
    private JComboBox scenarioSelector;
    private JSeparator separator2;

    private JSpinner controllerCount1;
    private JSpinner controllerCount2;
    private JSpinner controllerCount3;
    private JSeparator separator3;

    private JLabel subtitle2;
    private JComboBox behaviorSelector;
    private JSeparator sparator4;

    private JButton startButton;

    SettingsWindow() {
        GridBagConstraints gbc;

        f = new JFrame("Paramétrage");
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null));

        acronym = new JLabel();
        acronym.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 26));
        acronym.setHorizontalAlignment(0);
        acronym.setHorizontalTextPosition(0);
        acronym.setText("SiTR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        settingsPanel.add(acronym,gbc);

        title = new JLabel();
        title.setFont(new Font("default", Font.PLAIN, 18));
        title.setText("Simulation de Trafic Routier");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 50, 0, 50);
        settingsPanel.add(title, gbc);

        subtitle1 = new JLabel();
        subtitle1.setHorizontalAlignment(0);
        subtitle1.setText("Choix des paramètres du scénario");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        settingsPanel.add(subtitle1, gbc);

        separator1 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        settingsPanel.add(separator1, gbc);

        scenarioLabel = new JLabel();
        scenarioLabel.setText("Scénario :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 30);
        settingsPanel.add(scenarioLabel, gbc);
        scenarioSelector = new JComboBox();

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
        settingsPanel.add(scenarioSelector, gbc);

        separator2 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        settingsPanel.add(separator2, gbc);

        /********************************************************************/
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(11);
        label1.setHorizontalTextPosition(4);
        label1.setText("Agressif :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        settingsPanel.add(label1, gbc);

        controllerCount1 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 10, 0, 0);
        settingsPanel.add(controllerCount1, gbc);

        final JLabel label2 = new JLabel();
        label2.setText("Passif :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        settingsPanel.add(label2, gbc);

        controllerCount2 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 10, 0, 0);
        settingsPanel.add(controllerCount2, gbc);

        final JLabel label3 = new JLabel();
        label3.setText("Autonome :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        settingsPanel.add(label3, gbc);

        controllerCount3 = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 10, 0, 0);
        settingsPanel.add(controllerCount3, gbc);
        /********************************************************************/

        separator3 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        settingsPanel.add(separator3, gbc);

        subtitle2 = new JLabel();
        subtitle2.setHorizontalAlignment(0);
        subtitle2.setHorizontalTextPosition(0);
        subtitle2.setText("Comportement des véhicules lorsqu'ils arrivent à destination :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        settingsPanel.add(subtitle2, gbc);

        behaviorSelector = new JComboBox();

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
        settingsPanel.add(behaviorSelector, gbc);

        sparator4 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        settingsPanel.add(sparator4, gbc);

        startButton = new JButton();
        startButton.setText("Lancer");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 3;
        gbc.ipadx = 50;
        settingsPanel.add(startButton, gbc);

        //f.setSize(new Dimension(700,500));
        f.setContentPane(settingsPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.pack();
        f.setVisible(true);

    }

    public static void main(String[] args) {
        new SettingsWindow();
    }
}
