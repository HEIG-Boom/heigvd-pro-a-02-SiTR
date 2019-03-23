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

    /**
     * Max speed in [m/s] of the vehicle
     * @param new value for the max speed
     * @return the max speed
     */
    @Getter private final double maxSpeed;

    /**
     * Length of the vehicle in [m]
     * @return the current length
     */
    @Getter private double length;

    /**
     * Constructor
     */
    public Vehicle(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Constructor
     * @param length length [m] of the vehicle
     * @param maxSpeed max speed [m/s] of the vehicle
     */
    public Vehicle(double length, double maxSpeed) {
        this(maxSpeed);
        this.length = length;
    }

}
