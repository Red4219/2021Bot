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
    private boolean isSafe(double rawSpeed) {
        double signum = Math.signum(rawSpeed);
        if (signum == 0.0 || getPosition() > 0.0 && signum == -1 || getPosition() < 12 && signum == 1) { //TODO: fix pls
            return true;
        } else {
            System.out.println("Limit");
            stop();
        }
        return false;
    }
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
        if (isSafe(1.0)) {
            shooterAlignMotor.set(Config.shootAlignSpeed);
        }
    }
    /*
     *
     */ 
    public void setMotor(double rawSpeed) {
        if (isSafe(rawSpeed)) {
            shooterAlignMotor.set(Config.shootAlignSpeed*rawSpeed);
        }
    }
    /*
     * Move shooter aligner down
     */
    public void moveDown() {
        if (isSafe(-1.0)) {
            shooterAlignMotor.set(-Config.shootAlignSpeed);
        }
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
        double aMultSub[] = {bSubA[0]*t,bSubA[1]*t};
        double result[] = {a[0]+aMultSub[0],a[1]+aMultSub[1]}; 
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
        System.out.println("HELLO " + distance);
        if (distance >= 48 && distance <= 147.2) {
            double whole = (147.2 - 48);
            double p0[] = {48,0.1};
            double p1[] = {103.5,4};
            double p2[] = {125.7,13.5};
            double p3[] = {142.7,0};

            double result[] = cubicBezier((distance-48)/whole, p0, p1, p2, p3);
            //System.out.println((distance-48)/whole);
            System.out.println(result[0] + ", " + result[1]);
            return result[1];
        }
        return 0.0;
    }
}
