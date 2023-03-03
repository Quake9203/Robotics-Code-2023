package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.OI;
import edu.wpi.first.wpilibj.Servo;

public class Hand {
    public static Servo hand = new Servo(RobotMap.handServoPWM);
    
    public static int getHandPosition() {
        int theta = 0;

        if (OI.xbox.getAButton()) {
            theta = 90;
        } else {
            theta = 0;
        }

        return theta;
    }
}
