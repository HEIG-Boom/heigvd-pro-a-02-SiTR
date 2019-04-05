package ch.heigvd.sitr.gui.simulation;

import java.awt.Graphics2D;

public interface Displayer {
    /**
     * Gets the width of the panel
     * @return the width of the panel
     */
    int getMapWidth();

    /**
     * Gets the height of the panel
     * @return the height of the panel
     */
    int getMapHeight();

    /**
     * Gets an image object (as a Graphics2D) representing the panel's drawing area
     * @return a graphics area on which to draw, of the panel's size
     */
    Graphics2D getSimulationPane();

    /**
     * Draw the image with the shapes on the panel, and create a new image object
     */
    void repaint();
}
