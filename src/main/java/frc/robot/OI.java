package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
  public static JoystickButton raiseIntakeButton;
  public static JoystickButton lowerIntakeButton;

  public static JoystickButton intakeButton;
  
  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    raiseIntakeButton = new JoystickButton(driver, 6);
    lowerIntakeButton = new JoystickButton(driver, 5);

    intakeButton = new JoystickButton(driver, 3);


    raiseIntakeButton.whenPressed(new LiftIntake(true));
    lowerIntakeButton.whenPressed(new LiftIntake(false));
    intakeButton.whenPressed(new MoveIntake(true));
  }
}