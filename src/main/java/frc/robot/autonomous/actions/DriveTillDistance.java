package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command moves the robot straight a certain distance
 * 
 * Author: Francisco Fabregat
 */
public class DriveTillDistance extends CommandBase {

    /* Initialize variables */
    double driveDistance;
    double startDistanceL;
    double startDistanceR;
    boolean setIntake;
    int counter = 0;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public DriveTillDistance(double distance ) {

        /* Require the necessary subsystems */
        addRequirements(Robot.driveTrain,Robot.intake);

        /* Determine distance to move in feet */
        driveDistance = distance;
        /* Determine whether to start the intake or not while moving */
    }
    double startTime = Timer.getFPGATimestamp();
    /*
     * Function runs only once when the command starts
     */
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
        /*
         * Reset encoder values
         */
        
        Robot.limelight.setVision();
        Robot.limelight.ledOn();
        Robot.driveTrain.resetDriveEncoders();

        //
        //if (!RobotMap.intakeDownSwitch.get()) {
        Robot.intake.lower();
        //}
        /* Determine the starting (current) distance for both sides */
        startDistanceR = Robot.driveTrain.getRightDistance();
        startDistanceL = Robot.driveTrain.getLeftDistance();

        /* Print debug information in console */
        System.out.println("Drive Distance: " + driveDistance);
        System.out.println("Starting Distance: " + Robot.driveTrain.getRightDistance());

        System.out.println("Tape Found: " + Robot.limelight.hasTarget());
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {

        /* Get distance moved since command started */
        double currentL = 0.0;
        double currentR = 0.0;

        
        currentL = Math.abs(startDistanceL - Robot.driveTrain.getLeftDistance());
        currentR = Math.abs(startDistanceR - Robot.driveTrain.getRightDistance());

        // compensate for potential motor inconsistencies. Will help with slight drift to 1 side
        double rSpeed = 0.4;
        double lSpeed = 0.4;
        /*if (currentL > currentR) {
            rSpeed *= (currentR/currentL);
        } else if (currentR > currentL) {
            lSpeed *= (currentL/currentR);
        }*/

        // SAFETY JUST INCASE MY MATH SUCKS
        if (rSpeed > 0.5 || lSpeed > 0.5) {
            rSpeed = 0.4;
            lSpeed = 0.4;
            System.out.println("VERY BAD AUTONOMOUS CODE");
        }

        ///
        /* Find an average of both distances moved to calculate an appropriate speed */
        // double averageDistance = (currentL + currentR) / 2;

        Robot.driveTrain.tankDrive(-lSpeed, -rSpeed);
        
        /* Print debug information in console */
        System.out.println("Current lime dist: " + Robot.limelight.getDistance());
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        return Robot.limelight.getDistance() <= driveDistance;
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.limelight.setDrive();
        Robot.limelight.ledOff();
        Robot.driveTrain.stopTank();
        Robot.driveTrain.resetDriveEncoders();
        Robot.intake.stopLift();
    }

    /*
     * Ends the command of autonomous is stopped or interrupted
     */
    protected void interrupted() {
        end();
    }

    /*
     * Calculates speed based on distance to target. Demo of this function can be
     * found here: https://www.desmos.com/calculator/mjxmn8nmug
     */
    private double getSpeed(double current, double total) {
        double speed = (-1 / ((-1 - ((total - 1) / 2)) * (-1 - ((total - 1) / 2))));
        speed = speed * (current - ((total - 1) / 2)) * (current - ((total - 1) / 2));
        speed = speed + 1;
        if (speed < Config.moveMinSpeed) {
            speed = Config.moveMinSpeed;
        }
        return speed;
    }
}