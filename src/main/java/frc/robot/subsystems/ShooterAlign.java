package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Shooter Aligner subsystem where anything related to the shooter aligner is found
 * 
 * Author: Francisco Fabregat
 */
public class ShooterAlign extends SubsystemBase {
    
    /* Call revolverMotor defined in RobotMap */
    CANSparkMax shooterAlignMotor = RobotMap.shooterAlignMotor;
    
    /*
     * Make this class public
     */
    public ShooterAlign() {}

    /*
     * Get encoder position of shooter aligner
     */
    public double getPosition() {
        return RobotMap.shooterAlignEncoder.getPosition();
    }

    /*
     * Reset shooter aligner encoder position
     */
    public void reset() {
        RobotMap.shooterAlignEncoder.setPosition(0.0);
    }

    /*
     * Move shooter aligner up
     */
    public void moveUp() {
        shooterAlignMotor.set(Config.shootAlignSpeed);
    }

    /*
     * Move shooter aligner down
     */
    public void moveDown() {
        shooterAlignMotor.set(-Config.shootAlignSpeed);
    }

    /*
     * Stop shooter aligner
     */
    public void stop() {
        shooterAlignMotor.stopMotor();
    }

    /*
     * Calculate the posiiton of the shooter aligner based on distance to target
     */
    public double getTargetPosition(double distance) {
        return 0.0;
    }
}
