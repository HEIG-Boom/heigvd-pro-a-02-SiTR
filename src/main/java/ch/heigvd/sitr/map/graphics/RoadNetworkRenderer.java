/*
 * Filename : RoadNetworkRenderer.java
 * Creation date : 24.04.2019
 */

package ch.heigvd.sitr.map.graphics;

import ch.heigvd.sitr.map.RoadNetwork;
import ch.heigvd.sitr.map.RoadSegment;
import ch.heigvd.sitr.map.roadmappings.RoadMapping;
import lombok.Setter;

import java.awt.*;

/**
 * A singleton renderer for road networks
 */
public class RoadNetworkRenderer {
    private static final float LINE_WIDTH = 1.0f;
    private static final float LINE_LENGTH = 5.0f;
    private static final float GAP_LENGTH = 15.0f;
    private static final Color ROAD_LINE_COLOR = new Color(221, 221, 210);
    private static final Color ROAD_EDGE_COLOR = new Color(34, 34, 34);

    // Unique instance of the class
    private static RoadNetworkRenderer instance;

    // The road network to draw
    @Setter
    private RoadNetwork roadNetwork;

    /**
     * Private constructor to avoid instantiation
     */
    private RoadNetworkRenderer() {
    }

    /**
     * Get the singleton's instance
     *
     * @return a reference to the renderer
     */
    public static RoadNetworkRenderer getInstance() {
        if (instance == null)
            instance = new RoadNetworkRenderer();

        return instance;
    }

    /**
     * Rendering method for road networks
     *
     * @param g The Graphics on which to draw the road network
     */
    public void display(Graphics2D g) {
        drawRoadSegmentsAndLines(g);
    }

    /**
     * This method draws each road segment in the road network
     *
     * @param g The Graphics on which to draw the road segment
     */
    private void drawRoadSegmentsAndLines(Graphics2D g) {
        for (final RoadSegment roadSegment : roadNetwork) {
            final RoadMapping roadMapping = roadSegment.getRoadMapping();

            // Set the road properties
            BasicStroke roadStroke = new BasicStroke((float) roadMapping.roadWidth(),
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
            g.setStroke(roadStroke);
            g.setColor(roadMapping.getRoadColor());

            // Paint the road segment based on their road mapping
            PaintRoadMapping.paintRoadMapping(g, roadMapping);

            // Draw the road line
            final float dashPhase =
                    (float) (roadMapping.getRoadLength() % (LINE_LENGTH + GAP_LENGTH));
            final Stroke lineStroke = new BasicStroke(LINE_WIDTH, BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_MITER, 10.0f, new float[]{LINE_LENGTH, GAP_LENGTH},
                    dashPhase);
            g.setStroke(lineStroke);
            g.setColor(ROAD_LINE_COLOR);

            int maxRightLane = -roadMapping.getLaneGeometries().getRight().getLaneCount();
            int maxLeftLane = roadMapping.getLaneGeometries().getLeft().getLaneCount();
            for (int lane = maxRightLane + 1; lane < maxLeftLane; lane++) {
                final double offset = lane * roadMapping.getLaneGeometries().getLaneWidth();
                g.setStroke(lineStroke);
                PaintRoadMapping.paintRoadMapping(g, roadMapping, offset);
            }

            // Draw the road edges
            g.setStroke(new BasicStroke());
            g.setColor(ROAD_EDGE_COLOR);
            double offset = roadMapping.getLaneGeometries().getLeft().getLaneCount() *
                    roadMapping.getLaneGeometries().getLaneWidth();
            PaintRoadMapping.paintRoadMapping(g, roadMapping, offset);
            offset = roadMapping.getMaxOffsetRight();
            PaintRoadMapping.paintRoadMapping(g, roadMapping, offset);
        }
    }
}
