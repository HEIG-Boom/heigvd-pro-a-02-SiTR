/*
 * Filename : SimulationTest.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

/**
 * Unit tests for Simulation class
 * @author Luc Wachter
 */
class SimulationTest {
    /**
     * Does the getControllerNames method return a correct List of available controllers?
     */
    @Test
    public void shouldEnableToGetControllerNames() {
        LinkedList<String> controllerNames = new LinkedList<>();
        for (VehicleControllerType vct : VehicleControllerType.values()) {
            controllerNames.add(vct.getName());
        }

        assertEquals(controllerNames, Simulation.getControllerNames());
    }
}