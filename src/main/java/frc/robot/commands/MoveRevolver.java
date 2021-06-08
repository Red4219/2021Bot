package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command moves the revolver clockwise and counterclockwise
 * 
 * Author: Francisco Fabregat
 */
public class MoveRevolver extends CommandBase {
    
    /* Initialize variables */
    boolean moveCw = false;

    /*
     * Declares public function MoveRevolver with parameter of whether the revolver will move clockwise
     */
    public MoveRevolver(boolean cw) {
        addRequirements(Robot.revolver);

        moveCw = cw;
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        if (moveCw) {
            Robot.revolver.rotateCW();
            System.out.println("CW");
        } else {
            System.out.println("CCW");
            Robot.revolver.rotateCCW();
        }
    }

    /*
     * Sets the revolver to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.revolver.stop();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        if (moveCw) {
            return !OI.revolverCWButton.get();
        } else {
            return !OI.revolverCCWButton.get();
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
}
