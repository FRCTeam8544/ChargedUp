// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import java.rmi.server.RemoteObjectInvocationHandler;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveWithJoystickCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem drivetrainSubsystem;
  int m_val ;

  public DriveWithJoystickCommand(DrivetrainSubsystem drivetrainSubsystem){
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }
  /*public CommandBase testbutton() {
    drivetrainSubsystem.testingThings();
    return this;
  }
  public CommandBase testbuttontwo(){
    drivetrainSubsystem.testtwo();
    return this;
  }
   public CommandBase setBreakMode(){
    drivetrainSubsystem.setBreakMode();
    return this;
     }*/

     @Override
  public void initialize(){
    System.out.println("use joysticks");

  }
  //value to determine what formula used
  /*public CommandBase setFormulaValue (int val) {
    m_val = val;
    System.out.println(m_val);
    return this ;
  }*/

  //this gets controller imput and uses it for the tank drive in drivetrainSubsystem
  @Override
  public void execute() {
    double lForwardSpeed = RobotContainer.leftJoystick.getY();
    double rForwardSpeed = RobotContainer.rightJoystick.getY();
    //linuwux > windowos
    //my opinions >>>>>>>>>> everything else

    //RobotContainer.button11.whenPressed(testbuttontwo());
    //RobotContainer.button11.whenReleased(testbutton());
    //button11.onTrue(testbutton());

  

    if (RobotContainer.controller.getYButtonPressed() == true) {
      SmartDashboard.putString("testing 13", "askdfjh"+Math.random());
    }    
    if (RobotContainer.controller.getYButtonReleased() == true) {
      SmartDashboard.putString("released 13", "a;sldfkj"+Math.random());
    }

    if (RobotContainer.controller.getBButtonPressed() == true) {
      m_val = 8;
      SmartDashboard.putString("B button", "using sqrt");
      SmartDashboard.putString("A button", "not in use");
      SmartDashboard.putString("X button", "not in use");
    }
    else if (RobotContainer.controller.getAButtonPressed() == true) {
      m_val = 10;
      SmartDashboard.putString("A button", "using expo");
      SmartDashboard.putString("B button", "not in use");
      SmartDashboard.putString("X button", "not in use");
    }
    else if (RobotContainer.controller.getXButtonPressed() == true) {
      m_val = 12;
      SmartDashboard.putString("X button", "using cool expo (y = 2^x - 1 0r y = -2^-x - 1)");
      SmartDashboard.putString("B button", "not in use");
      SmartDashboard.putString("A button", "not in use");
    }

    if (RobotContainer.controller.getLeftBumperPressed() == true) {
      m_val = 0;
      SmartDashboard.putString("X button", "not in use");
      SmartDashboard.putString("B button", "not in use");
      SmartDashboard.putString("A button", "not in use");
    }


    //sqrt
    if (m_val == 8){
      lForwardSpeed = formulas(lForwardSpeed);
      rForwardSpeed = formulas(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    //normal exponential (x^3)
    else if (m_val == 10) {
      lForwardSpeed = formulaEx(lForwardSpeed);
      rForwardSpeed = formulaEx(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    //cool exponential (2^x) but better
    else if (m_val == 12) {
      lForwardSpeed = formula1(lForwardSpeed);
      rForwardSpeed = formula1(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }

    drivetrainSubsystem.tankDrive(lForwardSpeed, rForwardSpeed);
    //double turningSpeed = RemoteObjectInvocationHandler
  }
  //y=x^2
  private double formulaEx (double value) {
    value = Math.pow(value, 3);
    return value;
  }
  //sqrt
  private double formulas(double value) {
    
    if (value < 0) {
      value = (Math.sqrt(value))*-1;
    }
    else{
      value = Math.sqrt(value);
    }
    return value;
  }
  //y=2^(x) -1 or y=-2^(-x) +1
  //its 727 wysi
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







