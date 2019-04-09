/*
 * Filename : ScenarioTypeTest.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ScenarioType enum
 *
 * @author Luc Wachter
 */
class ScenarioTypeTest {
    @Test
    public void configPathGetterShouldWork() {
        assertEquals("config/mapData/placeholder", ScenarioType.SIMPLE_ROAD.getConfigPath());
    }

    /**
     * Test enum's toString
     */
    @Test
    public void toStringShouldEnableToGetScenarioNames() {
        assertEquals("Route simple", ScenarioType.SIMPLE_ROAD.toString());
    }
}