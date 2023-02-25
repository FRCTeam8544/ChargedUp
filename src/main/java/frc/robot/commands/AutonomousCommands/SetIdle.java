// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class SetIdle extends CommandBase {
  /** Creates a new SetIdle. */
  IdleMode modeset;
  DrivetrainSubsystem a_drivetrain;
  public SetIdle(IdleMode mode, DrivetrainSubsystem driveTrain) {
    modeset = mode;
    a_drivetrain = driveTrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(a_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (modeset == IdleMode.kCoast) {
      a_drivetrain.setCoastMode();
    }
    if (modeset == IdleMode.kBrake) {
      a_drivetrain.setBreakMode();;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
