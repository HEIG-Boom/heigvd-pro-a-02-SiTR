/*
 * Filename: CarControlPanel.java
 * Creation date: 01.04.2019
 */

package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.model.VehicleControllerType;
import ch.heigvd.sitr.utils.Conversions;
import ch.heigvd.sitr.vehicle.Vehicle;
import ch.heigvd.sitr.vehicle.VehicleController;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * Car control panel class represents a car with his stats and his available action.
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
public class CarControlPanel extends JPanel implements Observer {
    private JLabel accidentCounterValue;
    private  JLabel speedValue;
    private JLabel locationValue;
    private JButton colorChangeButton;

    private Color selectedColor;

    // selector of controller
    @Getter
    private JComboBox controllerChangeBox;

    // Vehicle observed by the panel
    @Setter
    private Vehicle vehicle;

    /**
     * Package-private constructor of the panel
     */
    CarControlPanel() {
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

        final JLabel controllerChangeLabel = new JLabel("Changement du contrôleur :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(controllerChangeLabel, gbc);

        controllerChangeBox = new JComboBox();
        for (VehicleControllerType vct : VehicleControllerType.values()) {
            controllerChangeBox.addItem(vct);
        }
        controllerChangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VehicleControllerType vct = (VehicleControllerType) controllerChangeBox.getSelectedItem();
                vehicle.setVehicleController(new VehicleController(vct));

                if (!vehicle.isCustomColor()) {
                    vehicle.setColor(vct.getColor());
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(controllerChangeBox, gbc);

        final JLabel colorChangeLabel = new JLabel("Changement de couleur :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(colorChangeLabel, gbc);

        colorChangeButton = new JButton();
        colorChangeButton.setPreferredSize(new Dimension(25,25));
        colorChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(colorChangeButton, "Sélectionner la couleur", vehicle.getColor());
                if (selectedColor != null) {
                    vehicle.setColor(selectedColor);
                    vehicle.setCustomColor(true);
                    colorChangeButton.setBackground(selectedColor);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(colorChangeButton, gbc);

        final JCheckBox showRoute = new JCheckBox("Afficher l'itinéraire");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
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

        speedValue = new JLabel();
        speedValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedValue, gbc);

        final JLabel locationLabel = new JLabel("Coordonnées du véhicule :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(locationLabel, gbc);

        locationValue = new JLabel("abc");
        locationValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(locationValue, gbc);

        final JLabel waitingTimeLabel = new JLabel("Temps d'attente :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeLabel, gbc);

        final JLabel waitingTimeValue = new JLabel("abc");
        waitingTimeValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeValue, gbc);

        final JLabel accidentCounterLabel = new JLabel("Compteur d'accidents :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterLabel, gbc);

        accidentCounterValue = new JLabel();
        accidentCounterValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterValue, gbc);

        setVisible(false);
    }

    /**
     * Method used to update the observer. In this case, refresh the statistics of the
     * selected car.
     * @param o the selected car
     * @param arg param used to notify all observers of o (never used in this app)
     */
    @Override
    public void update(Observable o, Object arg) {
        // convert received object into vehicle
        Vehicle v = (Vehicle) o;

        // Set the color of the color selection button
        colorChangeButton.setBackground(vehicle.getColor());

        // update speed according to the pattern "123.45 Km/H"
        String pattern = "###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String speed = decimalFormat.format(Conversions.mpsToKph(vehicle.getSpeed()));
        speedValue.setText(speed + " Km/h");

        // update the location of the vehicle
        locationValue.setText("[" + v.getGlobalPosition().x + ", " + v.getGlobalPosition().y + "]");

        // update accident counter
        accidentCounterValue.setText(Integer.toString(v.getNbOfAccidents()));
    }
}
