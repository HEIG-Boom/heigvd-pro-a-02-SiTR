/*
 * Filename : Simulation.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import java.util.LinkedList;

/**
 * Simulation class handles all global simulation settings and values
 * @author Luc Wachter
 */
public class Simulation {
    /**
     * Returns list of names of all vehicle controllers
     *
     * @return a list of Strings representing the names of the controllers
     */
    public static LinkedList<String> getControllerNames() {
        LinkedList<String> controllerNames = new LinkedList<>();

        for (VehicleControllerType vct : VehicleControllerType.values()) {
            controllerNames.add(vct.getName());
        }

        return controllerNames;
    }

//    public static LinkedList<String> getScenarioNames() {
//        LinkedList<String> scenarioNames = new LinkedList<>();
//
//        return scenarioNames;
//    }

//    public static LinkedList<String> getVehicleBehaviourNames() {
//        LinkedList<String> vehicleBehaviourNames = new LinkedList<>();
//
//        return vehicleBehaviourNames;
//    }
}
