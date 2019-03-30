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
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setPosition(10.5);
        assertEquals(10.5, vehicle.getPosition());
    }

    @Test
    public void speed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setSpeed(25.5);
        assertEquals(25.5, vehicle.getSpeed());
    }

    @Test
    public void length() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        assertEquals(1.6, vehicle.getLength());
    }

    @Test
    public void maxSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        assertEquals(44.44, vehicle.getMaxSpeed());
    }

    @Test
    public void speedShouldNotExceedMaxSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setSpeed(50);
        assertEquals(44.44, vehicle.getSpeed());
    }

    @Test
    public void negativeSpeedShouldNotExceedMaxSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setSpeed(-50);
        assertEquals(-44.44, vehicle.getSpeed());
    }

    @Test
    public void frontVehicle() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        Vehicle vehicle2 = new Vehicle(null, 1.6, 44.44);

        vehicle.setFrontVehicle(vehicle2);
        assertEquals(vehicle2, vehicle.getFrontVehicle());
        assertNull(vehicle2.getFrontVehicle());
    }

    @Test
    public void frontDistance() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setPosition(50);
        Vehicle vehicle2 = new Vehicle(null, 1.7, 44.44);
        vehicle2.setPosition(120);
        vehicle.setFrontVehicle(vehicle2);
        assertEquals(68.35, vehicle.frontDistance());
    }

    @Test
    public void frontDistanceShouldBeInfiniteIfThereIsntFrontVehicle() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        assertEquals(Double.POSITIVE_INFINITY, vehicle.frontDistance());
    }

    @Test
    public void vehicleController() {
        VehicleController vehicleController = new VehicleController();
        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 44.44);
        assertEquals(vehicleController, vehicle.getVehicleController());
    }

    @Test
    public void relSpeed() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setSpeed(12.5);
        Vehicle frontVehicle = new Vehicle(null, 1.6, 44.44);
        frontVehicle.setSpeed(32);
        vehicle.setFrontVehicle(frontVehicle);
        assertEquals(-19.5, vehicle.relSpeed());
    }

    @Test
    public void relSpeedWithNoFrontVehicle() {
        Vehicle vehicle = new Vehicle(null, 1.6, 44.44);
        vehicle.setSpeed(22.22);
        assertEquals(0.0, vehicle.relSpeed());
    }

    @Test
    /**
     * cacul speed difference with acceleration and time difference
     *
     * deltaV = a * deltaT
     *
     * Variables :
     * a (acceleration)         : 0.15 [m/s^2]
     * deltaT (time difference) : 60 [s]
     *
     * => deltaV = 0.15 * 60 = 9 [m/s]
     */
    public void speedDifference() {
        assertEquals(9.0, Vehicle.speedDifference(0.15, 60));
    }

    @Test
    /**
     * update speed with acceleration and time difference
     *
     * new speed = speed + acceleration * time difference
     *
     * Variables :
     * speed           : 22.22 [m/s]
     * acceleration    : 0.23719631730028704 [m/s^2]
     * time difference : 10 [s]
     *
     * => new speed = 22.22 + 0.23719631730028704 * 10 = 24.59196317300287 [m/s]
     */
    public void updateSpeed() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 33.33);
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);

        vehicle.updateSpeed(10);

        assertEquals(24.59196317300287, vehicle.getSpeed());
    }

    @Test
    /**
     * update speed with acceleration and time difference
     *
     * new speed = speed + acceleration * time difference
     *
     * Variables :
     * speed           : 22.22 [m/s]
     * acceleration    : 0.23719631730028704 [m/s^2]
     * time difference : 60 [s]
     *
     * => new speed = 22.22 + 0.23719631730028704 * 60 = 36.451779038 [m/s],
     *    max speed = 33.33
     * => new speed = 33.33
     */
    public void updateSpeedExceedingMaxSpeed() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 33.33);
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);

        vehicle.updateSpeed(60);

        assertEquals(33.33, vehicle.getSpeed());
    }

    @Test
    /**
     * calcul position difference
     *
     * position difference [m] = speed [m/s] * time difference [s]
     *
     * Variables :
     * speed            : 22.22 [m/s]
     * time difference  : 30 [s]
     *
     * => position difference = 22.22 * 20 = 444.4 [m]
     */
    public void positionDifference() {
        assertEquals(444.4, Vehicle.positionDifference(22.22, 20));
    }

    @Test
    /**
     * Update position with speed, acceleration and time difference
     *
     * new position = position + new speed * time difference
     *
     * Variables :
     * position        : 50 [m]
     * speed           : 22.22 [s]
     * new speed       : 24.59196317300287 [m/s]
     * time difference : 10 [s]
     *
     * => new position = 50 + 24.59196317300287 * 10 = 295,91963173 [m]
     */
    public void updatePosition() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 33.33);
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);
        vehicle.setPosition(50);

        vehicle.updatePosition(10);

        assertEquals(296.22284806492166, vehicle.getPosition());

    }
}
