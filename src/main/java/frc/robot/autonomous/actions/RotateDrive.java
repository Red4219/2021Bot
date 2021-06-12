package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Config;
import frc.robot.Robot;

/*
 * This command moves the robot straight a certain distance
 * 
 * Author: Harrison Lewis
 */
public class RotateDrive extends CommandBase {

    /* Initialize variables */
    boolean clockwise;
    double driveDistance;
    double startDistanceL;
    double startDistanceR;
    double endDistanceL;
    double endDistanceR;
    int counter = 0;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public RotateDrive(boolean clockw, double degrees) {

        /* Require the necessary subsystems */
        addRequirements(Robot.driveTrain);

        /* Sets wether the movement is forward or not */
        clockwise = clockw;

        /* Determine distance to move in feet */ // 2πr (θ/360)
        driveDistance = (2*Math.PI*14*(degrees/360))/12; //Arc length

        /* Determine whether to start the intake or not while moving */
    }

    /*
     * Function runs only once when the command starts
     */
    public void initialize() {

        /*
         * Reset encoder values
         */
        Robot.driveTrain.resetDriveEncoders();

        if (clockwise) {
            /*
             * Set end distance for both sides by adding the distance to move to the current
             * distance
             */
            endDistanceL = Robot.driveTrain.getLeftDistance() + driveDistance;
            endDistanceR = Robot.driveTrain.getRightDistance() + driveDistance;
        } else {
            /*
             * Set end distance for both sides by subtracting the distance to move to the
             * current distance
             */
            endDistanceL = Robot.driveTrain.getLeftDistance() - driveDistance; //TODO: MAYBE change signs?
            endDistanceR = Robot.driveTrain.getRightDistance() - driveDistance;
        }

        /* Determine the starting (current) distance for both sides */
        startDistanceR = Robot.driveTrain.getRightDistance();
        startDistanceL = Robot.driveTrain.getLeftDistance();

        /* Print debug information in console */
        System.out.println("Drive Distance: " + driveDistance);
        System.out.println("Starting Distance: " + Robot.driveTrain.getRightDistance());
        System.out.println("Left End Distance: " + endDistanceL);
        System.out.println("Right End Distance: " + endDistanceR);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {

        /* Get distance moved since command started */
        double currentL = 0.0;
        double currentR = 0.0;

        if (clockwise) {
            currentL = startDistanceL - Robot.driveTrain.getLeftDistance();
            currentR = Robot.driveTrain.getRightDistance() - startDistanceR;
        } else {
            currentL = Robot.driveTrain.getLeftDistance() - startDistanceL;
            currentR = startDistanceR - Robot.driveTrain.getRightDistance();
        }
        /* Find an average of both distances moved to calculate an appropriate speed */
        // double averageDistance = (currentL + currentR) / 2;

        /* Set a tank drive movement with speed returning from getSpeed() */
        if (clockwise) {
            // Robot.driveTrain.tankDrive(getSpeed(currentL, driveDistance) * -1,
            // getSpeed(currentL, driveDistance) * -1);
            Robot.driveTrain.tankDrive(-0.4, 0.4);
        } else {
            // Robot.driveTrain.tankDrive(getSpeed(currentL, driveDistance),
            // getSpeed(currentL, driveDistance));
            Robot.driveTrain.tankDrive(-0.4, 0.4);
        }
        /* Print debug information in console */
        System.out.println("Distance: " + Robot.driveTrain.getRightDistance());
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        if (clockwise) {
            /* Command ends if current distance is greater than the end distance */
            return (Robot.driveTrain.getRightDistance() >= endDistanceR);
        } else {
            /*
             * Command ends if current distance is less than the end distance, as it is
             * moving back
             */
            System.out.println("****" + Robot.driveTrain.getLeftDistance() + " " + endDistanceL);
            return (Robot.driveTrain.getRightDistance() <= endDistanceR);
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.driveTrain.stopTank();
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