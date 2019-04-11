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
//    VehicleRenderer getRenderer();

//    Color getColor();

//    Shape getShape();

    /**
     * Method to make the object call its renderer
     */
    void draw();
}
