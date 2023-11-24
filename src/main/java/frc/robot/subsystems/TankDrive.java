
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
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

  public static MotorControllerGroup leftTalons = new MotorControllerGroup(leftTalonMaster, leftTalonSlave);
  public static MotorControllerGroup rightTalons = new MotorControllerGroup(rightTalonMaster, rightTalonSlave);

  public static DifferentialDrive drive = new DifferentialDrive(rightTalons, leftTalons);


  public static void DrivetrainSetup() {
      rightTalonMaster.configFactoryDefault();
      rightTalonSlave.configFactoryDefault();
      leftTalonMaster.configFactoryDefault();
      leftTalonSlave.configFactoryDefault();


      rightTalonSlave.follow(rightTalonMaster);
      leftTalonSlave.follow(leftTalonMaster);

      rightTalonMaster.setInverted(false);
      rightTalonSlave.setInverted(InvertType.FollowMaster);
      leftTalonMaster.setInverted(true);
      leftTalonSlave.setInverted(InvertType.FollowMaster);

  }

  public static double getLeftDriveSpeed() {
      double raw = 0;
// getrawbutton(1) is checking for A button pressed for Turbo
      if (OI.xbox.getRawButton(1)) {
        raw = Math.signum(OI.GetXboxLeftJoyY()) * Math.pow(OI.GetXboxLeftJoyY(), 2);
      } else {
        raw = Math.signum(OI.GetXboxLeftJoyY()) * Math.pow(OI.GetXboxLeftJoyY(), 2) * 0.9;
      }

      return -raw;
    }
    public static double getRightDriveSpeed() {
      double raw = 0;

      if (OI.xbox.getRawButton(1)) {
        raw = Math.signum(OI.GetXboxRightJoyY()) * Math.pow(OI.GetXboxRightJoyY(), 2);
      } else {
        raw = Math.signum(OI.GetXboxRightJoyY()) * Math.pow(OI.GetXboxRightJoyY(), 2) * 0.9;
      }

      return raw;
    }


}
