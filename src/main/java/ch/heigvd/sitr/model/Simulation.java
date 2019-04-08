/*
 * Filename : Simulation.java
 * Creation date : 07.04.2019
 */

package ch.heigvd.sitr.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Simulation class handles all global simulation settings and values
 *
 * @author Luc Wachter, Simon Walther
 */
public class Simulation {
    // the scale of m/px
    @Getter
    @Setter
    public double scale;

    public Simulation(double scale) {
        this.scale = scale;
    }

    public static double mpsToKph(double mps) {
        // m/s => km/h : x * 3.6
        return mps * 3.6;
    }

    public static double kphToMps(double kph) {
        // km/h => m/s : x / 3.6
        return kph / 3.6;
    }

    public double mToPx(double m) {
        return scale * m;
    }
}
