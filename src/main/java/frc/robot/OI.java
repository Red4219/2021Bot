package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoAlign;
import frc.robot.commands.LiftIntake;
import frc.robot.commands.MoveIntake;

/*
 * This class defines everything related to joysticks and controls
 * 
 * Author: Francisco Fabregat
 */
public class OI {

  /* Define joysticks and controllers */
  public static Joystick driver;

  /* Define joystick buttons */
  public static JoystickButton raiseIntakeButton;
  public static JoystickButton lowerIntakeButton;
  public static JoystickButton intakeButton;
  public static JoystickButton revolverButton;
  public static JoystickButton autoAlignButton;
  public static JoystickButton keepIntakeHighButton;
  public static JoystickButton shootButton;
  
  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    /* Assign button ids to buttons */
    raiseIntakeButton = new JoystickButton(driver, 6);
    lowerIntakeButton = new JoystickButton(driver, 5);
    intakeButton = new JoystickButton(driver, 3);
    revolverButton = new JoystickButton(driver, 4);
    autoAlignButton = new JoystickButton(driver, 2);
    keepIntakeHighButton = new JoystickButton(driver, 1);
    shootButton = new JoystickButton(driver, 7);

    /* Assign commands to buttons */
    
    // TEMPORARILY DISABLED COMMANDS FOR SAFETY WHILE SENSORS ARE INSTALLED

    /*raiseIntakeButton.whenPressed(new LiftIntake(true));
    lowerIntakeButton.whenPressed(new LiftIntake(false));
    intakeButton.whenPressed(new MoveIntake(true));
    autoAlignButton.whenPressed(new AutoAlign());*/
  }
}