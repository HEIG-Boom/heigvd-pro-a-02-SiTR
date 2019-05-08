package ch.heigvd.sitr.statistics;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.vehicle.Vehicle;
import java.util.LinkedList;

/**
 * Allows you to store simulation information in order to make statistics
 */
public class Statistics extends Thread{

    private LinkedList<Integer> networkOccupancy;
    private LinkedList<Vehicle> vehicles;
    private int coolingTime; // time to refresh
    private boolean running; // thread is in progress

    /**
     * Constructor
     * @param v List of vehicule
     * @param coolingTime time to refresh statistics in seconds
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
    public void terminate(){
        running = false;
    }

    /**
     * Start this Thread
     */
    public void run(){
        while(running) {
            try {
                sleep(coolingTime * 1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            SimulationWindow.getInstance().getSimControlPanel().setWaitingTimeValue(String.valueOf(getWaitingTime()));
            SimulationWindow.getInstance().getSimControlPanel().setAccidentCounterValue(String.valueOf(getAccident()));
            SimulationWindow.getInstance().getSimControlPanel().setOccupationValue(String.valueOf(getNetworkOccupancy()));
        }
    }

    /**
     * Adds the percentage of network occupancy between 0 and 100
     *
     * temporary
     *
     * @param occupancy The percentage
     */
    public void addNetworkOccupancy(int occupancy) {
        if (occupancy >= 0 && occupancy <= 100)
            networkOccupancy.add(occupancy);
    }

    /**
     * @return The average waiting time of the vehicles
     */
    public double getWaitingTime(){
        if(vehicles.size() == 0)
            return 0;

        double average = 0;
        for(Vehicle v : vehicles){
            average += v.getWaitingTime();
        }
        return average / vehicles.size();
    }

    /**
     * @return The number of accidents in all vehicles
     */
    public int getAccident(){
        int nbAccident = 0;
        for(Vehicle v : vehicles){
            nbAccident += v.getNbAccidents();
        }
        return nbAccident;
    }

    /**
     * @return The percentage of network occupancy
     */
    public int getNetworkOccupancy(){
        if(networkOccupancy.size() == 0)
            return 0;

        int average = 0;
        for(int i : networkOccupancy){
            average += i;
        }
        return average / networkOccupancy.size();
    }

    /**
     * Exports the statistics to a file
     */
    public void exportStatistical(){
        // TO DO
    }
}
