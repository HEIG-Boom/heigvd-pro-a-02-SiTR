package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;
import lombok.Getter;

/**
 * This class is used to map a road arc
 */
public class RoadMappingArc extends RoadMapping {

    private static final double HALF_PI = 0.5 * Math.PI;
    // Location the center of Arc
    private double centerX;
    private double centerY;

    // atribut of Arc
    @Getter
    private final double radius;
    @Getter
    private final boolean clockwise;
    @Getter
    private double startAngle;
    @Getter
    private double arcAngle;

    /**
     * Constructor
     *
     * @param laneGeometries Lane's geometry in the road mapping
     * @param s              The start position of the plan view geometry (s-coordinate)
     * @param x0             The start position of the plan view geometryy (x inertial)
     * @param y0             The start position of the plan view geometry (y inertial)
     * @param startAngle     The start Angle when begin the Arc
     * @param length         The length of the line
     * @param curvature      The curvateur of the Arc
     */
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

    /**
     * This methode create the road mapping Arc
     *
     * @param roadGeometry The road geometry
     * @return The created road mapping
     */
    public static RoadMappingArc create(RoadGeometry roadGeometry) {
        return create(roadGeometry.getLaneGeometries(), roadGeometry.getGeometry());
    }

    /**
     * This methode private maps the road Arc
     *
     * @param laneGeometries The line Geometries of the road
     * @param geometry       OpenDRIVE plan view geometry
     * @return The created road mapping
     */
    private static RoadMappingArc create(LaneGeometries laneGeometries, Geometry geometry) {
        return new RoadMappingArc(laneGeometries, geometry.getS(), geometry.getX(), geometry.getY(), geometry.getHdg(),
                geometry.getLength(), geometry.getArc().getCurvature());
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

    @Override
    public String toString() {
        return "RoadMappingArc [x0=" + x0 + ", y0=" + y0 + ", centerX=" + centerX + ", centerY=" + centerY + ", radius="
                + radius + ", clockwise=" + clockwise + ", startAngle=" + startAngle + ", arcAngle=" + arcAngle + "]";
    }
}
