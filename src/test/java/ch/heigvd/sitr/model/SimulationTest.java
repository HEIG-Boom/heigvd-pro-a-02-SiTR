/*
 * Filename : SimulationTest.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Simulation class
 *
 * @author Luc Wachter
 */
class SimulationTest {
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
        Simulation simulation = new Simulation(8);
        assertEquals(8, simulation.getScale());
    }

    @Test
    public void shouldBeAbleToConvertMToPx() {
        Simulation simulation = new Simulation(4);
        assertEquals(simulation.mToPx(10), 40);
    }

    @Test
    public void shouldBeAbleToConvertPxToM() {
        Simulation simulation = new Simulation(4);
        assertEquals(simulation.pxToM(40), 10);
    }
}
