package ch.heigvd.sitr.map;

import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import java.util.Iterator;

/**
 * This class represents a road segment that contains a number of lane segments
 */
public class RoadSegment implements Iterable<Vehicle> {
    private static int counter = 1;             // Counter of RoadSegment
    @Getter
    private final int id;                       // RoadSegment's id
    private String roadName;                    // RoadSegment's name
    @Getter
    private final double roadLength;            // RoadSegment's length
    @Getter
    private final int laneCount;                // RoadSegment's number of lane
    private final LaneSegment laneSegments[];   // RoadSegment contains lane segments

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
     * Getter for a lane segment
     *
     * @param lane The lane segment index
     * @return The lane segment
     */
    public final LaneSegment getLaneSegment(int lane) {
        return laneSegments[lane - 1];
    }

    @Override
    public Iterator<Vehicle> iterator() {
        return null;
    }

//
//    private int roadId;
//    @Getter
//    private int nbLaneLeft;
//    @Getter
//    private int nbLaneRight;
//    private int length; //ne fait pas sens à modifier
//    @Getter
//    private float width;
//    private int speedLimit;
//    private int xCoordinatesBegin;
//    private int yCoordinatesBegin;
//    private int xCoordinatesEnd;
//    private int yCoordinatesEnd;
//    private LinkedList<LaneSegment> laneList;
//
//    RoadSegment(int xCoordinatesBegin, int yCoordinatesBegin, int xCoordinatesEnd, int yCoordinatesEnd,
//                int lenght, float width, int nbLaneLeft, int nbLaneRight, int speedLimit, int roadId) {
//
//        this.xCoordinatesBegin = xCoordinatesBegin;
//        this.yCoordinatesBegin = yCoordinatesBegin;
//        this.xCoordinatesEnd = xCoordinatesEnd;
//        this.yCoordinatesEnd = yCoordinatesEnd;
//        this.nbLaneLeft = nbLaneLeft;
//        this.nbLaneRight = nbLaneRight;
//        this.length = lenght;
//        this.width = width;
//        this.speedLimit = speedLimit;
//        this.roadId = roadId;
//
//        float laneWidth = width / (float) (nbLaneLeft + nbLaneRight);
//        /*Création des voies sur la gauche de la ligne, id négatif*/
//        for (int i = 1; i <= nbLaneLeft; i++) {
//            laneList.add(new LaneSegment(this, xCoordinatesEnd, (yCoordinatesEnd - (int) (laneWidth * i) + (int) (laneWidth / 2)),
//                    xCoordinatesBegin, (yCoordinatesBegin - (int) (laneWidth * i) + (int) (laneWidth / 2)), lenght, laneWidth, speedLimit, -i));
//        }
//        /*Création des voies sur la droite de la ligne, id positif*/
//        for (int i = 1; i <= nbLaneRight; i++) {
//            laneList.add(new LaneSegment(this, xCoordinatesBegin, (yCoordinatesBegin + (int) (laneWidth * i) - (int) (laneWidth / 2)),
//                    xCoordinatesEnd, (yCoordinatesEnd + (int) (laneWidth * i) - (int) (laneWidth / 2)), lenght, laneWidth, speedLimit, i));
//        }
//    }
//
//    /*Premet de savoir si il existe une voies sur la gauche de la voie donnée en paramètre*/
//    public boolean checkIfLaneOnLeft(int laneId) {
//        if (laneId < 0) {
//            if ((laneId * (-1)) < nbLaneLeft) {
//                return true;
//            }
//            return false;
//        } else {
//            if (laneId < nbLaneRight) {
//                return true;
//            }
//            return false;
//        }
//    }
//
//    /*retourne la ligne sur la gauche de la ligne donnée en paramètre
//     * !! Aucun contrôle (pour le moment) si la ligne n'existe pas*/
//    public LaneSegment getLaneOnLeft(int laneId) {
//        if (laneId < 0) {
//            return laneList.get(laneId * (-1));
//        } else {
//            return laneList.get((nbLaneLeft - 1) + laneId);
//        }
//    }
//
//    /*retourne la ligne sur la droite de la ligne donnée en paramètre
//     * !! Aucun contrôle (pour le moment) si la ligne n'existe pas*/
//    public LaneSegment getLaneOnRight(int laneId) {
//        if (laneId < 0) {
//            return laneList.get((laneId * (-1)) - 2);
//        } else {
//            return laneList.get(nbLaneLeft + laneId - 2);
//        }
//    }
//
//    /*Retourne true si il y a assez de place sur la voie où le véhicule veut se rendre*/
//    public boolean checkIfChangeLaneIsSafe(double relativePosition, LaneSegment lane) {
//        return lane.checkIfChangeLaneIsSafe(relativePosition, lane);
//    }
//
//    /*Change un véhicule de voie*/
//    public void changeLane(int laneIdFrom, int laneIdTo, Vehicle vehicle) {
//
//        if (laneIdFrom < 0) {
//            laneList.get((laneIdFrom * (-1)) - 1).removeVehicle(vehicle);
//            laneList.get((laneIdTo * (-1)) - 1).addVehicle(vehicle);
//        } else {
//            laneList.get(laneIdFrom + nbLaneLeft - 1).removeVehicle(vehicle);
//            laneList.get(laneIdTo + nbLaneLeft - 1).addVehicle(vehicle);
//        }
//    }
}
