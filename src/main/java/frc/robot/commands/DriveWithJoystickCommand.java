// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;
//import java.rmi.server.RemoteObjectInvocationHandler;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.SwerveSubsystem;



public class DriveWithJoystickCommand extends CommandBase {
  public static final double NavXRollinit = RobotContainer.ahrs.getRoll();
  public static final double navXPitchinit = RobotContainer.ahrs.getPitch();
  public static final double navXYawinit = RobotContainer.ahrs.getYaw();
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem drivetrainSubsystem;
  private final SwerveSubsystem swerveSubsystem;
  int m_val ;
  public int uwu = 0;
  int coastnum = 0;
  int balancenum = 0;
  int balancetri = 4;
  boolean swerve = false;
  double thirtySixtyEncoder = 360;//spin the wheels 360 degrees and change this value to the encoders
  double xPos, yPos, angle, swerveSpeed, twist, kP, kI, kD, multiplyer = thirtySixtyEncoder / 360;
  double topLeft, topRight, bottomLeft, bottomRight;
  PIDController swervePID;

  public DriveWithJoystickCommand(DrivetrainSubsystem drivetrainSubsystem, SwerveSubsystem swerveSubsystem){
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.swerveSubsystem = swerveSubsystem;
     //kP = 5e-5;
     kP = 0.1; 
     //kI = 1e-6;
     kI = 0;
     kD = 0; 
    addRequirements(drivetrainSubsystem, swerveSubsystem);
  }

     @Override
  public void initialize(){
    System.out.println("use joysticks");
    drivetrainSubsystem.setCoastMode(); //sets coast mode when initialized
    swervePID = new PIDController(kP, kI, kD);
  }

  //this gets controller imput and uses it for the tank drive in drivetrainSubsystem
  @Override
  public void execute() {
    if (!swerve){
    if (RobotContainer.rightJoystick.getRawButtonPressed(12)){swerve = true;}
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
      SmartDashboard.putBoolean("auto balance", true);
      //SmartDashboard.putNumber("left speed", lForwardSpeed);
      //SmartDashboard.putNumber("right speed", rForwardSpeed);
    }
    else {
      lForwardSpeed = wammy(lForwardSpeed);
      rForwardSpeed = wammy(rForwardSpeed);
      SmartDashboard.putBoolean("auto balance", false);
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
    SmartDashboard.putNumber("navroll", RobotContainer.ahrs.getRoll());
    drivetrainSubsystem.tankDrive(lForwardSpeed, rForwardSpeed);
    //double turningSpeed = RemoteObjectInvocationHandler
  }
  else{
    /**
     * Hello!
     * This was made without a swerve drive
     * does it work you ask
     * well I dont know
     * check the subsystem for more information
     */
    yPos = RobotContainer.rightJoystick.getY();
    xPos = RobotContainer.rightJoystick.getX();
    twist = RobotContainer.rightJoystick.getTwist();
    swerveSpeed = wammy(Math.sqrt(yPos*yPos + xPos*xPos));

    
    
    if (twist < 0.1 && twist > -0.1){
      angle = Math.atan(yPos/xPos);
      /*if (yPos > 0 && xPos < 0){//II
        angle = 180 - angle;
      }
      else if (yPos < 0){
        if (xPos < 0){//III
          angle = angle + 180;
        }
        else if (xPos > 0){//VI
          angle = angle - 360;
        }
      }*/
      //has to be done like this because the encoders start facing forwards at the position 0
      if (yPos > 0){
        if (xPos > 0){//IV
          angle = 360 - angle;
        }
        else if (xPos < 0){//III
          angle = angle + 180;
        }
      }
      else if (yPos < 0){
        if (xPos > 0){//II
          angle = 180 - angle;
        }
        //if xPos is greater than zero but the yPos is negative it is qI for the motor
      }
      topLeft = swervePID.calculate(swerveSubsystem.swerveEncoders(1) * multiplyer, angle);
      topRight = swervePID.calculate(swerveSubsystem.swerveEncoders(2) * multiplyer, angle);
      bottomLeft = swervePID.calculate(swerveSubsystem.swerveEncoders(3) * multiplyer, angle);
      bottomRight = swervePID.calculate(swerveSubsystem.swerveEncoders(4) * multiplyer, angle);
      
    }//twist bool
    else if (twist < 0.9 && twist > -0.9){
      angle = twist * 45;
      topLeft = swervePID.calculate(swerveSubsystem.swerveEncoders(1) * multiplyer, angle);
      topRight = swervePID.calculate(swerveSubsystem.swerveEncoders(2) * multiplyer, angle);
      bottomLeft = swervePID.calculate(swerveSubsystem.swerveEncoders(3) * multiplyer, angle * -1);
      bottomRight = swervePID.calculate(swerveSubsystem.swerveEncoders(4) * multiplyer, angle * -1);
    
    }
    else{
      topLeft = swervePID.calculate(swerveSubsystem.swerveEncoders(1) * multiplyer, 45);
      topRight = swervePID.calculate(swerveSubsystem.swerveEncoders(2) * multiplyer, 135);
      bottomLeft = swervePID.calculate(swerveSubsystem.swerveEncoders(3) * multiplyer, 315);
      bottomRight = swervePID.calculate(swerveSubsystem.swerveEncoders(4) * multiplyer, 225);
      
      
    }
    swerveSubsystem.lightningMcQueen(swerveSpeed, topLeft, topRight, bottomLeft, bottomRight);
  }
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







