package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;
import lombok.Getter;

/**
 * This class is used to map a road arc
 */
public class RoadMappingArc extends RoadMapping {

    private static final double HALF_PI = 0.5 * Math.PI;
    @Getter
    private double centerX;
    @Getter
    private double centerY;
    @Getter
    private double startX;
    @Getter
    private double startY;
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
     * @param startX         The start position of the plan view geometry (x inertial)
     * @param startY         The start position of the plan view geometry (y inertial)
     * @param startAngle     The start Angle when begin the Arc
     * @param length         The length of the line
     * @param curvature      The curvature of the Arc
     */
    RoadMappingArc(LaneGeometries laneGeometries, double startX, double startY, double startAngle, double length, double curvature) {
        super(laneGeometries, startX, startY);
        this.clockwise = curvature < 0;
        this.startAngle = startAngle + (clockwise ? HALF_PI : -HALF_PI);
        this.roadLength = length;
        this.radius = 1.0 / Math.abs(curvature);
        this.arcAngle = roadLength * curvature;
        this.startX = startX;
        this.startY = startY;
        this.centerX = startX - radius * Math.cos(this.startAngle);
        this.centerY = startY + radius * Math.sin(this.startAngle);
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

    /**
     * Position at a certain arc length
     *
     * @param posFromStart  Position from the arc start
     * @param lateralOffset The lateral offset
     * @return the angle and position
     */
    @Override
    public AngleAndPos posAt(double posFromStart, double lateralOffset) {
        // TODO: use lateral offset
        double angle = posFromStart / radius * (clockwise ? -1 : 1);
        double totalAngle = startAngle + angle;
        double x = centerX + radius * Math.cos(totalAngle) * (clockwise ? -1 : 1);
        double y = centerY + radius * Math.sin(totalAngle) * (clockwise ? -1 : 1);

        AngleAndPos vehiclePos = new AngleAndPos();
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
