/*
 * Filename : ScenarioTypeTest.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

/**
 * Unit tests for the ScenarioType enum
 *
 * @author Luc Wachter
 */
class ScenarioTypeTest {
    /**
     * Test simple getters on the enum
     */
    @Test
    public void nameGetterShouldWork() {
        assertEquals("Simple road", ScenarioType.SIMPLE_ROAD.getName());
    }

    @Test
    public void configPathGetterShouldWork() {
        assertEquals("config/mapData/placeholder", ScenarioType.SIMPLE_ROAD.getConfigPath());
    }

    /**
     * Does the getScenarioNames method return a correct List of available scenarios?
     */
    @Test
    public void shouldEnableToGetScenarioNames() {
        // Test manually
        LinkedList<String> controllerNames = new LinkedList<>();
        controllerNames.add("Simple road");

        assertEquals(controllerNames, ScenarioType.getScenarioNames());
    }
}