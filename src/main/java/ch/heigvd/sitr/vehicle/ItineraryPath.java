/*
 * Filename: ItineraryPath.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.map.RoadSegment;
import ch.heigvd.sitr.utils.Conversions;
import lombok.Getter;

import java.util.Objects;

/**
 * ItineraryPath class represents the a stop in an vehicle's itinerary
 *
 * @author Simon Walther
 */
public class ItineraryPath {
    // Itinerary road segment
    @Getter
    RoadSegment roadSegment;

    // scale to be applied
    private double scale;

    /**
     * Constructor
     *
     * @param roadSegment the road segment
     */
    public ItineraryPath(RoadSegment roadSegment, double scale) {
        this.roadSegment = roadSegment;
        this.scale = scale;
    }

    /**
     * Get the length of the vector formed by the itinerary
     *
     * @return the length
     */
    public double length() {
        return Conversions.pixelsToMeters(scale, (int) roadSegment.getRoadMapping().getRoadLength());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItineraryPath that = (ItineraryPath) o;
        return Objects.equals(roadSegment, that.roadSegment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roadSegment);
    }
}
