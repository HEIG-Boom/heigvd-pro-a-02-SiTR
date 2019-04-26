/*
 * Filename: ItineraryPath.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import lombok.Getter;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * ItineraryPath class represents the a stop in an vehicle's itinerary
 *
 * @author Simon Walther
 */
public class ItineraryPath {
    // the point of origin of the itinerary [px]
    @Getter
    private Point2D.Double origin;

    // the point of destination of the itinerary [px]
    @Getter
    private Point2D.Double destination;

    // normed direction vector
    @Getter
    private Point2D.Double directionVector;

    /**
     * Constructor
     *
     * @param origin      the point of origin
     * @param destination the point of destination
     * @throws IllegalArgumentException if we specify a null destination or origin
     */
    public ItineraryPath(Point2D.Double origin, Point2D.Double destination) throws IllegalArgumentException {
        // ensure that origin is not empty
        if (origin == null) {
            throw new IllegalArgumentException("Itinerary stop must have an origin");
        }

        this.origin = origin;

        // ensure that destination is not empty
        if (destination == null) {
            throw new IllegalArgumentException("Itinerary stop must have a destination");
        }

        this.destination = destination;
        this.directionVector = new Point2D.Double((destination.x - origin.x) / norm(), (destination.y - origin.y) / norm());
    }

    /**
     * Get the norm of the vector formed by the itinerary
     * @return the norm
     */
    public double norm() {
        return Math.sqrt(Math.pow(destination.x - origin.x, 2) + Math.pow(destination.y - origin.y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItineraryPath that = (ItineraryPath) o;
        return Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(directionVector, that.directionVector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, directionVector);
    }
}
