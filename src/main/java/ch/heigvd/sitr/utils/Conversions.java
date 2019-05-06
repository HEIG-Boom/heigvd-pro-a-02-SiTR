/*
 * Filename : Conversions.java
 * Creation date : 06.05.2019
 */

package ch.heigvd.sitr.utils;

/**
 * Utils class containing conversion methods
 *
 * @author Luc Wachter
 */
public class Conversions {
    /**
     * Convert meters per second to kilometers per hour
     *
     * @param mps The amount of m/s to convert
     * @return the corresponding amount of km/h
     */
    public static double mpsToKph(double mps) {
        // m/s => km/h : x * 3.6
        return mps * 3.6;
    }

    /**
     * Convert kilometers per hour to meters per second
     *
     * @param kph The amount of km/h to convert
     * @return the corresponding
     */
    public static double kphToMps(double kph) {
        // km/h => m/s : x / 3.6
        return kph / 3.6;
    }

    /**
     * Convert m to px
     *
     * @param scale the ratio px/m
     * @param m     the number of m
     * @return the number of px
     */
    public static int mToPx(double scale, double m) {
        return (int) Math.round(m * scale);
    }

    /**
     * Convert px to m
     *
     * @param scale the ratio px/m
     * @param px    the number of px
     * @return the number of px
     */
    public static double pxToM(double scale, int px) {
        return px / scale;
    }
}
