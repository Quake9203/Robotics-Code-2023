package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public  class OI {
    public static Joystick xbox = new Joystick(RobotMap.xboxUSB);
    private static double deadzone = 0.1;


    public static  double GetXboxLeftJoyX() {
        
        return Math.abs(xbox.getX(Hand.kLeft)) < deadzone ? 0.0 : xbox.getX(Hand.kLeft);
    }

public static double GetXboxLeftJoyY() {
    return Math.abs(xbox.getY(Hand.kLeft)) < deadzone ? 0.0 : xbox.getY(Hand.kLeft);
}


public static double GetXboxRightJoyX() {
    return Math.abs(xbox.getX(Hand.kRight)) < deadzone ? 0.0 : xbox.getX(Hand.kRight);
}

public static double GetXboxRightJoyY() {
    return Math.abs(xbox.getRawAxis(5)) < deadzone ? 0.0 : xbox.getRawAxis(5);
}

public static double GetXboxRightTrigger() {
    return Math.abs(xbox.getRawAxis(3)) < deadzone ? 0.0 : xbox.getRawAxis(3);
}




}