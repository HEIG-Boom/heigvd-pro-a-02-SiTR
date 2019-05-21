/*
 * Filename: VehicleTest.java
 * Creation date: 23.03.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.map.RoadSegment;
import ch.heigvd.sitr.map.roadmappings.LaneGeometries;
import ch.heigvd.sitr.map.roadmappings.RoadMappingLine;
import ch.heigvd.sitr.model.VehicleBehaviour;
import ch.heigvd.sitr.model.VehicleControllerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Vehicle.
 *
 * @author Simon Walther
 */
public class VehicleTest {
    private Vehicle vehicle;
    private Vehicle frontVehicle;
    private VehicleController vehicleController;
    private ItineraryPath itineraryPath;
    private LinkedList<ItineraryPath> defaultItinerary = new LinkedList<>();

    @BeforeEach
    public void createDummyVehicleController() {
        vehicleController = new VehicleController(33.33, 2, 1.5, 0.3, 3, false);
    }

    @BeforeEach
    public void createDummyItinerary() {
        RoadSegment roadSegment = new RoadSegment(10000, 1, new RoadMappingLine(new LaneGeometries(), 0, 0, 0, 0, 10000));
        itineraryPath = new ItineraryPath(roadSegment, 1);
        defaultItinerary.add(itineraryPath);
    }

    @BeforeEach
    public void createDummyVehicle() {
        frontVehicle = new Vehicle(vehicleController, 1.7, 1, 33.33, 2.5, defaultItinerary);
        vehicle = new Vehicle(vehicleController, 1.6, 1, 33.33, 2.5, defaultItinerary);
        vehicle.setFrontVehicle(frontVehicle);
    }

    @Test
    public void constructor() {
        Vehicle vehicle = new Vehicle(vehicleController, 1.6, 1, 33.33, 2.5, defaultItinerary);
        assertEquals(1.6, vehicle.getLength());
        assertEquals(33.33, vehicle.getMaxSpeed());
        assertEquals(vehicleController, vehicle.getVehicleController());
        assertEquals(itineraryPath, vehicle.currentPath());
    }

    @Test
    public void constructorWithConfigFile() {
        VehicleController controller = new VehicleController(VehicleControllerType.CAREFUL);
        Vehicle vehicle = new Vehicle("regular.xml", controller, defaultItinerary);
        assertEquals(1.6, vehicle.getLength());
        assertEquals(1, vehicle.getWidth());
        assertEquals(33.33, vehicle.getMaxSpeed());
        assertEquals(2.5, vehicle.getMaxAcceleration());
        assertEquals(controller, vehicle.getVehicleController());
        assertEquals(itineraryPath, vehicle.currentPath());
    }

    @Test
    public void initialPathStepShouldBeAt0() {
        assertEquals(0, vehicle.getPathStep());
    }

    @Test
    public void addMultipleItineraryPaths() {
        assertEquals(1, vehicle.itinerarySize());

        vehicle.addToItinerary(itineraryPath);
        vehicle.addToItinerary(itineraryPath);
        vehicle.addToItinerary(itineraryPath);
        vehicle.addToItinerary(itineraryPath);
        vehicle.addToItinerary(itineraryPath);

        assertEquals(6, vehicle.itinerarySize());
    }

    @Test
    public void nextPath() {
        RoadSegment roadSegment = new RoadSegment(10, 1, new RoadMappingLine(new LaneGeometries(), 0, 0, 0, 0, 10000));
        ItineraryPath path1 = new ItineraryPath(roadSegment, 1);
        RoadSegment roadSegment2 = new RoadSegment(15, 1, new RoadMappingLine(new LaneGeometries(), 0, 0, 0, 0, 10000));
        ItineraryPath path2 = new ItineraryPath(roadSegment2, 1);
        RoadSegment roadSegment3 = new RoadSegment(20, 1, new RoadMappingLine(new LaneGeometries(), 0, 0, 0, 0, 10000));
        ItineraryPath path3 = new ItineraryPath(roadSegment3, 1);
        LinkedList<ItineraryPath> itinerary = new LinkedList<>();
        itinerary.add(path1);
        itinerary.add(path2);
        itinerary.add(path3);

        Vehicle vehicle = new Vehicle(vehicleController, 1.7, 1, 33.33, 2.5, itinerary);

        assertEquals(0, vehicle.getPathStep());
        assertEquals(path1, vehicle.currentPath());

        vehicle.moveToNextPath();
        assertEquals(1, vehicle.getPathStep());
        assertEquals(path2, vehicle.currentPath());

        vehicle.moveToNextPath();
        assertEquals(2, vehicle.getPathStep());
        assertEquals(path3, vehicle.currentPath());
    }

    @Test
    public void pathStepShouldNotExceedItinerarySize() {
        vehicle.addToItinerary(itineraryPath);
        vehicle.addToItinerary(itineraryPath);
        vehicle.moveToNextPath();
        vehicle.moveToNextPath();

        // move to an inexistant path
        vehicle.moveToNextPath();

        // get back to origin
        assertEquals(0, vehicle.getPathStep());
    }

    @Test
    public void position() {
        vehicle.setPosition(10.5);
        assertEquals(10.5, vehicle.getPosition());
    }

    /**
     * if vehicle get to the end of an itinerary path, change of itinerary path
     */
    @Test
    public void positionShouldSwitchOfItineraryIfItExceedstheItineraryLength() {
        RoadSegment roadSegment = new RoadSegment(10, 1, new RoadMappingLine(new LaneGeometries(), 0, 0, 0, 0, 10));
        ItineraryPath path1 = new ItineraryPath(roadSegment, 1);
        RoadSegment roadSegment2 = new RoadSegment(10, 1, new RoadMappingLine(new LaneGeometries(), 0, 0, 0, 0, 10));
        ItineraryPath path2 = new ItineraryPath(roadSegment2, 1);
        LinkedList<ItineraryPath> itineraryPaths = new LinkedList<>();
        itineraryPaths.add(path1);
        itineraryPaths.add(path2);

        Vehicle vehicle = new Vehicle(vehicleController, 1.7, 1, 33.33, 2.5, itineraryPaths);

        assertEquals(0, vehicle.getPosition());
        assertEquals(0, vehicle.getPathStep());

        vehicle.setPosition(11);

        assertEquals(1, vehicle.getPosition());
        assertEquals(1, vehicle.getPathStep());
    }

    @Test
    public void accelerationShouldNotExceedMax() {
        frontVehicle.setPosition(1);
        frontVehicle.setSpeed(0);
        vehicle.setSpeed(33.33);
        vehicle.setPosition(0);
        vehicle.setFrontVehicle(frontVehicle);
        assertFalse(Math.abs(vehicle.acceleration()) > vehicle.getMaxAcceleration());
        assertEquals(vehicle.getMaxAcceleration(), -vehicle.acceleration());
    }

    @Test
    public void speed() {
        vehicle.setSpeed(25.5);
        assertEquals(25.5, vehicle.getSpeed());
    }

    @Test
    public void speedShouldNotExceedMaxSpeed() {
        vehicle.setSpeed(50);
        assertEquals(33.33, vehicle.getSpeed());
    }

    @Test
    public void speedShouldNotGoUnder0() {
        vehicle.setSpeed(-50);
        assertEquals(0, vehicle.getSpeed());
    }

    @Test
    public void frontVehicle() {
        vehicle.setFrontVehicle(frontVehicle);
        assertEquals(frontVehicle, vehicle.getFrontVehicle());
        assertNull(frontVehicle.getFrontVehicle());
    }

    @Test
    public void frontDistance() {
        vehicle.setPosition(50);
        frontVehicle.setPosition(120);
        assertEquals(68.35, vehicle.frontDistance());
    }

    @Test
    public void frontDistanceShouldBeInfiniteIfThereIsntFrontVehicle() {
        assertEquals(Double.POSITIVE_INFINITY, frontVehicle.frontDistance());
    }

    @Test
    public void relSpeed() {
        vehicle.setSpeed(12.5);
        frontVehicle.setSpeed(32);
        assertEquals(-19.5, vehicle.relSpeed());
    }

    @Test
    public void relSpeedWithNoFrontVehicle() {
        frontVehicle.setSpeed(22.22);
        assertEquals(0.0, frontVehicle.relSpeed());
    }

    /**
     * Calculate speed difference with acceleration and time difference
     * <p>
     * deltaV = a * deltaT
     * <p>
     * Variables :
     * a (acceleration)         : 0.15 [m/s^2]
     * deltaT (time difference) : 60 [s]
     * <p>
     * => deltaV = 0.15 * 60 = 9 [m/s]
     */
    @Test
    public void speedDifference() {
        assertEquals(9.0, Vehicle.speedDifference(0.15, 60));
    }

    /**
     * Update speed with acceleration and time difference
     * <p>
     * new speed = speed + acceleration * time difference
     * <p>
     * Variables :
     * speed           : 22.22 [m/s]
     * acceleration    : 0.23719631730028704 [m/s^2]
     * time difference : 10 [s]
     * <p>
     * => new speed = 22.22 + 0.23719631730028704 * 10 = 24.59196317300287 [m/s]
     */
    @Test
    public void updateSpeed() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);

        vehicle.updateSpeed(10);

        assertEquals(24.592, vehicle.getSpeed(), 0.001);
    }

    /**
     * Update speed with acceleration and time difference
     * <p>
     * new speed = speed + acceleration * time difference
     * <p>
     * Variables :
     * speed           : 22.22 [m/s]
     * acceleration    : 0.23719631730028704 [m/s^2]
     * time difference : 60 [s]
     * <p>
     * => new speed = 22.22 + 0.23719631730028704 * 60 = 36.451779038 [m/s],
     * max speed = 33.33
     * => new speed = 33.33
     */
    @Test
    public void updateSpeedExceedingMaxSpeed() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);
        vehicle.updateSpeed(60);

        assertEquals(33.33, vehicle.getSpeed());
    }

    /**
     * Calculate position difference
     * <p>
     * position difference [m] = speed [m/s] * time difference [s]
     * <p>
     * Variables :
     * speed            : 22.22 [m/s]
     * time difference  : 30 [s]
     * <p>
     * => position difference = 22.22 * 20 = 444.4 [m]
     */
    @Test
    public void positionDifference() {
        assertEquals(444.4, Vehicle.positionDifference(22.22, 20));
    }

    /**
     * Update position with speed, acceleration and time difference
     * <p>
     * new position = position + speed * time difference
     * <p>
     * Variables :
     * position        : 50 [m]
     * speed           : 22.22 [s]
     * time difference : 10 [s]
     * <p>
     * => new position = 50 + 22.22 * 10 = 272.2 [m]
     */
    @Test
    public void updatePosition() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);
        vehicle.setPosition(50);
        vehicle.updatePosition(10);

        assertEquals(272.2, vehicle.getPosition(), 0.001);
    }

    /**
     * Update position with speed, acceleration and time difference
     *
     * new position = position + new speed * time difference
     *
     * Variables :
     * position        : 50 [m]
     * speed           : 22.22 [s]
     * new speed       : 24.59196317300287 [m/s]
     * time difference : 10 [s]
     *
     * => new position = 50 + 24.59196317300287 * 10 = 295,91963173 [m]
     */
    @Test
    public void update() {
        frontVehicle.setSpeed(27.77);
        frontVehicle.setPosition(100);

        vehicle.setSpeed(22.22);
        vehicle.setPosition(80);
        vehicle.setFrontVehicle(frontVehicle);
        vehicle.setPosition(50);
        vehicle.update(10);

        assertEquals(296.223, vehicle.getPosition(), 0.001);
    }

    @Test
    public void resetShouldResetVehicleAttributes() {
        vehicle.reset(VehicleBehaviour.START_AGAIN);
        assertEquals(vehicle.getSpeed(), 0);
        assertEquals(vehicle.getPosition(), 0);
        assertEquals(vehicle.getPathStep(), 0);
        assertFalse(vehicle.isFinished());
    }
}
