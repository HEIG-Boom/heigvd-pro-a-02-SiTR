/*
 * Filename: VehicleTest.java
 * Creation date: 23.03.2019
 */

package ch.heigvd.sitr.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit test for Vehicle.
 * @author Simon Walther
 */
public class VehicleTest {
    Vehicle vehicle = null;
    Vehicle frontVehicle = null;
    VehicleController vehicleController = null;

    @BeforeEach
    public void createDummyVehicleController() {
        vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);
    }

    @BeforeEach
    public void createDummyVehicle() {
        VehicleFactory vehicleFactory = VehicleFactory.getInstance();
        frontVehicle = vehicleFactory.vehicle(vehicleController, 1.7, 33.33);
        vehicle = vehicleFactory.vehicle(vehicleController, 1.6, 33.33);
        vehicle.setFrontVehicle(frontVehicle);
    }

    @Test
    public void position() {
        vehicle.setPosition(10.5);
        assertEquals(10.5, vehicle.getPosition());
    }

    @Test
    public void speed() {
        vehicle.setSpeed(25.5);
        assertEquals(25.5, vehicle.getSpeed());
    }

    @Test
    public void length() {
        assertEquals(1.6, vehicle.getLength());
    }

    @Test
    public void maxSpeed() {
        assertEquals(33.33, vehicle.getMaxSpeed());
    }

    @Test
    public void speedShouldNotExceedMaxSpeed() {
        vehicle.setSpeed(50);
        assertEquals(33.33, vehicle.getSpeed());
    }

    @Test
    public void negativeSpeedShouldNotExceedMaxSpeed() {
        vehicle.setSpeed(-50);
        assertEquals(-33.33, vehicle.getSpeed());
    }

    @Test
    public void frontVehicle() {
        vehicle.setFrontVehicle(frontVehicle);
        assertEquals(frontVehicle, vehicle.getFrontVehicle());
        assertNull(frontVehicle.getFrontVehicle());
    }

    @Test
    public void frontDistance() {
        vehicle.setPosition(50);
        frontVehicle.setPosition(120);
        assertEquals(68.35, vehicle.frontDistance());
    }

    @Test
    public void frontDistanceShouldBeInfiniteIfThereIsntFrontVehicle() {
        assertEquals(Double.POSITIVE_INFINITY, frontVehicle.frontDistance());
    }

    @Test
    public void vehicleController() {
        assertEquals(vehicleController, vehicle.getVehicleController());
    }

    @Test
    public void relSpeed() {
        vehicle.setSpeed(12.5);
        frontVehicle.setSpeed(32);
        assertEquals(-19.5, vehicle.relSpeed());
    }

    @Test
    public void relSpeedWithNoFrontVehicle() {
        frontVehicle.setSpeed(22.22);
        assertEquals(0.0, frontVehicle.relSpeed());
    }

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
    @Test
    public void speedDifference() {
        assertEquals(9.0, Vehicle.speedDifference(0.15, 60));
    }

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
    @Test
    public void updateSpeed() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);

        vehicle.updateSpeed(10);

        assertEquals(24.592, vehicle.getSpeed(), 0.001);
    }

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
    @Test
    public void updateSpeedExceedingMaxSpeed() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);
        vehicle.updateSpeed(60);

        assertEquals(33.33, vehicle.getSpeed());
    }

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
    @Test
    public void positionDifference() {
        assertEquals(444.4, Vehicle.positionDifference(22.22, 20));
    }

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
    @Test
    public void updatePosition() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);
        vehicle.setPosition(50);
        vehicle.updatePosition(10);

        assertEquals(296.223, vehicle.getPosition(), 0.001);

    }
}
