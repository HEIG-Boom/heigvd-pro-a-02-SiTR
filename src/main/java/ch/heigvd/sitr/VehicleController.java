package ch.heigvd.sitr;

import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle class represents the simulation vehicles
 */
public class VehicleController {
    /**
     * Desired velocity (v0) of the vehicle [m/s]
     * @param new value for v0
     * @return the current v0
     */
    @Getter @Setter private double desiredVelocity;

    /**
     * Minimum spacing (s0) of the vehicle [m]
     * @param new value for s0
     * @return the current s0
     */
    @Getter @Setter private double minimumSpacing;
}
