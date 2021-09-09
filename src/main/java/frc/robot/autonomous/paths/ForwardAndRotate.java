package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.actions.StraightDrive;
import frc.robot.autonomous.actions.RotateDrive;

/*
 * This autonomous path just drives forward
 * 
 * Author: Harrison Lewis
 */
public class ForwardAndRotate extends SequentialCommandGroup {

    /*
     * Autonomous Path to Drive Straight and do a lil spin
     */
    public ForwardAndRotate() {
        addCommands(
            /* Drives forward for 6ft 0in */
            new StraightDrive(true, 15,5, true),
            new StraightDrive(false, 15, 5, false),
            new RotateDrive(true, 90),
            new StraightDrive(false, 6, 0, true),
            new RotateDrive(true, 90)
            //System.out.println("YEEEEE");
        );
    }

}