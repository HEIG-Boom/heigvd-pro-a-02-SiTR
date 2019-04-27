/*
 * Filename : VehicleControllerType.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import lombok.Getter;

/**
 * Enum for controller types, provides controller names and paths to data files
 *
 * @author Luc Wachter
 */
public enum VehicleControllerType {
    TIMID("Timide", "timid.xml"),
    CAREFUL("Prudent", "careful.xml"),
    RECKLESS("Téméraire", "reckless.xml"),
    // Autonomous vehicles can alter the properties afterwards
    AUTONOMOUS("Autonome", "autonomous.xml");

    // Base folder containing config files for controllers
    private static final String BASE_PATH = "/vehicleController/";

    // Name of the controller (to display in GUI and such)
    private final String name;
    // Path to the controller's data file
    @Getter
    private final String configPath;

    /**
     * Constructor defining name and path to the controller's data file
     *
     * @param name       The name of the controller
     * @param configFile The name of the controller's data file (with extension)
     */
    VehicleControllerType(String name, String configFile) {
        this.name = name;
        // Construct path to data file from base path and filename
        configPath = BASE_PATH + configFile;
    }

    /**
     * Override to return a more friendly controller name
     *
     * @return the String representation of the vehicle controller
     */
    @Override
    public String toString() {
        return name;
    }
}
