package SiTR;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Vehicle.
 */
public class VehicleTest extends TestCase {

    public void testPosition() {
        Vehicle vehicle = new Vehicle();
        vehicle.setPosition(10);
        assertEquals(vehicle.getPosition(), 10);
    }

    public void testSpeed() {
        Vehicle vehicle = new Vehicle();
        vehicle.setSpeed(63.5);
        assertEquals(vehicle.getSpeed(), 63.5);
    }

    public void testLength() {
        Vehicle vehicle = new Vehicle(1.6);
        assertEquals(vehicle.getLength(), 1.6);
    }
}
