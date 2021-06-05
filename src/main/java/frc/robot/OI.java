package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoAlign;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.LiftIntake;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MoveRevolver;
import frc.robot.commands.MoveShooterAdjust;

/*
 * This class defines everything related to joysticks and controls
 * 
 * Author: Francisco Fabregat
 */
public class OI {

  /* Define joysticks and controllers */
  public static Joystick driver;
  public static Joystick operator;

  /* Define joystick buttons */
  public static JoystickButton raiseIntakeButton;
  public static JoystickButton lowerIntakeButton;
  public static JoystickButton intakeButton;
  public static JoystickButton revolverButton;
  public static JoystickButton autoAlignButton;
  public static JoystickButton keepIntakeHighButton;
  public static JoystickButton shootButton;
  public static JoystickButton shooterAdjustUpButton;
  public static JoystickButton shooterAdjustDownButton;
  
  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);
    operator = new Joystick(0);

    /* Assign button ids to buttons */
    raiseIntakeButton = new JoystickButton(driver, 6);
    lowerIntakeButton = new JoystickButton(driver, 5);
    intakeButton = new JoystickButton(operator, 3);
    revolverButton = new JoystickButton(driver, 2);
    autoAlignButton = new JoystickButton(driver, 3);
    keepIntakeHighButton = new JoystickButton(driver, 8);
    shootButton = new JoystickButton(driver, 7);
    shooterAdjustUpButton = new JoystickButton(driver, 4);
    shooterAdjustDownButton = new JoystickButton(driver, 1);

    /* Assign commands to buttons */
    
    // TEMPORARILY DISABLED COMMANDS FOR SAFETY WHILE SENSORS ARE INSTALLED

    lowerIntakeButton.whenPressed(new LiftIntake(false));
    raiseIntakeButton.whenPressed(new LiftIntake(true));
    shooterAdjustUpButton.whenPressed(new MoveShooterAdjust(true));
    shooterAdjustDownButton.whenPressed(new MoveShooterAdjust(false));
    shootButton.whenPressed(new ManualShoot());
    //shootButton.whenPressed(new AutoShoot());
    autoAlignButton.whenPressed(new AutoAlign());
    revolverButton.whenPressed(new MoveRevolver(false));
    
    intakeButton.whenPressed(new MoveIntake(true));
  }
}