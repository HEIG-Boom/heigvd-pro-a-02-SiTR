/*
 * Filename : SimulationTest.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Simulation class
 *
 * @author Luc Wachter
 */
class SimulationTest {
    Simulation simulation;

    @BeforeEach
    public void instantiateTestSimulation() {
        HashMap<VehicleControllerType, Integer> map = new HashMap<>();
        simulation = new Simulation(ScenarioType.SIMPLE_ROAD, VehicleBehaviourType.STOP, map);
    }

    @Test
    public void shouldReturnCorrectAmountOfKph() {
        assertEquals(3.6, Simulation.mpsToKph(1));
        assertEquals(72.0, Simulation.mpsToKph(20));
    }

    @Test
    public void shouldReturnCorrectAmountOfMps() {
        assertEquals(1.0, Simulation.kphToMps(3.6));
        assertEquals(20.0, Simulation.kphToMps(72));
    }

    @Test
    public void shouldHaveScale() {
        assertEquals(6, simulation.getScale());
    }

    @Test
    public void shouldBeAbleToConvertMToPx() {
        assertEquals(6, Simulation.mToPx(simulation.getScale(), 1));
        assertEquals(60, simulation.mToPx(10));
    }

    @Test
    public void shouldBeAbleToConvertPxToM() {
        assertEquals(10, Simulation.pxToM(simulation.getScale(), 60));
        assertEquals(10, simulation.pxToM(60));
    }
}
