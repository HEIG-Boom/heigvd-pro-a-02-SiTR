/*
 * Filename : VehicleBehaviourTypeTest.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

/**
 * Unit tests for the VehicleBehaviourType enum
 *
 * @author Luc Wachter
 */
class VehicleBehaviourTypeTest {
    /**
     * Test simple getters on the enum
     */
    @Test
    public void nameGetterShouldWork() {
        assertEquals("Go from destination to start", VehicleBehaviourType.REVERSE_PATH.getName());
        assertEquals("Stop there", VehicleBehaviourType.STOP.getName());
    }

    /**
     * Does the getVehicleBehaviourNames method return a correct List of available behaviours?
     */
    @Test
    public void shouldEnableToGetBehaviourNames() {
        // Test manually
        LinkedList<String> behaviourNames = new LinkedList<>();
        behaviourNames.add("Go from destination to start");
        behaviourNames.add("Go from start to destination again");
        behaviourNames.add("Stop there");

        assertEquals(behaviourNames, VehicleBehaviourType.getVehicleBehaviourNames());
    }
}