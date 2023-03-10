/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.ArcadeDrive;
//import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hand;
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
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Timer autoTimer = new Timer();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
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
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    autoTimer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        final double matchTime = autoTimer.get();
        if (matchTime > 13.0){
          frc.robot.subsystems.TankDrive.drive.tankDrive(0.6, 0.6);
        }
        frc.robot.subsystems.Lift.liftVictorMaster.set(ControlMode.PercentOutput, 0.75);
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
    frc.robot.subsystems.Elevator.elevatorVictorMaster.set(ControlMode.PercentOutput, Elevator.getElevatorSpeed());
    frc.robot.subsystems.TankDrive.drive.tankDrive(TankDrive.getLeftDriveSpeed(), TankDrive.getRightDriveSpeed());
    frc.robot.subsystems.Hand.hand.setAngle(Hand.getHandPosition());
    frc.robot.subsystems.Arm.armVictorSPX.set(ControlMode.PercentOutput, Arm.getArmExtension());

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    frc.robot.subsystems.Hand.hand.setAngle(Hand.getHandPosition());
  }
}
