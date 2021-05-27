package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command lowers and rises the intake
 * 
 * Author: Francisco Fabregat
 */
public class LiftIntake extends CommandBase {

    /* Initialize variables */
    boolean moveUp = false;

    /*
     * Declares public function LiftIntake with parameter of whether the intake will move up
     */
    public LiftIntake(boolean up) {
        addRequirements(Robot.intake);

        moveUp = up;
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        if (moveUp) {
            if (!RobotMap.intakeUpSwitch.get()) {
                Robot.intake.raise();
            }
        } else {
            if (!RobotMap.intakeDownSwitch.get()) {
                Robot.intake.lower();
            }
        }
    }

    /*
     * Sets the intake to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.intake.stopLift();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        if (moveUp) {
            //return !OI.raiseIntakeButton.get();
            return RobotMap.intakeUpSwitch.get();
        } else {
            //return !OI.lowerIntakeButton.get();
            return RobotMap.intakeDownSwitch.get();
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}