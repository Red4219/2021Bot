package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/*
 * All universal variables and robot components are found here
 * 
 * Author: Francisco Fabregat
 */
public class RobotMap {
    /* Initialize drive Spark variables */
  public static CANSparkMax FrontLeftMotor;
  public static CANSparkMax MiddleLeftMotor;
  public static CANSparkMax BackLeftMotor;
  public static CANSparkMax FrontRightMotor;
  public static CANSparkMax MiddleRightMotor;
  public static CANSparkMax BackRightMotor;

  /* Initialize SpeedControllerGroups for DifferentialDrive */
  public static SpeedControllerGroup leftGroup;
  public static SpeedControllerGroup rightGroup;

  /* Initialize DifferentialDrive */
  public static DifferentialDrive robotDrive;
  
  /* Initialize encoders */
  public static CANEncoder leftDriveEncoder;
  public static CANEncoder rightDriveEncoder;

  /*
   * Initialize all components
   */
  public static void init() {

    /* Define drive Sparks with CAN id */
    FrontLeftMotor = new CANSparkMax(6, MotorType.kBrushless);
    MiddleLeftMotor = new CANSparkMax(5, MotorType.kBrushless);
    BackLeftMotor = new CANSparkMax(4, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(3, MotorType.kBrushless);
    MiddleRightMotor = new CANSparkMax(2, MotorType.kBrushless);
    BackRightMotor = new CANSparkMax(1, MotorType.kBrushless);

    /* Set ramp rate for drive motors to decrease current drawn and prevent browning out */
    FrontLeftMotor.setOpenLoopRampRate(0.1);
    MiddleLeftMotor.setOpenLoopRampRate(0.1);
    BackLeftMotor.setOpenLoopRampRate(0.1);
    FrontRightMotor.setOpenLoopRampRate(0.1);
    MiddleRightMotor.setOpenLoopRampRate(0.1);
    BackRightMotor.setOpenLoopRampRate(0.1);
    
    /* Define SpeedControllerGroups for DifferentialDrive */
    leftGroup = new SpeedControllerGroup(FrontLeftMotor, MiddleLeftMotor, BackLeftMotor);
    rightGroup = new SpeedControllerGroup(FrontRightMotor, MiddleRightMotor, BackRightMotor);

    /* Define robotDrive as a DifferentialDrive for drivetrain */
    robotDrive = new DifferentialDrive(leftGroup, rightGroup);

    /* Define encoders */
    leftDriveEncoder = MiddleLeftMotor.getEncoder();
    rightDriveEncoder = MiddleRightMotor.getEncoder();
  }
}
