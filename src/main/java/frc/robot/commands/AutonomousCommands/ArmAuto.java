// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmAuto extends CommandBase {
  /** Creates a new DriveDistance. */
  double inputedInches;
  double inputedSpeed;
  boolean returnValue;
  double inchesMoved;
  Timer a_timer;
  ArmSubsystem a_ArmSubsystem;

  public ArmAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
    
    addRequirements(a_ArmSubsystem);
    a_timer = new Timer();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_timer.reset();
    a_ArmSubsystem.setBreakMode();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = Constants.armthings.armstopspeed;



  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    //a_driveTrain.setIdleMode(IdleMode.kCoast);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return returnValue;
   
} 
}