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
 * @author Luc Wachter, Simon Walther
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
    public void scale() {
        Simulation simulation = new Simulation(0.5);
        assertEquals(0.5, simulation.getScale());
    }

    @Test
    public void mToPx() {
        Simulation simulation = new Simulation(0.5);
        assertEquals(50, simulation.mToPx(100));
    }
}