// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveDistance extends CommandBase {
  /** Creates a new DriveDistance. */
  DrivetrainSubsystem a_driveTrain;
  double inputedInches;
  double inputedSpeed;
  boolean returnValue;
  double inchesMoved;
  Timer a_timer;

  public DriveDistance(double inchesToTravel, double speedPercentage, DrivetrainSubsystem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    a_driveTrain = drive;
    inputedInches = inchesToTravel;
    inputedSpeed = speedPercentage;
    inchesMoved = a_driveTrain.encoderPositionToDistanceConversion(a_driveTrain.rightEncoder);
    
    addRequirements(a_driveTrain);

    a_timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_timer.reset();

    a_driveTrain.resetEncoder(a_driveTrain.leftEncoder);
    a_driveTrain.resetEncoder(a_driveTrain.rightEncoder);
    a_driveTrain.leftControllerGroup.setInverted(!Constants.DriveTrainConstantants.leftDriveEncoderInverted);
    a_driveTrain.righControllerGroup.setInverted(!Constants.DriveTrainConstantants.rightDriveEncoderInverted);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    inchesMoved = a_driveTrain.encoderPositionToDistanceConversion(a_driveTrain.rightEncoder);
    //if (Math.abs(inchesMoved) <= 0.85 * inputedInches) {
    SmartDashboard.putNumber("autonomous speed", inputedSpeed);
    a_driveTrain.tankDrive(inputedSpeed, inputedSpeed);
    //} else {
    //  a_driveTrain.tankDrive(0.25 * inputedSpeed, 0.25 * inputedSpeed);
    //}

    SmartDashboard.putNumber("Distance Traveled", a_driveTrain.encoderPositionToDistanceConversion(a_driveTrain.rightEncoder));

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_driveTrain.tankDrive(0, 0);
    a_driveTrain.leftControllerGroup.setInverted(Constants.DriveTrainConstantants.leftDriveEncoderInverted);
    a_driveTrain.righControllerGroup.setInverted(Constants.DriveTrainConstantants.rightDriveEncoderInverted);
    //a_driveTrain.setIdleMode(IdleMode.kCoast);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //SmartDashboard.putNumber("currentRM", a_driveTrain.currentRM(a_driveTrain.leftEncoder));
    
    if (a_driveTrain.currentRM(a_driveTrain.leftEncoder) > 0){
      return (a_driveTrain.currentRM(a_driveTrain.leftEncoder) >= a_driveTrain.distanceToEncoderPositionConversion(inputedInches));
    }

    else if (a_driveTrain.currentRM(a_driveTrain.leftEncoder) < 0) {
      return ((-1) * (a_driveTrain.currentRM(a_driveTrain.leftEncoder)) >= a_driveTrain.distanceToEncoderPositionConversion(inputedInches));
    }
    else {
    
      return false;
    }
  }
} 
