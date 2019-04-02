package ch.heigvd.sitr.gui.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimControlPanel extends JPanel {

    private final int MIN_SPEED = 25;
    private final int MAX_SPEED = 150;

    private JLabel speedValue;
    private int speedPercent = 100;

    public SimControlPanel() {
        GridBagConstraints gbc;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10,10,10,10), null));

        final JLabel title = new JLabel("Contrôles de la simulation");
        title.setFont(new Font(null,Font.BOLD,18));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(title,gbc);

        final JLabel speedLabel = new JLabel("Vitesse actuelle :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedLabel,gbc);

        speedValue = new JLabel(speedPercent + "%");
        speedValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(speedValue,gbc);

        final JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PAUSE !!!");
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(pause,gbc);

        final JButton decrease = new JButton("Décélérer");
        decrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speedPercent > MIN_SPEED) {
                    speedPercent -= 5;
                    speedValue.setText(speedPercent + "%");
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10);
        this.add(decrease,gbc);

        final JButton increase = new JButton("Accélérer");
        increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speedPercent < MAX_SPEED) {
                    speedPercent += 5;
                    speedValue.setText(speedPercent + "%");
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(increase,gbc);

        final JLabel subtitle = new JLabel("Statistiques globales");
        subtitle.setFont(new Font(null,Font.BOLD,14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(subtitle,gbc);

        final JLabel waitingTimeLabel = new JLabel("Temps d'attente moyen :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeLabel,gbc);

        final JLabel waitingTimeValue = new JLabel("abc");
        waitingTimeValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(waitingTimeValue,gbc);

        final JLabel accidentCounterLabel = new JLabel("Compteur d'accidents potentiels :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterLabel,gbc);

        final JLabel accidentCounterValue = new JLabel("abc");
        accidentCounterValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(accidentCounterValue,gbc);

        final JLabel occupationLabel = new JLabel("Taux d'occupation du réseau :");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(occupationLabel,gbc);

        final JLabel occupationValue = new JLabel("abc");
        occupationValue.setHorizontalAlignment(JLabel.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(occupationValue,gbc);
    }
}
