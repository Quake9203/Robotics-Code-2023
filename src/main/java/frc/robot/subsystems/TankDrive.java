package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;  // Replace with MotorControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.OI;
import frc.robot.RobotMap;

public class TankDrive {
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


    rightTalonSlave.follow(rightTalonMaster); // Tells right slave to follow right master
    leftTalonSlave.follow(leftTalonMaster);   // Tells left slave to follow left master

    rightTalonMaster.setInverted(true);
    rightTalonSlave.setInverted(InvertType.FollowMaster); // Inverts or doesn't invert motor based on if master is inverted or not
    leftTalonMaster.setInverted(true);
    leftTalonSlave.setInverted(InvertType.FollowMaster);

    drive.setRightSideInverted(false);  // Does the method being called even exist?! Cannot find setRightSideInverted in WPIlib docs or Phoenix CTRE docs 
}



/** 
 * Following methods near identical besides button being got
 * Possibly collaspable to one method which takes input for left or right
 */
public static double getLeftDriveSpeed() {
    double speed = 0;

 if (OI.xbox.getRawButton(1)) { // Checking if "turbo" is active
    speed = OI.GetXboxLeftJoyY(); // Gets the up/down position of the left joystick
    } else {
      speed = OI.GetXboxLeftJoyY()* 0.9; // Slowing down when "turbo" is not active
  }
  return speed; // Returns the speed at either 100% or 90%, depending on turbo state
}
  public static double getRightDriveSpeed() {
    double speed = 0;

 if (OI.xbox.getRawButton(1)) {
    speed = OI.GetXboxRightJoyY();
    } else {
      speed = OI.GetXboxRightJoyY()* 0.9;
  }

    return speed;
}


}