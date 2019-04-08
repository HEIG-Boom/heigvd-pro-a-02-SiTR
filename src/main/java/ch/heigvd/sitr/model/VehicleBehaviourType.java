/*
 * Filename : VehicleBehaviourType.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Enum for vehicle behaviour types, provides their names
 * It describes what a vehicle should do when
 *
 * @author Luc Wachter
 */
public enum VehicleBehaviourType {
    REVERSE_PATH("Go from destination to start"),
    START_AGAIN("Go from start to destination again"),
    STOP("Stop there");

    // Name of the behaviour (to display in GUI and such)
    @Getter
    private final String name;

    /**
     * Constructor defining name of the behaviour
     *
     * @param name The name of the vehicle behaviour
     */
    VehicleBehaviourType(String name) {
        this.name = name;
    }

    /**
     * Returns list of names of all vehicle behaviours
     *
     * @return a list of Strings representing the names of the behaviours
     */
    public static LinkedList<String> getVehicleBehaviourNames() {
        LinkedList<String> behaviourNames = new LinkedList<>();

        for (VehicleBehaviourType vbt : VehicleBehaviourType.values()) {
            behaviourNames.add(vbt.getName());
        }

        return behaviourNames;
    }
}
