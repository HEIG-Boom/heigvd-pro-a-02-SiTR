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
        vehicleController.setDesiredVelocity(22.22);
        assertEquals(22.22, vehicleController.getDesiredVelocity());
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
     * s0 (minimumSpacing)                   : 2 [m]
     * v (speed)                             : 22.22 [m/s]
     * T (desired time headway)              : 1.5 [s]
     * deltaV (relative speed)               : -5.55 [m/s]
     * a (max acceleration)                  : 0.3 [m/s^2]
     * b (comfortable braking deceleration)  : 3 [m/s^2]
     *
     * => s*(22.22, -5.55) = 2 + max(0, 22.22 * 1.5 + (22.22 * -5.55)/(2*sqrt(0.3 * 3)))
     *                     = 2 [m] = s0
     */
    public void desiredDynamicalDistanceWithFasterFrontVehicle() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 33.33);
        frontVehicle.setSpeed(27.77);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);
        vehicle.setFrontVehicle(frontVehicle);

        assertEquals(2.0, vehicleController.desiredDynamicalDistance(vehicle));
    }

    @Test
    /**
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     *
     * Variables :
     * s0 (minimumSpacing)                   : 2 [m]
     * v (speed)                             : 22.22 [m/s]
     * T (desired time headway)              : 1.5 [s]
     * deltaV (relative speed)               : 2.78 [m/s]
     * a (max acceleration)                  : 0.3 [m/s^2]
     * b (comfortable braking deceleration)  : 3 [m/s^2]
     *
     * => s*(22.22, 2.78) = 2 + max(0, 22.22 * 1.5 + (22.22 * 2.78)/(2*sqrt(0.3 * 3)))
     *                    = 67.88 [m]
     */
    public void desiredDynamicalDistanceWithSlowerFrontVehicle() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 33.33);
        frontVehicle.setSpeed(19.44);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);
        vehicle.setFrontVehicle(frontVehicle);

        assertEquals(67.88649178547615, vehicleController.desiredDynamicalDistance(vehicle));
    }

    @Test
    /**
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     *
     * Variables :
     * s0 (minimumSpacing)                   : 2 [m]
     * v (speed)                             : 22.22 [m/s]
     * T (desired time headway)              : 1.5 [s]
     * deltaV (relative speed)               : 0 [m/s]
     * a (max acceleration)                  : 0.3 [m/s^2]
     * b (comfortable braking deceleration)  : 3 [m/s^2]
     *
     * => s*(22.22, 0) = 2 + max(0, 22.22 * 1.5 + (22.22 * 0)/(2 * sqrt(0.3 * 3)))
     *                 = 35.33 [m]
     */
    public void desiredDynamicalDistanceWithSameSpeedFrontVehicle() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);
        vehicleController.setMinimumSpacing(2);
        vehicleController.setDesiredTimeHeadway(1.5);
        vehicleController.setMaxAcceleration(0.3);
        vehicleController.setComfortableBrakingDeceleration(3);

        Vehicle frontVehicle = new Vehicle(vehicleController, 1.6, 33.33);
        frontVehicle.setSpeed(22.22);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);
        vehicle.setFrontVehicle(frontVehicle);

        assertEquals(35.33, vehicleController.desiredDynamicalDistance(vehicle));
    }

    @Test
    /**
     * safe distance is : s0 + v*T
     *
     * Variables :
     * s0 (minimum spacing)      : 3 [m]
     * v (speed)                 : 22.22 [m/s]
     * T (desired time headway)  : 1.1 [s]
     *
     * => 3 + 22.22 * 1.1 = 27.442 [m]
     */
    public void safeDistance() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setMinimumSpacing(3);
        vehicleController.setDesiredTimeHeadway(1.1);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);

        assertEquals(27.442, vehicleController.safeDistance(vehicle));
    }

    @Test
    /**
     * desired acceleration is : (v / v0) ^ delta
     *
     * Variables :
     * v (speed)              : 22.22 [m/s]
     * v0 (desired velocity)  : 33.33 [m/s]
     * delta                  : 4
     *
     * => (22.22 / 33.33) ^ 4 = 0.197530864198 [m/s^2]
     */
    public void desiredAcceleration() {
        // define controller
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(33.33);

        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 33.33);
        vehicle.setSpeed(22.22);

        assertEquals(0.19753086419753083, vehicleController.desiredAcceleration(vehicle));
    }
}
