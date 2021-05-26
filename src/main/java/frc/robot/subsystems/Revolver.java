package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

public class Revolver extends SubsystemBase {

    CANSparkMax revolverMotor = RobotMap.revolverMotor;
    
    public Revolver() {}

    public void rotateCW() {
        revolverMotor.set(Config.revolverSpeed);
    }

    public void rotateCCW() {
        revolverMotor.set(-Config.revolverSpeed);
    }

    public void stop() {
        revolverMotor.stopMotor();
    }
}
