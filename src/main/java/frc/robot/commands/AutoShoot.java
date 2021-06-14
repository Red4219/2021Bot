package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command shoots automatically
 * 
 * Author: Francisco Fabregat
 */
public class AutoShoot extends CommandBase {

    /*
     * Declares public function AutoShoot
     */
    public AutoShoot() {
        addRequirements(Robot.driveTrain);
        addRequirements(Robot.intake);
        //addRequirements(Robot.revolver);
        addRequirements(Robot.shooter);
        addRequirements(Robot.shooterAlign);
    }

    /*
     * Function runs only once when the command starts
     */
    public void initialize() {
        Robot.limelight.setVision();
        Robot.limelight.ledOn();
        Robot.shooter.on();
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        /* Get distance moved since command started */
        double degreesOff = Robot.limelight.getTx();
        boolean tapeFound = Robot.limelight.hasTarget();

        if (!RobotMap.intakeDownSwitch.get()) {
            Robot.intake.lower();
            Robot.driveTrain.stopTank();
            //Robot.revolver.stop();
            Robot.shooterAlign.stop();
        } else {
            Robot.intake.stop();
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
                //
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
            } else {
                System.out.println("No Tape found");
                Robot.driveTrain.stopTank();
            }
        }
    }

     /*
     * Sets motors to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.driveTrain.stopTank();
        Robot.intake.stopLift();
        //Robot.revolver.stop();
        Robot.shooter.stop();
        Robot.shooterAlign.stop();
        Robot.limelight.setDrive();
        Robot.limelight.ledOff();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        return !OI.shootButton.get();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}