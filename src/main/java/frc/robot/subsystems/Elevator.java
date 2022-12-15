
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class Elevator {

    public static VictorSPX elevatorVictorMaster = new VictorSPX(RobotMap.elevatorVictorMasterCAN);

    public static double getElevatorSpeed() {
        double raw = 0;

        if (OI.xbox.getRawButton(1)) {
            raw = -0.45;
        } else {
            if(OI.xbox.getRawButton(2)) {
                raw = 0.45;
            }
            else {
                raw = 0;
            }
        }

        return raw;
    }

}
