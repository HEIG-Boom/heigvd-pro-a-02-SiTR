/*
 * Filename : VehicleRenderer.java
 * Creation date : 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.map.roadmappings.AngleAndPos;
import ch.heigvd.sitr.map.roadmappings.RoadMapping;
import ch.heigvd.sitr.map.roadmappings.RoadMappingArc;
import ch.heigvd.sitr.map.roadmappings.RoadMappingLine;
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

        int length = Conversions.metersToPixels(scale, vehicle.getLength());
        int width = Conversions.metersToPixels(scale, vehicle.getWidth());
        int vehiclePosition = Conversions.metersToPixels(scale, vehicle.getPosition());
        double lateralOffset = -vehicle.currentPath().getRoadSegment().getRoadMapping().laneWidth();
        RoadMapping roadMapping = vehicle.currentPath().getRoadSegment().getRoadMapping();
        AngleAndPos angleAndPos = roadMapping.posAt(vehiclePosition, lateralOffset);
        int x = (int)angleAndPos.getX();
        int y = (int)angleAndPos.getY();
        double vehicleRotationAngle = angleAndPos.getTheta();

        // Calculate correct rotation
        AffineTransform rotation = new AffineTransform();
        rotation.rotate(vehicleRotationAngle, x + length / 2, y + width / 2);
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
