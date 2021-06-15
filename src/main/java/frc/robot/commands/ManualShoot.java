package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command moves the shooter
 * 
 * Author: Francisco Fabregat
 */
public class ManualShoot extends CommandBase {

    public boolean isActive = false;
    boolean debounce = false;
    /*
     * Declares public function ManualShoot
     */
    public ManualShoot() {
        addRequirements(Robot.shooter);
    }

    public void initialize() {
        Robot.limelight.setVision();
        Robot.limelight.ledOn();
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    public void execute() {
        if (debounce == false) {
            debounce = true;
            if (Robot.shooter.isActive == true) {
                Robot.shooter.isActive = false;
                Robot.shooter.on();
            } else {
                Robot.shooter.isActive = true;
                Robot.shooter.stop();
            }
        }    
    }

     /*
     * Sets the shooter to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Robot.shooter.stop();
        }
        //Robot.limelight.setDrive();
        //Robot.limelight.ledOff();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        //return false; 
        debounce = false; // move this to make toggle
        return !OI.manualShootButton.get();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}