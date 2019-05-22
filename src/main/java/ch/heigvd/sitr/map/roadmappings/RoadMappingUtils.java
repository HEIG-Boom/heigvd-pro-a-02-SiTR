/*
 * Filename : RoadMappingUtils.java
 * Creation date : 22.04.2019
 */

package ch.heigvd.sitr.map.roadmappings;

import com.google.common.collect.Iterables;

/**
 * This class is used as a util to create the road mapping according to the road geometry
 */
public final class RoadMappingUtils {
    /**
     * Private constructor to avoid instantiation
     */
    private RoadMappingUtils() {
    }

    /**
     * Factory for creating the road mapping
     *
     * @param roadGeometries All road geometries of the road network
     * @return The created road mapping
     */
    public static final RoadMapping create(Iterable<RoadGeometry> roadGeometries) {
        if (Iterables.size(roadGeometries) == 1) {
            return create(Iterables.getOnlyElement(roadGeometries));
        }

        // TODO (tum) complete with RoadMappingPoly
        return create(Iterables.getOnlyElement(roadGeometries));
    }

    /**
     * Factory for creating the road mapping
     *
     * @param roadGeometry The road geometry of the road segment
     * @return The created road mapping
     */
    private static final RoadMapping create(RoadGeometry roadGeometry) {
        RoadMapping roadMapping;
        if (roadGeometry.getGeometry().isSetLine()) {
            roadMapping = RoadMappingLine.create(roadGeometry);
        } else if (roadGeometry.getGeometry().isSetArc()) {
            roadMapping = RoadMappingArc.create((roadGeometry));
        } else if (roadGeometry.getGeometry().isSetPoly3()) {
            throw new IllegalArgumentException("POLY3 geometry not yet supported. ");
        } else if (roadGeometry.getGeometry().isSetSpiral()) {
            throw new IllegalArgumentException("SPIRAL geometry not yet supported. ");
        } else {
            throw new IllegalArgumentException("Unknown geometry: " + roadGeometry.getGeometry());
        }
        return roadMapping;
    }
}
