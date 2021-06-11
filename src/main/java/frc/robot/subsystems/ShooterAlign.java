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
     *
     */ 
    public void setMotor(double rawSpeed) {
        shooterAlignMotor.set(Config.shootAlignSpeed*rawSpeed);
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
    private double numLerp(double a, double b, double c) {
        return a + (b - a) * c;
    }
    private double[] vectorLerp(double[] a , double[] b ,double t) {
        double bSubA[] = {b[0]-a[0],b[1]-a[1]};
        double aPlusSub[] = {a[0]+bSubA[0],a[1]+bSubA[1]};
        double result[] = {aPlusSub[0]*t,aPlusSub[1]*t};
        return result;
        
    }
    private double[] cubicBezier(double t, double[] p0, double[] p1, double[] p2, double[] p3) {
        double l1[] = vectorLerp(p0, p1, t);
	    double l2[] = vectorLerp(p1, p2, t);
	    double l3[] = vectorLerp(p2, p3, t);
    	double a[] = vectorLerp(l1, l2, t);
    	double b[] = vectorLerp(l2, l3, t);
    	double cubic[] = vectorLerp(a, b, t);
	    return cubic;
    }
    public double getTargetPosition(double distance) {
        System.out.println("HELLO");
        if (distance >= 48 && distance <= 147.2) {
            double whole = (147.2 - distance);
            double p0[] = {48,0.1};
            double p1[] = {103.5,4};
            double p2[] = {125.7,13.5};
            double p3[] = {142.7,0};

            double result[] = cubicBezier((distance-48)/whole, p0, p1, p2, p3);
            System.out.println(result[0]);
            System.out.println(result[1]);
            System.out.println(distance);
        }
        return 0.0;
    }
}
