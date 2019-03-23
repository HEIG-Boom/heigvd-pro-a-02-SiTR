package SiTR;

/**
 * Vehicle class represents the simulation vehicles
 */
public class Vehicle {
    private int xPos; // x coordinate

    /**
     * @param x x coordinate to set
     */
    public void setPosition(int x) {
        xPos = x;
    }

    /**
     * @return x coordinate of the vehicle
     */
    public int getPosition() {
        return xPos;
    }
}
