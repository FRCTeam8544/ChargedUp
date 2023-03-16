package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmExtenderSubsystem;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class AutoNotCenterCommand extends SequentialCommandGroup{
    
    DrivetrainSubsystem ar_driveTrain;
    ArmSubsystem ar_ArmSubsystem;
    WristSubsystem ar_wristSubsystem;
    ArmPneumaticsSubsystem a_pneumatics;
    ArmExtenderSubsystem a_extender;
    public AutoNotCenterCommand(DrivetrainSubsystem ar_drivetrain, ArmSubsystem ar_ArmSubsystem, WristSubsystem ar_wristSubsystem, ArmPneumaticsSubsystem a_pneumatics, ArmExtenderSubsystem a_extender) {
  
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
  
      new MoveArm(80, ar_ArmSubsystem, ar_wristSubsystem),
  
      //new WaitCommand(3),
  
      new ExtenderCommand(17, a_extender),
  
      new PneumaticCommand(true, a_pneumatics),
  
      new ExtenderCommand(-17, a_extender),
  
      new MoveArm(5, ar_ArmSubsystem, ar_wristSubsystem),
  
        new DriveAndBalance(ar_driveTrain, true, false, 108), // fix

        //new MoveArm(5, .2, ar_ArmSubsystem),
  
        new SetIdle(IdleMode.kCoast, ar_drivetrain) )
      );
  
    }
}
