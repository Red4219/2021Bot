// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.autonomous.paths.Straight;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Revolver;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterAlign;

/*
 * This is the "main" class
 * 
 * Author: Francisco Fabregat
 */
public class Robot extends TimedRobot {
  
  /* Initialize autonomousCommand to store chosen command */
  Command autonomousCommand;

  /* Initialize OI and Subsystems */
  public static OI oi;
  public static Drive driveTrain;
  public static Intake intake;
  public static Revolver revolver;
  public static Shooter shooter;
  public static ShooterAlign shooterAlign;

  /* Define default autonomous mode id */
  private int mode = 0;

  /* Initialize and define autonomous modes list */
  String[] autoList = { "Move Straight"};

  /* Initialize Dashboard */
  public static Dashboard dashboard = new Dashboard();

  /* Initialize Limelight */
  public static Limelight limelight = new Limelight();

  /*
   * This function is executed only once when the robot boots up
   */
  @Override
  public void robotInit() {
    /* Initialize RobotMap */
    RobotMap.init();

    /* Define OI and Subsystems */
    driveTrain = new Drive();
    intake = new Intake();
    revolver = new Revolver();
    shooter = new Shooter();
    shooterAlign = new ShooterAlign();
    oi = new OI();

    /* Set Default Commands for Subsystems */
    driveTrain.setDefaultCommand(new TankDrive());

    /* Push autonomous list to Dashboard */
    dashboard.setAutonomousList(autoList);

    /* Select default autonomous mode */
    dashboard.setAutonomous(0);

    /* Reset shooter align encoder */
    shooterAlign.reset();
  }

  /*
   * This function is executed periodically in any mode (disabled, teleop,
   * autonomous, etc.)
   */
  @Override
  public void robotPeriodic() {
    /* Run Command Scheduler */
    CommandScheduler.getInstance().run();

    /* Send battery voltage to Dashboard */
    dashboard.setBattery(RobotController.getBatteryVoltage());

    /* Send remaining time to Dashboard */
    dashboard.setTime(DriverStation.getInstance().getMatchTime());
    
    dashboard.setShootAdjustEncoder(shooterAlign.getPosition());

    /* Send distance to dashboard ONLY if tape is detected by the Limelight */
    if (limelight.hasTarget()) {
      dashboard.setDistance(limelight.getDistance());
    } else {
      dashboard.setDistance(0.0);
    }
  }

  /*
   * This function is executed only once when the robot changes into disabled mode
   */
  @Override
  public void disabledInit() {}

  /*
   * This function is executed periodically when in disabled mode
   */
  @Override
  public void disabledPeriodic() {}

  /*
   * This function is executed only once when the robot changes into autonomous
   * mode
   */
  @Override
  public void autonomousInit() {
    /* Set mode variable to the chosen autonomous mode id */
    mode = dashboard.getSelectedAutonomous();

    /* Set autonomousCommand to the right command according to the mode variable */
    if (mode == 0) {
      autonomousCommand = (Command) new Straight();
    } else {
      autonomousCommand = (Command) new Straight();
    }

    /* Start the autonomous command if it has not been started already */
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /*
   * This function is executed periodically when in autonomous mode
   */
  @Override
  public void autonomousPeriodic() {}

  /*
   * This function is executed only once when the robot changes into teleop mode
   */
  @Override
  public void teleopInit() {
    /* Stop the autonomous if it is still running */
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /*
   * This function is executed periodically when in teleop mode
   */
  @Override
  public void teleopPeriodic() {
    dashboard.setLeftMotorPower(RobotMap.MiddleLeftMotor.get());
    dashboard.setRightMotorPower(RobotMap.MiddleRightMotor.get());
    dashboard.setIntakeMotorPower(RobotMap.intakeMotor.get());
    dashboard.setIntakeLiftMotorPower(RobotMap.intakeLiftMotor.get());
    dashboard.setShooterMotorPower(RobotMap.shooterMotor.get());
    dashboard.setRevolverMotorPower(RobotMap.revolverMotor.get());
    dashboard.setAdjusterMotorPower(RobotMap.shooterAlignMotor.get());
  
    //System.out.println("Bottom Switch: "+RobotMap.intakeDownSwitch.get());
  }

  /*
   * This function is executed only once when the robot changes into test mode
   */
  @Override
  public void testInit() {
    /* Cancels all running commands at the start of test mode */
    CommandScheduler.getInstance().cancelAll();
  }

  /*
   * This function is executed periodically when in test mode
   */
  @Override
  public void testPeriodic() {}
}
