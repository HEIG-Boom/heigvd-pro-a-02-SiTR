/*
 * Filename : VehicleFactory.java
 * Creation date : 31.03.2019
 */

package ch.heigvd.sitr.vehicle;

/**
 * Singleton class used to create vehicles
 * @author Luc Wachter
 */
public class VehicleFactory {
    // Unique instance of the class
    private static VehicleFactory instance;

    // Private constructor to avoid instantiation
    private VehicleFactory() {}

    // Get the singleton's instance
    public static VehicleFactory getInstance() {
        if (instance == null)
            instance = new VehicleFactory();

        return instance;
    }

    // Factory method
    public Vehicle vehicle(VehicleController vc, double length, double maxSpeed) {
        return new Vehicle(vc, length, maxSpeed);
    }
}
