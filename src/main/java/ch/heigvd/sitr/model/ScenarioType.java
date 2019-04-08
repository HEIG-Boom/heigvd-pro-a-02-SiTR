/*
 * Filename : ScenarioType.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Enum listing scenarios, provides scenario names and paths to data files
 *
 * @author Luc Wachter
 */
public enum ScenarioType {
    SIMPLE_ROAD("Simple road", "placeholder");

    // Base folder containing data files for the scenario (map)
    private static final String BASE_PATH = "config/mapData/";

    // Name of the scenario (to display in GUI and such)
    @Getter
    private final String name;
    // Path to the scenario's data file
    @Getter
    private final String configPath;

    /**
     * Constructor defining name and path to the scenario's data file
     *
     * @param name The name of the scenario
     * @param configFile The name of the scenario's data file (with extension)
     */
    ScenarioType(String name, String configFile) {
        this.name = name;
        // Construct path to data file from base path and filename
        configPath = BASE_PATH + configFile;
    }

    /**
     * Returns list of names of all scenarios
     *
     * @return a list of Strings representing the names of the scenarios
     */
    public static LinkedList<String> getScenarioNames() {
        LinkedList<String> scenarioNames = new LinkedList<>();

        for (ScenarioType st : ScenarioType.values()) {
            scenarioNames.add(st.getName());
        }

        return scenarioNames;
    }
}
