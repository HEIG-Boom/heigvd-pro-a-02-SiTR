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

    public static final RoadMapping create(Iterable<RoadGeometry> roadGeometries) {
        if (Iterables.size(roadGeometries) == 1) {
            return create(Iterables.getOnlyElement(roadGeometries));
        }

        // TODO (tum) complete with RoadMappingPoly
        return create(Iterables.getOnlyElement(roadGeometries));
    }

    /**
     * Factory for creating the road mapping
     */
    private static final RoadMapping create(RoadGeometry roadGeometry) {
        RoadMapping roadMapping;
        if (roadGeometry.getGeometry().isSetLine()) {
            roadMapping = RoadMappingLine.create(roadGeometry);
        } else if (roadGeometry.getGeometry().isSetArc()) {
            throw new IllegalArgumentException("ARC geometry not yet supported.");
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
