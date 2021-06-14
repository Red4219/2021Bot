package frc.robot.autonomous.actions;

import java.sql.Time;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command moves the robot straight a certain distance
 * 
 * Author: Harrison Lewis
 */
public class Shoot extends CommandBase {

    /* Initialize variables */
    boolean forwardMovement;
    double driveDistance;
    double startDistance;
    double startTime;
    double endDistance;
    boolean setIntake;
    int counter = 0;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public Shoot() { // Dunno what the gear ratio is so once I find out i'll add a "ballCount"

        /* Require the necessary subsystems */
        addRequirements(Robot.revolver,Robot.shooter,Robot.intake,Robot.shooterAlign);

        Robot.limelight.setVision();
        Robot.limelight.ledOn();
    }

    /*
     * Function runs only once when the command starts
     */
    public void initialize() {

        startTime = Timer.getFPGATimestamp();
        /*
         * Reset encoder values
         */
        Robot.revolver.resetEncoder();
        if (!RobotMap.intakeDownSwitch.get()) {
            Robot.intake.lower();
        }
        //Robot.shooter.on();
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        if (Timer.getFPGATimestamp() - startTime > 6) {
            Robot.shooterAlign.stop();
            Robot.shooter.on();
            Robot.revolver.rotate(-0.8);
        } else if (Timer.getFPGATimestamp() - startTime > 4) {
            Robot.driveTrain.stopTank();
            double alignTarget = Robot.shooterAlign.getTargetPosition(Robot.limelight.getDistance());
            if (Math.abs(Robot.shooterAlign.getPosition() - alignTarget) > Config.shootAlignTolerance) {
                //Robot.revolver.stop();

                if (Robot.shooterAlign.getPosition() > alignTarget) {
                    Robot.shooterAlign.moveDown();
                } else {
                    Robot.shooterAlign.moveUp();
                }
            } else {
                Robot.shooterAlign.stop();
                //Robot.revolver.rotateCW();
                //Robot.revolver.rotateCW();
            }
        } else if (Timer.getFPGATimestamp() - startTime > 1) {
            double degreesOff = Robot.limelight.getTx();
            if (Math.abs(degreesOff) > Config.shootTurnTolerance) {
                if (degreesOff > 0) {
                    Robot.driveTrain.adjustTargetRight();
                } else {
                    Robot.driveTrain.adjustTargetLeft();
                }
            } else {
                Robot.driveTrain.stopTank();
            }
        }
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        System.out.println("CURRENT TIME: " + (Timer.getFPGATimestamp() - startTime));
        if (Timer.getFPGATimestamp() - startTime > 14) {
            Robot.shooter.stop();
            return true;
        }
        return false;
    }

    /*
     * Stops motors when command ends
     */
    protected void end() {
        Robot.shooter.stop(); // this one no work???
        Robot.limelight.setDrive();
        Robot.limelight.ledOff();
        Robot.driveTrain.stopTank();
        Robot.shooterAlign.stop();
        Robot.revolver.stop(false); 
    }

    /*
     * Ends the command of autonomous is stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}