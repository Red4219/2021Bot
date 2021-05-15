package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Intake subsystem where anything related to the intake is found
 * 
 * Author: Francisco Fabregat
 */
public class Intake extends SubsystemBase {

    /* Call intakeMotor defined in RobotMap */
    CANSparkMax intakeMotor = RobotMap.intakeMotor;

    /* Call intakeSolenoid defined in RobotMap */
    //Solenoid intakeSolenoid = RobotMap.intakeSolenoid;

    /*
     * Make this class public
     */
    public Intake() {}

    /*
     * Lower intake
     */
    public void lower() {
        //intakeSolenoid.set(false);
    }

    /*
     * Raise intake
     */
    public void raise() {
        //intakeSolenoid.set(true);
    }

    /*
     * Intakes the ball
     */
    public void intake() {
        intakeMotor.set(Config.intakeSpeed);
    }

    /*
     * Stops the intake
     */
    public void stop() {
        intakeMotor.stopMotor();
    }

    /*
     * Spits the ball out of the robot
     */
    public void moveOut() {
        intakeMotor.set(Config.intakeSpeed * -1);
    }
}
