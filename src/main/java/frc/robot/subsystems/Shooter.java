
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class Shooter {
    private static double deadzone = 0.5;

    public static VictorSPX shooterVictorMaster = new VictorSPX(RobotMap.shooterVictorMasterCAN);
    public static VictorSPX shooterVictorSPX = new VictorSPX(RobotMap.shooterVictorSPXCAN);

    public static double getShooterSpeed() {
        double raw = 0;

        if (Math.abs(OI.xbox.getRawAxis(3)) > deadzone) {
            raw = -1;
        } else {
            raw = 0;
        }

        return raw;
    }

}
