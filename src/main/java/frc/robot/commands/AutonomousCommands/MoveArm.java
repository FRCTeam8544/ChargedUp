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
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class MoveArm extends CommandBase {
  /** Creates a new  MoveArm. */
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



  public MoveArm(double AmountToMove,double speedPercentage,ArmSubsystem ar_ArmSubsystem ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(a_ArmSubsystem);

    inputedAmount = AmountToMove;
    inputSpeed = speedPercentage;
    turnsLeft = (inputedAmount/41);

    a_timer = new Timer();

  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_timer.reset();
    a_ArmSubsystem.resetEncoder(a_ArmSubsystem.firstJointEncoder);
  }


  // Called every time the scheduler runs while the command is scheduled.
  
  
  @Override
  public void execute() {
    double encoderPosition = firstJointEncoder.getPosition();
    AmountMoved = (inputedAmount - encoderPosition);
    AmountToMove =(encoderPosition - inputedAmount);
    SmartDashboard.putNumber("Arm height Encoder",encoderPosition);

    a_ArmSubsystem.movemotor(.5);

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
    
    if (a_ArmSubsystem.encoderPercentConversion(a_ArmSubsystem.firstJointEncoder) > 0){
      return (a_ArmSubsystem.encoderPercentConversion(a_ArmSubsystem.firstJointEncoder) >= (inputedAmount));
    }

    else if (a_ArmSubsystem.encoderPercentConversion(a_ArmSubsystem.firstJointEncoder) < 0) {
      return ((-1) * (a_ArmSubsystem.encoderPercentConversion(a_ArmSubsystem.firstJointEncoder)) >= (inputedAmount));
    }
    else {
      return false;
    } 
  }
}
