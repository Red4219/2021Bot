package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command centers the robot with the target
 * 
 * Author: Francisco Fabregat
 */
public class AutoAlign extends CommandBase {

    /* Initialize variables */
    boolean tapeFound;
    double degreesOff;

    /*
     * Declares public function AutoAlign
     */
    public AutoAlign() {
        /* Require the Drive subsystem */
        addRequirements(Robot.driveTrain);
    }

    /*
     * Function runs only once when the command starts
     */
    public void initialize() {
        Robot.limelight.setVision();
        Robot.limelight.ledOn();
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        /* Get distance moved since command started */
        degreesOff = Robot.limelight.getTx();
        tapeFound = Robot.limelight.hasTarget();

        if (tapeFound) {
            if (Math.abs(degreesOff) > Config.shootTurnTolerance) {
                if (degreesOff > 0) {
                    Robot.driveTrain.adjustTargetRight();
                } else {
                    Robot.driveTrain.adjustTargetLeft();
                }
            } else {
                Robot.driveTrain.stopTank();
            }
        } else {
            System.out.println("No Tape found");
            Robot.driveTrain.stopTank();
        }
        
        /* Print debug information in console */
        System.out.println("Degrees off: " + degreesOff);
    }

    /*
     * Sets the drivetrain to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.driveTrain.stopTank();
        Robot.limelight.setDrive();
        Robot.limelight.ledOff();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        if (!OI.autoAlignButton.get()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}
