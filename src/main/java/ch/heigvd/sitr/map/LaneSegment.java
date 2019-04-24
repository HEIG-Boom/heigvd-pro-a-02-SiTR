package ch.heigvd.sitr.map;

import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LaneSegment implements Iterable<Vehicle> {

    private RoadSegment roadSegment;
    private LaneSegment sinkLaneSegment;
    private LaneSegment sourceLaneSegment;
    @Getter
    private int nbVehicle;
    @Getter
    private int length;
    @Getter
    private float width;
    @Getter
    private int laneSegmentId;
    @Getter
    private int speedLimit;
    @Getter
    private int xCoordinatesBegin;
    @Getter
    private int yCoordinatesBegin;
    @Getter
    private int xCoordinatesEnd;
    @Getter
    private int yCoordinatesEnd;
    @Getter
    private final int lane; // physical lane
    private Lane.Type type;

    private List<Vehicle> vehicles = new ArrayList<>();

    LaneSegment(RoadSegment roadSegment, int xCoordinatesBegin, int yCoordinatesBegin, int xCoordinatesEnd, int yCoordinatesEnd,
                int length, float width, int speedLimit, int laneSegmentId, int lane) {
        this.roadSegment = roadSegment;
        this.length = length;
        this.width = width;
        this.laneSegmentId = laneSegmentId;
        this.speedLimit = speedLimit;
        this.lane = lane;
        vehicles = new LinkedList<>();
        this.xCoordinatesBegin = xCoordinatesBegin;
        this.yCoordinatesBegin = yCoordinatesBegin;
        this.xCoordinatesEnd = xCoordinatesEnd;
        this.yCoordinatesEnd = yCoordinatesEnd;
    }

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
