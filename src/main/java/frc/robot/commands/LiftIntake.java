package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
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
        //System.out.println("execute called");
        //System.out.println("upswitch: " + RobotMap.intakeUpSwitch.get());
        //System.out.println("downswitch: " + RobotMap.intakeDownSwitch.get());
        if (moveUp) {
            //System.out.println("raise moveUp:" + moveUp);
            //System.out.println("upswitch: " + RobotMap.intakeUpSwitch.get());
            //if (!RobotMap.intakeUpSwitch.get()) {
                Robot.intake.raise();
            //}
        } else {
            //System.out.println("downswitch: " + RobotMap.intakeDownSwitch.get());
        //    if (!RobotMap.intakeDownSwitch.get()) {
                //System.out.println("lower moveUp:" + moveUp);
                Robot.intake.lower();
          //  }
        }
    }

    /*
     * Sets the intake to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Robot.intake.stopLift();
        }
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        return true;
        /*if (moveUp) {
            return !OI.raiseIntakeButton.get();
            //return RobotMap.intakeUpSwitch.get();
        } else {
            return !OI.lowerIntakeButton.get();
            //return RobotMap.intakeDownSwitch.get();
        }*/
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}