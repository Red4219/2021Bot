package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Revolver subsystem where anything related to the revolver is found
 * 
 * Author: Francisco Fabregat
 */
public class Revolver extends SubsystemBase {

    /* Call revolverMotor defined in RobotMap */
    CANSparkMax revolverMotor = RobotMap.revolverMotor;
    
    /*
     * Make this class public
     */
    public Revolver() {}

    /*
     * Rotate revolver clockwise
     */
    public void rotateCW() {
        revolverMotor.set(Config.revolverSpeed);
    }

    /*
     * Rotate revolver counterclockwise
     */
    public void rotateCCW() {
        revolverMotor.set(-Config.revolverSpeed);
    }

    /*
     * Rotate revolver from raw input
     */
    public void rotate(double factor) {
        revolverMotor.set(factor * Config.revolverSpeed);
    }
    /*
     * Stop revolver
     */
    public void stop() {
        revolverMotor.stopMotor();
    }
}
