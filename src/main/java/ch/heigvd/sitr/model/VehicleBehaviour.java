/*
 * Filename : VehicleBehaviour.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

/**
 * Enum for vehicle behaviour types, provides their names
 * <p>
 * It describes what a vehicle should do when it arrives at its destination
 *
 * @author Luc Wachter
 */
public enum VehicleBehaviour {
    STOP("S'arrêter là"),
    START_AGAIN("Recommencer un trajet identique"),
    LOOP("Continuer dans une boucle");

    // Name of the behaviour (to display in GUI and such)
    private final String name;

    /**
     * Constructor defining name of the behaviour
     *
     * @param name The name of the vehicle behaviour
     */
    VehicleBehaviour(String name) {
        this.name = name;
    }

    /**
     * Override to return a more friendly behaviour name
     *
     * @return the String representation of the behaviour
     */
    @Override
    public String toString() {
        return name;
    }
}
