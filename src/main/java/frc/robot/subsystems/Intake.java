package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

//import org.graalvm.compiler.lir.phases.PostAllocationOptimizationPhase.PostAllocationOptimizationContext;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the Intake subsystem where anything related to the intake is found
 * 
 * Author: Harrison Lewis
 */
public class Intake extends SubsystemBase {

    /* Call intakeMotor defined in RobotMap */
    CANSparkMax intakeMotor = RobotMap.intakeMotor;
    

    /* Call intakeSolenoid defined in RobotMap */
    CANSparkMax intakeLiftMotor = RobotMap.intakeLiftMotor;
    CANEncoder intakeLiftEncoder = RobotMap.intakeLiftEncoder;
    /*
     * Make this class public
     */
    boolean state = false; // false = up
    public boolean currentState = false;
    boolean moving = false;
    public Intake() {
        intakeLiftEncoder.setPosition(0.0);
    }

    //double downEncoderPos;
    //boolean isDownEncoderSet = false;
    double upEncoderPos = 0.0;
    //boolean isUpEncoderSet = false;
    int failCount = 0;
    public void periodicIntake() {
        //boolean a = RobotBase.isEnabled();
        double RPM = Math.abs(intakeLiftEncoder.getVelocity());
        double Position = intakeLiftEncoder.getPosition();
        if (RPM < 5) {
            failCount++;
            if (failCount > 5) {
                moving = false;
                intakeLiftMotor.stopMotor();
                currentState = state;
                failCount = 0;
            }
        } else {
            moving = true;
            failCount --;
        }
        if (state == true) {
            if (currentState != true) {
                intakeLiftMotor.set(-Config.intakeLiftSpeed);
            }
            /*if (!isDownEncoderSet && moving == false) {
                downEncoderPos = Position;
                isDownEncoderSet = true;
                System.out.println("Down encoder set!");
            } */
            //

        } else {
            if (currentState != false || currentState == true && Math.abs(Position-upEncoderPos) > 2) {
                intakeLiftMotor.set(-Config.intakeLiftSpeed);
            }
            /*if (!isUpEncoderSet && moving == false) {
                upEncoderPos = Position;
                isUpEncoderSet = true;
                System.out.println("Up encoder set!");
            }*/
            //
        }
    }
    /*
     * Lower intake
     */
    public void lower() {
        //intakeSolenoid.set(false);
        //intakeLiftMotor.set(-Config.intakeDownSpeed);
        state = true;
    }

    /*
     * Raise intake
     */
    public void raise() {
        //intakeSolenoid.set(true);
        //intakeLiftMotor.set(Config.intakeLiftSpeed);
        state = false;
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

    public void stopLift() {
        intakeLiftMotor.stopMotor();
    }

    /*
     * Spits the ball out of the robot
     */
    public void moveOut() {
        intakeMotor.set(Config.intakeSpeed * -1);
    }
}
