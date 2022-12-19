package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class Elevator {

    public static VictorSPX elevatorVictorMaster = new VictorSPX(RobotMap.elevatorVictorMasterCAN);

    public static double getElevatorSpeed() {
        double speed = 0;

        if (OI.xbox.getRawButton(1)) {  // ELevator turns on at 45% speed forward or backward depending on button pressed
            speed = -0.45;
        } else {
            if(OI.xbox.getRawButton(2)) {
                speed = 0.45;
            }
            else {
                speed = 0;
            }
        }

        return speed;
    }

}
