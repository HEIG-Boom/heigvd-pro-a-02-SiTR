package ch.heigvd.sitr.map.graphics;

import ch.heigvd.sitr.map.roadmappings.PosTheta;
import ch.heigvd.sitr.map.roadmappings.RoadMapping;
import ch.heigvd.sitr.map.roadmappings.RoadMappingLine;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * This class is used to optimize drawing of road segment base on the type of their road mapping
 */
public final class PaintRoadMapping {
    /**
     * Private constructor to avoid instantiation
     */
    private PaintRoadMapping() {
    }

    /**
     * This method paint the road segment based on the type of their road mapping
     *
     * @param g The Graphics on which to draw the road segment
     * @param roadMapping The road mapping of the road segment
     */
    public static void paintRoadMapping(Graphics2D g, RoadMapping roadMapping) {
        double lateralOffset = roadMapping.calcOffsetToCenterline();
        paintRoadMapping(g, roadMapping, lateralOffset);
    }

    /**
     * This method paints the road segment based on the type of their road mapping
     *
     * @param g The Graphics on which to draw the road segment
     * @param roadMapping The road mapping of the road segment
     * @param lateralOffset The road's lateral offset
     */
    public static void paintRoadMapping(Graphics2D g, RoadMapping roadMapping,
                                    double lateralOffset) {
        final Line2D.Double line = new Line2D.Double();
        final Point2D from = new Point2D.Double();
        final Point2D to = new Point2D.Double();
        final double roadLength = roadMapping.getRoadLength();
        PosTheta posTheta;

        final Class<? extends RoadMapping> roadMappingClass = roadMapping.getClass();
        if (roadMappingClass == RoadMappingLine.class) {
            posTheta = roadMapping.startPos(lateralOffset);
            from.setLocation(posTheta.getX(), posTheta.getY());
            posTheta = roadMapping.endPos(lateralOffset);
            to.setLocation(posTheta.getX(), posTheta.getY());
            line.setLine(from, to);
            g.draw(line);
        }

        // TODO (tum) Handle other type of road mapping
    }
}
