package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public  class OI {
    public static XboxController xbox = new XboxController(RobotMap.xboxUSB);
    private static double deadzone = 0.1;


    public static  double GetXboxLeftJoyX() {
        
        return Math.abs(xbox.getLeftX()) < deadzone ? 0.0 : xbox.getLeftX();
    }

public static double GetXboxLeftJoyY() {
    return Math.abs(xbox.getLeftY()) < deadzone ? 0.0 : xbox.getLeftY();
}


public static double GetXboxRightJoyX() {
    return Math.abs(xbox.getRightX()) < deadzone ? 0.0 : xbox.getRightX();
}

public static double GetXboxRightJoyY() {
    return Math.abs(xbox.getRawAxis(5)) < deadzone ? 0.0 : xbox.getRawAxis(5);
}

public static double GetXboxRightTrigger() {
    return Math.abs(xbox.getRawAxis(3)) < deadzone ? 0.0 : xbox.getRawAxis(3);
}

public static double GetXboxLeftTrigger() {
    return Math.abs(xbox.getLeftTriggerAxis()) < deadzone ? 0.0 : xbox.getLeftTriggerAxis();
}




}