/*
 * Filename: SimControlPanel.java
 * Creation date: 01.04.2019
 */

package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.gui.settings.SettingsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simulation control panel represents the simulation's stats and his available actions
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
public class SimControlPanel extends JPanel {

    // Minimal and maximal speed of the simulation
    private final int MIN_SPEED = 50;
    private final int MAX_SPEED = 150;

    private final int DEC_INC_PERCENT = 10;

    // Actual speed of the simulation. Initially 100%
    private int speedPercent = 100;

    private JLabel waitingTimeValue;
    private JLabel accidentCounterValue;
    private JLabel occupationValue;

    /**
     * Package-private constructor of the panel
     */
    SimControlPanel() {
        GridBagConstraints gbc;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null));

        final JLabel title = new JLabel("Contrôles de la simulation");
        title.setFont(new Font(null, Font.BOLD, 18));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(title, gbc);


        final JLabel speedLabel = new JLabel("Vitesse actuelle :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedLabel, gbc);

        final JLabel speedValue = new JLabel(speedPercent + "%");
        speedValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedValue, gbc);

        final JButton decrease = new JButton("Décélérer");
        decrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (speedPercent > MIN_SPEED) {
                    speedPercent -= DEC_INC_PERCENT;
                    speedValue.setText(speedPercent + "%");
                    double defaultDelta = SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().getDefaultDeltaT();
                    double delta = SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().getDeltaT();
                    delta -= (defaultDelta * DEC_INC_PERCENT / 100);
                    SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().setDeltaT(delta);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 0, 5);
        this.add(decrease, gbc);

        final JButton increase = new JButton("Accélérer");
        increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (speedPercent < MAX_SPEED) {
                    speedPercent += DEC_INC_PERCENT;
                    speedValue.setText(speedPercent + "%");
                    double defaultDelta = SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().getDefaultDeltaT();
                    double delta = SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().getDeltaT();
                    delta += (defaultDelta * DEC_INC_PERCENT / 100);
                    SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().setDeltaT(delta);
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(increase, gbc);

        final JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {
            // Is the simulation's main loop running?
            boolean isRunning = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    pause.setText("Lancer");
                    decrease.setEnabled(false);
                    increase.setEnabled(false);
                    SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().stopLoop();
                    isRunning = false;
                } else {
                    pause.setText("Pause");
                    decrease.setEnabled(true);
                    increase.setEnabled(true);
                    SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().startLoop();
                    isRunning = true;
                }
            }
        });
        pause.setPreferredSize(new Dimension(75, 27));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(pause, gbc);

        final JLabel subtitle = new JLabel("Statistiques globales");
        subtitle.setFont(new Font(null, Font.BOLD, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(subtitle, gbc);

        final JLabel waitingTimeLabel = new JLabel("Temps d'attente moyen :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeLabel, gbc);

        waitingTimeValue = new JLabel("NaN");
        waitingTimeValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeValue, gbc);

        final JLabel accidentCounterLabel = new JLabel("Compteur d'accidents potentiels :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterLabel, gbc);

        accidentCounterValue = new JLabel("0");
        accidentCounterValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterValue, gbc);

        final JLabel occupationLabel = new JLabel("Taux d'occupation du réseau :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(occupationLabel, gbc);

        occupationValue = new JLabel("NaN");
        occupationValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(occupationValue, gbc);

        final JButton newSim = new JButton("Nouvelle simulation");
        newSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationWindow.getInstance().closeWindow();
                SettingsWindow.getInstance().showWindow();
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 30);
        this.add(newSim, gbc);

        final JButton quit = new JButton("Quitter");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationWindow.getInstance().closeWindow();
                System.exit(0);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(quit, gbc);

        setPreferredSize(new Dimension(320, 250));
    }

    /**
     * Method used to set the global waiting time
     *
     * @param value new value of waiting time
     */
    public void setWaitingTimeValue(String value) {
        this.waitingTimeValue.setText(value);
    }

    /**
     * Method used to set the global accident counter
     *
     * @param value new value of accident counter
     */
    public void setAccidentCounterValue(String value) {
        this.accidentCounterValue.setText(value);
    }

    /**
     * Method used to set the global network occupation percent
     *
     * @param value new value of the network occupation
     */
    public void setOccupationValue(String value) {
        this.occupationValue.setText(value);
    }
}
