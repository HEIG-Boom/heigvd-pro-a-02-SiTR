package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;

/**
 * This class is used to posAt a road segment onto straight line
 */
public class RoadMappingLine extends RoadMapping {
    // Line coordinates
    protected double x1;
    protected double y1;

    /**
     * Constructor
     *
     * @param laneGeometries Lane's geometry in the road mapping
     * @param s The start position of the plan view geometry (s-coordinate)
     * @param x0 The start position of the plan view geometryy (x inertial)
     * @param y0 The start position of the plan view geometry (y inertial)
     * @param theta The direction of the line
     * @param length The length of the line
     */
    public RoadMappingLine(LaneGeometries laneGeometries, double s, double x0, double y0,
                           double theta, double length) {
        super(laneGeometries, x0, y0);
        roadLength = length;
        posTheta.sinTheta = Math.sin(theta);
        posTheta.cosTheta = Math.cos(theta);
        x1 = x0 + length * posTheta.cosTheta;
        y1 = y0 + length * posTheta.sinTheta;
    }

    /**
     * This method creates the road mapping line
     *
     * @param roadGeometry The road geometry
     * @return The created road mapping
     */
    public static RoadMapping create(RoadGeometry roadGeometry) {
        return create(roadGeometry.getLaneGeometries(), roadGeometry.getGeometry());
    }

    /**
     * This private method maps the road segment onto straight line
     *
     * @param laneGeometries The lane geometries of the road
     * @param geometry OpenDRIVE plan view geometry
     * @return The created road mapping
     */
    private static RoadMapping create(LaneGeometries laneGeometries, Geometry geometry) {
        return new RoadMappingLine(laneGeometries, geometry.getS(), geometry.getX(),
                geometry.getY(), geometry.getHdg(), geometry.getLength());
    }

    @Override
    public PosTheta posAt(double roadPos, double lateralOffset) {
        posTheta.x = x0 + roadPos * posTheta.cosTheta - lateralOffset * posTheta.sinTheta;
        posTheta.y = y0 + roadPos * posTheta.sinTheta + lateralOffset * posTheta.cosTheta;
        return posTheta;
    }

    @Override
    public String toString() {
        return "RoadMappingLine [x1=" + x1 + ", y1=" + y1 + "]";
    }
}
