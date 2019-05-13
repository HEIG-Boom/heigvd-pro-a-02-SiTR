/*
 * Filename : Scenario.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import lombok.Getter;

/**
 * Enum listing scenarios, provides scenario names and paths to data files
 *
 * @author Luc Wachter
 */
public enum Scenario {
    SIMPLE_ROAD("Route simple", 8, "simple_road.xodr"),
    RING_ROAD("Route anneau (1 voie)", 8, "ring_road.xodr"),
    OFFRAMP("Bretelle de sortie", 8, "offramp_v2.xodr"),
    HIGHWAY_EXCHANGE("Highway exchange", 8, "highway_exchange.xodr"),
    EIGHT("Eight", 8, "eight.xodr"),
    OFFRAMP_LOW("Bretelle de sortie bas", 8, "offramp_low.xodr");

    // Base folder containing data files for the scenario (map)
    private static final String BASE_PATH = "/map/simulation/";

    // Name of the scenario (to display in GUI and such)
    private final String name;
    // Scale of the scenario (how many pixels for a meter)
    @Getter
    private int scale;
    // Path to the scenario's data file
    @Getter
    private final String configPath;

    /**
     * Constructor defining name and path to the scenario's data file
     *
     * @param name       The name of the scenario
     * @param configFile The name of the scenario's data file (with extension)
     */
    Scenario(String name, int scale, String configFile) {
        this.name = name;
        this.scale = scale;
        // Construct path to data file from base path and filename
        configPath = BASE_PATH + configFile;
    }

    /**
     * Override to return a more friendly scenario name
     *
     * @return the String representation of the scenario
     */
    @Override
    public String toString() {
        return name;
    }
}
