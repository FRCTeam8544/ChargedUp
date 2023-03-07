package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.BalanceCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Autobots extends ParallelCommandGroup{


    DrivetrainSubsystem drivetrainSubsystem;

    
    public Autobots(DrivetrainSubsystem drive){
        drive = drivetrainSubsystem;

        addCommands(
      new SequentialCommandGroup(

      new BalanceCommand(drive),


        new SetIdle(IdleMode.kBrake, drive)));

    }
    //add stuff
    
}
