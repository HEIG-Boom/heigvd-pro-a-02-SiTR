/*
 * Filename : VehicleRenderer.java
 * Creation date : 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.utils.Conversions;

import java.awt.*;

/**
 * A singleton renderer for vehicles
 *
 * @author Luc Wachter
 */
public class VehicleRenderer {
    // Unique instance of the class
    private static VehicleRenderer instance;

    /**
     * Private constructor to avoid instantiation
     */
    private VehicleRenderer() {}

    /**
     * Rendering method for vehicles
     *
     * @param g       The Graphics on which to draw the vehicle
     * @param vehicle The vehicle to draw on the image
     * @param scale   The ratio px/m
     */
    public void display(Graphics2D g, Vehicle vehicle, double scale) {
        // Add some antialiasing for our eyes
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

        // Hard coded tests
        // TODO store drawing information in Vehicle (x, y, length, width, color)
//        g.setColor(vehicle.getVehicleController().getColor());
        g.setColor(Color.BLUE);
        g.fillRect(Conversions.metersToPixels(scale, vehicle.getPosition()), 20, Conversions.metersToPixels(scale,
                vehicle.getLength()), Conversions.metersToPixels(scale, vehicle.getWidth()));
    }

    /**
     * Get the singleton's instance
     *
     * @return a reference to the renderer
     */
    public static VehicleRenderer getInstance() {
        if (instance == null)
            instance = new VehicleRenderer();

        return instance;
    }
}
