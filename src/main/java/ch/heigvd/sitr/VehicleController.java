package ch.heigvd.sitr;

import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle class represents the simulation vehicles
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
}
