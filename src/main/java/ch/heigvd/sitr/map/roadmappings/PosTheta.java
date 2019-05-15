package ch.heigvd.sitr.map.roadmappings;

import lombok.Getter;

/**
 * This class represents a position in space plus a direction
 */
public class PosTheta {
    @Getter
    protected double x;         // x-coordinate
    @Getter
    protected double y;         // y-coordinate
    protected double cosTheta;  // cosine of angle
    protected double sinTheta;  // sine of angle

    /**
     * Get the angle, in radians, measured counter-clockwise from x-axis.
     *
     * @return Angle, in radians, measure counter-clockwise from x-axis
     */
    public double getTheta() {
        return Math.atan2(sinTheta, cosTheta);
    }

    @Override
    public String toString() {
        return "PosTheta [x=" + String.format("%.1f", x) + ", y=" + String.format("%.1f", y) +
                ", cosTheta=" + String.format("%.1f", cosTheta) + ", sinTheta=" +
                String.format("%.1f", sinTheta) + "]";
    }
}
