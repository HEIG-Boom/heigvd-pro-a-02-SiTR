/*
 * Filename : ConversionsTest.java
 * Creation date : 06.05.2019
 */

package ch.heigvd.sitr.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Conversions utils class
 *
 * @author Luc Wachter
 */
class ConversionsTest {
    @Test
    public void shouldReturnCorrectAmountOfKph() {
        assertEquals(3.6, Conversions.mpsToKph(1));
        assertEquals(72.0, Conversions.mpsToKph(20));
    }

    @Test
    public void shouldReturnCorrectAmountOfMps() {
        assertEquals(1.0, Conversions.kphToMps(3.6));
        assertEquals(20.0, Conversions.kphToMps(72));
    }

    @Test
    public void shouldReturnCorrectAmountOfM() {
        // 100 pixels in meters
        assertEquals(12.5, Conversions.pixelsToMeters(8, 100));
    }

    @Test
    public void shouldReturnCorrectAmountOfPx() {
        // 4 meters in pixels
        assertEquals(32, Conversions.metersToPixels(8, 4));
    }
}