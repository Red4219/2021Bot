package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Shooter subsystem where anything related to the shooter is found
 * 
 * Author: Francisco Fabregat
 */
public class Shooter extends SubsystemBase {
    
    /* Call shooterMotor defined in RobotMap */
    CANSparkMax shooterMotor = RobotMap.shooterMotor;

    /*
     * Make this class public
     */
    public Shooter() {}

    /*
     * Turn on shooter
     */
    public void on() {
        shooterMotor.set(Config.shooterSpeed);
    }

    /*
     * Stop shooter
     */
    public void stop() {
        shooterMotor.stopMotor();
    }
}
