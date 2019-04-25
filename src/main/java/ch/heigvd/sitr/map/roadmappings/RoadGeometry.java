package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;
import lombok.Getter;

/**
 * This class represents the road geometry
 */
public class RoadGeometry {
    @Getter
    protected final Geometry geometry;              // OpenDRIVE plan view geometry
    @Getter
    protected final LaneGeometries laneGeometries;  // Lane geometries

    /**
     * Constructor
     *
     * @param geometry OpenDRIVE plan view geometry
     * @param laneGeometries Lane geometries
     */
    public RoadGeometry(Geometry geometry, LaneGeometries laneGeometries) {
        this.geometry = geometry;
        this.laneGeometries = laneGeometries;
    }
}
