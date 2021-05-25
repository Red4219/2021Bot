package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

public class LiftIntake extends CommandBase {
    boolean moveUp = false;

    public LiftIntake(boolean up) {
        addRequirements(Robot.intake);

        moveUp = up;
    }

    public void execute() {
        System.out.println("Moving");
        if (moveUp) {
            Robot.intake.raise();
        } else {
            Robot.intake.lower();
        }
    }

     /*
     * Sets the intake to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        System.out.println("End of command");
        Robot.intake.stopLift();
    }

    @Override
    public boolean isFinished() {
        if (moveUp) {
            System.out.println("Moving Up: "+!OI.raiseIntakeButton.get());
            return !OI.raiseIntakeButton.get();
        } else {
            return !OI.lowerIntakeButton.get();
        }
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}