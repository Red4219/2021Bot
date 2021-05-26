package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Config;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    
    CANSparkMax shooterMotor = RobotMap.shooterMotor;

    public Shooter() {}

    public void on() {
        shooterMotor.set(Config.shooterSpeed);
    }

    public void stop() {
        shooterMotor.stopMotor();
    }
}
