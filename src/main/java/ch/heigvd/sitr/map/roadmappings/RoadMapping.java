package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.map.Lane;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
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

    // Clipping region properties
    protected static final int POINT_COUNT = 4;
    protected final PolygonFloat polygonFloat = new PolygonFloat(POINT_COUNT);
    @Getter
    protected ArrayList<PolygonFloat> clippingPolygons;
    @Getter
    protected PolygonFloat outsideClippingPolygon;
    protected final PolygonFloat lineFloat = new PolygonFloat(2);

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
     * Get the road width
     *
     * @return The road width
     */
    public final double roadWidth() {
        return laneGeometries.getTotalLaneCount() * laneWidth();
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
     * Get the offset of the center of the lane
     *
     * @param lane The lane where we want to get the offset
     * @return The offset of the center of the lane
     */
    protected double laneCenterOffset(double lane) {
        return laneOffset(lane) + 0.5 * laneWidth();
    }

    /**
     * Set a clipping region based on the road position and the length. Only one clipping
     * region is supported
     *
     * @param pos    The road position
     * @param length The road length
     */
    public void addClippingRegion(double pos, double length) {
        if (clippingPolygons == null) {
            clippingPolygons = new ArrayList<>();
        }

        if (outsideClippingPolygon == null) {
            final float LARGE_NUMBER = 100000.0f;
            outsideClippingPolygon = new PolygonFloat(POINT_COUNT);
            outsideClippingPolygon.xPoints[0] = -LARGE_NUMBER;
            outsideClippingPolygon.yPoints[0] = -LARGE_NUMBER;
            outsideClippingPolygon.xPoints[1] = LARGE_NUMBER;
            outsideClippingPolygon.yPoints[1] = -LARGE_NUMBER;
            outsideClippingPolygon.xPoints[2] = LARGE_NUMBER;
            outsideClippingPolygon.yPoints[2] = LARGE_NUMBER;
            outsideClippingPolygon.xPoints[3] = -LARGE_NUMBER;
            outsideClippingPolygon.yPoints[3] = LARGE_NUMBER;
        }

        final PolygonFloat clippingPolygon = new PolygonFloat(POINT_COUNT);
        final double offset = 1.5 * laneCount() * laneWidth();

        PosTheta posTheta;
        posTheta = map(pos + length, -offset);
        clippingPolygon.xPoints[0] = (float) posTheta.x;
        clippingPolygon.yPoints[0] = (float) posTheta.y;

        posTheta = map(pos + length, offset);
        clippingPolygon.xPoints[1] = (float) posTheta.x;
        clippingPolygon.yPoints[1] = (float) posTheta.y;

        posTheta = map(pos, offset);
        clippingPolygon.xPoints[2] = (float) posTheta.x;
        clippingPolygon.yPoints[2] = (float) posTheta.y;

        posTheta = map(pos, -offset);
        clippingPolygon.xPoints[3] = (float) posTheta.x;
        clippingPolygon.yPoints[3] = (float) posTheta.y;

        clippingPolygons.add(clippingPolygon);
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

        /**
         * Get the x-coordinate
         *
         * @param i index of x-coordinate
         * @return The x-coordinate
         */
        public float getXPoint(int i) {
            return xPoints[i];
        }

        /**
         * Get the y-coordinate
         *
         * @param i Index of y-coordinate
         * @return The y-coordinate
         */
        public float getYPoint(int i) {
            return -yPoints[i];
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

    /**
     * Get the left offset
     *
     * @param lane The lane where we want to get the left offset
     * @return The left offset of the lane
     */
    public double getOffsetLeft(int lane) {
        return Math.min(lane, laneGeometries.getLeft().getLaneCount())
                * laneGeometries.getLaneWidth();
    }

    /**
     * Get number of lane in a direction
     *
     * @return The number of lane in a direction
     */
    public int getLaneCountInDirection() {
        return laneGeometries.getRight().getLaneCount();
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
