/*
 * Filename : ScenarioTest.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Scenario enum
 *
 * @author Luc Wachter
 */
class ScenarioTest {
    @Test
    public void configPathGetterShouldWork() {
        assertEquals("mapData/placeholder", Scenario.SIMPLE_ROAD.getConfigPath());
    }

    /**
     * Test enum's toString
     */
    @Test
    public void toStringShouldEnableToGetScenarioNames() {
        assertEquals("Route simple", Scenario.SIMPLE_ROAD.toString());
    }
}