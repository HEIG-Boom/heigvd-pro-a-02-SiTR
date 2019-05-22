/*
 * Filename: Renderable.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.utils;

/**
 * Interface to be implemented by renderable objects
 *
 * @author Luc Wachter
 */
public interface Renderable {
    /**
     * Method to make the object call its renderer
     *
     * @param scale the ratio px/m
     */
    void draw(double scale);
}
