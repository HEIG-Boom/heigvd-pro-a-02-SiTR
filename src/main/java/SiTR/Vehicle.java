package SiTR;

import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle class represents the simulation vehicles
 */
public class Vehicle {
    /**
     * X position of the vehicle
     * @param new value for the x position
     * @return the current x position
     */
    @Getter @Setter private int position;

    /**
     * Speed in [m/s] of the vehicle
     * @param new value for the speed
     * @return the current speed
     */
    @Getter @Setter private double speed;
}
