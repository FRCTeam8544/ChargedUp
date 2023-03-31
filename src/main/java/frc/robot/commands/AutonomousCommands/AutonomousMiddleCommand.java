// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmExtenderSubsystem;
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
  WristSubsystem wristSubsystem;
  ArmExtenderSubsystem extenderSubsystem;

  public AutonomousMiddleCommand(DrivetrainSubsystem drive, ArmSubsystem moveArm, WristSubsystem wristSubsystem, ArmPneumaticsSubsystem ph, ArmExtenderSubsystem extenderSubsystem) {
    ar_driveTrain = drive; 
    ar_ArmSubsystem = moveArm;
    ar_ArmPneumaticsSubsystem = ph;
    this.wristSubsystem = wristSubsystem;
    this.extenderSubsystem = extenderSubsystem;
        
    addCommands(
      new SequentialCommandGroup(
        new ParallelCommandGroup(
          new MoveArm(40, ar_ArmSubsystem, wristSubsystem, true),
          new ExtenderCommand(2, extenderSubsystem, true, 0.3)
        ),
        new ArmScore(ar_ArmSubsystem).withTimeout(3),

      new SetIdle(IdleMode.kCoast, drive) )
    );
  }
}
