package ch.heigvd.sitr.map;

import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a lane segment in a road segment
 */
public class LaneSegment implements Iterable<Vehicle> {

    private RoadSegment roadSegment;    // The road segment which the lane segment belongs to
    @Getter
    private final int lane;             // Physical lane
    private Lane.Type type;             // Lane segment's type

    private List<Vehicle> vehicles = new ArrayList<>(); // List of vehicles in the lane segment

    /**
     * Constructor
     *
     * @param roadSegment The road segment which the lane segment belongs to
     * @param lane The lane segment's physical lane
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

    @Override
    public Iterator<Vehicle> iterator() {
        return vehicles.iterator();
    }
}
