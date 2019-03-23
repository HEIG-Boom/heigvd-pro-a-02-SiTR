package SiTR;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Vehicle.
 */
public class VehicleTest extends TestCase {

    public void testPosition() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setPosition(10.5);
        assertEquals(vehicle.getPosition(), 10.5);
    }

    public void testSpeed() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setSpeed(63.5);
        assertEquals(vehicle.getSpeed(), 63.5);
    }

    public void testLength() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        assertEquals(vehicle.getLength(), 1.6);
    }

    public void testMaxSpeed() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        assertEquals(vehicle.getMaxSpeed(), 145.2);
    }

    public void speedShouldNotExceedMaxSpeed() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setSpeed(250);
        assertEquals(vehicle.getSpeed(), 145.2);
    }

    public void testFrontObject() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        Vehicle vehicle2 = new Vehicle(1.6, 145.2);

        vehicle.setFrontObject(vehicle2);
        assertEquals(vehicle.getFrontObject(), vehicle2);
        assertNull(vehicle2.getFrontObject());
    }

    public void testFrontDistance() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setPosition(50);
        Vehicle vehicle2 = new Vehicle(1.7, 145.2);
        vehicle2.setPosition(120);
        vehicle.setFrontObject(vehicle2);
        assertEquals(vehicle.frontDistance(), 168.35);
    }

    public void frontDistanceShouldBeInfiniteIfThereIsntFrontObject() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        assertEquals(vehicle.frontDistance(), Double.POSITIVE_INFINITY);
    }
}
