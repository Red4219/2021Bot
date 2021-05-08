package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/*
 * This class defines everything related to joysticks and controls
 * 
 * Author: Francisco Fabregat
 */
public class OI {

  /* Define joysticks and controllers */
  public static Joystick driver;
  
  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

  }
}