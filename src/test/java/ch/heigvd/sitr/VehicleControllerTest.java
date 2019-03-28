package ch.heigvd.sitr;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Unit test for Vehicle.
 */
public class VehicleControllerTest {

    @Test
    public void desiredVelocity() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredVelocity(35.5);
        assertEquals(vehicleController.getDesiredVelocity(), 35.5);
    }
}
