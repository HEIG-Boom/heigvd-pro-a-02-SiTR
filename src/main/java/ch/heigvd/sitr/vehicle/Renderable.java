/*
 * Filename: Renderable.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

/**
 * Interface to be implemented by renderable objects
 *
 * @author Luc Wachter
 */
public interface Renderable {
    /**
     * Getter for the object's renderer
     */
    VehicleRenderer getRenderer(); // TODO generalize renderer?

//    Color getColor();

//    Shape getShape();

    /**
     * Method to make the object call its renderer
     */
    void draw();
}
