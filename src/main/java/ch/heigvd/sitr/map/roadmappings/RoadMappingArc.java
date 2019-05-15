package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;
import lombok.Getter;

import java.awt.geom.Point2D;

/**
 * This class is used to map a road arc
 */
public class RoadMappingArc extends RoadMapping {

    private static final double HALF_PI = 0.5 * Math.PI;
    private double centerX;
    private double centerY;

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
     * @param x0             The start position of the plan view geometryy (x inertial)
     * @param y0             The start position of the plan view geometry (y inertial)
     * @param startAngle     The start Angle when begin the Arc
     * @param length         The length of the line
     * @param curvature      The curvature of the Arc
     */
    RoadMappingArc(LaneGeometries laneGeometries, double x0, double y0, double startAngle, double length, double curvature) {
        super(laneGeometries, x0, y0);
        this.startAngle = startAngle;
        this.roadLength = length;
        this.radius = 1.0 / Math.abs(curvature);
        this.clockwise = curvature < 0;
        this.arcAngle = roadLength * curvature;
        this.centerX = x0 - radius * Math.cos(startAngle - HALF_PI) * (clockwise ? -1 : 1);
        this.centerY = y0 - radius * Math.sin(startAngle - HALF_PI) * (clockwise ? -1 : 1);
    }

    /**
     * This method create the road mapping Arc
     *
     * @param roadGeometry The road geometry
     * @return The created road mapping
     */
    public static RoadMappingArc create(RoadGeometry roadGeometry) {
        return create(roadGeometry.getLaneGeometries(), roadGeometry.getGeometry());
    }

    /**
     * This private method maps the road Arc
     *
     * @param laneGeometries The line Geometries of the road
     * @param geometry       OpenDRIVE plan view geometry
     * @return The created road mapping
     */
    private static RoadMappingArc create(LaneGeometries laneGeometries, Geometry geometry) {
        return new RoadMappingArc(laneGeometries, geometry.getX(), geometry.getY(), geometry.getHdg(),
                geometry.getLength(), geometry.getArc().getCurvature());
    }

    @Override
    public PosTheta map(double roadPos, double lateralOffset) {
        final double theta = clockwise ? startAngle - roadPos / radius : startAngle + roadPos / radius;
        final double arcTheta = theta - HALF_PI;
        posTheta.cosTheta = Math.cos(theta);
        posTheta.sinTheta = Math.sin(theta);
        final double r = radius - lateralOffset * (clockwise ? -1 : 1);
        posTheta.x = centerX + r * Math.cos(arcTheta) * (clockwise ? -1 : 1);
        posTheta.y = centerY + r * Math.sin(arcTheta) * (clockwise ? -1 : 1);
        return posTheta;
    }

    /**
     * Computes the position on screen and the angle where is the arc at a given vehicle position
     *
     * @param vehiclePosFromStart the vehicle position
     * @return the angle and position
     */
    public PosTheta posAt(double vehiclePosFromStart) {
        PosTheta posTheta = startPos();

        double angSt = getStartAngle() + (isClockwise() ? 0.5 * Math.PI : -0.5 * Math.PI);
        double angle = vehiclePosFromStart / radius;
        double totalAngle = angSt + angle;
        double centerX = posTheta.getX() - radius * Math.cos(angSt);
        double centerY = posTheta.getY() + radius * Math.sin(angSt);
        double x = centerX + radius * Math.cos(totalAngle);
        double y = centerY + radius * Math.sin(totalAngle);

        PosTheta vehiclePos = new PosTheta();
        vehiclePos.x = x;
        vehiclePos.y = y;
        vehiclePos.sinTheta = Math.sin(angle);
        vehiclePos.cosTheta = Math.cos(angle);

        return vehiclePos;
    }

    @Override
    public String toString() {
        return "RoadMappingArc [centerX=" + centerX + ", centerY=" + centerY + ", radius="
                + radius + ", clockwise=" + clockwise + ", arcAngle=" + arcAngle + "]";
    }
}
