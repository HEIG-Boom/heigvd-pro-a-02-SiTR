/*
 * Filename: Vehicle.java
 * Creation date: 23.03.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.utils.AccelerationNoise;
import ch.heigvd.sitr.utils.Renderable;
import lombok.Getter;
import lombok.Setter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Vehicle class represents the simulation vehicles
 *
 * @author Simon Walther
 */
public class Vehicle extends Observable implements Renderable {
    private static final String BASE_CONFIG_PATH = "/vehicle/";

    // Itinerary of the vehicle, subdivided in multiple paths
    private LinkedList<ItineraryPath> itinerary;

    // Current path step
    @Getter
    private int pathStep;

    // Position of the vehicle relative to the lane's start [m]
    @Getter
    private double position;

    // Speed in [m/s] of the vehicle
    @Getter
    private double speed;

    // Max speed in [m/s] of the vehicle
    @Getter
    private final double maxSpeed;

    // Max acceleration in [m/s^2] of the vehicle
    @Getter
    private final double maxAcceleration;

    // Length of the vehicle in [m]
    @Getter
    private final double length;

    // Width of the vehicle in [m]
    @Getter
    private final double width;

    // Vehicle in front of this vehicle
    @Getter
    @Setter
    private Vehicle frontVehicle;

    // Vehicle controller of this vehicle
    @Getter
    private VehicleController vehicleController;

    // Accleration noise
    private AccelerationNoise accelerationNoise = new AccelerationNoise();
  
    // Rectangle of the car on the map
    @Getter
    private Rectangle rectangle;

    /**
     * Constructor
     *
     * @param vehicleController controller of the vehicle
     * @param length            length [m] of the vehicle
     * @param width             width [m] of the vehicle
     * @param maxSpeed          max speed [m/s] of the vehicle
     * @param maxAcceleration   max acceleration [m/s^2] of the vehicle
     * @param itinerary         the vehicle itinerary
     */
    public Vehicle(VehicleController vehicleController, double length, double width, double maxSpeed,
                   double maxAcceleration, LinkedList<ItineraryPath> itinerary) {
        this.vehicleController = vehicleController;
        this.width = width;
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.maxAcceleration = maxAcceleration;
        this.itinerary = itinerary;
    }

    /**
     * Create vehicle with configuration taken from an XML configuration file
     *
     * @param configPath        the path to the configuration file
     * @param vehicleController the vehicle controller
     * @param itinerary         the vehicle itinerary
     */
    public Vehicle(String configPath, VehicleController vehicleController, LinkedList<ItineraryPath> itinerary) {
        this.vehicleController = vehicleController;

        InputStream in = Vehicle.class.getResourceAsStream(BASE_CONFIG_PATH + configPath);
        SAXBuilder saxBuilder = new SAXBuilder();

        // non-final temporary variables to ensure final
        // variables are initialized
        double length = 0;
        double width = 0;
        double maxSpeed = 0;
        double maxAcceleration = 0;

        try {
            Document document = saxBuilder.build(in);
            Element root = document.getRootElement();

            length = Double.parseDouble(root.getChildText("length"));
            width = Double.parseDouble(root.getChildText("width"));
            maxSpeed = Double.parseDouble(root.getChildText("maxSpeed"));
            maxAcceleration = Double.parseDouble(root.getChildText("maxAcceleration"));
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }

        this.length = length;
        this.width = width;
        this.maxSpeed = maxSpeed;
        this.maxAcceleration = maxAcceleration;
        this.itinerary = itinerary;
    }

    public void setPosition(double position) {
        // if it exceed the itinerary path length,
        // we add the excess to the position on the next itinerary path
        if(position > currentPath().norm()) {
            position -= currentPath().norm();
            moveToNextPath();
        }

        this.position = position;
    }

    /**
     * New value for the speed
     * <p>
     * Note: if speed exceed max speed then set speed to max speed
     *
     * @param speed The new speed of the vehicle [m/s]
     */
    public void setSpeed(double speed) {
        if (speed > maxSpeed) {
            speed = maxSpeed;
        }

        // speed shouldn't be negative
        if (speed < 0) {
            speed = 0;
        }

        this.speed = speed;
    }

    /**
     * update the acceleration white noise
     *
     * @param deltaT time difference [s]
     */
    public void updateAccelerationNoise(double deltaT) {
        accelerationNoise.updateAccelerationWhiteNoise(deltaT);
    }

    /**
     * Calculate the speed difference with acceleration, noise and time difference
     *
     * @param acceleration acceleration [m/s^2]
     * @param deltaT       time difference [s]
     * @param accelerationNoise acceleration noise
     * @return speed difference [m/s]
     */
    public static double speedDifference(double acceleration, double deltaT, double accelerationNoise) {
        return speedDifference(acceleration, deltaT) + accelerationNoise;
    }

    /**
     * Calculate the speed difference with acceleration and time difference
     *
     * @param acceleration acceleration [m/s^2]
     * @param deltaT       time difference [s]
     * @return speed difference [m/s]
     */
    public static double speedDifference(double acceleration, double deltaT) {
        return acceleration * deltaT;
    }

    /**
     * Calculate the position difference with speed and time difference
     *
     * @param speed  speed [m/s]
     * @param deltaT time difference [s]
     * @return position difference [m]
     */
    public static double positionDifference(double speed, double deltaT) {
        return speed * deltaT;
    }

    /**
     * Front distance [m] between this vehicle and its front vehicle
     * <p>
     * Note: length is calculated between vehicles extremities
     *
     * @return front distance
     */
    public double frontDistance() {
        if (frontVehicle == null || frontVehicle == this) {
            return Double.POSITIVE_INFINITY;
        }

        // this vehicle position should be subtracted to the distance
        double posDistance = -position;

        int path = getPathStep();

        if(position > frontVehicle.position && itinerary.get(path).equals(frontVehicle.currentPath())) {
            posDistance += itinerary.get(path).norm(); // add the whole path distance
            path = (path + 1) % itinerarySize();
        }

        // add all itinerary path distance in between
        while(!itinerary.get(path).equals(frontVehicle.currentPath())) {
            posDistance += itinerary.get(path).norm(); // add the whole path distance
            path = (path + 1) % itinerarySize();
        }

        posDistance += frontVehicle.getPosition();

        // We subtract from this distance, the distance from the vehicles center and vehicles extremities
        posDistance -= (this.getLength() / 2 + frontVehicle.getLength() / 2);

        return posDistance;
    }

    /**
     * Relative speed of this vehicle compared to front Vehicle
     * <p>
     * Note : if there is no front vehicle, relative speed is equal to 0
     *
     * @return the relative speed
     */
    public double relSpeed() {
        return (frontVehicle != null) ? speed - frontVehicle.getSpeed() : 0;
    }

    /**
     * Acceleration of the vehicle
     *
     * Note: max acceleration is returned if accleration exceed max
     *
     * @return acceleration of the vehicle
     */
    public double acceleration() {
        double acceleration = getVehicleController().acceleration(this);

        if(acceleration > maxAcceleration) {
            acceleration = maxAcceleration;
        } else if(acceleration < -maxAcceleration) {
            acceleration = -maxAcceleration;
        }

        return acceleration;
    }

    /**
     * Change speed [m/s] of the vehicle according to its acceleration and a time difference
     *
     * @param deltaT time difference [s]
     */
    void updateSpeed(double deltaT) {
        if(vehicleController.isHumanDriven()) {
            // set speed taking noise in account
            setSpeed(getSpeed() + speedDifference(acceleration(), deltaT, accelerationNoise.getAccelerationNoise()));
        } else {
            setSpeed(getSpeed() + speedDifference(acceleration(), deltaT));
        }
    }

    /**
     * Change position [m] of this vehicle according to its acceleration, speed and a time difference
     *
     * @param deltaT the time difference [s]
     */
    void updatePosition(double deltaT) {
        setPosition(getPosition() + positionDifference(getSpeed(), deltaT));
    }

    /**
     * Update vehicle speed and position
     *
     * @param deltaT the time difference [s]
     */
    public void update(double deltaT) {
        // update noise if it's an human driven vehicle
        if(vehicleController.isHumanDriven()) {
            // update the acceleration noise
            updateAccelerationNoise(deltaT);
        }

        // First update speed according to the vehicle acceleration
        updateSpeed(deltaT);
        // Then update position, taking into account the new speed
        updatePosition(deltaT);
    }

    /**
     * Method that calls the renderer in order to draw the Vehicle on the simulation pane
     *
     * @param scale the ratio px/m
     */
    @Override
    public void draw(double scale) {
        rectangle = VehicleRenderer.getInstance().display(SimulationWindow.getInstance().getSimulationPane(), this, scale);
    }

    /**
     * Debug toString
     *
     * @return A debug representation of a Vehicle
     */
    @Override
    public String toString() {
        String ret = "";
        ret += "Pos: " + position;
        ret += " a: " + ((vehicleController != null) ? acceleration() : "");
        ret += " v: " + speed;
        ret += " frontDistance: " + frontDistance();
        ret += " noise: " + ((accelerationNoise != null) ? accelerationNoise.getAccelerationNoise() : "0");

        return ret;
    }

    /**
     * Get the current path of the vehicle
     *
     * @return the current path
     */
    public ItineraryPath currentPath() {
        return this.itinerary.get(pathStep);
    }

    /**
     * Add itinerary path to the itinerary
     * <p>
     * Note: does not add it if null
     *
     * @param itineraryPath the itinerary path
     */
    public void addToItinerary(ItineraryPath itineraryPath) {
        if (itineraryPath != null) {
            this.itinerary.add(itineraryPath);
        }
    }

    /**
     * Get the itinerary size of the vehicle
     *
     * @return the itinerary size
     */
    public int itinerarySize() {
        return this.itinerary.size();
    }

    /**
     * get the next step
     * @return the next step
     */
    public int nextStep() {
        return (pathStep + 1) % itinerarySize();
    }

    /**
     * get the next itinerary path
     *
     * @return the next itinerary path
     */
    public ItineraryPath nextPath() {
        return itinerary.get(nextStep());
    }

    /**
     * Move vehicle to the next path of its itinerary
     * <p>
     * Note: if exceed max path step, path step does not change
     */
    public void moveToNextPath() {
        pathStep = nextStep();
    }
}
