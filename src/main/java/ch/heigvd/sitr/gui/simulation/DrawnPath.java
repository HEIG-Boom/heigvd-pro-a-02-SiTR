package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.vehicle.Vehicle;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Observable;
import java.util.Observer;

public class DrawnPath extends JComponent implements Observer {
    // the only one instance of the class
    private static DrawnPath instance;

    // path object of the vehicle
    GeneralPath path;

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

    @Override
    public void update(Observable o, Object arg) {
        addStroke();
        paint(SimulationWindow.getInstance().getSimulationPane());
    }

    public void paint(Graphics g) {
        Graphics2D map = (Graphics2D) g;
        map.setStroke(new BasicStroke(2.0f));
        map.setPaint(vehicle.getColor());
        map.draw(path);
    }

    public void kill() {
        vehicle.setDrawingPath(false);
        vehicle.deleteObserver(this);
        reset();
        vehicle = null;
    }

    public void setVehicle(Vehicle vehicle) {
        if (this.vehicle != null) {
            kill();
        }
        this.vehicle = vehicle;
        reset();
    }

    private void reset() {
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path.moveTo(vehicle.getGlobalPosition().x, vehicle.getGlobalPosition().y);
    }

    private void addStroke() {
        path.lineTo(vehicle.getGlobalPosition().x, vehicle.getGlobalPosition().y);
    }
}
