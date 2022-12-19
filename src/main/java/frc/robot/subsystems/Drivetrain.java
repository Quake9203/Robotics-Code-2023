package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;   // Should be replaced with MotorControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class Drivetrain {
public static WPI_TalonSRX rightTalonMaster = new WPI_TalonSRX(RobotMap.rightTalonMasterCAN);
public static WPI_TalonSRX rightTalonSlave = new WPI_TalonSRX(RobotMap.rightTalonSlaveCAN);

public static WPI_TalonSRX leftTalonMaster = new WPI_TalonSRX(RobotMap.leftTalonMasterCAN);
public static WPI_TalonSRX leftTalonSlave = new WPI_TalonSRX(RobotMap.leftTalonSlaveCAN);


public static SpeedControllerGroup leftTalons = new SpeedControllerGroup(leftTalonMaster, leftTalonSlave);  // Replace with MotorControllerGroup
public static SpeedControllerGroup rightTalons = new SpeedControllerGroup(rightTalonMaster, rightTalonSlave);  // Replace with MotorControllerGroup

public static DifferentialDrive drive = new DifferentialDrive(rightTalons, leftTalons);


public static void DrivetrainSetup() {
    rightTalonMaster.configFactoryDefault();
    rightTalonSlave.configFactoryDefault();
    leftTalonMaster.configFactoryDefault();
    leftTalonSlave.configFactoryDefault();


    rightTalonSlave.follow(rightTalonMaster);
    leftTalonSlave.follow(leftTalonMaster);

    rightTalonMaster.setInverted(true);
    rightTalonSlave.setInverted(InvertType.FollowMaster);
    leftTalonMaster.setInverted(true);
    leftTalonSlave.setInverted(InvertType.FollowMaster);

    drive.setRightSideInverted(false);
}

public static double getDriveSpeed() {
    double speed = 0;

    if (OI.xbox.getRawButton(1)) {
        speed = OI.GetXboxLeftJoyY();
    } else {
        speed = OI.GetXboxLeftJoyY()* 0.75;
    }

    return speed;
}


public static double getDriveRotation() {
    double rotation = 0;

    if (OI.xbox.getRawButton(1)) {
        rotation = OI.GetXboxLeftJoyX();
    } else {
        rotation = OI.GetXboxLeftJoyX()* 0.5;
    }

    return rotation;
}
}
