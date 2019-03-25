package ch.heigvd.SiTR;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import static junit.framework.TestCase.*;


/**
 * Unit test for Vehicle.
 */
public class VehicleTest {

    @Test
    public void position() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setPosition(10.5);
        assertEquals(vehicle.getPosition(), 10.5);
    }

    @Test
    public void speed() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setSpeed(63.5);
        assertEquals(vehicle.getSpeed(), 63.5);
    }

    @Test
    public void length() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        assertEquals(vehicle.getLength(), 1.6);
    }

    @Test
    public void maxSpeed() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        assertEquals(vehicle.getMaxSpeed(), 145.2);
    }

    @Test
    public void speedShouldNotExceedMaxSpeed() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setSpeed(250);
        assertEquals(vehicle.getSpeed(), 145.2);
    }

    @Test
    public void frontVehicle() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        Vehicle vehicle2 = new Vehicle(1.6, 145.2);

        vehicle.setFrontVehicle(vehicle2);
        assertEquals(vehicle.getFrontVehicle(), vehicle2);
        assertNull(vehicle2.getFrontVehicle());
    }

    @Test
    public void frontDistance() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        vehicle.setPosition(50);
        Vehicle vehicle2 = new Vehicle(1.7, 145.2);
        vehicle2.setPosition(120);
        vehicle.setFrontVehicle(vehicle2);
        assertEquals(vehicle.frontDistance(), 68.35);
    }

    @Test
    public void frontDistanceShouldBeInfiniteIfThereIsntFrontVehicle() {
        Vehicle vehicle = new Vehicle(1.6, 145.2);
        assertEquals(vehicle.frontDistance(), Double.POSITIVE_INFINITY);
    }
}
