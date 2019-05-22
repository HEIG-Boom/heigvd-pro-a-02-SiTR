/*
 * Filename : LaneGeometries.java
 * Creation date : 22.04.2019
 */


package ch.heigvd.sitr.map.roadmappings;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents all lane geometries
 */
public class LaneGeometries {
    @Getter
    @Setter
    private LaneGeometry left;

    @Getter
    @Setter
    private LaneGeometry right;

    /**
     * Constructor
     */
    public LaneGeometries() {
        this.left = new LaneGeometry();
        this.right = new LaneGeometry();
    }

    /**
     * Get the total number of lanes
     * @return The total number of lanes
     */
    public int getTotalLaneCount() {
        return left.getLaneCount() + right.getLaneCount();
    }

    /**
     * Get the lane's width. We consider only right lane's width
     */
    public double getLaneWidth() {
        return right.getLaneWidth();
    }

    /**
     * This class represents a lane geometry
     */
    public static class LaneGeometry {
        private static final double DEFAULT_LANE_WIDTH = 5;
        @Getter
        private final int laneCount;    // The number of lanes
        @Getter
        private final double laneWidth; // The lane's width

        /**
         * Constructor
         */
        public LaneGeometry() {
            this.laneCount = 0;
            this.laneWidth = DEFAULT_LANE_WIDTH;
        }

        /**
         * Constructor
         *
         * @param laneCount The number of lanes
         * @param laneWidth Lane's width
         */
        public LaneGeometry(int laneCount, double laneWidth) {
            this.laneCount = laneCount;
            this.laneWidth = laneWidth;
        }

        @Override
        public String toString() {
            return "LaneGeometry [laneCount=" + laneCount + ", laneWidth=" + laneWidth + "]";
        }
    }

    @Override
    public String toString() {
        return "LaneGeometries [left=" + left + ", right=" + right + "]";
    }
}
