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
}
