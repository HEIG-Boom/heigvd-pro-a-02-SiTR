/*
 * Filename : AccelerationNoise.java
 * Creation date : 08.05.2019
 */

package ch.heigvd.sitr.utils;

import lombok.Getter;
import org.apache.commons.math3.distribution.UniformRealDistribution;

/**
 * Provides noise functions
 *
 * See : Understanding widely scattered traffic flows,
 *       the capacity drop, platoons, and times-to-collision as effects of variance-driven time gaps
 *       http://arxiv.org/abs/physics/0508222 by M. Treiber, A. Kesting, D. Helbing
 *
 * @author Simon Walther
 */
public class AccelerationNoise {
    static private final double TAU_RELAXATION_TIME = 0.4; // tau relaxation time in [s]
    static private final double FLUCTUATION_STRENGTH = 0.1;
    static private UniformRealDistribution uniformRealDistribution = new UniformRealDistribution();

    @Getter
    public double accelerationNoise = 0;

    /**
     * Use Wiener process to determines the new acceleration white noise
     *
     * Note : computation based on Movsim update in Noise class
     *
     * @param deltaT the time difference [s]
     */
    public void updateAccelerationWhiteNoise(double deltaT) {
        accelerationNoise *= Math.exp(-deltaT / TAU_RELAXATION_TIME);
        accelerationNoise += FLUCTUATION_STRENGTH * Math.sqrt(2 * deltaT / TAU_RELAXATION_TIME) * uniformRealDistribution.sample();
    }
}
