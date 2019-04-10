/*
 * Filename: ItineraryStop.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import lombok.Getter;

import java.awt.geom.Point2D;

/**
 * ItineraryStop class represents the a stop in an vehicle's itinerary
 * @author Simon Walther
 */
public class ItineraryStop {

    // the point of origin of the itinerary [px]
    @Getter
    private Point2D.Double origin;

    // the point of destination of the itinerary [px]
    @Getter
    private Point2D.Double destination;

    /**
     * Constructor
     * @param origin the point of origin
     * @param destination the point of destination
     */
    public ItineraryStop(Point2D.Double origin, Point2D.Double destination) {
        // ensure that origin is not empty
        if(origin == null) {
            throw new IllegalArgumentException("Itinerary stop must have an origin");
        }

        this.origin = origin;

        // ensure that destination is not empty
        if(destination == null) {
            throw new IllegalArgumentException("Itinerary stop must have a destination");
        }

        this.destination = destination;
    }
}
