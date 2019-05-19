/*
 * Filename : Statistics.java
 * Creation date : 08.05.2019
 */

package ch.heigvd.sitr.statistics;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.map.RoadNetwork;
import ch.heigvd.sitr.map.RoadSegment;
import ch.heigvd.sitr.model.Scenario;
import ch.heigvd.sitr.utils.Conversions;
import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Allows you to store simulation information in order to make statistics
 *
 * @author Alexandre Monteiro Marques
 */
public class Statistics extends Thread {
    // Directory where the General statistics file is located
    private final String pathFileStats = "./statistics/";
    // Separator for CSV file
    private final String separator = ";";

    @Getter
    private double networkOccupancy;
    private LinkedList<Vehicle> vehicles;
    // Time to refresh
    private int coolingTime;
    // Thread is in progress
    private boolean running;
    // Time in ms
    private long simulationStartTime;
    // Lets you know if the statistics are paused
    private boolean pause;
    //time when the statistics was paused
    private long beginSimulationTimePause;

    /**
     * Constructor
     *
     * @param v           List of vehicle
     * @param coolingTime Time to refresh statistics in seconds
     */
    public Statistics(LinkedList<Vehicle> v, RoadNetwork rn, int coolingTime) {
        vehicles = v;
        this.coolingTime = coolingTime;
        running = true;
        pause = false;
        beginSimulationTimePause = 0;

        // Calculating the network occupancy rate
        double distanceNetwork = 0;
        for (RoadSegment roadSegment : rn) {
            distanceNetwork += roadSegment.getRoadLength();
        }
        networkOccupancy = rounded((getSizeAllCar() / distanceNetwork) * 100, 2);

        // keep the current time
        simulationStartTime = System.currentTimeMillis();
    }

    /**
     * Allows you to pause the data collection of statistics
     */
    public void pause() {
        if (running && !pause) {
            beginSimulationTimePause = System.currentTimeMillis();
            pause = true;
        }
    }

    /**
     * Allows to restart the data collection of statistics
     */
    public void restart() {
        if (running && pause) {
            pause = false;
            simulationStartTime += (System.currentTimeMillis() - beginSimulationTimePause);
        }
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
            if (!pause) {
                // Check if the window is still open because it was implemented in singleton
                // and the thread could create an instance if the window does not exist
                if (running)
                    SimulationWindow.getInstance().getSimControlPanel().setWaitingTimeValue(getWaitingTime() + "%");
                if (running)
                    SimulationWindow.getInstance().getSimControlPanel().setAccidentCounterValue(String.valueOf(nbrOfAccidents()));
                if (running)
                    SimulationWindow.getInstance().getSimControlPanel().setOccupationValue(getNetworkOccupancy() + "%");

            }
            try {
                sleep(coolingTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculating the average waiting time of all vehicles
     *
     * @return The average waiting time of all vehicles
     */
    public double getWaitingTime() {
        // check if there is a vehicle
        if (vehicles.size() == 0)
            return 0;

        // adds up all the waiting times for each vehicle
        long average = 0;
        for (Vehicle v : vehicles) {
            average += v.getWaitingTime();
        }

        // achieves the average
        return rounded((double) average / (vehicles.size() * elapsedTime()) * 100, 3);
    }

    /**
     * Calculating the total accident number
     *
     * @return The number of accidents in all vehicles
     */
    public int nbrOfAccidents() {
        // Adds the number of accidents in each vehicle
        int nbAccident = 0;
        for (Vehicle v : vehicles) {
            nbAccident += v.getNbOfAccidents();
        }
        return nbAccident;
    }

    /**
     * Calculating the size of all vehicles
     *
     * @return The size of all vehicles
     */
    private double getSizeAllCar() {
        if (vehicles.size() == 0)
            return 0;

        double sizeAllCar = 0;
        for (Vehicle v : vehicles) {
            sizeAllCar += Conversions.metersToPixels(8, v.getLength());
        }

        return sizeAllCar;
    }

    /**
     * Rounding the number
     *
     * @param value        The value
     * @param nbAfterComma Number of digits after the decimal point
     * @return The value with the decimal point
     */
    private double rounded(double value, int nbAfterComma) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(nbAfterComma, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Duration of the current simulation
     *
     * @return Duration of the current simulation
     */
    private long elapsedTime() {
        return System.currentTimeMillis() - simulationStartTime;
    }

    /**
     * Convert time to millisecond in character string time
     *
     * @param time Time in millisecond
     * @return Character string time
     */
    private String convertMsToHour(long time) {
        String duration = "";
        long d = TimeUnit.MILLISECONDS.toDays(time);
        long h = TimeUnit.MILLISECONDS.toHours(time) % 24;
        long m = TimeUnit.MILLISECONDS.toMinutes(time) % 60;
        long s = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
        long ms = TimeUnit.MILLISECONDS.toMillis(time) % 1000;

        // Used to set the date format (X day hh:mm:ss.SSS)
        duration += d == 0 ? "0 Day" : d == 1 ? "1 Day" : d + " Days";
        duration += " ";

        duration += h < 10 ? "0" : "";
        duration += h + ":";

        duration += m < 10 ? "0" : "";
        duration += m + ":";

        duration += s < 10 ? "0" : "";
        duration += s + ".";

        duration += ms < 10 ? "00" : ms < 100 ? "0" : "";
        duration += ms;

        return duration;
    }

    /**
     * Exports the statistics to a file
     */
    public void exportStatistics(Scenario scenario) {
        writeGeneralStatistics(scenario);
        writeVehicleStatistics();
    }

    /**
     * Allows you to export General statistics to a CSV file
     *
     * @param scenario The scenario used for the simulation
     */
    private void writeGeneralStatistics(Scenario scenario) {
        // Know the date of the export of statistics
        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());

        try {
            File file = new File(pathFileStats + "GeneralStatistics.csv");
            Writer writer = getWriter(file);

            if (file.length() == 0) {
                // Add header to CSV file
                String head = "Date" + separator;
                head += "Duration" + separator;
                head += "Scenario name" + separator;
                head += "Number of vehicles" + separator;
                head += "Waiting time" + separator;
                head += "Number of accidents" + separator;
                head += "Network occupancy\n";
                writer.append(head);
            }

            // adding date
            writer.append(date);
            writer.append(separator);
            // adding duration
            writer.append(convertMsToHour(elapsedTime()));
            writer.append(separator);
            // adding scenario
            writer.append(scenario.toString());
            writer.append(separator);
            // adding vehicle number
            writer.append(Integer.toString(vehicles.size()));
            writer.append(separator);
            // adding waiting time
            writer.append(getWaitingTime() + "%");
            writer.append(separator);
            // adding number accident
            writer.append(Integer.toString(nbrOfAccidents()));
            writer.append(separator);
            // adding network occupancy
            writer.append(networkOccupancy + "%");
            writer.append("\n");

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows you to export vehicles statistics to a CSV file
     */
    private void writeVehicleStatistics() {
        // Creates a file name with the date
        String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        try {
            // Directory where the Vehicle statistics file is located
            String pathFileVehicleStats = pathFileStats + "vehicles/";
            File file = new File(pathFileVehicleStats + date + ".csv");
            Writer writer = getWriter(file);

            // Add header to CSV file
            String head = "Vehicle number" + separator;
            head += "Controller Type" + separator;
            head += "Waiting time" + separator;
            head += "Number of accidents\n";
            writer.append(head);

            // Adds the information of each vehicle to the file
            int counter = 1;
            for (Vehicle v : vehicles) {
                writer.append(counter++ + separator);
                writer.append(v.getVehicleController().getControllerType().toString() + separator);
                writer.append(v.getWaitingTime() + separator);
                writer.append(v.getNbOfAccidents() + "\n");
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows you to create the file as needed and give write access
     *
     * @param file The desired file
     * @return Writer Object to write to the file
     */
    private Writer getWriter(File file) {
        try {
            file.getParentFile().mkdirs();

            Writer writer = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
            writer = new BufferedWriter(writer);
            return writer;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
