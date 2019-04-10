/*
 * Filename: ItineraryTest.java
 * Creation date: 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for Itinerary.
 *
 * @author Simon Walther
 */
public class ItineraryTest {
    @Test
    public void emptyItinerary() {
        Itinerary itinerary = new Itinerary();
        assertEquals(0, itinerary.size());
        assertNull(itinerary.pop());
    }
}
