// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.AutonomousCommands;

//import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveRotateDegrees extends CommandBase {
  /** Creates a new DriveDistance. */
  DrivetrainSubsystem a_driveTrain;
  double inputedDegrees;
  double inchesToTravel;
  double inputedSpeed;
  boolean returnValue;
  double inchesRotated;
  Timer a_timer;

  public DriveRotateDegrees(double degreesToTravel, double speedPercentage, DrivetrainSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    a_driveTrain = drive;
    inputedDegrees = degreesToTravel;
    inputedSpeed = speedPercentage;
    inchesToTravel = (Math.abs(inputedDegrees)/360 * 21 * Math.PI);
    inchesRotated = a_driveTrain.encoderPositionToDistanceConversion(a_driveTrain.leftEncoder);
    addRequirements(a_driveTrain);

    a_timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_timer.reset();
    
    a_driveTrain.resetEncoder(a_driveTrain.leftEncoder);
    a_driveTrain.resetEncoder(a_driveTrain.rightEncoder);
    //a_driveTrain.setIdleMode(IdleMode.kBrake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    inchesRotated = a_driveTrain.encoderPositionToDistanceConversion(a_driveTrain.leftEncoder);
    //if (Math.abs(inchesRotated) <= 0.85 * inchesToTravel) {
      if (inputedDegrees < 0) {
      a_driveTrain.tankDrive(-inputedSpeed, inputedSpeed);
      }
      if (inputedDegrees > 0) {
        a_driveTrain.tankDrive(inputedSpeed, -inputedSpeed);
      }
    //} else {
    //  if (inputedDegrees < 0) {
    //    a_driveTrain.tankDrive(-0.25 * inputedSpeed, 0.25 * inputedSpeed);
    //    }
    //    if (inputedDegrees > 0) {
    //      a_driveTrain.tankDrive(0.25 * inputedSpeed, -0.25 * inputedSpeed);
    //    }
    //}
    SmartDashboard.putNumber("inchesToTravel", inchesToTravel);
    SmartDashboard.putNumber("Distance Traveled", a_driveTrain.encoderPositionToDistanceConversion(a_driveTrain.leftEncoder));


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_driveTrain.tankDrive(0, 0);
    //a_driveTrain.setIdleMode(IdleMode.kCoast);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (a_driveTrain.currentRM(a_driveTrain.leftEncoder) > 0){
      return (a_driveTrain.currentRM(a_driveTrain.leftEncoder) >= a_driveTrain.distanceToEncoderPositionConversion(inchesToTravel));
    }
    else if (a_driveTrain.currentRM(a_driveTrain.leftEncoder) < 0){
      return (a_driveTrain.currentRM(a_driveTrain.leftEncoder) <= -a_driveTrain.distanceToEncoderPositionConversion(inchesToTravel));  
    }
    else {
      return false;
    }
  }
}
