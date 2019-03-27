package ch.heigvd.sitr;

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
     * Vehicle in front of this vehicle
     * @param new front vehicle
     * @return the current front vehicle
     */
    @Getter @Setter private Vehicle frontVehicle;

    /**
     * Constructor
     * @param length length [m] of the vehicle
     * @param maxSpeed max speed [m/s] of the vehicle
     */
    public Vehicle(double length, double maxSpeed) {
        this.length = length;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Front distance [m] between this vehicle and its front vehicle
     *
     * Note: length is calculated between vehicles extremities
     * @return front distance
     */
    public double frontDistance() {
        Vehicle frontVehicle = this.getFrontVehicle();

        if(frontVehicle == null) {
            return Double.POSITIVE_INFINITY;
        }

        // distance between two vehicles is the absolute value of the position difference
        double posDistance = Math.abs(this.getPosition() - frontVehicle.getPosition());

        // we substract from this distance, the distance from the vehicles center and vehicles extremities
        return posDistance - (this.getLength() / 2 + frontVehicle.getLength() / 2);
    }
}
