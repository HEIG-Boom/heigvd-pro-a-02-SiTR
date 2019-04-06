/*
 * Filename: VehicleController.java
 * Creation date: 28.03.2019
 */

package ch.heigvd.sitr.vehicle;

import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

/**
 * Vehicle Controller represents the vehicle controller
 *
 * It's based on the Intelligent Driver Model (IDM).
 *
 * See :
 * - the wikipedia article : https://en.wikipedia.org/wiki/Intelligent_driver_model
 * - Congested Traffic States in Empirical Observations and Microscopic Simulations : https://arxiv.org/pdf/cond-mat/0002177.pdf
 * - Concise explanation : http://www.mtreiber.de/trafficSimulationDe_html5_2016_06_29/IDM.html
 * - Traffic Flow Dynamics : Data, Models and Simulation, by Martin Treiber and Arne Kesting, chap 11
 * @author Simon Walther
 */
public class VehicleController {
    // Delta exponent, traditionnaly set at 4
    static final double delta = 4;

    // Desired velocity (v0) of the vehicle controller [m/s]
    @Getter @Setter private double desiredVelocity;

    // Minimum spacing (s0) of the vehicle controller [m]
    @Getter @Setter private double minimumSpacing;

    // Desired time headway (T) of the vehicle controller [s]
    @Getter @Setter private double desiredTimeHeadway;

    // Max acceleration (a) of the vehicle controller [m/s^2]
    @Getter @Setter private double maxAcceleration;

    // Comfortable braking deceleration (b) of the vehicle controller [m/s^2]
    @Getter @Setter private double comfortableBrakingDeceleration;

    /**
     * Vehicle constructor without parameters
     */
    public VehicleController() {

    }

    /**
     * Create vehicle controller with configuration taken from an XML configration file
     * @param configPath the path to the configuration file
     */
    public VehicleController(String configPath) {
        desiredVelocity = 33.33;
        minimumSpacing = 2;
        desiredTimeHeadway = 1.5;
        maxAcceleration = 1.0;
        comfortableBrakingDeceleration = 2.2;
    }

    /**
     * Calculate the safe distance
     * s0 + v*T
     *
     * Variables :
     * s0 (minimum spacing) [m]
     * v (speed) [m/s]
     * T (desired time headway) [s]
     *
     * @param vehicle
     * @return the safe distance [m]
     */
    public double safeDistance(Vehicle vehicle) {
        return minimumSpacing + vehicle.getSpeed() * desiredTimeHeadway;
    }

    /**
     * Calculate the desired dynamical distance s*
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     *
     * Variables :
     * s0 (minimumSpacing) [m]
     * v (speed) [m/s]
     * T (desired time headway) [s]
     * deltaV (relative speed) [m/s]
     * a (max acceleration) [m/s^2]
     * b (comfortable braking deceleration) [m/s^2]
     *
     * Note : s0 + v*T is the safe distance
     *
     * @param vehicle the vehicule
     * @return the desired dynamical distance [m]
     */
    public double desiredDynamicalDistance(Vehicle vehicle) {
        double dynamicalTerm = (vehicle.getSpeed() * vehicle.relSpeed()) /
                               (2 * sqrt(maxAcceleration * comfortableBrakingDeceleration));

        if(safeDistance(vehicle) - minimumSpacing + dynamicalTerm < 0) {
            return minimumSpacing;
        }

        return safeDistance(vehicle) + dynamicalTerm;
    }

    /**
     * Calculate the desired acceleration
     * a * [1 - (v / v0) ^ delta]
     *
     * Variables :
     * a (max acceleration) [m/s^2]
     * v (speed) [m/s]
     * v0 (desired velocity) [m/s]
     *
     * @param vehicle the vehicle
     * @return the desired acceleration
     */
    public double desiredAcceleration(Vehicle vehicle) {
        return maxAcceleration * (1 - Math.pow(vehicle.getSpeed() / desiredVelocity, delta));
    }

    /**
     * Calculate the acceleration
     * a * [1 - (v / v0)^delta - (s*(v, deltaV) / s)^2]
     *
     * Variables :
     * a (max acceleration) [m/s^2]
     * v (speed) [m/s]
     * v0 (desired velocity) [m/s]
     * deltaV (relative speed) [m/s]
     * s (front distance) [m]
     * delta
     *
     * @param vehicle the vehicle
     * @return the acceleration
     */
    public double acceleration(Vehicle vehicle) {
        return desiredAcceleration(vehicle) - maxAcceleration * Math.pow((desiredDynamicalDistance(vehicle) / vehicle.frontDistance()), 2);
    }
}
