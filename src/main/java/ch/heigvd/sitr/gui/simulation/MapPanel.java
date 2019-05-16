/*
 * Filename: MapPanel.java
 * Creation date: 01.04.2019
 */

package ch.heigvd.sitr.gui.simulation;

import ch.heigvd.sitr.model.VehicleControllerType;
import lombok.Getter;
import ch.heigvd.sitr.gui.settings.SettingsWindow;
import ch.heigvd.sitr.vehicle.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Map panel represent the drawing area for the map of the simulation
 *
 * @author Alexandre Monteiro Marques, Loris Gilliand
 */
class MapPanel extends JPanel implements MouseListener {
    // color of the background
    @Getter
    private final String backgroundColor = "0x008000";

    // dimensions of the map
    static final int WIDTH = 800;
    static final int HEIGHT = 800;

    /**
     * Package-private constructor of the map panel
     */
    MapPanel() {
        Dimension d = new Dimension(WIDTH, HEIGHT);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
        addMouseListener(this);
    }

    /**
     * This method detect click on the map. If the click is on a vehicle,
     * set the CarControlPanel to observe this car.
     *
     * @param e mouse event used to get the position of the click
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        boolean hitCar = false;
        for (Vehicle v : SettingsWindow.getInstance().getSettingsPanel().getCurrentSim().getVehicles()) {
            if (v.getRectangle().contains(point)) {
                hitCar = true;
                v.deleteObservers();
                v.addObserver(SimulationWindow.getInstance().getCarControlPanel());
                SimulationWindow.getInstance().getCarControlPanel().setVehicle(v);
                VehicleControllerType vct = v.getVehicleController().getControllerType();
                SimulationWindow.getInstance().getCarControlPanel().getControllerChangeBox().setSelectedIndex(VehicleControllerType.valueOf(vct.name()).ordinal());
                v.initiateObservable();
            }
        }
        SimulationWindow.getInstance().getCarControlPanel().setVisible(hitCar);
    }

    /**
     * Not Implemented
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Not Implemented
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Not Implemented
     * @param e mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Not Implemented
     * @param e mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
