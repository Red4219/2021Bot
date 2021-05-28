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
    NetworkTableEntry shooterRPM = table.getEntry("shooterRPM");
    NetworkTableEntry newTargetDistance = table.getEntry("targetDistance");
    NetworkTableEntry leftDriveMotor = table.getEntry("leftDriveMotor");
    NetworkTableEntry rightDriveMotor = table.getEntry("rightDriveMotor");
    NetworkTableEntry intakeMotor = table.getEntry("intakeMotor");
    NetworkTableEntry intakeLiftMotor = table.getEntry("intakeLiftMotor");
    NetworkTableEntry shooterMotor = table.getEntry("shooterMotor");
    NetworkTableEntry revolverMotor = table.getEntry("revolverMotor");
    NetworkTableEntry adjusterMotor = table.getEntry("adjusterMotor");
    NetworkTableEntry shootAdjustEncoder =  table.getEntry("shootAdjustEncoder");

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

    public void setShooterRPM(final double rpm) {
        shooterRPM.setDouble(rpm);
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

    public void setShootAdjustEncoder(final double value) {
        System.out.println("Setting encoder to: "+value);
        shootAdjustEncoder.setDouble(value);
    }

    public void setLeftMotorPower(final double motorPower) {
        leftDriveMotor.setDouble(motorPower);
    }

    public void setRightMotorPower(final double motorPower) {
        rightDriveMotor.setDouble(motorPower);
    }

    public void setIntakeMotorPower(final double motorPower) {
        intakeMotor.setDouble(motorPower);
    }

    public void setIntakeLiftMotorPower(final double motorPower) {
        intakeLiftMotor.setDouble(motorPower);
    }

    public void setShooterMotorPower(final double motorPower) {
        shooterMotor.setDouble(motorPower);
    }

    public void setRevolverMotorPower(final double motorPower) {
        revolverMotor.setDouble(motorPower);
    }

    public void setAdjusterMotorPower(final double motorPower) {
        adjusterMotor.setDouble(motorPower);
    }
}