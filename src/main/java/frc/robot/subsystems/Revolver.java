package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.RobotMap;

/*
 * This is the Revolver subsystem where anything related to the revolver is found
 * 
 * Author: Francisco Fabregat
 */
public class Revolver extends SubsystemBase {

    /* Call revolverMotor defined in RobotMap */
    CANSparkMax revolverMotor = RobotMap.revolverMotor;
    CANEncoder revolverEncoder = RobotMap.revolverEncoder;

    //Safety stuff
    int failLimit = 10;
    int failCount = 0;
    boolean isFailing = false;
    /*
     * Make this class public
     */
    public Revolver() {}


    // Safety function
    private void safetyCheck() {
        double RPM = Math.abs(revolverEncoder.getVelocity());
        if (RPM < 150) {
            failCount ++;
        }
        if (failCount >= failLimit) {
            isFailing = true;
            stop(true);
            OI.operator.setRumble(RumbleType.kLeftRumble, 0.5);
            OI.operator.setRumble(RumbleType.kRightRumble, 0.5);
        }
    }

    /*
     * Rotate revolver clockwise
     */
    public void rotateCW() {
        if (isFailing == false) {
            revolverMotor.set(Config.revolverSpeed);
            // Safety
        }
        safetyCheck();
    }

    /*
     * Rotate revolver counterclockwise
     */
    public void rotateCCW() {
        if (isFailing == false) {
            revolverMotor.set(-Config.revolverSpeed);
            // Safety
        }
        safetyCheck();
    }

    /*
     * Rotate revolver from raw input
     */
    public void rotate(double factor) {
        if (isFailing == false) {
            revolverMotor.set(factor * Config.revolverSpeed);
        }
    }
    /*
     * Stop revolver
     */
    public void stop(boolean isAuto) {
        revolverMotor.stopMotor();
        if (isAuto == false) {
            isFailing = false;
        }
    }
}
