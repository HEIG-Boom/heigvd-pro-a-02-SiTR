/*
 * Filename : VehicleFactoryTest.java
 * Creation date : 31.03.2019
 */

package ch.heigvd.sitr.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for VehicleFactory
 * @author Luc Wachter
 */
public class VehicleFactoryTest {

    @Test
    public void factoryShouldProduceVehicle() {
        Vehicle testVehicle = VehicleFactory.getInstance().vehicle(null, 1.8, 42);
        assertTrue(testVehicle instanceof Vehicle);
    }
}
