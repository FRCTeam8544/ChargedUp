// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.AutonomousCommands.DriveDistance;
import frc.robot.commands.AutonomousCommands.WaitTime;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class AutonomousMiddleCommand extends ParallelCommandGroup {
  DrivetrainSubsystem ar_driveTrain;

  public AutonomousMiddleCommand(DrivetrainSubsystem drive) {
    ar_driveTrain = drive; 

    addCommands(
      new SequentialCommandGroup(
      
      new DriveDistance(80, 5, ar_driveTrain),

      new WaitTime(5),

      new DriveDistance(6,1,ar_driveTrain),
      
      new SetIdle(IdleMode.kCoast, ar_driveTrain) )
    );
  }
}