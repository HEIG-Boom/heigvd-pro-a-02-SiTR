/*
 * Filename: Renderable.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.map.graphics;

/**
 * Interface to be implemented by renderable objects
 *
 * @author Luc Wachter
 */
public interface Renderable {
    // TODO (tum) Move this inteface in utils package (Right now it's a copy of
    //  vehicule.Renderable
    /**
     * Method to make the object call its renderer
     */
    void draw();
}
