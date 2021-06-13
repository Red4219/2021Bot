package frc.robot.subsystems;

import java.io.Console;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Counter;

/*
 * This is the Shooter subsystem where anything related to the shooter is found
 * 
 * Author: Francisco Fabregat
 */
public class Shooter extends SubsystemBase {
    
    /* Call shooterMotor defined in RobotMap */
    CANSparkMax shooterMotor = RobotMap.shooterMotor;
    public boolean isActive = false;
    // This counter is attached to DIO 0 and measures PWM
	Counter ShooterMag;   
	// This counter counts the index pulses (revolutions)
	Counter ShooterIndex = new Counter(0); //full period counter
    int lastRotations = 0;

    /*
     * Make this class public
     */
    public Shooter() { 
        ShooterIndex.reset();
    }

    /*
     * Turn on shooter
     */
    public void on() {
        shooterMotor.set(-Config.shooterSpeed);
        isActive = true;
        //OI.operator.setRumble(RumbleType.kLeftRumble, 0.5);
        //OI.operator.setRumble(RumbleType.kRightRumble, 0.5);
    }

    /*
     * Stop shooter
     */
    public void stop() {
        shooterMotor.stopMotor();
        isActive = false;
        //OI.operator.setRumble(RumbleType.kLeftRumble, 0.5);
        //OI.operator.setRumble(RumbleType.kRightRumble, 0.5);
    }
    /*
    *   Safety for shooter motor
    */
    public double RPM = 0.0;
    private int failedChecks = 0;
    public void checkSafety(double timeDelta) {
        int rotations = ShooterIndex.get();
        int rotationDelta = rotations - lastRotations;
        lastRotations = rotations;

        //System.out.println("RAW: " + timeDelta);
        double timeFactor = (60/timeDelta);
        RPM = (rotationDelta*timeFactor);
        //System.out.println( RPM + " RPM");
        if (isActive == true && RPM < 1000 ) {
            failedChecks ++;
            System.out.println("RPM CHECK FAILED <1000");
            if (failedChecks >= 8) {
                stop();
            } 
        } else {
            failedChecks = 0;
        }
        //NEED ENCODER FOR REDLINE
    }
}
