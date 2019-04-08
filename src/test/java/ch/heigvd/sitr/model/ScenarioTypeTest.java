package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

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
     * Does the getScenarioNames method return a correct List of available controllers?
     */
    @Test
    public void shouldEnableToGetControllerNames() {
        // Test manually
        LinkedList<String> controllerNames = new LinkedList<>();
        controllerNames.add("Simple road");

        assertEquals(controllerNames, ScenarioType.getScenarioNames());
    }
}