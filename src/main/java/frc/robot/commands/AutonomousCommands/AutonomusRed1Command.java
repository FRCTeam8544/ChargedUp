

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.AutonomousCommands.DriveDistance;
import frc.robot.commands.AutonomousCommands.WaitTime;

public class AutonomusRed1Command extends ParallelCommandGroup {
    DrivetrainSubsystem ar_driveTrain;
    public AutonomusRed1Command(DrivetrainSubsystem drivetrainSubsystem) {
	}
	public void AutonomousRed1Command(DrivetrainSubsystem drive){
    ar_driveTrain = drive; 


    addCommands(
      new SequentialCommandGroup(
      
      new DriveDistance(224, -.5, drive),

      new DriveDistance(0, 0, drive),

      //new ArmAuto(5),

      //new DriveRotateDegrees(180, .2, ar_driveTrain),
      
      new SetIdle(IdleMode.kCoast, drive) )
    );
  }
}

