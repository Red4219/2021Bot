package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

public class MoveRevolver extends CommandBase {
    
    boolean moveCw = false;

    public MoveRevolver(boolean cw) {
        addRequirements(Robot.revolver);

        moveCw = cw;
    }

    public void execute() {
        if (moveCw) {
            Robot.revolver.rotateCW();
        } else {
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

    @Override
    public boolean isFinished() {
        if (moveCw) {
            return !OI.revolverButton.get();
        } else {
            return !OI.revolverButton.get();
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
}
