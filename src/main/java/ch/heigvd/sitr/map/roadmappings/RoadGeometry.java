package ch.heigvd.sitr.map.roadmappings;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.Road.PlanView.Geometry;
import lombok.Getter;

/**
 * This class represents the road geometry
 */
public class RoadGeometry {
    // The types of road
    public enum GeometryType {
        LINE, ARC, SPIRAL, POLY3
    }

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

    /**
     * Get the geometry type
     *
     * @return The geometry type
     */
    public GeometryType geometryType() {
        if (geometry.isSetLine()) {
            return GeometryType.LINE;
        } else if (geometry.isSetArc()) {
            return GeometryType.ARC;
        } else if (geometry.isSetPoly3()) {
            return GeometryType.POLY3;
        } else if (geometry.isSetSpiral()) {
            return GeometryType.SPIRAL;
        } else {
            throw new IllegalArgumentException("Unknown geometry type: " + geometry);
        }
    }
}
