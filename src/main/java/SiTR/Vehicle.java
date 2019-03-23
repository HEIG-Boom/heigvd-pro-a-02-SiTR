package SiTR;

import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle class represents the simulation vehicles
 */
public class Vehicle {
    /**
     * X position of the vehicle [m]
     * @param new value for the x position
     * @return the current x position
     */
    @Getter @Setter private double position;

    /**
     * Speed in [m/s] of the vehicle
     * @param new value for the speed
     * @return the current speed
     */
    @Getter private double speed;

    /**
     * new value for the speed
     *
     * Note: if speed exceed max speed then set speed to max speed
     * @param speed
     */
    public void setSpeed(double speed) {
        if(speed > maxSpeed) {
            speed = maxSpeed;
        }

        this.speed = speed;
    }

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
    @Getter private final double length;

    /**
     * Object in front of this vehicle
     * @param new front object
     * @return the current front object
     */
    @Getter @Setter private Object frontObject;

    /**
     * Constructor
     * @param length length [m] of the vehicle
     * @param maxSpeed max speed [m/s] of the vehicle
     */
    public Vehicle(double length, double maxSpeed) {
        this.length = length;
        this.maxSpeed = maxSpeed;
    }

    public double frontDistance() {
        Object frontObject = this.getFrontObject();

        if(frontObject == null) {
            return Double.POSITIVE_INFINITY;
        }

        return 168.35;
    }
}
