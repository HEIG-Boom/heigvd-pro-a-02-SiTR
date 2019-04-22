package ch.heigvd.sitr.map;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents an iterable collection of the road segments in the road network
 */
public class RoadNetwork implements Iterable<RoadSegment> {
    // Road segments that form the road network
    private final ArrayList<RoadSegment> roadSegments = new ArrayList<>();

    @Getter
    @Setter
    private String name;    // Road network's name

    /**
     * Clear the road network so that it's empty and ready to accept new road segments
     */
    public void clear() {
        name = null;
        roadSegments.clear();
    }

    /**
     * Returns the number of road segments in the road network
     *
     * @return The number of road segments in the road network
     */
    public final int size() {
        return roadSegments.size();
    }

    /**
     * Add a road segment to the road network
     *
     * @param roadSegment The road segment to add to the road network
     * @return The added road segment
     */
    public RoadSegment add(RoadSegment roadSegment) {
        roadSegments.add(roadSegment);
        return roadSegment;
    }

    @Override
    public Iterator<RoadSegment> iterator() {
        return roadSegments.iterator();
    }
}
