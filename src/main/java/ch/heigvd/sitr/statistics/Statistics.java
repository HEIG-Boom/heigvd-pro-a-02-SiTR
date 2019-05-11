/*
 * Filename : Statistics.java
 * Creation date : 08.05.2019
 */

package ch.heigvd.sitr.statistics;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.map.RoadNetwork;
import ch.heigvd.sitr.map.RoadSegment;
import ch.heigvd.sitr.utils.Conversions;
import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Allows you to store simulation information in order to make statistics
 *
 * @author Alexandre Monteiro Marques
 */
public class Statistics extends Thread {

    @Getter
    private double networkOccupancy;
    private LinkedList<Vehicle> vehicles;
    private int coolingTime; // time to refresh
    private boolean running; // thread is in progress

    /**
     * Constructor
     *
     * @param v           List of vehicule
     * @param coolingTime Time to refresh statistics in seconds
     */
    public Statistics(LinkedList<Vehicle> v, RoadNetwork rn, int coolingTime) {
        vehicles = v;
        this.coolingTime = coolingTime;
        running = true;

        double distanceNetwork = 0;
        Iterator<RoadSegment> it = rn.iterator();
        while(it.hasNext()) {
            distanceNetwork += it.next().getRoadLength();
        }

        BigDecimal bd = new BigDecimal( (getSizeAllCar() / distanceNetwork) * 100);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        networkOccupancy = bd.doubleValue();
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
            //check if the window is still open because it was implemented in singleton
            // and the thread could create an instance if the window does not exist
            if(running)
                SimulationWindow.getInstance().getSimControlPanel().setWaitingTimeValue(String.valueOf(getWaitingTime()));
            if(running)
                SimulationWindow.getInstance().getSimControlPanel().setAccidentCounterValue(String.valueOf(getAccident()));
            if(running)
                SimulationWindow.getInstance().getSimControlPanel().setOccupationValue(String.valueOf(getNetworkOccupancy()));

            try {
                sleep(coolingTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
            nbAccident += v.getAccidents();
        }
        return nbAccident;
    }

    private double getSizeAllCar(){
        if (vehicles.size() == 0)
            return 0;

        double sizeAllCar = 0;
        for (Vehicle v : vehicles) {
            sizeAllCar += Conversions.metersToPixels(8, v.getLength());
        }

        return sizeAllCar;
    }

    /**
     * Exports the statistics to a file
     */
    public void exportStatistical() {
        // TO DO
    }
}
