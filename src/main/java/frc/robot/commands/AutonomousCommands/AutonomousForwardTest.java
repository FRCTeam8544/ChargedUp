// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmExtenderSubsystem;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.commands.AutonomousCommands.DriveDistance;
import frc.robot.commands.AutonomousCommands.DriveRotateDegrees;
import frc.robot.commands.AutonomousCommands.PneumaticCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousForwardTest extends SequentialCommandGroup {
  DrivetrainSubsystem ar_driveTrain;
  ArmSubsystem ar_ArmSubsystem;
  WristSubsystem ar_wristSubsystem;
  ArmPneumaticsSubsystem a_pneumatics;
  ArmExtenderSubsystem a_extender;
  public AutonomousForwardTest(DrivetrainSubsystem ar_drivetrain, ArmSubsystem ar_ArmSubsystem, WristSubsystem ar_wristSubsystem, ArmPneumaticsSubsystem a_pneumatics, ArmExtenderSubsystem a_extender) {

    this.ar_ArmSubsystem = ar_ArmSubsystem;
    this.ar_driveTrain = ar_drivetrain;
    this.ar_wristSubsystem = ar_wristSubsystem;
    this.a_pneumatics = a_pneumatics;
    this.a_extender = a_extender;

    addCommands(
      new SequentialCommandGroup(
      
        //new DriveDistance(24, -.4, drive), // test which one goes backward

        //new DriveDistance(0, 0, drive),
      
        //new WaitTime(5),

        // new DriveRotateDegrees(90, .4, drive),

        new PneumaticCommand(false, a_pneumatics),

        // new WaitCommand(.4),

        new ParallelCommandGroup(
          new PIDArm(ar_ArmSubsystem, 30),
          new SequentialCommandGroup(
            new ExtenderCommand(3, a_extender, false, 0.2),

            //new WaitCommand(3),

            new ExtenderCommand(3, a_extender, true, 0.5),

            new WaitCommand(.5),

            new PneumaticCommand(true, a_pneumatics),

            new ExtenderCommand(3, a_extender, false, 0.5),

            new PIDArm(ar_ArmSubsystem, 0).withTimeout(5)

          )
        ),

        new DriveAndBalance(ar_driveTrain, true, false, 108), // fix

        new DriveAndBalance(ar_drivetrain, false, true, 60),
    
        //new MoveArm(5, .2, ar_ArmSubsystem),

        new SetIdle(IdleMode.kCoast, ar_drivetrain) 
      )
    );

  }
}
