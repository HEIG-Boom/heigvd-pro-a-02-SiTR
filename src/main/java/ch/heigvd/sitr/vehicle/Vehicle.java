/*
 * Filename: Vehicle.java
 * Creation date: 23.03.2019
 */

package ch.heigvd.sitr.vehicle;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

/**
 * Vehicle class represents the simulation vehicles
 * @author Simon Walther
 */
public class Vehicle {
    // Itinerary of the vehicle
    private LinkedList<ItineraryStop> itinerary = new LinkedList<ItineraryStop>();

    // Position of the vehicle relative to the lane's start [m]
    @Getter @Setter private double position;

    // Speed in [m/s] of the vehicle
    @Getter private double speed;

    /**
     * New value for the speed
     *
     * Note: if speed exceed max speed then set speed to max speed
     * @param speed The new speed of the vehicle [m/s]
     */
    public void setSpeed(double speed) {
        if(speed > maxSpeed) {
            speed = maxSpeed;
        } else if(speed < -maxSpeed) {
            speed = -maxSpeed;
        }

        this.speed = speed;
    }

    // Max speed in [m/s] of the vehicle
    @Getter private final double maxSpeed;

    // Length of the vehicle in [m]
    @Getter private final double length;

    // Vehicle in front of this vehicle
    @Getter @Setter private Vehicle frontVehicle;

    // Vehicle controller of this vehicle
    @Getter private VehicleController vehicleController;

    /**
     * Constructor
     *
     * @param vehicleController controller of the vehicle
     * @param length length [m] of the vehicle
     * @param maxSpeed max speed [m/s] of the vehicle
     */
    public Vehicle(VehicleController vehicleController, double length, double maxSpeed, ItineraryStop itineraryStop) {
        this.vehicleController = vehicleController;
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.itinerary.add(itineraryStop);
    }

    /**
     * Calculate the speed difference with acceleration and time difference
     *
     * @param acceleration acceleration [m/s^2]
     * @param deltaT time difference [s]
     * @return speed difference [m/s]
     */
    public static double speedDifference(double acceleration, double deltaT) {
        return acceleration * deltaT;
    }

    /**
     * Calculate the position difference with speed and time difference
     *
     * @param speed speed [m/s]
     * @param deltaT time difference [s]
     * @return position difference [m]
     */
    public static double positionDifference(double speed, double deltaT) {
        return speed * deltaT;
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

        // Distance between two vehicles is the absolute value of the position difference
        double posDistance = Math.abs(this.getPosition() - frontVehicle.getPosition());

        // We subtract from this distance, the distance from the vehicles center and vehicles extremities
        return posDistance - (this.getLength() / 2 + frontVehicle.getLength() / 2);
    }

    /**
     * Relative speed of this vehicle compared to front Vehicle
     *
     * Note : if there is no front vehicle, relative speed is equal to 0
     * @return the relative speed
     */
    public double relSpeed() {
        return (frontVehicle != null) ? speed - frontVehicle.getSpeed() : 0;
    }

    /**
     * Change speed [m/s] of the vehicle according to its acceleration and a time difference
     *
     * @param deltaT time difference [s]
     */
    public void updateSpeed(double deltaT) {
        setSpeed(getSpeed() + speedDifference(getVehicleController().acceleration(this), deltaT));
    }

    /**
     * Change position [m] of this vehicle according to its acceleration, speed and a time difference
     *
     * Note : it updates the vehicle's speed
     * @param deltaT the time difference [s]
     */
    public void updatePosition(double deltaT) {
        // First update speed according to the vehicle acceleration
        updateSpeed(deltaT);

        setPosition(getPosition() + positionDifference(getSpeed(), deltaT));
    }
}
