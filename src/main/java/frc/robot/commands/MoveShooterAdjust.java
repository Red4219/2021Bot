package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command moves the shooter adjuster
 * 
 * Author: Francisco Fabregat
 */
public class MoveShooterAdjust extends CommandBase {

    /* Initialize variables */
    boolean moveUp = false;

    /*
     * Declares public function MoveShooterAdjust with parameter of whether to move the adjuster up or down
     */
    public MoveShooterAdjust(boolean up) {
        addRequirements(Robot.shooterAlign);

        moveUp = up;
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        double forwardMove = OI.operator.getRawAxis(3); // Right trigger 
        double backwardMove = OI.operator.getRawAxis(2); // Left trigger 
        //double moveSpeed = OI.driver.getRawAxis(5); // right joystick 
        if (forwardMove > 0.2) {
            Robot.shooterAlign.setMotor(forwardMove);
        } else if (backwardMove > 0.2)  {
            Robot.shooterAlign.setMotor(-backwardMove);
        } else {
            Robot.shooterAlign.setMotor(0.0);
        }
    }

     /*
     * Sets the adjuster to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.shooterAlign.stop();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        /*if (moveUp) {
            return !OI.shooterAdjustUpButton.get();
        } else {
            return !OI.shooterAdjustDownButton.get();
        }*/
        return false
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}