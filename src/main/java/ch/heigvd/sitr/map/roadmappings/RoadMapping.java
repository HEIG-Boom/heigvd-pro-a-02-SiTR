package ch.heigvd.sitr.map.roadmappings;

import java.awt.*;

/**
 * This class represents a road mapping that maps a logical road position (given by a lane and
 * a position on a road segment) onto a physical position with x,y coordinate
 */
public abstract class RoadMapping {
    // Road properties
    protected double roadLength;
    protected Color roadColor;
    protected static Color defaultRoadColor = new Color(129, 128, 128);

    protected RoadMapping()
}
