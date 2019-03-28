package ch.heigvd.sitr;

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
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        vehicle.setPosition(10.5);
        assertEquals(10.5, vehicle.getPosition());
    }

    @Test
    public void speed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        vehicle.setSpeed(63.5);
        assertEquals(63.5, vehicle.getSpeed());
    }

    @Test
    public void length() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        assertEquals(1.6, vehicle.getLength());
    }

    @Test
    public void maxSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        assertEquals(145.2, vehicle.getMaxSpeed());
    }

    @Test
    public void speedShouldNotExceedMaxSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        vehicle.setSpeed(250);
        assertEquals(145.2, vehicle.getSpeed());
    }

    @Test
    public void negativeSpeedShouldNotExceedMaxSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        vehicle.setSpeed(-250);
        assertEquals(-145.2, vehicle.getSpeed());
    }

    @Test
    public void frontVehicle() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        Vehicle vehicle2 = new Vehicle(null, 1.6, 145.2);

        vehicle.setFrontVehicle(vehicle2);
        assertEquals(vehicle2, vehicle.getFrontVehicle());
        assertNull(vehicle2.getFrontVehicle());
    }

    @Test
    public void frontDistance() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        vehicle.setPosition(50);
        Vehicle vehicle2 = new Vehicle(null, 1.7, 145.2);
        vehicle2.setPosition(120);
        vehicle.setFrontVehicle(vehicle2);
        assertEquals(68.35, vehicle.frontDistance());
    }

    @Test
    public void frontDistanceShouldBeInfiniteIfThereIsntFrontVehicle() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        assertEquals(Double.POSITIVE_INFINITY, vehicle.frontDistance());
    }

    @Test
    public void vehicleController() {
        VehicleController vehicleController = new VehicleController();
        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 145.2);
        assertEquals(vehicleController, vehicle.getVehicleController());
    }

    @Test
    public void relSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 145.2);
        vehicle.setSpeed(80);
        Vehicle frontVehicle = new Vehicle(null, 1.6, 145.2);
        frontVehicle.setSpeed(100);
        vehicle.setFrontVehicle(frontVehicle);
        assertEquals(-20.0, vehicle.relSpeed());
    }
    
}
