/*
 * Filename: ItineraryPath.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.map.RoadSegment;
import ch.heigvd.sitr.model.Simulation;
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

    // padding at the top of the lane [px]
    private static final int LANE_PADDING = 2;

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
     * Constructor
     *
     * @param roadSegment the road segment
     */
    public ItineraryPath(RoadSegment roadSegment) {
        // TODO: change way of centering vehicles on lane, it doesn't take in account lane orientation
        // TODO: the end of the itinerary should take in account the length of the vehicle
        int startX = (int)roadSegment.getRoadMapping().startPos().getX();
        int startY = (int)(roadSegment.getRoadMapping().startPos().getY() + LANE_PADDING - roadSegment.getRoadMapping().roadWidth());
        int endX   = (int)roadSegment.getRoadMapping().endPos().getX();
        int endY   = (int)(roadSegment.getRoadMapping().endPos().getY() + LANE_PADDING - roadSegment.getRoadMapping().roadWidth());

        // TODO: IMPORTANT SCALE SHOULD BE TAKEN FROM SCENARIO
        this.origin      = new Point2D.Double(Simulation.pxToM(6, startX), Simulation.pxToM(6, startY));
        this.destination = new Point2D.Double(Simulation.pxToM(6, endX),   Simulation.pxToM(6, endY));

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
