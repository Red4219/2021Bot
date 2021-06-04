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
        Robot.shooter.on();
    }

     /*
     * Sets the shooter to stop once the command is finished
     */
    @Override
    public void end(boolean interrupted) {
        Robot.shooter.stop();
        //Robot.limelight.setDrive();
        //Robot.limelight.ledOff();
    }

    /*
     * Determines when to end the command
     */
    @Override
    public boolean isFinished() {
        return !OI.shootButton.get();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end(true);
    }
    
}