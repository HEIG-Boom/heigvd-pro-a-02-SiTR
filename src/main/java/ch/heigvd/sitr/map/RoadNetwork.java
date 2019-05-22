/*
 * Filename : RoadNetwork.java
 * Creation date : 22.04.2019
 */

package ch.heigvd.sitr.map;

import ch.heigvd.sitr.gui.simulation.SimulationWindow;
import ch.heigvd.sitr.utils.Renderable;
import ch.heigvd.sitr.map.graphics.RoadNetworkRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents an iterable collection of the road segments in the road network
 */
public class RoadNetwork implements Iterable<RoadSegment>, Renderable {
    // Road segments that form the road network
    private final ArrayList<RoadSegment> roadSegments = new ArrayList<>();

    @Getter
    @Setter
    private String name;    // Road network's name

    /**
     * Returns the number of road segments in the road network
     *
     * @return The number of road segments in the road network
     */
    public final int size() {
        return roadSegments.size();
    }

    /**
     * Add a road segment to the road network
     *
     * @param roadSegment The road segment to add to the road network
     * @return The added road segment
     */
    public RoadSegment add(RoadSegment roadSegment) {
        roadSegments.add(roadSegment);
        return roadSegment;
    }

    @Override
    public Iterator<RoadSegment> iterator() {
        return roadSegments.iterator();
    }

    /**
     * Method that calls the renderer in order to draw the road network on the simulation pane
     */
    @Override
    public void draw(double scale) {
        RoadNetworkRenderer.getInstance().setRoadNetwork(this);
        RoadNetworkRenderer.getInstance().display(SimulationWindow.getInstance().getBackgroundSimulationPane());
    }
}
