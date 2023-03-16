// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.commands.AutonomousCommands.DriveDistance;
import frc.robot.commands.AutonomousCommands.WaitTime;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class AutonomousMiddleCommand extends ParallelCommandGroup {
  DrivetrainSubsystem ar_driveTrain;
  ArmSubsystem ar_ArmSubsystem;
  ArmPneumaticsSubsystem ar_ArmPneumaticsSubsystem;

  public AutonomousMiddleCommand(DrivetrainSubsystem drive, ArmSubsystem moveArm, WristSubsystem moveWrist, ArmPneumaticsSubsystem ph) {
    ar_driveTrain = drive; 
    ar_ArmSubsystem = moveArm;
    ar_ArmPneumaticsSubsystem = ph;
        
    addCommands(
      new SequentialCommandGroup(

      //new ArmScore(moveArm, moveWrist, ph),
      //new MoveArm(0, 0, moveArm, moveWrist, ph)
      
      new DriveDistance(210, -.2, drive),

      //new DriveAndBalance(drive, true),

      new SetIdle(IdleMode.kCoast, drive) )
    );
  }
}
