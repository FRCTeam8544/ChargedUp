// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

//import java.rmi.server.RemoteObjectInvocationHandler;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveWithJoystickCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem drivetrainSubsystem;

  public DriveWithJoystickCommand(DrivetrainSubsystem drivetrainSubsystem){
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize(){
    System.out.println("use joysticks");

  }

  //this gets controller imput and uses it for the tank drive in drivetrainSubsystem
  @Override
  public void execute() {
    double lForwardSpeed = RobotContainer.leftJoystick.getX();
    double rForwardSpeed = RobotContainer.rightJoystick.getX();

    lForwardSpeed = lForwardSpeed/4;
    rForwardSpeed = rForwardSpeed/4;

    drivetrainSubsystem.tankDrive(lForwardSpeed, rForwardSpeed);
    //double turningSpeed = RemoteObjectInvocationHandler
  }

  //tank drive, comment one out
 /*  @Override
  public void execute() {
    double leftController = RobotContainer.leftJoystick.getX();
    double rightController = RobotContainer.rightJoystick.getX();

    DrivetrainSubsystem.mode(leftController, rightController); */

    //drivetrainSubsystem.tankDrive(leftController, rightController);
}







