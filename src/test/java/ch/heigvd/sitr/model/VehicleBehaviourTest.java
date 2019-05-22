/*
 * Filename : VehicleBehaviourTest.java
 * Creation date : 08.04.2019
 */

package ch.heigvd.sitr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the VehicleBehaviour enum
 *
 * @author Luc Wachter
 */
class VehicleBehaviourTest {
    /**
     * Test enum's toString
     */
    @Test
    public void toStringShouldEnableToGetBehaviourNames() {
        assertEquals("Continuer dans une boucle", VehicleBehaviour.LOOP.toString());
        assertEquals("S'arrêter là", VehicleBehaviour.STOP.toString());
    }
}