/*
 * Filename : VehicleControllerTypeTest.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the VehicleControllerType enum
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
}