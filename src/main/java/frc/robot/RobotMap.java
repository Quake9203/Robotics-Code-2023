/**
* THIS IS WHERE WE MAP OUT MOTOR CONTROLS!
*/

package frc.robot;

public class RobotMap {

    /*
    Check CAN locations using Phoenix Tuner CAN Devices utility (app on laptop)
    PWM locations (including servos) determined by location number on RoboRIO 
    */ 

    //Drivetrain
    public static int rightTalonMasterCAN = 2;
    public static int rightTalonSlaveCAN = 1;
    public static int leftTalonMasterCAN = 4;
    public static int leftTalonSlaveCAN = 3;

    //new 2020
    public static int elevatorVictorMasterCAN = 8; //brake mode
    // public static int shooterVictorMasterCAN = 6;
    // public static int shooterVictorSPXCAN = 8;
    // public static int liftVictorMasterCAN = 7; //brake mode

    // new 2023
    public static int handServoTopPWM = 9;
    public static int handServoBottomPWM = 8;
    public static int armVictorCAN = 7;

    //new 2019
    //public static int ballVictorMasterCAN = 10;

    //new 2019
    //public static int armVictorMasterPWM = 2;
// below has always been zero, regardless of what else is plugged in
    public static int xboxUSB = 0;

}
