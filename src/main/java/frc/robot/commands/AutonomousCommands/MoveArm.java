// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
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
  //ArmSubsystem a_ArmSubsystem;
  double turnsLeft;
  double inputedAmount;
  double speed = Constants.armthings.armstopspeed;
  double FinalArmPosition;
  double AmountToMove;
  double input;
  double AmountMoved;
  private ArmSubsystem a_armSubsystem;
  private WristSubsystem a_wristSubsystem;
  private ArmPneumaticsSubsystem a_pneumatics;



  public MoveArm(double input, ArmSubsystem a_armSubsystem, WristSubsystem a_wristSubsystem, ArmPneumaticsSubsystem a_pneumatics) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.a_armSubsystem = a_armSubsystem;
    this.a_wristSubsystem = a_wristSubsystem;
    this.a_pneumatics = a_pneumatics;
    this.input = input;
    
    addRequirements(a_armSubsystem, a_wristSubsystem, a_pneumatics);

    
    //a_timer = new Timer();

  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_pneumatics.out();
    //a_ArmSubsystem.resetEncoder(a_ArmSubsystem.firstJointEncoder);
  }


  // Called every time the scheduler runs while the command is scheduled.
  
  
  @Override
  public void execute() {
    double encoderPosition = a_armSubsystem.getEncoder();
    SmartDashboard.putNumber("Arm height Encoder",encoderPosition);
    SmartDashboard.putNumber("Arm height amtToMove", AmountToMove);
    if (input > 0){
      a_armSubsystem.movemotor((Constants.armthings.armspeed *-1));
      a_wristSubsystem.wristWatch(a_armSubsystem.god() * 0.3);
    }
    else{
      //add extender after a time limit since encoder is scuffed
      //add led lights be
    }
    

  }

  
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_pneumatics.apush();
    a_armSubsystem.movemotor(0);
    //a_driveTrain.setIdleMode(IdleMode.kCoast);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if (a_armSubsystem.getEncoder() > 0){
      
      return (a_armSubsystem.getEncoder() >= (input));
    }

    else if (a_armSubsystem.getEncoder() < 0) {
      return ((-1) * a_armSubsystem.getEncoder() >= (input));
    }
    else {
      return false;
    } 
  }
}
  
