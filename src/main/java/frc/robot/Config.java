package frc.robot;

/*
 * This class contains all variables that can be edited in order to further configure and calibrate the robot. 
 * 
 * Author: Francisco Fabregat
 */
public class Config {

    /* Configuration for drivetrain speeds */
    public static double moveMinSpeed = 0.2;
    public static double turnMinSpeed = 0.2;
    public static double moveMaxSpeed = 1.0;
    public static double turnMaxSpeed = 0.95;
    public static double turnAccel = 0.075;
    public static double driveTargetAdjustSpeed = 0.3;
    public static double driveManualAdjustSpeed = 0.3;

    /* Limelight variables */
    public static double limelightHeight = 19.875; //inches
    public static double targetHeight = 98.19; //inches
    public static double limelightAngle = 24.0; //Degrees

    /* Define robot width */
    public static double robotWidth = 0.0;
}