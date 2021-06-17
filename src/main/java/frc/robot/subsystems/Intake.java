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
    boolean state = true; // false = down
    public boolean currentState = true;
    boolean countering = false;
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
        //System.out.println("CHECK: " + moving);
        if ( intakeLiftMotor.get() != 0.0) {
            if (RPM < 10 ) {
                failCount++;
                System.out.println("FAIL INC");
                if (failCount > 8 || countering && failCount > 8) {
                    moving = false;
                    countering = false;
                    intakeLiftMotor.stopMotor();
                    System.out.println("FAIL MAX!");
                    //System.out.println("STOP");
                    currentState = state;
                    if (state == true) {
                        upEncoderPos = Position;
                    }
                    failCount = 0;
                }
            } else {
                System.out.println("MOVE IS NOW TRUE");
                moving = true;
                failCount --;
                if (failCount < 0) {
                    failCount = 0;
                }
            }
        }
        if (state == true) {
            double motorDelta = Math.abs(Position-upEncoderPos);
            if (currentState != true || currentState == true && motorDelta > 0.06) {
                
                if (currentState == true && motorDelta > 0.06) {
                    intakeLiftMotor.set(0.5);    
                    System.out.println("COUNTER : " + Math.abs(Position-upEncoderPos));
                    countering = true;
                } else {
                    intakeLiftMotor.set(Config.intakeLiftSpeed); // raise
                    System.out.println("NORMAL RAISE");
                }
            }
            /*if (!isDownEncoderSet && moving == false) {
                downEncoderPos = Position;
                isDownEncoderSet = true;
                System.out.println("Down encoder set!");
            } */
            //

        } else {
            if (currentState != false ) {
                intakeLiftMotor.set(-Config.intakeDownSpeed); //owering
                System.out.println("LOWERing");
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
        System.out.println("LOWER");
        state = false;
    }

    /*
     * Raise intake
     */
    public void raise() {
        //intakeSolenoid.set(true);
        //intakeLiftMotor.set(Config.intakeLiftSpeed);
        state = true;
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
