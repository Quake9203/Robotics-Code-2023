package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class Lift {

    public static VictorSPX liftVictorMaster = new VictorSPX(RobotMap.liftVictorMasterCAN);
       private static boolean toggleLift = false;    // Turning lift motor off by default
       private static boolean toggleLiftBack = false; // Also turning lift motor off by default

    public static double getLiftSpeed() {
        double raw = 0;

        if (OI.xbox.getRawButtonPressed(6)) {  // If a certain button is pressed (need to check which button) lift is toggled on
            toggleLift = !toggleLift;          // until any of buttons 6, 5, or 3 are pressed.
            toggleLiftBack = false;            // toggleLiftBack does not appear to be used, test for functionality.
        } else {
            if (OI.xbox.getRawButtonPressed(5)) {  // Supposed to reverse motor but that functionality is actualy covered later in the last if else statement 
                toggleLift = false;
                toggleLiftBack = !toggleLiftBack;
            } else {
                if (OI.xbox.getRawButtonPressed(3)) {
                    toggleLift = false;
                    toggleLiftBack = false;
                }
            }
        }

        if (toggleLift) {
            raw = 0.85;
        } else {
            if (OI.xbox.getRawButton(5))  // Seems to provide functionality for toggleLiftBack by reversing lift motor
                raw = -0.85;
            else {
                raw = 0;
            }
        }

        return raw;
    }

}
