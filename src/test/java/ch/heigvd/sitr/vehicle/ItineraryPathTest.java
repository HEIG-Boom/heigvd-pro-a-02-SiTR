/*
 * Filename: ItineraryPathTest.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Itinerary path.
 *
 * @author Simon Walther
 */
public class ItineraryPathTest {

    @Test
    public void constructor() {
        ItineraryPath itineraryPath = new ItineraryPath(new Point2D.Double(50, 50), new Point2D.Double(100, 100));
        assertEquals(new Point2D.Double(50, 50), itineraryPath.getOrigin());
        assertEquals(new Point2D.Double(100, 100), itineraryPath.getDestination());
    }

    @Test
    public void emptyOriginShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItineraryPath itineraryPath = new ItineraryPath(null, new Point2D.Double(100, 100));
        });
    }

    @Test
    public void emptyDestinationShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItineraryPath itineraryPath = new ItineraryPath(new Point2D.Double(50, 50), null);
        });
    }

    @Test
    public void validConstructorShouldNotThrowException() {
        assertDoesNotThrow(() -> {
            ItineraryPath itineraryPath = new ItineraryPath(new Point2D.Double(50, 50), new Point2D.Double(100, 100));
        });
    }

    /**
     * return the normalized direction vector
     *
     * it's vector / the vector's norm
     */
    @Test
    public void directionVector() {
        ItineraryPath itineraryPath = new ItineraryPath(new Point2D.Double(50, 50), new Point2D.Double(100, 100));
        assertEquals(new Point2D.Double(0.7071067811865475, 0.7071067811865475), itineraryPath.getDirectionVector());
    }

    /**
     * norm of the vector formed by the path
     */
    @Test
    public void norm() {
        ItineraryPath itineraryPath = new ItineraryPath(new Point2D.Double(50, 50), new Point2D.Double(100, 100));
        assertEquals(70.710, itineraryPath.norm(), 0.001);
    }

    @Test
    public void twoIdenticItineraryPathShouldBeEquals() {
        ItineraryPath path1 = new ItineraryPath(new Point2D.Double(50, 50), new Point2D.Double(100, 100));
        ItineraryPath path2 = new ItineraryPath(new Point2D.Double(50, 50), new Point2D.Double(100, 100));

        assertEquals(path1, path2);
    }
}
