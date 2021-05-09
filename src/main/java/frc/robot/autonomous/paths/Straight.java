package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.actions.StraightDrive;

/*
 * This autonomous path just drives forward
 * 
 * Author: Francisco Fabregat
 */
public class Straight extends SequentialCommandGroup {

    /*
     * Autonomous Path to Drive Straight
     */
    public Straight() {
        addCommands(
            /* Drives forward for 6ft 0in */
            new StraightDrive(true, 6, 0, false)
        );
    }

}