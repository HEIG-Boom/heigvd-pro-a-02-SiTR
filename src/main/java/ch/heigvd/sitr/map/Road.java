package ch.heigvd.sitr.map;

import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents an iterable collection of road segments that form a road.
 */
public class Road implements Iterable<RoadSegment> {
    private final LinkedList<RoadSegment> roadSegments; // Road segments that form the road
    @Getter
    private String name;    // Road's name
    @Getter
    private double length;  // Road's length

    /**
     * Constructor
     *
     * @param name Road's name
     */
    public Road(String name) {
        roadSegments = new LinkedList<>();
        this.name = name;
    }

    /**
     * Add a road segment to the road
     *
     * @param roadSegment The road segment to add
     * @return roadsegment for convenience
     */
    public RoadSegment add(RoadSegment roadSegment) {
        // TODO Make some check if empty.
        roadSegments.add(roadSegment);
        length += roadSegment.getRoadLength();
        return roadSegment;
    }

    /**
     * Get the number of road segments in the road
     *
     * @return the number of road segments in the road
     */
    public final int size() {
        return roadSegments.size();
    }

    /**
     * Getter for a road segments
     *
     * @param road The road segments index
     * @return The road segment
     */
    public RoadSegment getRoadSegment(int road) {
        return roadSegments.get(road);
    }

    @Override
    public Iterator<RoadSegment> iterator() {
        return roadSegments.iterator();
    }
}
