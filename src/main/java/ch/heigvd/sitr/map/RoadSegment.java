package ch.heigvd.sitr.map;

import ch.heigvd.sitr.map.roadmappings.RoadMapping;
import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

/**
 * This class represents a road segment that contains a number of lane segments
 */
public class RoadSegment implements Iterable<Vehicle> {
    private static int counter = 1;             // Counter of RoadSegment
    @Getter
    private final int id;                       // RoadSegment's id
    @Getter
    @Setter
    private String userId;                      // The userID specified in the .xodr file
    @Setter
    private String roadName;                    // RoadSegment's name
    @Getter
    private final double roadLength;            // RoadSegment's length
    @Getter
    private final int laneCount;                // RoadSegment's number of lane
    private final LaneSegment laneSegments[];   // RoadSegment contains lane segments

    @Getter
    private RoadMapping roadMapping;            // The road segment's road mapping

    /**
     * Constructor
     *
     * @param roadLength Length of the road
     * @param laneCount  Number of lane in the road segment
     */
    public RoadSegment(double roadLength, int laneCount) {
        laneSegments = new LaneSegment[laneCount];

        for (int i = 0; i < laneCount; ++i) {
            laneSegments[i] = new LaneSegment(this, i + 1);
        }

        id = counter++;
        this.roadLength = roadLength;
        this.laneCount = laneCount;
    }

    /**
     * Constructor
     *
     * @param roadLength The length of the road
     * @param laneCount The number of lane in the road segment
     * @param roadMapping The road mapping
     */
    public RoadSegment(double roadLength, int laneCount, RoadMapping roadMapping) {
        this(roadLength, laneCount);
        this.roadMapping = roadMapping;
    }

    /**
     * Getter for a lane segment
     *
     * @param lane The lane segment index
     * @return The lane segment
     */
    public final LaneSegment getLaneSegment(int lane) {
        return laneSegments[lane - 1];
    }

    /**
     * Sets the type of the given lane.
     *
     * @param lane The given lane
     * @param laneType The type of the given lane
     */
    public void setLaneType(int lane, Lane.Type laneType) {
        laneSegments[lane - 1].setType(laneType);
    }

    @Override
    public Iterator<Vehicle> iterator() {
        return null;
    }
}
