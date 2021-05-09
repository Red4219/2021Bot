package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/*
 * This class handles all NetworkTable actions that communicate with the Dashboard
 * 
 * Author: Francisco Fabregat
 */
public class Dashboard {

    /* Initialize NetworkTables instance */
    static NetworkTableInstance inst = NetworkTableInstance.getDefault();

    /* Fetch table 'RedDashboard' from NetworkTables */
    NetworkTable table = inst.getTable("RedDashboard");

    /* Define NetworkTable entries */
    NetworkTableEntry battery = table.getEntry("battery");
    NetworkTableEntry selectedAuto = table.getEntry("autoSelected");
    NetworkTableEntry autoList = table.getEntry("AutoList");
    NetworkTableEntry robotMode = table.getEntry("RobotMode");
    NetworkTableEntry cameraActive = table.getEntry("CameraActive");
    NetworkTableEntry vision = table.getEntry("vision");
    NetworkTableEntry time = table.getEntry("time");
    NetworkTableEntry newTargetDistance = table.getEntry("targetDistance");

    /* Send battery voltage to NetworkTables */
    public void setBattery(final Double voltage) {
        battery.setDouble(voltage);
    }

    /* Get the current selected autonomous mode from NetworkTables */
    public int getSelectedAutonomous() {
        return (int) selectedAuto.getDouble(0.0);
    }

    /* Set an autonomous mode in NetworkTables */
    public void setAutonomous(final int mode) {
        selectedAuto.setNumber(mode);
    }

    /* Send the list of all autonomous modes to NetworkTables */
    public void setAutonomousList(final String[] list) {
        autoList.setStringArray(list);
    }

    /* Send the current robot mode to NetworkTables (Disabled, Teleop, Autonomous) */
    public void setRobotMode(final String mode) {
        robotMode.setString(mode);
    }

    /* Send the camera state to NetworkTables */
    public void setCameraTrackingStatus(final Boolean active) {
        cameraActive.setBoolean(active);
    }

    /* Send vision data to NetworkTables */
    public void setVision(final Double data) {
        vision.setDouble(data);
    }

    /* Send time to NetworkTables */
    public void setTime(final double seconds) {
        if (seconds == -1.0) {
            time.setString("2:15");
        } else {
            final int minutes = (int) (seconds / 60);
            final int secondsRemaining = (int) (seconds - (minutes * 60));
            time.setString(minutes + ":" + secondsRemaining);
        }
    }

    /* Send calculated distance from robot to target by Limelight */
    public void setDistance(final double distance) {
        newTargetDistance.setDouble(distance);
    }
}