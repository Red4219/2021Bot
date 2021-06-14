package frc.robot;

/*
 * This class contains all variables that can be edited in order to further configure and calibrate the robot. 
 * 
 * Author: Francisco Fabregat
 */
public final class Config {

    /* Configuration for drivetrain speeds */
    public static double moveMinSpeed = 0.2;
    public static double turnMinSpeed = 0.2;
    public static double moveMaxSpeed = 0.7;
    public static double turnMaxSpeed = 0.95;
    public static double turnAccel = 0.075;
    public static double driveTargetAdjustSpeed = 0.2;
    public static double driveManualAdjustSpeed = 0.3;

    /* Configuration for intake */
    public static double intakeSpeed = 0.7;
    public static double intakeLiftSpeed = 0.2;
    public static double intakeDownSpeed = 0.1;

    /* Configuration for revolver */
    public static double revolverSpeed = 0.12;

    /* Configuration for shooter */
    public static double shooterSpeed = 1;
    public static double shootTurnTolerance = 1.0;
    public static double shootAlignSpeed = 0.2;
    public static double shootAlignTolerance = 10.0;

    /* Limelight variables */
    public static double limelightHeight = 20; //inches
    public static double targetHeight = 98.19; //inches
    public static double limelightAngle = 30.7; //Degrees

    /* Define robot width */
    public static double robotWidth = 0.0;
}