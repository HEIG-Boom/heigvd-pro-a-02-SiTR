/*
 * Filename : SimulationTest.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Simulation class
 *
 * @author Luc Wachter
 */
class SimulationTest {
    private static Simulation simulation;

    @BeforeAll
    public static void instantiateTestSimulation() {
        HashMap<VehicleControllerType, Integer> map = new HashMap<>();
        map.put(VehicleControllerType.CAREFUL, 12);
        map.put(VehicleControllerType.AUTONOMOUS, 4);

        simulation = new Simulation(ScenarioType.SIMPLE_ROAD, VehicleBehaviourType.STOP, map);
    }

    @Test
    public void simulationObjectShouldHaveCorrectAttributes() {
        assertEquals(simulation.getScenario(), ScenarioType.SIMPLE_ROAD);
        assertEquals(simulation.getBehaviour(), VehicleBehaviourType.STOP);
    }

    @Test
    public void simulationShouldGenerateAListOfVehicles() {
        assertNotNull(simulation.getVehicles());
    }

    @Test
    public void listOfVehiclesShouldHaveCorrectNbrOfVehicles() {
        assertEquals(simulation.getVehicles().size(), 16);
    }
}
