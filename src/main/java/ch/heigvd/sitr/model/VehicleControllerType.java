/*
 * Filename : VehicleControllerType.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import lombok.Getter;

import java.awt.*;

/**
 * Enum for controller types, provides controller names and paths to data files
 *
 * @author Luc Wachter
 */
public enum VehicleControllerType {
    TIMID("Timide", Color.GRAY, "timid.xml"),
    CAREFUL("Prudent", Color.BLUE, "careful.xml"),
    RECKLESS("Téméraire", Color.RED, "reckless.xml"),
    // Autonomous vehicles can alter the properties afterwards
    AUTONOMOUS("Autonome", Color.BLACK, "autonomous.xml");

    // Base folder containing config files for controllers
    private static final String BASE_PATH = "/vehicleController/";

    // Name of the controller (to display in GUI and such)
    private final String name;
    // Default color for a vehicle using this controller
    @Getter
    private final Color color;
    // Path to the controller's data file
    @Getter
    private final String configPath;

    /**
     * Constructor defining name and path to the controller's data file
     *
     * @param name       The name of the controller
     * @param configFile The name of the controller's data file (with extension)
     */
    VehicleControllerType(String name, Color color, String configFile) {
        this.name = name;
        this.color = color;
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
