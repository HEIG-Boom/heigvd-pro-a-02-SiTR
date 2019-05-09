/*
 * Filename: VehicleController.java
 * Creation date: 28.03.2019
 */

package ch.heigvd.sitr.vehicle;

import ch.heigvd.sitr.model.VehicleControllerType;
<<<<<<< HEAD
import com.sun.org.apache.xpath.internal.operations.Bool;
=======
import lombok.EqualsAndHashCode;
>>>>>>> Replace equals and hashcode with lombok
import lombok.Getter;
import lombok.Setter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;

import static java.lang.Math.sqrt;

/**
 * Vehicle Controller represents the vehicle controller
 * <p>
 * It's based on the Intelligent Driver Model (IDM).
 * <p>
 * For more information, see :
 * - the wikipedia article : https://en.wikipedia.org/wiki/Intelligent_driver_model
 * - Congested Traffic States in Empirical Observations and Microscopic Simulations : https://arxiv.org/pdf/cond-mat/0002177.pdf
 * - Concise explanation : http://www.mtreiber.de/trafficSimulationDe_html5_2016_06_29/IDM.html
 * - Traffic Flow Dynamics : Data, Models and Simulation, by Martin Treiber and Arne Kesting, chap 11
 *
 * @author Simon Walther
 */
@EqualsAndHashCode(of = {"desiredVelocity", "minimumSpacing", "desiredTimeHeadway",
        "maxAcceleration", "comfortableBrakingDeceleration"})
public class VehicleController {
    // Delta exponent, traditionally set at 4
    private static final double delta = 4;

    // Desired velocity (v0) of the vehicle controller [m/s]
    @Getter
    @Setter
    private double desiredVelocity;

    // Minimum spacing (s0) of the vehicle controller [m]
    @Getter
    @Setter
    private double minimumSpacing;

    // Desired time headway (T) of the vehicle controller [s]
    @Getter
    @Setter
    private double desiredTimeHeadway;

    // Max acceleration (a) of the vehicle controller [m/s^2]
    @Getter
    @Setter
    private double maxAcceleration;

    // Comfortable braking deceleration (b) of the vehicle controller [m/s^2]
    @Getter
    @Setter
    private double comfortableBrakingDeceleration;

    // If this vehicle is human driven
    @Getter
    @Setter
    private boolean humanDriven;

    // The type of controller represented by this controller
    @Getter
    private VehicleControllerType controllerType;

    public VehicleController(double desiredVelocity, double minimumSpacing, double desiredTimeHeadway,
                             double maxAcceleration, double comfortableBrakingDeceleration, boolean humanDriven) {
        this.desiredVelocity = desiredVelocity;
        this.minimumSpacing = minimumSpacing;
        this.desiredTimeHeadway = desiredTimeHeadway;
        this.maxAcceleration = maxAcceleration;
        this.comfortableBrakingDeceleration = comfortableBrakingDeceleration;
        this.humanDriven = humanDriven;
    }

    /**
     * Create vehicle controller with configuration taken from an XML configuration file
     *
     * @param vct The type of controller we want to create
     */
    public VehicleController(VehicleControllerType vct) {
        // Get the controller's config file
        InputStream in = VehicleController.class.getResourceAsStream(vct.getConfigPath());
        SAXBuilder saxBuilder = new SAXBuilder();

        controllerType = vct;

        try {
            Document document = saxBuilder.build(in);
            Element root = document.getRootElement();

            desiredVelocity = Double.parseDouble(root.getChildText("desiredVelocity"));
            minimumSpacing = Double.parseDouble(root.getChildText("minimumSpacing"));
            desiredTimeHeadway = Double.parseDouble(root.getChildText("desiredTimeHeadway"));
            maxAcceleration = Double.parseDouble(root.getChildText("maxAcceleration"));
            comfortableBrakingDeceleration = Double.parseDouble(root.getChildText("comfortableBrakingDeceleration"));
            humanDriven = Boolean.parseBoolean(root.getChildText("humanDriven"));
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }

    /**
     * Calculate the safe distance
     * s0 + v*T
     * <p>
     * Variables :
     * s0 (minimum spacing) [m]
     * v (speed) [m/s]
     * T (desired time headway) [s]
     *
     * @param vehicle Vehicle for which to calculate safe distance
     * @return the safe distance [m]
     */
    public double safeDistance(Vehicle vehicle) {
        return minimumSpacing + vehicle.getSpeed() * desiredTimeHeadway;
    }

    /**
     * Calculate the desired dynamical distance s*
     * s*(v, deltaV) = s0 + max(0, (v*T + (v * deltaV)/(2*sqrt(a * b)))
     * <p>
     * Variables :
     * s0 (minimumSpacing) [m]
     * v (speed) [m/s]
     * T (desired time headway) [s]
     * deltaV (relative speed) [m/s]
     * a (max acceleration) [m/s^2]
     * b (comfortable braking deceleration) [m/s^2]
     * <p>
     * Note : s0 + v*T is the safe distance
     *
     * @param vehicle Vehicle for which to calculate the desired dynamical distance
     * @return the desired dynamical distance [m]
     */
    public double desiredDynamicalDistance(Vehicle vehicle) {
        double dynamicalTerm = (vehicle.getSpeed() * vehicle.relSpeed()) /
                (2 * sqrt(maxAcceleration * comfortableBrakingDeceleration));

        if (safeDistance(vehicle) - minimumSpacing + dynamicalTerm < 0) {
            return minimumSpacing;
        }

        return safeDistance(vehicle) + dynamicalTerm;
    }

    /**
     * Calculate the desired acceleration
     * a * [1 - (v / v0) ^ delta]
     * <p>
     * Variables :
     * a (max acceleration) [m/s^2]
     * v (speed) [m/s]
     * v0 (desired velocity) [m/s]
     *
     * @param vehicle Vehicle for which to calculate the desired acceleration
     * @return the desired acceleration
     */
    public double desiredAcceleration(Vehicle vehicle) {
        return maxAcceleration * (1 - Math.pow(vehicle.getSpeed() / desiredVelocity, delta));
    }

    /**
     * Calculate the acceleration
     * a * [1 - (v / v0)^delta - (s*(v, deltaV) / s)^2]
     * <p>
     * Variables :
     * a (max acceleration) [m/s^2]
     * v (speed) [m/s]
     * v0 (desired velocity) [m/s]
     * deltaV (relative speed) [m/s]
     * s (front distance) [m]
     * delta
     *
     * @param vehicle Vehicle for which to calculate the acceleration
     * @return the acceleration
     */
    public double acceleration(Vehicle vehicle) {
        return desiredAcceleration(vehicle) - maxAcceleration *
                Math.pow((desiredDynamicalDistance(vehicle) / vehicle.frontDistance()), 2);
    }
}
