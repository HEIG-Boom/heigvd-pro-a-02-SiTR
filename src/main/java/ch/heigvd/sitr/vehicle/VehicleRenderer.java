/*
 * Filename : VehicleRenderer.java
 * Creation date : 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.map.roadmappings.RoadMappingArc;
import ch.heigvd.sitr.utils.Conversions;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
     * Rendering method for vehicles.
     *
     * @param g       The Graphics on which to draw the vehicle
     * @param vehicle The vehicle to draw on the image
     * @param scale   The ratio px/m
     * @return the rectangle object of the car drawn
     */
    public Rectangle display(Graphics2D g, Vehicle vehicle, double scale) {
        // Add some antialiasing for our eyes
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(vehicle.getColor());

        int x = 0;
        int y = 0;

        if(vehicle.currentPath().getRoadSegment().getRoadMapping() instanceof RoadMappingArc) {
            x = (int)((RoadMappingArc) vehicle.currentPath().getRoadSegment().getRoadMapping()).posAt(Conversions.metersToPixels(scale, vehicle.getPosition())).getX();
            y = (int)((RoadMappingArc) vehicle.currentPath().getRoadSegment().getRoadMapping()).posAt(Conversions.metersToPixels(scale, vehicle.getPosition())).getY();
        } else {
            // Get positional information, using correct conversions
            x = Conversions.metersToPixels(scale, vehicle.currentPath().getOrigin().getX() +
                    vehicle.getPosition() * vehicle.currentPath().getDirectionVector().getX());
            y = Conversions.metersToPixels(scale, vehicle.currentPath().getOrigin().getY() +
                    vehicle.getPosition() * vehicle.currentPath().getDirectionVector().getY());
        }

        int length = Conversions.metersToPixels(scale, vehicle.getLength());
        int width = Conversions.metersToPixels(scale, vehicle.getWidth());

        // Calculate correct rotation
        AffineTransform rotation = new AffineTransform();
        rotation.rotate(Math.atan2(vehicle.currentPath().getDirectionVector().y,
                vehicle.currentPath().getDirectionVector().x), x + length / 2, y + width / 2);
        g.transform(rotation);

        // Draw rectangle
        g.fillRect(x, y, length, width);

        return new Rectangle(x, y, length, width);
    }

    /**
     * Get the singleton's instance
     *
     * @return a reference to the renderer
     */
    public static VehicleRenderer getInstance() {
        if (instance == null) {
            instance = new VehicleRenderer();
        }

        return instance;
    }
}
