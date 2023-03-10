package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.OI;
import edu.wpi.first.wpilibj.Servo;

public class Hand {
    public static Servo handTop = new Servo(RobotMap.handServoTopPWM);
    public static Servo handBottom = new Servo(RobotMap.handServoBottomPWM);

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
