package ch.heigvd.sitr.map;

import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

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

    private LinkedList<Vehicle> listVehicle = new LinkedList<>();

    LaneSegment(RoadSegment roadSegment, int xCoordinatesBegin, int yCoordinatesBegin, int xCoordinatesEnd, int yCoordinatesEnd,
                int length, float width, int speedLimit, int laneSegmentId) {
        this.roadSegment = roadSegment;
        this.length = length;
        this.width = width;
        this.laneSegmentId = laneSegmentId;
        this.speedLimit = speedLimit;
        listVehicle = new LinkedList<>();
        this.xCoordinatesBegin = xCoordinatesBegin;
        this.yCoordinatesBegin = yCoordinatesBegin;
        this.xCoordinatesEnd = xCoordinatesEnd;
        this.yCoordinatesEnd = yCoordinatesEnd;
    }

    public boolean checkIfLaneOnLeft() {
        return roadSegment.checkIfLaneOnLeft(laneSegmentId);
    }

    public LaneSegment getLaneOnLeft(int laneId) {
        return roadSegment.getLaneOnLeft(laneId);
    }

    public LaneSegment getLaneOnRight(int laneId) {
        return roadSegment.getLaneOnRight(laneId);
    }

    /*Ne fonctionne que pour une ligne droite*/
    public boolean checkIfChangeLaneIsSafe(double position, LaneSegment lane) {
        if (lane.laneSegmentId != laneSegmentId) {
            return roadSegment.checkIfChangeLaneIsSafe(position, lane);
        }
        Vehicle vehicle = listVehicle.getFirst();
        for (int i = 1; position > vehicle.getPosition() && i < listVehicle.size(); i++) {
            if ((position - vehicle.getPosition()) < 5) {
                return false;
            }
            vehicle = listVehicle.get(i);
        }
        if ((vehicle.getPosition() - position) < 5) {
            return false;
        }
        return true;
    }

    /*Ajoute un véhicule en les classant par leur position sur la voie
     * le premier véhicule dans la liste est le plus proche du début de la ligne, le dernier le plus éloigné
     * !! Ne contrôle pas si le véhicule est déjà dans cette voie*/
    public void addVehicle(Vehicle vehicle) {
        int i;
        for (i = 0; i < listVehicle.size(); i++) {
            if (vehicle.getPosition() > listVehicle.get(i).getPosition()) {
                i++;
                break;
            }
        }
        i--;
        listVehicle.add(i, vehicle);
        nbVehicle++;
    }

    public void removeVehicle(Vehicle vehicle) {
        listVehicle.remove(vehicle);
        nbVehicle --;
    }

    @Override
    public Iterator<Vehicle> iterator() {
        return listVehicle.iterator();
    }
}
