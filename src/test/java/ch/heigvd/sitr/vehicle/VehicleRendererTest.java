/*
 * Filename : VehicleRendererTest.java
 * Creation date : 10.04.2019
 */

package ch.heigvd.sitr.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the vehicle renderer
 */
class VehicleRendererTest {
    @Test
    public void getInstanceShouldNotReturnNull() {
        assertNotNull(VehicleRenderer.getInstance());
    }
}