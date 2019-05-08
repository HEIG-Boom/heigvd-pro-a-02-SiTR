/*
 * Filename: MapPanel.java
 * Creation date: 01.04.2019
 */

package ch.heigvd.sitr.gui.simulation;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Map panel represent the drawing area for the map of the simulation
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
class MapPanel extends JPanel {
    // color of the background
    @Getter
    private final String backgroundColor = "0x008000";

    // dimensions of the map
    static final int WIDTH = 800;
    static final int HEIGHT = 800;

    /**
     * Package-private constructor of the map panel
     */
    MapPanel() {
        Dimension d = new Dimension(WIDTH, HEIGHT);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
    }
}
