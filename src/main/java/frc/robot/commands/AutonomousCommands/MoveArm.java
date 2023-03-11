// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class MoveArm extends CommandBase {
  /** Creates a new MoveArm. */
  RelativeEncoder firstJointEncoder;
  boolean returnValue;
  Timer a_timer;
  ArmSubsystem a_ArmSubsystem;
  double turnsLeft;
  double inputedAmount;
  double speed = Constants.armthings.armstopspeed;
  double FinalArmPosition;
  double AmountToMove;
  double inputSpeed;
  double AmountMoved;
  private ArmSubsystem a_armSubsystem;
  private WristSubsystem a_wristSubsystem;
  private ArmPneumaticsSubsystem a_pneumatics;



  public MoveArm(double AmountToMove,double speedPercentage, ArmSubsystem a_armSubsystem, WristSubsystem a_wristSubsystem, ArmPneumaticsSubsystem a_pneumatics) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.a_armSubsystem = a_armSubsystem;
    this.a_wristSubsystem = a_wristSubsystem;
    this.a_pneumatics = a_pneumatics;
    addRequirements(a_ArmSubsystem, a_wristSubsystem, a_pneumatics);

    inputedAmount = AmountToMove;
    inputSpeed = speedPercentage;
    turnsLeft = (inputedAmount/41);

    //a_timer = new Timer();

  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_timer.reset();
    //a_ArmSubsystem.resetEncoder(a_ArmSubsystem.firstJointEncoder);
  }


  // Called every time the scheduler runs while the command is scheduled.
  
  
  @Override
  public void execute() {
    double encoderPosition = firstJointEncoder.getPosition();
    AmountMoved = (inputedAmount - encoderPosition);
    AmountToMove =(encoderPosition - inputedAmount);
    SmartDashboard.putNumber("Arm height Encoder",encoderPosition);
    SmartDashboard.putNumber("Arm height amtToMove", AmountToMove);
    if (AmountToMove > 0){
      a_ArmSubsystem.movemotor((Constants.armthings.armspeed));
      a_wristSubsystem.wristWatch(a_armSubsystem.god() * -0.2);
    }

  }
 

  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_ArmSubsystem.movemotor(0);
    //a_driveTrain.setIdleMode(IdleMode.kCoast);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if (a_ArmSubsystem.encoderValue(a_ArmSubsystem.firstJointEncoder) > 0){
      a_pneumatics.apush();
      return (a_ArmSubsystem.encoderValue(a_ArmSubsystem.firstJointEncoder) >= (inputedAmount));
    }

    else if (a_ArmSubsystem.encoderValue(a_ArmSubsystem.firstJointEncoder) < 0) {
      a_pneumatics.apush();
      return ((-1) * (a_ArmSubsystem.encoderValue(a_ArmSubsystem.firstJointEncoder)) >= (inputedAmount));
    }
    else {
      return false;
    } 
  }
}
