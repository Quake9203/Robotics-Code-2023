/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.subsystems.TankDrive;
// import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.ArcadeDrive;
//import frc.robot.subsystems.Drivetrain;
// import frc.robot.subsystems.Elevator;
// import frc.robot.subsystems.Hand;
// import frc.robot.subsystems.Lift;
// import frc.robot.subsystems.Shooter;


 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "Center";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Timer autoTimer = new Timer();
  ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  BuiltInAccelerometer accel = new BuiltInAccelerometer();
  double oldGyro = 0;

  @Override
  public void disabledPeriodic() {
    frc.robot.subsystems.TankDrive.drive.tankDrive(0, 0);
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    gyro.reset();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Center", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    CameraServer.startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // DriverStation.reportError(String.valueOf(gyro.getRate()), false);
    SmartDashboard.putNumber("Gyro", accel.getY());
    SmartDashboard.putNumber("Yaw", gyro.getAngle());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    gyro.reset();
    autoTimer.reset();
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    autoTimer.start();

    // frc.robot.subsystems.TankDrive.DrivetrainSetup();
  }

  /**
   * This function is called periodically during autonomous.
   */
  

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        final double matchTime1 = autoTimer.get();
        double leftAutoSpeedClimb = 0.6695;
        double rightAutoSpeedClimb = -0.65; // a constant speed diff between right and left motors
        
        double leftAutoSpeed1 = 0.515;
        double rightAutoSpeed1 = -0.5; // a constant speed diff between right and left motors
        double yaw = gyro.getAngle();
        double cmdYaw = yaw * 0.05;


        if (matchTime1 <= 1.9) {
          frc.robot.subsystems.TankDrive.drive.tankDrive(leftAutoSpeedClimb - cmdYaw, rightAutoSpeedClimb - cmdYaw);
        } else if (matchTime1 <= 4.3) {
          frc.robot.subsystems.TankDrive.drive.tankDrive(leftAutoSpeed1 - cmdYaw, rightAutoSpeed1 - cmdYaw);
        } else {
          double a = 0.85;
          if (matchTime1 >= 8.5) {
            a = 0.88;
          }
          double ay = oldGyro * a + accel.getY() * (1 - a);

          oldGyro = ay;
          double cmd = -ay * 2.7;
          double limit = 0.55;
          if (matchTime1 >= 9) {
            limit = 0.3;
          }
          
          if (cmd > limit) {
            cmd = limit;
          } else if (cmd < -limit) {
            cmd = -limit;
          }
          frc.robot.subsystems.TankDrive.drive.tankDrive(cmd, -cmd);
        }
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        final double matchTime = autoTimer.get();
        double leftAutoSpeed = 0.515;
        double rightAutoSpeed = -0.4875; // a constant speed diff between right and left motors
        double yaw1 = gyro.getAngle();
        double cmdYaw1 = yaw1 * 0.05;
        
        if (matchTime <= 8.2) {
          frc.robot.subsystems.TankDrive.drive.tankDrive(leftAutoSpeed - cmdYaw1, rightAutoSpeed - cmdYaw1);
          // if (matchTime <= 1.5) {
          // frc.robot.subsystems.Elevator.elevatorVictorMaster.set(ControlMode.PercentOutput, 0.3);
          // } else {
          // frc.robot.subsystems.Elevator.elevatorVictorMaster.set(ControlMode.PercentOutput, 0.075);
          // }
        }
        // frc.robot.subsystems.Lift.liftVictorMaster.set(ControlMode.PercentOutput, 0.75);
        // frc.robot.subsystems.Shooter.shooterVictorSPX.set(ControlMode.PercentOutput, -1);
        // frc.robot.subsystems.Shooter.shooterVictorMaster.set(ControlMode.PercentOutput, -1);
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //frc.robot.subsystems.Drivetrain.drive.arcadeDrive(Drivetrain.getDriveSpeed(), Drivetrain.getDriveRotation());
    // frc.robot.subsystems.Shooter.shooterVictorMaster.set(ControlMode.PercentOutput, Shooter.getShooterSpeed());
    // frc.robot.subsystems.Shooter.shooterVictorSPX.set(ControlMode.PercentOutput, Shooter.getShooterSpeed());
    // frc.robot.subsystems.Lift.liftVictorMaster.set(ControlMode.PercentOutput, Lift.getLiftSpeed());
    // frc.robot.subsystems.Elevator.elevatorVictorMaster.set(ControlMode.PercentOutput, Elevator.getElevatorSpeed());
    frc.robot.subsystems.TankDrive.drive.tankDrive(TankDrive.getLeftDriveSpeed(), TankDrive.getRightDriveSpeed());
    // frc.robot.subsystems.Hand.handTop.setAngle(Hand.getHandPosition());
    // frc.robot.subsystems.Hand.handBottom.setAngle(Hand.getHandPosition());
    // frc.robot.subsystems.Arm.armVictorSPX.set(ControlMode.PercentOutput, Arm.getArmExtension());

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    // frc.robot.subsystems.Arm.armVictorSPX.set(ControlMode.PercentOutput, Arm.getArmExtension());
  }
}
