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
  public static final double NavXRollinit = RobotContainer.ahrs.getRoll();
  public static final double navXPitchinit = RobotContainer.ahrs.getPitch();
  public static final double navXYawinit = RobotContainer.ahrs.getYaw();
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem drivetrainSubsystem;
  int m_val ;
  public int uwu = 0;
  int coastnum = 0;
  int balancenum = 0;
  int balancetri = 4;

  public DriveWithJoystickCommand(DrivetrainSubsystem drivetrainSubsystem){
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

     @Override
  public void initialize(){
    System.out.println("use joysticks");
    drivetrainSubsystem.setCoastMode(); //sets coast mode when initialized
  }

  //this gets controller imput and uses it for the tank drive in drivetrainSubsystem
  @Override
  public void execute() {
    double lForwardSpeed = RobotContainer.leftJoystick.getY();
    double rForwardSpeed = RobotContainer.rightJoystick.getY();
    //SmartDashboard.putNumber("ahrs roll 1", RobotContainer.ahrs.getRoll());
    //linuwux > windowos
    //my opinions >>>>>>>>>> everything else

    //changes between break and coast
    if (RobotContainer.leftJoystick.getRawButtonPressed(12) == true) {
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
    // starts balance
    /*else if (RobotContainer.controller.getBButtonPressed() == true) {
      if (RobotContainer.autobalance ) {RobotContainer.autobalance = false;  SmartDashboard.putString("mode", "auto balance off");} else {RobotContainer.autobalance = true; SmartDashboard.putString("mode", "balancing");} 
    }*/
    //balancetri:1 means pressed on 2 means released on 3 means pressed off 4 means released off
    else if (RobotContainer.leftJoystick.getRawButtonPressed(11) == true) {
      if (balancetri == 4 ||balancetri == 1) {
        balancetri = 1;
        RobotContainer.autobalance = true;
      }
      else if (balancetri == 2 || balancetri == 3) {
        RobotContainer.autobalance = false;
        balancetri = 3;
      }
    }
    else if (RobotContainer.leftJoystick.getRawButtonReleased(11) == true) {
      if(balancetri == 1) {
        balancetri = 2;
      }  
      if (balancetri == 3) {
        balancetri = 4;
      }
    }
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
      lForwardSpeed = BalanceCommand.balancepwease();
      rForwardSpeed = lForwardSpeed;
      //SmartDashboard.putNumber("left speed", lForwardSpeed);
      //SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    else {
      lForwardSpeed = wammy(lForwardSpeed);
      rForwardSpeed = wammy(rForwardSpeed);
      //SmartDashboard.putNumber("left speed", lForwardSpeed);
      //SmartDashboard.putNumber("right speed", rForwardSpeed);
    }

    /*
     * to implement just do:
     * if(Robotcontainer.controller.getAButtonPressed == true) {
     *    m_val = 8;
     *    SmartDashboard.putstring("mode", "sqrt");
     * }
     * the controller works weirdly so pressing the button will return multiple true but using the button pressed 
     * to set a value to do a method later on works well and gets around that issue
     * 
     * the stuff next are all just testing curves we ended up deciding on logrithms as of 2023/02/18
     */
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
    if (Constants.CURRENTRAMPSPEED < 0.5 && Constants.CURRENTRAMPSPEED > -0.5){
      if (balancenum == 5 && up == true) {
        Constants.CURRENTRAMPSPEED = Constants.CURRENTRAMPSPEED + Constants.RAMPSPEEDADJUSTMENT;
        SmartDashboard.putNumber("ramp balance speed", Constants.CURRENTRAMPSPEED);
      }
      else if (balancenum == 5 && up == false){
        Constants.CURRENTRAMPSPEED = Constants.CURRENTRAMPSPEED - Constants.RAMPSPEEDADJUSTMENT;
        SmartDashboard.putNumber("ramp balance speed", Constants.CURRENTRAMPSPEED);
      }
      else if (balancenum < 5) {
        balancenum ++;
      }
      else {
        balancenum = 0;
      }
    }
  }
  /*
  private double balancepwease() {
    double navXPitch = RobotContainer.ahrs.getPitch();
    double navXRoll = RobotContainer.ahrs.getRoll();
    double navXYaw = RobotContainer.ahrs.getYaw();
    double value = 0;
    navXRoll = navXRoll - NavXRollinit;
    navXPitch = navXPitch - navXPitchinit;
    navXYaw = navXYaw - navXYawinit;*/
    /**
     * 17.5 max angle when fully on
     * 0.25 keeps balanced 
     * ^ old robot data ^
     */
    /*
    value = ((Constants.BASESPEED + Constants.CURRENTRAMPSPEED) / Constants.MAXANGLE ) * navXRoll ;
    SmartDashboard.putNumber("constant ramp speed", Constants.CURRENTRAMPSPEED);
    SmartDashboard.putNumber("ahrs pitch", navXPitch);
    SmartDashboard.putNumber("ahrs roll", navXRoll);
    SmartDashboard.putNumber("ahrs yaw", navXYaw);
    return value;
  }*/
}







