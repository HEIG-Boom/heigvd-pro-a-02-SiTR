package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;

public class RoadMappingArc extends RoadMapping {

    private static final double HALF_PI = 0.5 * Math.PI;

    protected double centerX;
    protected double centerY;

    protected final double radius;

    protected final boolean clockwise;
    protected double startAngle;
    protected double arcAngle;

    RoadMappingArc(LaneGeometries laneGeometries, double s, double x0, double y0, double startAngle, double length, double curvature) {
        super(laneGeometries, x0, y0);
        this.startAngle = startAngle;
        this.roadLength = length;
        this.radius = 1.0 / Math.abs(curvature);
        this.clockwise = curvature < 0;
        arcAngle = roadLength * curvature;
        centerX = x0 - radius * Math.cos(startAngle - HALF_PI) * (clockwise ? -1 : 1);
        centerY = y0 - radius * Math.sin(startAngle - HALF_PI) * (clockwise ? -1 : 1);
    }

    @Override
    public PosTheta map(double roadPos, double lateralOffset) {
        // tangent to arc (road direction)
        final double theta = clockwise ? startAngle - roadPos / radius : startAngle + roadPos / radius;
        // angle arc subtends at center
        final double arcTheta = theta - HALF_PI;
        posTheta.cosTheta = Math.cos(theta);
        posTheta.sinTheta = Math.sin(theta);
        // lateralOffset is perpendicular to road
        final double r = radius - lateralOffset * (clockwise ? -1 : 1);
        posTheta.x = centerX + r * Math.cos(arcTheta) * (clockwise ? -1 : 1);
        posTheta.y = centerY + r * Math.sin(arcTheta) * (clockwise ? -1 : 1);
        return posTheta;
    }

    public static RoadMappingArc create(RoadGeometry roadGeometry) {
        return create(roadGeometry.getLaneGeometries(), roadGeometry.getGeometry());
    }

    private static RoadMappingArc create(LaneGeometries laneGeometries, Geometry geometry) {
        return new RoadMappingArc(laneGeometries, geometry.getS(), geometry.getX(), geometry.getY(), geometry.getHdg(),
                geometry.getLength(), geometry.getArc().getCurvature());
    }

    /**
     * Returns the start angle of the arc.
     *
     * @return the start angle of the arc, radians
     */
    public double startAngle() {
        return startAngle;
    }

    /**
     * Returns the sweep angle of the arc.
     *
     * @return sweep angle of the arc, radians
     */
    public double arcAngle() {
        return arcAngle;
    }

    /**
     * Returns true if the circle mapping is in a clockwise direction.
     *
     * @return true if the circle mapping is in a clockwise direction
     */
    public boolean clockwise() {
        return clockwise;
    }

    /**
     * Returns the radius of the circle.
     *
     * @return the radius of the circle
     */
    public double radius() {
        return radius;
    }

    @Override
    public String toString() {
        return "RoadMappingArc [x0=" + x0 + ", y0=" + y0 + ", centerX=" + centerX + ", centerY=" + centerY + ", radius="
                + radius + ", clockwise=" + clockwise + ", startAngle=" + startAngle + ", arcAngle=" + arcAngle + "]";
    }
}
