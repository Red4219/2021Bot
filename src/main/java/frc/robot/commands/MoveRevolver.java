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
    public MoveRevolver() {
        addRequirements(Robot.revolver);

        //moveCw = cw;
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        double rotateAmount = OI.operator.getRawAxis(4);
        if (Math.abs(rotateAmount) > 0.1) {
            Robot.revolver.rotate(rotateAmount);
        } else if (OI.revolverCWButton.get()) {
            Robot.revolver.rotateCW();
            System.out.println("CW");
        } else if(OI.revolverCCWButton.get()) {
            System.out.println("CCW");
            Robot.revolver.rotateCCW();
        } else {
            Robot.revolver.stop(false);
        }
    }

    /*
     * Sets the revolver to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.revolver.stop(false);
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        return false;
        /*if (moveCw) {
            return !OI.revolverCWButton.get();
        } else {
            return !OI.revolverCCWButton.get();
        }*/
        
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
}
