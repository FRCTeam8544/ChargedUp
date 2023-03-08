package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.AutonomousCommands.DriveDistance;
import frc.robot.commands.AutonomousCommands.WaitTime;

public class AutonomusRed3Command extends ParallelCommandGroup {
    DrivetrainSubsystem ar_driveTrain;
    public AutonomusRed3Command(DrivetrainSubsystem drivetrainSubsystem) {
	}
	public void AutonomousRed1Command(DrivetrainSubsystem drive){
    ar_driveTrain = drive; 


    addCommands(
      new SequentialCommandGroup(
      
      new DriveDistance(210, -.5, ar_driveTrain),

      new DriveDistance(0, 0, ar_driveTrain),
      

      //new DriveRotateDegrees(180, .2, ar_driveTrain),
      
      new SetIdle(IdleMode.kCoast, ar_driveTrain) )
    );
  }
}

