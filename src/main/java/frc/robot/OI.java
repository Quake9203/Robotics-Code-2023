package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public  class OI {
    public static XboxController xbox = new XboxController(RobotMap.xboxUSB);
    private static double deadzoneJoy = 0.1;
    private static double deadzoneTrigger = 0.1;

    public static double GetXboxLeftJoyX() {
        return Math.abs(xbox.getLeftX()) < deadzoneJoy ? 0.0 : xbox.getLeftX();
    }

    public static double GetXboxLeftJoyY() {
        return Math.abs(xbox.getLeftY()) < deadzoneJoy ? 0.0 : xbox.getLeftY();
    }


    public static double GetXboxRightJoyX() {
        return Math.abs(xbox.getRightX()) < deadzoneJoy ? 0.0 : xbox.getRightX();
    }

    public static double GetXboxRightJoyY() {
        return Math.abs(xbox.getRightY()) < deadzoneJoy ? 0.0 : xbox.getRightY();
    }

    public static double GetXboxRightTrigger() {
        return Math.abs(xbox.getRightTriggerAxis()) < deadzoneTrigger ? 0.0 : xbox.getRightTriggerAxis();
    }

    public static double GetXboxLeftTrigger() {
        return Math.abs(xbox.getLeftTriggerAxis()) < deadzoneTrigger ? 0.0 : xbox.getLeftTriggerAxis();
    }
}