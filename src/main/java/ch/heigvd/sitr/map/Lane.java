package ch.heigvd.sitr.map;

/**
 * This class represents a Lane on the road
 */
public final class Lane {
    // from inner to outer lanes
    public static final int LANE1 = 1;
    public static final int LANE2 = 2;
    public static final int LANE3 = 3;
    public static final int LANE4 = 4;
    public static final int LANE5 = 5;
    public static final int HARD_SHOULDER = -1; // NOT DRIVABLE
    public static final int NONE = -2;          // NOT DRIVABLE

    public static final int TO_LEFT = -1;
    public static final int TO_RIGHT = 1;
    public static final int NO_CHANGE = 0;

    public static final int MOST_INNER_LANE = LANE1;

    /**
     * Private constructor to avoid instantiation
     */
    private Lane() {
    }

    /**
     * Mapping of OpenDRIVE laneIndex types.
     */
    public enum Type {
        // Lanes for normal traffice
        TRAFFIC("driving"),

        // Entrance (acceleration) laneIndex
        ENTRANCE("mwyEntry"),

        // Exit (deceleration) laneIndex
        EXIT("mwyExit"),

        // Shoulder laneIndex
        SHOULDER("shoulder"),

        // Restriced laneIndex (bus or multiple-occupancy vehicle laneIndex)
        RESTRICTED("restricted"),

        // Bicycle laneIndex
        BICYCLE("biking");

        private final String openDriveIdentifier;

        /**
         * Constructor
         *
         * @param keyword The OpenDRIVE identifier
         */
        Type(String keyword) {
            this.openDriveIdentifier = keyword;
        }

        /**
         * Get the OpenDRIVE identifier
         *
         * @return The OpenDRIVE identifier
         */
        public String getOpenDriveIdentifier() {
            return openDriveIdentifier;
        }
    }

    /**
     * This enum represents the type of a lane section
     */
    public enum LaneSectionType {
        LEFT("-", true),
        RIGHT("+", false);

        private final String idAppender;
        private final boolean reverseDirection;

        LaneSectionType(String idAppender, boolean reverseDirection) {
            this.idAppender = idAppender;
            this.reverseDirection = reverseDirection;
        }

        public String idAppender() {
            return idAppender;
        }

        public boolean isReverseDirection() {
            return reverseDirection;
        }
    }
}
