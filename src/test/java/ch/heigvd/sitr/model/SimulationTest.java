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
        assertEquals(8, simulation.getScale());
    }

    @Test
    public void shouldBeAbleToConvertMToPx() {
        assertEquals(Simulation.mToPx(simulation.getScale(), 10), 80);
        assertEquals(simulation.mToPx(10), 80);
    }

    @Test
    public void shouldBeAbleToConvertPxToM() {
        assertEquals(Simulation.pxToM(simulation.getScale(), 80), 10);
        assertEquals(simulation.pxToM(80), 10);
    }
}
