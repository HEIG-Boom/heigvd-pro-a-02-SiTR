package ch.heigvd.sitr;

import lombok.Getter;
import lombok.Setter;

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
 */
public class VehicleController {
    /**
     * Desired velocity (v0) of the vehicle controller [m/s]
     * @param new value for v0
     * @return the current v0
     */
    @Getter @Setter private double desiredVelocity;

    /**
     * Minimum spacing (s0) of the vehicle controller [m]
     * @param new value for s0
     * @return the current s0
     */
    @Getter @Setter private double minimumSpacing;

    /**
     * Desired time headway (T) of the vehicle controller [s]
     * @param new value for T
     * @return the current T
     */
    @Getter @Setter private double desiredTimeHeadway;

    /**
     * Max acceleration (a) of the vehicle controller [m/s^2]
     * @param new value for a
     * @returm the current a
     */
    @Getter @Setter private double maxAcceleration;

    /**
     * Comfortable braking deceleration (b) of the vehicle controller [m/s^2]
     * @param new value for b
     * @return the current b
     */
    @Getter @Setter private double comfortableBrakingDeceleration;

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
     * Calculate desired dynamical distance s*
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

        if((safeDistance(vehicle) - minimumSpacing) + dynamicalTerm < 0) {
            return minimumSpacing;
        }

        return safeDistance(vehicle) + dynamicalTerm;
    }
}
