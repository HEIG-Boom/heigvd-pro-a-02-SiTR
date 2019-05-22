/*
 * Filename : LaneSegment.java
 * Creation date : 22.04.2019
 */

package ch.heigvd.sitr.map;

import lombok.Getter;

/**
 * This class represents a lane segment in a road segment
 */
public class LaneSegment {
    private RoadSegment roadSegment;    // The road segment which the lane segment belongs to
    @Getter
    private final int lane;             // Physical lane
    private Lane.Type type;             // Lane segment's type

    /**
     * Constructor
     *
     * @param roadSegment The road segment which the lane segment belongs to
     * @param lane        The lane segment's physical lane
     */
    LaneSegment(RoadSegment roadSegment, int lane) {
        this.roadSegment = roadSegment;
        this.lane = lane;
    }

    /**
     * Sets the type of the lane.
     *
     * @param type The type of the lane
     */
    public final void setType(Lane.Type type) {
        this.type = type;
    }
}
