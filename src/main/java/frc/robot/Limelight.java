package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/*
 * This class handles all NetworkTable actions that communicate with the Limelight
 * 
 * Author: Francisco Fabregat
 */
public class Limelight {

    /* Initialize NetworkTables instance */
    static NetworkTableInstance inst = NetworkTableInstance.getDefault();

    /* Fetch table 'limelight' from NetworkTables */
    NetworkTable table = inst.getTable("limelight");

    /* Define NetworkTable readable entries */
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    /* Define NetworkTable writable entries */
    NetworkTableEntry ledMode = table.getEntry("ledMode");
    NetworkTableEntry camMode = table.getEntry("camMode");
    NetworkTableEntry pipeline = table.getEntry("pipeline");

    /* Get whether Limelight has found a target */
    public boolean hasTarget() {
        double target = tv.getDouble(0.0);
        if (target == 1.0) {
            return true;
        } else {
            return false;
        }
    }

    /* Get the current x coordinate of target */
    public double getTx() {
        return tx.getDouble(0.0);
    }

    /* Get the current y coordinate of target */
    public double getTy() {
        return ty.getDouble(0.0);
    }

    /* Get the target area */
    public double getTa() {
        return ta.getDouble(0.0);
    }

    /* Turn off LED */
    public void ledOff() {
        ledMode.setNumber(1);
    }

    /* Turn on LED */
    public void ledOn() {
        ledMode.setNumber(3);
    }

    /* Blink LED */
    public void ledBlink() {
        ledMode.setNumber(2);
    }

    /* Set LED to pipeline mode */
    public void ledDefault() {
        ledMode.setNumber(0);
    }

    /* Set Limelight for driver mode */
    public void setDrive() {
        camMode.setNumber(1);
        ledOff();
    }

    /* Set Limelight for vision tracking mode */
    public void setVision() {
        camMode.setNumber(0);
        ledOn();
    }

    /* Get distance to target */
    public double getDistance() {
        return ((Config.targetHeight-Config.limelightHeight) / Math.tan(Math.toRadians(Config.limelightAngle)+Math.toRadians(getTy())));
    }


}