package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command moves the intake to both move balls into the robot or out of the intake
 * 
 * Author: Francisco Fabregat
 */
public class MoveIntake extends CommandBase {

    /* Initialize variables */
    boolean moveIn = false;

    /*
     * Declares public function MoveIntake with parameter of whether the intake will move the balls in
     */
    public MoveIntake(boolean in) {
        addRequirements(Robot.intake);

        moveIn = in;
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        if (moveIn) {
            Robot.intake.intake();
        } else {
            Robot.intake.moveOut();
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
        if (moveIn) {
            return !OI.intakeButton.get();
        } else {
            return !OI.reverseIntakeButton.get();
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}