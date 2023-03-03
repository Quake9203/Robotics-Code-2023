package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class ArcadeDrive {
public static WPI_TalonSRX rightTalonMaster = new WPI_TalonSRX(RobotMap.rightTalonMasterCAN);
public static WPI_TalonSRX rightTalonSlave = new WPI_TalonSRX(RobotMap.rightTalonSlaveCAN);

public static WPI_TalonSRX leftTalonMaster = new WPI_TalonSRX(RobotMap.leftTalonMasterCAN);
public static WPI_TalonSRX leftTalonSlave = new WPI_TalonSRX(RobotMap.leftTalonSlaveCAN);


public static MotorControllerGroup leftTalons = new MotorControllerGroup(leftTalonMaster, leftTalonSlave);


public static DifferentialDrive drive = new DifferentialDrive(rightTalonMaster, leftTalons);


public static void DrivetrainSetup() {
    rightTalonMaster.configFactoryDefault();
    rightTalonSlave.configFactoryDefault();


    rightTalonSlave.follow(rightTalonMaster);

    rightTalonMaster.setInverted(true);
    rightTalonSlave.setInverted(InvertType.FollowMaster);

    leftTalons.setInverted(true);
}


    public static double getDriveSpeed() {
        double raw = 0;

     if (OI.xbox.getRawButton(1)) {
        raw = OI.GetXboxLeftJoyY();
        } else {
          raw = OI.GetXboxLeftJoyY()* 0.75;
      }

        return raw;
    }

    public static double getDriveRotation() {
        double raw = 0;

        if (OI.xbox.getRawButton(1)) {
          raw = OI.GetXboxLeftJoyX();
     } else {
         raw = OI.GetXboxLeftJoyX()* 0.5;
     }

      return raw;
    }
}
