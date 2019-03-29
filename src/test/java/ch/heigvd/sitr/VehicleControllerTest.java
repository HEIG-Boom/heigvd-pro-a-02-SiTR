package ch.heigvd.sitr;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Unit test for Vehicle.
 */
public class VehicleControllerTest {

    @Test
    public void desiredVelocity() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(35.5);
        assertEquals(35.5, vehicleController.getDesiredVelocity());
    }

    @Test
    public void minimumSpacing() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setMinimumSpacing(2.5);
        assertEquals(2.5, vehicleController.getMinimumSpacing());
    }

    @Test
    public void desiredTimeHeadway() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredTimeHeadway(1.5);
        assertEquals(1.5, vehicleController.getDesiredTimeHeadway());
    }

    @Test
    public void maxAcceleration() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setMaxAcceleration(0.73);
        assertEquals(0.73, vehicleController.getMaxAcceleration());
    }

    @Test
    public void comfortableBrakingDeceleration() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setComfortableBrakingDeceleration(1.67);
        assertEquals(1.67, vehicleController.getComfortableBrakingDeceleration());
    }

    @Test
    /**
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     *
     * Variables :
     * s0 (minimumSpacing): 2 [m]
     * v (speed) : 80 [m/s]
     * T (desired time headway) : 1.5 [s]
     * deltaV (relative speed) : -20.0 [m/s]
     * a (max acceleration) : 0.3 [m/s^2]
     * b (comfortable braking deceleration) : 3 [m/s^2]
     *
     * => s*(80, -20) = 2 + max(0, 80 * 1.5 + (80 * -20)/(2*sqrt(0.3 * 3)))
     *                = 2 [m] = s0
     */
    public void desiredDynamicalDistanceWithFasterFrontVehicle() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(120);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 120);
        frontVehicle.setSpeed(100);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 120);
        vehicle.setSpeed(80);
        vehicle.setFrontVehicle(frontVehicle);

        assertEquals(2.0, vehicleController.desiredDynamicalDistance(vehicle));
    }

    @Test
    /**
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     *
     * Variables :
     * s0 (minimumSpacing): 2 [m]
     * v (speed) : 80 [m/s]
     * T (desired time headway) : 1.5 [s]
     * deltaV (relative speed) : 10.0 [m/s]
     * a (max acceleration) : 0.3 [m/s^2]
     * b (comfortable braking deceleration) : 3 [m/s^2]
     *
     * => s*(80, -20) = 2 + max(0, 80 * 1.5 + (80 * 10)/(2*sqrt(0.3 * 3)))
     *                = 543.6370213557839 [m]
     */
    public void desiredDynamicalDistanceWithSlowerFrontVehicle() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(120);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 120);
        frontVehicle.setSpeed(70);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 120);
        vehicle.setSpeed(80);
        vehicle.setFrontVehicle(frontVehicle);

        assertEquals(543.6370213557839, vehicleController.desiredDynamicalDistance(vehicle));
    }

    @Test
    /**
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     *
     * Variables :
     * s0 (minimumSpacing): 2 [m]
     * v (speed) : 80 [m/s]
     * T (desired time headway) : 1.5 [s]
     * deltaV (relative speed) : -20.0 [m/s]
     * a (max acceleration) : 0.3 [m/s^2]
     * b (comfortable braking deceleration) : 3 [m/s^2]
     *
     * => s*(80, 0) = 2 + max(0, 80 * 1.5 + (80 * 0)/(2*sqrt(0.3 * 3)))
     *              = 122 [m]
     */
    public void desiredDynamicalDistanceWithSameSpeedFrontVehicle() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(120);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 120);
        frontVehicle.setSpeed(80);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 120);
        vehicle.setSpeed(80);
        vehicle.setFrontVehicle(frontVehicle);

        assertEquals(122.0, vehicleController.desiredDynamicalDistance(vehicle));
    }
}
