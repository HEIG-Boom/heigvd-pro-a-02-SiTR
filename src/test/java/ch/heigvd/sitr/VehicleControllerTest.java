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
        assertEquals(35.5, vehicleController.getDesiredVelocity());
    }

    @Test
    public void minimumSpacing() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setMinimumSpacing(2.5);
        assertEquals(2.5, vehicleController.getMinimumSpacing());
    }

    @Test
    public void desiredTimeHeadway() {
        VehicleController vehicleController = new VehicleController();
        vehicleController.setDesiredTimeHeadway(1.5);
        assertEquals(1.5, vehicleController.getDesiredTimeHeadway());
    }
}
