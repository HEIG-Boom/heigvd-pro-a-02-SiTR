package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.map.Lane;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Arrays;

/**
 * This class represents a road mapping that maps a logical road position (given by a lane and
 * a position on a road segment) onto a physical position with x,y coordinate
 */
public abstract class RoadMapping {
    // Lane properties
    @Getter
    protected LaneGeometries laneGeometries;

    // Road properties
    @Getter
    protected double roadLength;
    @Getter
    @Setter
    protected Color roadColor;
    protected static Color defaultRoadColor = new Color(129, 128, 128);

    // Positioning properties
    protected final PosTheta posTheta = new PosTheta();
    protected double x0;
    protected double y0;

    /**
     * Constructor
     *
     * @param laneGeometries Lane geometry
     * @param x0             The start position of the plan view geometryy (x inertial)
     * @param y0             The start position of the plan view geometry (y inertial)
     */
    protected RoadMapping(LaneGeometries laneGeometries, double x0, double y0) {
        this.x0 = x0;
        this.y0 = y0;
        this.laneGeometries = laneGeometries;
        roadColor = defaultRoadColor;
    }

    /**
     * Convenience function, get the start position of the road
     *
     * @return The start position of the road
     */
    public PosTheta startPos() {
        return startPos(0.0);
    }

    /**
     * Convenience function, get the start position of the road for a given lateral offset
     *
     * @param lateralOffset The lateral offset of the road
     * @return The start position of the road
     */
    public PosTheta startPos(double lateralOffset) {
        return map(0.0, lateralOffset);
    }

    /**
     * Convenience function, get the end position of the road
     *
     * @return The end position of the road
     */
    public PosTheta endPos() {
        return endPos(0.0);
    }

    /**
     * Convenience function, get the end position of the road for a given lateral offset
     *
     * @param lateralOffset The lateral offset of the road
     * @return The end position of the road
     */
    public PosTheta endPos(double lateralOffset) {
        return map(roadLength, lateralOffset);
    }

    /**
     * Get the lane width
     *
     * @return The lane width
     */
    public final double laneWidth() {
        return laneGeometries.getLaneWidth();
    }

    /**
     * Get the number of lane in the road
     *
     * @return The number of lane in the road
     */
    public int laneCount() {
        return laneGeometries.getTotalLaneCount();
    }

    /**
     * Get the road width
     *
     * @return The road width
     */
    public final double roadWidth() {
        return laneCount() * laneWidth();
    }

    /**
     * Get the offset of the center of the lane. Fractional lanes are supported to facilitate
     * the drawing of vehicles in the process of changing lanes.
     *
     * @param lane The lane where we want to get the offset
     * @return The offset of the center of the lane
     */
    protected double laneOffset(double lane) {
        return lane == Lane.NONE ? 0 : (lane - 1) * laneWidth();
    }

    /**
     * Get the offset of the center of the lane
     *
     * @param lane The lane where we want to get the offset
     * @return The offset of the center of the lane
     */
    protected double laneOffset(int lane) {
        return laneOffset((double) lane);
    }

    /**
     * This class represents a polygon with floating point coordinates.
     */
    public static class PolygonFloat {
        public int pointCount;  // Number of points in the polygon
        float[] xPoints;        // Array of x-coordinates of the polygon
        float[] yPoints;        // Array of y-coordinates of the polygon

        /**
         * Constructor
         *
         * @param pointCount number of points in the polygon.
         */
        PolygonFloat(int pointCount) {
            this.pointCount = pointCount;
            xPoints = new float[pointCount];
            yPoints = new float[pointCount];
        }

        @Override
        public String toString() {
            return "PolygonFloat [pointCount=" + pointCount + ", xPoints=" +
                    Arrays.toString(xPoints) + ", yPoints=" + Arrays.toString(yPoints) + "]";
        }
    }

    /**
     * Returns the offset to the center line of the road with different number of lanes in the
     * two driving directions (RoadSegments). The center line of a road is defined by the
     * reference line given in the xodr network specification.
     *
     * @return the offset to the centerline in case of different lane counts
     */
    public double calcOffsetToCenterline() {
        int laneDiff = laneGeometries.getLeft().getLaneCount() -
                laneGeometries.getRight().getLaneCount();
        return 0.5 * laneDiff * laneGeometries.getLaneWidth();
    }

    /**
     * Get the max right offset
     *
     * @return The max right offset
     */
    public double getMaxOffsetRight() {
        return -laneGeometries.getRight().getLaneCount() * laneGeometries.getLaneWidth();
    }


    @Override
    public String toString() {
        return "RoadMapping [LaneGeometries=" + laneGeometries + ", roadLength=" + roadLength +
                ", posTheta=" + posTheta + ", x0=" + x0 + ", y0=" + y0 + "]";
    }

    /**
     * This method maps the road position with the lateral offset
     *
     * @param roadPos       The road position
     * @param lateralOffset The lateral offset
     * @return The position in space and the direction
     */
    public abstract PosTheta map(double roadPos, double lateralOffset);
}
