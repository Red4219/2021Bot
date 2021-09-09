package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command runs indefinetely, and controls the Drive subsystem from user input (joystick)
 */
public class TankDrive extends CommandBase {

    double currentTurnSpeed = 0;

    /*
     * State the required driveTrain subsystem
     */
    public TankDrive() {
        addRequirements(Robot.driveTrain);
    }

    /*
     * This function is what is done when the command is run
     */
    public void execute() {

        /* Define joystick axis */
        double leftAxis = OI.driver.getRawAxis(1);
        double rightAxis = OI.driver.getRawAxis(5);

        // Define Trigger
        double linearForwardMovement = OI.driver.getRawAxis(3) * 1; // Right trigger 
        double linearBackwardMovement = OI.driver.getRawAxis(2) * 0.6; // Left trigger 
        //
        double leftSpeed = leftAxis;
        double rightSpeed = rightAxis;

        /* Set Dead Zones */
        if (Math.abs(leftSpeed) <= Config.moveMinSpeed) {
            leftSpeed = 0;
        }
        if (Math.abs(rightSpeed) <= Config.moveMinSpeed) {
            rightSpeed = 0;
        }


        // Linear Drive
        if (linearForwardMovement > 0.04) {
            rightSpeed = -linearForwardMovement;
            leftSpeed = -linearForwardMovement;
        } else if (linearBackwardMovement > 0.04) {
            rightSpeed = linearBackwardMovement;
            leftSpeed = linearBackwardMovement;
        }

        /* Set left maximum speed */
        if (Math.abs(leftSpeed) >= Config.moveMaxSpeed) {
            leftSpeed = Config.moveMaxSpeed * Math.signum(leftSpeed);
            /*if (leftSpeed > 0) {
                leftSpeed = Config.moveMaxSpeed;
            } else {
                leftSpeed = Config.moveMaxSpeed * -1;
            }*/
        }

        /* Set right maximum speed */
        if (Math.abs(rightSpeed) >= Config.moveMaxSpeed) {
            rightSpeed = Config.moveMaxSpeed * Math.signum(rightSpeed);
            /*if (rightSpeed > 0) {
                rightSpeed = Config.moveMaxSpeed;
            } else {
                rightSpeed = Config.moveMaxSpeed * -1;
            }*/
        }

        if (leftSpeed == 0.0 && rightSpeed == 0.0) {
            /*
             * if (OI.robotSpinLeftButton.get() || OI.driver.getRawButton(3)) {
             * Robot.driveTrain.spinAdjustLeft(); } else if (OI.robotSpinRightButton.get()
             * || OI.driver.getRawButton(2)) { Robot.driveTrain.spinAdjustRight(); } else {
             */
            /* Sets the tankDrive to the 2 drive axis */
            Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
            // }
        } else {
            /* Sets the tankDrive to the 2 drive axis */
            Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
        }

    }

    /*
     * Makes command run forever
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}