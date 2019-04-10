/*
 * Filename : VehicleBehaviourTypeTest.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the VehicleBehaviourType enum
 *
 * @author Luc Wachter
 */
class VehicleBehaviourTypeTest {
    /**
     * Test enum's toString
     */
    @Test
    public void toStringShouldEnableToGetBehaviourNames() {
        assertEquals("Aller de la destination au départ", VehicleBehaviourType.REVERSE_PATH.toString());
        assertEquals("S'arrêter là", VehicleBehaviourType.STOP.toString());
    }
}