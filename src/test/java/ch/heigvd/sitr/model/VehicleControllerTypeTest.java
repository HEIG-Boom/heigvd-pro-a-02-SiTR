/*
 * Filename : VehicleControllerTypeTest.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

/**
 * Unit tests for the VehicleControllerType enum
 *
 * @author Luc Wachter
 */
class VehicleControllerTypeTest {
    /**
     * Test simple getters on the enum
     */
    @Test
    public void nameGetterShouldWork() {
        assertEquals("Reckless", VehicleControllerType.RECKLESS.getName());
        assertEquals("Autonomous", VehicleControllerType.AUTONOMOUS.getName());
    }

    @Test
    public void configPathGetterShouldWork() {
        assertEquals("config/vehicleController/careful.xml", VehicleControllerType.CAREFUL.getConfigPath());
        assertEquals("config/vehicleController/reckless.xml", VehicleControllerType.RECKLESS.getConfigPath());
    }

    /**
     * Does the getControllerNames method return a correct List of available controllers?
     */
    @Test
    public void shouldEnableToGetControllerNames() {
        // Test manually
        LinkedList<String> controllerNames = new LinkedList<>();
        controllerNames.add("Timid");
        controllerNames.add("Careful");
        controllerNames.add("Reckless");
        controllerNames.add("Autonomous");

        assertEquals(controllerNames, VehicleControllerType.getControllerNames());
    }
}