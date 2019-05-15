/*
 * Filename: DrawnPath.java
 * Creation date: 12.05.2019
 */

package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Observable;
import java.util.Observer;

/**
 * Drawn Path class represents a drawn trajectory of the selected car.
 *
 * @author Loris Gilliand
 */
public class DrawnPath extends JComponent implements Observer {
    // the only one instance of the class
    private static DrawnPath instance;

    // path object of the vehicle
    GeneralPath path;

    // vehicle that draw its path
    @Getter
    private Vehicle vehicle;

    /**
     * static method of implementation as singleton
     *
     * @return the only one instance of the class
     */
    public static DrawnPath getInstance() {
        if (instance == null) {
            instance = new DrawnPath();
        }
        return instance;
    }

    /**
     * Method used to update the observer according to its observable
     *
     * @param o   observable that has change
     * @param arg never use in this case
     */
    @Override
    public void update(Observable o, Object arg) {
        addStroke();
        paint(SimulationWindow.getInstance().getSimulationPane());
    }

    /**
     * Method used to paint the trajectory on the map
     *
     * @param g the map image
     */
    public void paint(Graphics g) {
        Graphics2D map = (Graphics2D) g;
        map.setStroke(new BasicStroke(2.0f));
        map.setPaint(vehicle.getColor());
        map.draw(path);
    }

    /**
     * Method used to kill the path object.
     * Delete the drawn trajectory on the screen and remove the link between
     * observer and observable.
     */
    public void kill() {
        vehicle.setDrawingPath(false);
        vehicle.deleteObserver(this);
        reset();
        vehicle = null;
    }

    /**
     * Method used to set the observed vehicle
     *
     * @param vehicle observed vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        if (this.vehicle != null) {
            kill();
        }
        this.vehicle = vehicle;
        reset();
    }

    /**
     * Method used to reset the path.
     * Remove the drawn path on screen and set the new starting
     * point at the vehicle emplacement
     */
    private void reset() {
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path.moveTo(vehicle.getGlobalPosition().x, vehicle.getGlobalPosition().y);
    }

    /**
     * Method used to add a stroke frome between the last coordinate and the current one.
     */
    private void addStroke() {
        path.lineTo(vehicle.getGlobalPosition().x, vehicle.getGlobalPosition().y);
    }
}
