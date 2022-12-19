package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class Shooter {
    private static double deadzone = 0.5;  // Decide how much of a push is considered "pushed". Larger numbers have lower sensitivity, smaller numbers have more sensitivity

    public static VictorSPX shooterVictorMaster = new VictorSPX(RobotMap.shooterVictorMasterCAN);
    public static VictorSPX shooterVictorSPX = new VictorSPX(RobotMap.shooterVictorSPXCAN);

    public static double getShooterSpeed() {
        double raw = 0;

        if (Math.abs(OI.xbox.getRawAxis(3)) > deadzone) {  // Checks if trigger is pressed more than minimum
            raw = -1;                                      // Seemingly motor must be inverted but is not inverted in initialization so is inverted here
        } else {
            raw = 0;
        }

        return raw;
    }

}
