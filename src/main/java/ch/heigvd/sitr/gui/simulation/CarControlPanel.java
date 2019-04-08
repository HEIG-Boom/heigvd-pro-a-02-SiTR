package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.Vehicle;

import javax.swing.*;
import java.awt.*;

/**
 * Car control panel class represents a car with his stats and his available action.
 */
class CarControlPanel extends JPanel {

    /**
     * Package-private constructor of the panel
     *
     * @param vehicle vehicle observed by the panel
     */
    CarControlPanel(Vehicle vehicle) {
        GridBagConstraints gbc;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null));

        final JSeparator separator = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(separator, gbc);

        final JLabel title = new JLabel("Contrôles du véhicule");
        title.setFont(new Font(null, Font.BOLD, 18));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(title, gbc);

        final JLabel controlerChangeLabel = new JLabel("Changement du contôleur :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(controlerChangeLabel, gbc);

        final JComboBox controlerChangeBox = new JComboBox();
        /* AJOUT DES CONTROLEURS */
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(controlerChangeBox, gbc);

        final JLabel colorChangeLabel = new JLabel("Changement de couleur :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(colorChangeLabel, gbc);

        final JComboBox colorChangeBox = new JComboBox();
        /* AJOUT DES CONTROLEURS */
        colorChangeBox.addItem("1234567890");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(colorChangeBox, gbc);

        final JCheckBox showRoute = new JCheckBox("Afficher l'itinéraire");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(showRoute, gbc);

        final JLabel subtitle = new JLabel("Statistiques du véhicule");
        subtitle.setFont(new Font(null, Font.BOLD, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(subtitle, gbc);

        final JLabel speedLabel = new JLabel("Vitesse :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedLabel, gbc);

        final JLabel speedValue = new JLabel("abc");
        speedValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedValue, gbc);

        final JLabel startingPointLabel = new JLabel("Coordonées de départ :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(startingPointLabel, gbc);

        final JLabel startingPointValue = new JLabel("abc");
        startingPointValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(startingPointValue, gbc);

        final JLabel destinationPointLabel = new JLabel("Coordonées de destination :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(destinationPointLabel, gbc);

        final JLabel destinationPointValue = new JLabel("abc");
        destinationPointValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(destinationPointValue, gbc);

        final JLabel waitingTimeLabel = new JLabel("Temps d'attente moyen :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeLabel, gbc);

        final JLabel waitingTimeValue = new JLabel("abc");
        waitingTimeValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeValue, gbc);

        final JLabel accidentCounterLabel = new JLabel("Compteur d'accidents potentiels :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterLabel, gbc);

        final JLabel accidentCounterValue = new JLabel("abc");
        accidentCounterValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterValue, gbc);
    }
}
