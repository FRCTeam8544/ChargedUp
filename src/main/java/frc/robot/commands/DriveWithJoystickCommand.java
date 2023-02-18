// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;
//import java.rmi.server.RemoteObjectInvocationHandler;



public class DriveWithJoystickCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem drivetrainSubsystem;
  int m_val ;
  public int uwu = 0;
  int coastnum = 0;
  int balancenum = 0;

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
    drivetrainSubsystem.setCoastMode(); //sets coast mode when initialized
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

    //changes between break and coast

    if (RobotContainer.controller.getYButtonPressed() == true) {
      coastnum ++;
      if (coastnum % 2 == 0) {
        drivetrainSubsystem.setCoastMode();
        SmartDashboard.putString("coast or break?", "coast");
      }
      else{
        drivetrainSubsystem.setBreakMode();
        SmartDashboard.putString("coast or break?", "break");
      }
    }
    //sets sqrt
    /*
    if (RobotContainer.controller.getBButtonPressed() == true) {
      m_val = 8;
      SmartDashboard.putString("mode", "using sqrt");
    }*/
    //changes input output ratio to be exponential
    /*else if (RobotContainer.controller.getAButtonPressed() == true) {
      m_val = 10;
      SmartDashboard.putString("mode", "using expo");
    }*/
    //changes input output ratio to be cooler exponential(y = 2^x - 1 0r y = -2^-x - 1)
    /*else if (RobotContainer.controller.getLeftTriggerAxis() > 0) {
      m_val = 12;
      SmartDashboard.putString("mode", "using cool expo");
    }*/
    //resets so input = output
    /*else if (RobotContainer.controller.getLeftBumperPressed() == true) {
      m_val = 0;
      SmartDashboard.putString("mode", "not in use");
      RobotContainer.autobalance = false;
    }*/
    // starts balance
    else if (RobotContainer.controller.getRightBumper() == true) {
      if (RobotContainer.autobalance ) {RobotContainer.autobalance = false;  SmartDashboard.putString("mode", "auto balance off");} else {RobotContainer.autobalance = true; SmartDashboard.putString("mode", "balancing");} 
    }
    //logrithms (best option)
    /*else if (RobotContainer.controller.getXButtonPressed() == true) { //actually b button for some reason
      SmartDashboard.putString("mode", "logrithm");
      m_val = 69;
    }*/
    else if (RobotContainer.leftJoystick.getTriggerPressed() == true) {// x button
      wantbalance(false);
    }
    /*else if (RobotContainer.controller.getAButtonReleased() == true) {
      balancenum = 0;
    }*/
    else if (RobotContainer.rightJoystick.getTriggerPressed() == true) {
      wantbalance(true);
    }
    /*else if (RobotContainer.controller.getBButtonReleased()) {
      balancenum = 0;
    }*/


    if (RobotContainer.autobalance){
      lForwardSpeed = balancepwease();
      rForwardSpeed = lForwardSpeed;
      SmartDashboard.putNumber("left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    else {
      lForwardSpeed = wammy(lForwardSpeed);
      rForwardSpeed = wammy(rForwardSpeed);
      SmartDashboard.putNumber("left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    //sqrt function
    /*
    else if (m_val == 8){
        lForwardSpeed = formulas(lForwardSpeed);
        rForwardSpeed = formulas(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    //normal exponential (x^3) function
    else if (m_val == 10) {
      lForwardSpeed = formulaEx(lForwardSpeed);
      rForwardSpeed = formulaEx(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    //cool exponential (2^x) but better function
    else if (m_val == 12) {
      lForwardSpeed = formula1(lForwardSpeed);
      rForwardSpeed = formula1(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    //log
    else if (m_val == 69) {
      lForwardSpeed = wammy(lForwardSpeed);
      rForwardSpeed = wammy(rForwardSpeed);
      SmartDashboard.putNumber("Left speed", lForwardSpeed);
      SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    */

    SmartDashboard.putNumber("actual speed left", lForwardSpeed);
    SmartDashboard.putNumber("actual speed right", rForwardSpeed);
    drivetrainSubsystem.tankDrive(lForwardSpeed, rForwardSpeed);
    //double turningSpeed = RemoteObjectInvocationHandler
  }
  //y=x^2 function
  private double formulaEx (double value) {
    value = Math.pow(value, 3);
    return value;
  }
  //sqrt function
  private double formulas(double value) {
    
    if (value < 0) {
      value = (Math.sqrt(value*(-1)))*-1;
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
  //logrithms
  private double wammy( double value){
    if (value > 0) {
      value = Math.log(((1.264241118*Math.E*value)/2) + 1 );
    }
    else if (value < 0) {
      value = (Math.log(((1.264241118*Math.E*(-1)*value)/2) + 1)) *-1;
    }
    return value;
  }

  private void wantbalance(boolean up) {
    if (Constants.CURRENTRAMPSPEED < 0.1 && Constants.CURRENTRAMPSPEED > -0.1){
      if (balancenum == 10 && up == true) {
        Constants.CURRENTRAMPSPEED = Constants.CURRENTRAMPSPEED + Constants.RAMPSPEEDADJUSTMENT;
        SmartDashboard.putNumber("ramp balance speed", Constants.CURRENTRAMPSPEED);
      }
      if (balancenum == 10 && up == false){
        Constants.CURRENTRAMPSPEED = Constants.CURRENTRAMPSPEED - Constants.RAMPSPEEDADJUSTMENT;
        SmartDashboard.putNumber("ramp balance speed", Constants.CURRENTRAMPSPEED);
      }
      else if (balancenum < 10) {
        balancenum ++;
      }
      else {
        balancenum = 0;
      }
    }
  }

  private double balancepwease() {
    double navXPitch = RobotContainer.ahrs.getPitch();
    double navXRoll = RobotContainer.ahrs.getRoll();
    double value = 0;
    /**
     * 17.5 max angle when fully on
     * 0.25 keeps balanced
     */
    value = ((Constants.BASESPEED + Constants.CURRENTRAMPSPEED) / Constants.MAXANGLE ) * navXRoll * -1 ;
    SmartDashboard.putNumber("constant ramp speed", Constants.CURRENTRAMPSPEED);
    SmartDashboard.putNumber("ahrs", RobotContainer.ahrs.getPitch());
    SmartDashboard.putNumber("ahrs roll", RobotContainer.ahrs.getRoll());
    return value;
  }
}







