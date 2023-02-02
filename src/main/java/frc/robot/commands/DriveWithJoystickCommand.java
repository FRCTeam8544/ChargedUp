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
    double lForwardSpeed = RobotContainer.leftJoystick.getY();
    double rForwardSpeed = RobotContainer.rightJoystick.getY();


    /**Chose one comment the rest out
     * it changes the input to output ratio
     * all can be commented out for linear drive
     */
    //square root function
    //lForwardSpeed = formulas(lForwardSpeed);
    //rForwardSpeed = formulas(rForwardSpeed);

    //normal exponential (x^3)
    //lForwardSpeed = formulaEx(lForwardSpeed);
    //rForwardSpeed = formulaEx(rForwardSpeed);

    //cool exponential (2^x) but better
    lForwardSpeed = formula1(lForwardSpeed);
    rForwardSpeed = formula1(rForwardSpeed);

    drivetrainSubsystem.tankDrive(lForwardSpeed, rForwardSpeed);
    //double turningSpeed = RemoteObjectInvocationHandler
  }

  private double formulaEx (double value) {
    value = Math.pow(value, 3);
    return value;
  }


  private double formulas(double value) {
    
    if (value < 0) {
      value = (Math.sqrt(value))*-1;
    }
    else{
      value = Math.sqrt(value);
    }
    return value;
  }

  private double formula1( double value ){
    if (value > 0){
      value = Math.pow(2, value) - 1;
    }
    else if (value < 0) {
      value = Math.pow(-2, (value * -1)) + 1;
    }
    return value;
  }

  //tank drive, comment one out
 /*  @Override
  public void execute() {
    double leftController = RobotContainer.leftJoystick.getX();
    double rightController = RobotContainer.rightJoystick.getX();

    DrivetrainSubsystem.mode(leftController, rightController); */

    //drivetrainSubsystem.tankDrive(leftController, rightController);
}







