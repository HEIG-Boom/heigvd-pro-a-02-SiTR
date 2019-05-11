/*
 * Filename : Statistics.java
 * Creation date : 08.05.2019
 */

package ch.heigvd.sitr.statistics;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.vehicle.Vehicle;

import java.util.LinkedList;

/**
 * Allows you to store simulation information in order to make statistics
 *
 * @author Alexandre Monteiro Marques
 */
public class Statistics extends Thread {

    private LinkedList<Integer> networkOccupancy;
    private LinkedList<Vehicle> vehicles;
    private int coolingTime; // time to refresh
    private boolean running; // thread is in progress

    /**
     * Constructor
     *
     * @param v           List of vehicule
     * @param coolingTime Time to refresh statistics in seconds
     */
    public Statistics(LinkedList<Vehicle> v, int coolingTime) {
        networkOccupancy = new LinkedList<>();
        vehicles = v;
        this.coolingTime = coolingTime;
        running = true;
    }

    /**
     * Stop this Thread
     */
    public void terminate() {
        running = false;
    }

    /**
     * Start this Thread
     */
    public void run() {
        while (running) {
            SimulationWindow.getInstance().getSimControlPanel().setWaitingTimeValue(String.valueOf(getWaitingTime()));
            SimulationWindow.getInstance().getSimControlPanel().setAccidentCounterValue(String.valueOf(getAccident()));
            SimulationWindow.getInstance().getSimControlPanel().setOccupationValue(String.valueOf(getNetworkOccupancy()));

            try {
                sleep(coolingTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds the percentage of network occupancy between 0 and 100
     *
     * @param occupancy The percentage
     */
    public void addNetworkOccupancy(int occupancy) {
        // checking the user's input
        if (occupancy >= 0 && occupancy <= 100)
            networkOccupancy.add(occupancy);
    }

    /**
     * calculating the average waiting time of all vehicles
     *
     * @return The average waiting time of all vehicles
     */
    public double getWaitingTime() {
        // check if there is a vehicle
        if (vehicles.size() == 0)
            return 0;

        // adds up all the waiting times for each vehicle
        double average = 0;
        for (Vehicle v : vehicles) {
            average += v.getWaitingTime();
        }
        // achieves the average
        return average / vehicles.size();
    }

    /**
     * calculating the total accident number
     *
     * @return The number of accidents in all vehicles
     */
    public int getAccident() {
        // Adds the number of accidents in each vehicle
        int nbAccident = 0;
        for (Vehicle v : vehicles) {
            nbAccident += v.getNbAccidents();
        }
        return nbAccident;
    }

    /**
     * calculating the average network occupancy
     *
     * @return The percentage of network occupancy
     */
    public int getNetworkOccupancy() {
        // checks if there is any data
        if (networkOccupancy.size() == 0)
            return 0;

        // calculating the average network occupancy
        int average = 0;
        for (int i : networkOccupancy) {
            average += i;
        }
        return average / networkOccupancy.size();
    }

    /**
     * Exports the statistics to a file
     */
    public void exportStatistical() {
        // TO DO
    }
}
