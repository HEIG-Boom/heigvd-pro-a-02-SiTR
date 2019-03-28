package ch.heigvd.sitr;

import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle class represents the simulation vehicles
 */
public class VehicleController {
    /**
     * Desired velocity (v0) of the vehicle [m/s]
     * @param new value for the v0
     * @return the current v0
     */
    @Getter @Setter private double desiredVelocity;


}
