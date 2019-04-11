/*
 * Filename : Simulation.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

/**
 * Simulation class handles all global simulation settings and values
 *
 * @author Luc Wachter
 */
public class Simulation {
    /**
     * Convert meters per second to kilometers per hour
     *
     * @param mps The amount of m/s to convert
     * @return the corresponding amount of km/h
     */
    public static double mpsToKph(double mps) {
        // m/s => km/h : x * 3.6
        return mps * 3.6;
    }

    /**
     * Convert kilometers per hour to meters per second
     *
     * @param kph The amount of km/h to convert
     * @return the corresponding
     */
    public static double kphToMps(double kph) {
        // km/h => m/s : x / 3.6
        return kph / 3.6;
    }

    /**
     * Main display loop, runs in a fixed rate timer loop
     */
    public void loop() {
        // Schedule a task to run immediately, and then
        // every UPDATE_RATE per second
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Vehicle vehicle : vehicles) {
                    vehicle.update(0.5);
                    vehicle.draw();
                }

                // Callback to paintComponent()
                window.repaint();
            }
        }, 0, UPDATE_RATE);
    }
}
