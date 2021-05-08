package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Drive subsystem where anything related to drive is found
 * 
 * Author: Francisco Fabregat
 */
public class Drive extends SubsystemBase {

    /* Call DifferentialDrive defined in RobotMap */
    DifferentialDrive robotDrive = RobotMap.robotDrive;

    /* Make this class public */
    public Drive() {}

    /*
     * Arcade drive is used for teleop (manual driving)
     */
    public void arcadeDrive(double moveAxis, double rotateAxis) {

        /* Sets arcadeDrive values */
        robotDrive.arcadeDrive(moveAxis, -rotateAxis);
    }

    /*
     * Tank drive is used for autonomous
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        /* Sets tankDrive values */
        robotDrive.tankDrive(leftSpeed, rightSpeed);
    }

    /*
     * Adjusts robot to turn to the left for target
     */
    public void adjustTargetLeft() {
        /* Sets tankDrive values */
        robotDrive.tankDrive(Config.driveTargetAdjustSpeed, Config.driveTargetAdjustSpeed* -1);
    }

    /*
     * Adjusts robot to turn to the right for target
     */
    public void adjustTargetRight() {
        /* Sets tankDrive values */
        robotDrive.tankDrive(Config.driveTargetAdjustSpeed* -1, Config.driveTargetAdjustSpeed);
    }

    /* Spins robot to the left for manual adjustment */
    public void spinAdjustLeft() {
        robotDrive.tankDrive(Config.driveManualAdjustSpeed, Config.driveManualAdjustSpeed * -1);
    }

    /* Spins robot to the right for manual adjustment */
    public void spinAdjustRight() {
        robotDrive.tankDrive(Config.driveManualAdjustSpeed * -1, Config.driveManualAdjustSpeed);
    }

    /*
     * Return distance in feet recorded by left drivetrain encoder
     */
    public double getLeftDistance() {
        return (RobotMap.leftDriveEncoder.getPosition() * 2.61)/12;
    }

    /*
     * Return distance in feet recorded by right drivetrain encoder
     */
    public double getRightDistance() {
        return (RobotMap.rightDriveEncoder.getPosition() * 2.61)/12;
    }

    /*
     * Resets encoder distances
     */
    public void resetDriveEncoders() {
        RobotMap.leftDriveEncoder.setPosition(0.0);
        RobotMap.rightDriveEncoder.setPosition(0.0);
    }

    /*
     * Stops tank drivetrain by setting speeds to 0
     */
    public void stopTank() {
        robotDrive.arcadeDrive(0.0, 0.0);
        robotDrive.tankDrive(0.0, 0.0);
    }

}