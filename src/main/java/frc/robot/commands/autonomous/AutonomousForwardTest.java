// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousForwardTest extends ParallelCommandGroup {
  DriveTrain ar_driveTrain;

  public AutonomousForwardTest(DriveTrain drive) {
    ar_driveTrain = drive; 
    addCommands(new SequentialCommandGroup(
      new DriveDistance(inchesToTravel: 35, speedPercentage: 0.75, ar_driveTrain),
      new SetIdle(IdleMode.kCoast, ar_driveTrain)
    ));
  }
}
