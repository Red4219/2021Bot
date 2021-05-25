package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

public class MoveIntake extends CommandBase {
    boolean moveIn = false;

    public MoveIntake(boolean in) {
        addRequirements(Robot.intake);

        moveIn = in;
    }

    public void execute() {
        System.out.println("Moving");
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
        System.out.println("End of command");
        Robot.intake.stop();
    }

    @Override
    public boolean isFinished() {
        if (moveIn) {
            return !OI.intakeButton.get();
        } else {
            return !OI.intakeButton.get();
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}