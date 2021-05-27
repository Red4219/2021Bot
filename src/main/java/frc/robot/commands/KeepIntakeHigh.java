package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command keeps the intake in its high position
 * 
 * Author: Francisco Fabregat
 */
public class KeepIntakeHigh extends CommandBase {

    /*
     * Declares public function KeepIntakeHigh
     */
    public KeepIntakeHigh() {
        addRequirements(Robot.intake);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        if (!RobotMap.intakeUpSwitch.get()) {
            Robot.intake.raise();
        } else {
            Robot.intake.stopLift();
        }
    }

     /*
     * Sets the intake to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.intake.stop();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        return !OI.keepIntakeHighButton.get();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}