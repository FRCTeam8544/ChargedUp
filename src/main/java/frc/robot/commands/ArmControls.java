package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.led;
import frc.robot.commands.AutonomousCommands.PIDArm;
import frc.robot.subsystems.ArmExtenderSubsystem;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.WristGoBRRR;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.utils.math.Point;

public class ArmControls extends CommandBase{

  ArmSubsystem armSubsystem;
  WristSubsystem wristSubsystem;
  ArmExtenderSubsystem armExtenderSubsystem;
  ArmPneumaticsSubsystem armPneumaticsSubsystem;
  WristGoBRRR wristGoBrrr;
  LedSubsystem ledSubsystem;
  
  
  boolean ramp = false;

  int x = 0;
  int y = 0;
  int w = 0;
  int phd = 0;
  int adjust = 100;//when over 90 it does nothing
  boolean goinup = false;
  int fellowship = 1;
  boolean ofTheRing = false;
  boolean stopnow = false;
  boolean openClaw = false;
  boolean needForSpeed = false;
  boolean sickdrift = false;
  boolean isrun = false;
  double wsetPoint = 0;
  double wajah = 0;//wajah
  double multiplyer = 0.4;
  //boolean pid = false;
  PIDController pid;
  int rampUpGo = 0;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, setPoint;

    public ArmControls(ArmSubsystem armSubsystem, WristSubsystem wristSubsystem, ArmExtenderSubsystem armExtenderSubsystem, ArmPneumaticsSubsystem armPneumaticsSubsystem, WristGoBRRR wristGoBrrr, LedSubsystem ledSubsystem){
        this.armSubsystem = armSubsystem;
        this.wristSubsystem = wristSubsystem;
        this.armExtenderSubsystem = armExtenderSubsystem;
        this.armPneumaticsSubsystem = armPneumaticsSubsystem;
        this.wristGoBrrr = wristGoBrrr;
        this.ledSubsystem = ledSubsystem;
        //kP = 5e-5;
        kP = 0.1; 
        //kI = 1e-6;
        kI = 0;
        kD = 0; 
        this.setPoint = setPoint;
        addRequirements(armSubsystem, wristSubsystem, armExtenderSubsystem, armPneumaticsSubsystem, wristGoBrrr, ledSubsystem);
      }


    @Override
    public void initialize() {
        armSubsystem.setBreakMode();
        armExtenderSubsystem.setBreakMode();
        pid = new PIDController(kP, kI, kD);
        pid.enableContinuousInput(0, 110);
        setPoint = armSubsystem.getEncoder();
    }
    
    @Override
    public void execute() {
      double speed = 0;
      double speedw = Constants.armthings.wriststopspeed;//make later
      double speede = 0;

      // purple is one yellow is two red is three and blue is four
      if (RobotContainer.controller.getRawButton(9)){
        if (RobotContainer.controller.getRawButtonPressed(1)){
          ledSubsystem.madeInHeaven(4);
          ledSubsystem.slay = false;
        }
        else if (RobotContainer.controller.getRawButtonPressed(3)){
          ledSubsystem.madeInHeaven(3);
          ledSubsystem.slay = false;
        }
        else if (RobotContainer.controller.getRawButtonPressed(2)){
          ledSubsystem.madeInHeaven(1);
          ledSubsystem.slay = false;
        }
        else if (RobotContainer.controller.getRawButtonPressed(4)){
          ledSubsystem.madeInHeaven(2);
          ledSubsystem.slay = false;
        }
        if (RobotContainer.controller.getRawButtonPressed(7)){
          ledSubsystem.eyesOfHeaven = true;
        }
        if (RobotContainer.controller.getPOV() > 135 && RobotContainer.controller.getPOV() <= 225){
          ledSubsystem.slay = true;
        }
      }
      else{//might cause big issues

      /* axis for xbox controller
       * 
       * 
       */
      /*
      XboxController controller = RobotContainer.controller;
      // left controller left and right
      SmartDashboard.putNumber("XBoxControllerAxis0", controller.getRawAxis(0));
      // left controller up and down 
      SmartDashboard.putNumber("XBoxControllerAxis1", controller.getRawAxis(1));
      // right controller left and right
      SmartDashboard.putNumber("XBoxControllerAxis2", controller.getRawAxis(2));
      // right contoller up and down
      SmartDashboard.putNumber("XBoxControllerAxis3", controller.getRawAxis(3));
  
      Point leftControllerPoint = new Point(controller.getRawAxis(0), controller.getRawAxis(1));
      Point RightControllerPoint = new Point(controller.getRawAxis(2), controller.getRawAxis(3));
*/
      //speede = RightControllerPoint.y / 2;

      if (RobotContainer.controller.getPOV() == -1) {
        
      }
      //else if (RobotContainer.controller.getPOV() > 45 && RobotContainer.controller.getPOV() <= 135){y = 1;}//exe
      //else if(RobotContainer.controller.getPOV() > 225 && RobotContainer.controller.getPOV() <= 315){y = 2;}

      else if (RobotContainer.controller.getPOV() > 135 && RobotContainer.controller.getPOV() <= 225) {speed = 0.3;}//was both now noth (im a poet)
      else if (RobotContainer.controller.getPOV() > 315 || RobotContainer.controller.getPOV() < 45) {speed = 0.3;}//tis thy high and the low


      //if (RobotContainer.controller.getButtonStateA() == false && RobotContainer.controller.getButtonStateY() == false) {speede = 0;}
      if (RobotContainer.controller.getButtonStateB()) {speede = Constants.armthings.armexespeed;}
      if (RobotContainer.controller.getButtonStateY()) {speede = Constants.armthings.armexespeed * -1;}


      /*if (RobotContainer.controller.getLeftBumperPressed()) {x = 1;}
      else if (RobotContainer.controller.getRightBumperPressed()) {x = 2;}
      else if (RobotContainer.controller.getLeftBumperReleased() == true && RobotContainer.controller.getRightBumperReleased() == true){x = 0;}
      */
      
      /*
      if (RobotContainer.controller.getRawButtonPressed(1) == true){
        //speedw = Constants.armthings.wristspeed;
        w = 1;
      }//both
      else if(RobotContainer.controller.getRawButtonPressed(2) == true){
        //speedw = Constants.armthings.wristspeed * -1;
        w = 2;
      }
      else if (RobotContainer.controller.getRawButtonReleased(1) == true && RobotContainer.controller.getRawButtonReleased(2) == true){
        w = 0;
      }//one fish two fish red fish blue fish
      */

      if (RobotContainer.controller.getRawAxis(3) > 0.1 || RobotContainer.controller.getRawAxis(3) < -0.1){speede = RobotContainer.controller.getRawAxis(3) / 2;}
      //if  (RobotContainer.controller.getRawAxis(2) > 0.2){w = 1;}
      //if (RobotContainer.controller.getRawAxis(3) > 0.2) {w = 2;}
  
      //speedw = RobotContainer.controller.get

      /*if (RobotContainer.controller.getAButtonReleased() && RobotContainer.controller.getBButtonReleased()){
        speedw = 0;
        w = 3;
      }*/


      /*if (RobotContainer.controller.getXButtonPressed()){
        armPneumaticsSubsystem.in();
      }
      if (RobotContainer.controller.getBButtonPressed()){
        armPneumaticsSubsystem.out();
      }*/
      if (RobotContainer.controller.getRawButtonPressed(2)) {
        armPneumaticsSubsystem.apush();
        if (openClaw) {openClaw = false;}
        else{openClaw = true;}
        SmartDashboard.putBoolean("claw", openClaw);
      }//chech button num A

      //if(RobotContainer.controller.getBackButtonPressed()){Constants.armthings.morecontrol = true;}

      //if (Constants.armthings.morecontrol){speed = controlslb(speed);}

      
      if (RobotContainer.controller.getRawAxis(1) > 0.05){ramp = false;speed = RobotContainer.controller.getRawAxis(1) * 0.3;}
      else if (RobotContainer.controller.getRawAxis(1) < -0.05){ramp = false;speed = RobotContainer.controller.getRawAxis(1) * 0.3;}

      //if (RobotContainer.controller.getPOV() == -1){wajah = 0;}
      //if (RobotContainer.controller.getPOV() > 135 && RobotContainer.controller.getPOV() <= 225) {speed = poswammy(up())*0.3;goinup = true;}//was both now noth (im a poet)
      //else if (RobotContainer.controller.getPOV() > 315 || RobotContainer.controller.getPOV() < 45) {speed = negwammy(up())*0.1;goinup = false;}//tis thy high and the low

      /*if (RobotContainer.controller.getRawAxis(1) < 0.5 && RobotContainer.controller.getRawAxis(1) > -0.5 && RobotContainer.controller.getPOV() == -1){
        if (wajah > 0.05 || wajah < -0.05){
          if (goinup){speed = negwammy(stop());}
          else {speed = poswammy(stop());}
        }
        else{speed = 0;}
      }*/


      /*if (y == 1) {
        speede = Constants.armthings.armexespeed;
      }
      else if (y == 2) {
        speede = Constants.armthings.armexespeed * -1;
      }
      else {speede = 0;}*/


      //if (w == 1) {speedw = Constants.armthings.wristspeed;}
      //else if (w == 2) {speedw = Constants.armthings.wristspeed * -1;}

      if (RobotContainer.controller.getRawButton(7)) {speedw = Constants.armthings.NOTAUTOwristspeed;}
      else if (RobotContainer.controller.getRawButton(8)) {speedw = Constants.armthings.NOTAUTOwristspeed * -1;}

      if (RobotContainer.controller.getRawButton(6)){wristGoBrrr.iveGotANeed();}
      else if (RobotContainer.controller.getRawButton(5)){wristGoBrrr.forSpeed();}
      else{wristGoBrrr.skrrttt();}
      

      //removed since some people dont like to follow instructions
      /*if (RobotContainer.controller.getButtonStateX()) {//check num should coralate with X button
        if(stopnow) {stopnow = false;}
        else{ stopnow = true;}
      }
      if (stopnow == true){
        x = 0;
        y = 0;
        w = 0;
        speed = 0;
        speede = 0;
        speedw = 0;
      }*/
      //if (RobotContainer.controller.getRightTriggerAxis() > 0) {stopnow = false;}
      if (RobotContainer.controller.getRawButtonPressed(1)){
        fellowship ++;
        if (fellowship % 2 == 0){
          ofTheRing = true;
        }
        else{
          ofTheRing = false;
        }
        
      }

      /*if (ofTheRing && speed != 0  && RobotContainer.controller.getRawAxis(2) < 0.02 && RobotContainer.controller.getRawAxis(2) > -0.02) {
        if (speed > 0){speedw = armSubsystem.god() * 0.8;}
        else {speedw = armSubsystem.god() * 0.6;}

      }*/

      //buttons might be mapped wrong
      //if (RobotContainer.controller.getRawButtonPressed(9)){setPoint = 30;}//change depending on the angle for goals
      //if (RobotContainer.controller.getRawButtonPressed(10)){setPoint = 60;}
      if (speed == 0 && !ramp){
        armSubsystem.movemotor(pid.calculate(armSubsystem.getEncoder(), setPoint));//adjust is a test for moving the arm to the scoring position with a single button
        /*if (adjust >= 90){
          armSubsystem.movemotor(pid.calculate(armSubsystem.getEncoder(), setPoint));
        }
        else if (adjust > setPoint){
          armSubsystem.movemotor(adjustUp(adjust, setPoint));
        }
        else if (adjust < setPoint){
          armSubsystem.movemotor(adjustDown(adjust, setPoint));
        }*/
      }
      else{
        armSubsystem.movemotor(speed);
        setPoint = armSubsystem.getEncoder();
        //wsetPoint = setPoint * 1;//change
        SmartDashboard.putNumber("set point", setPoint);
        System.out.println("set point "+setPoint);
        //ledSubsystem.zaWauldo = true;
        //ledSubsystem.bitesTheDust = false;
        //adjust = 100;
      }
      wristSubsystem.wristWatch(speedw);
      armExtenderSubsystem.movemotor(speede);
    }
    }

    private double up(){
      if (wajah != 1){
        wajah += 0.02;
      }
      return wajah;
    }

    private double stop(){
      if (goinup){
        wajah -= 0.05;
      }
      else{
        wajah += 0.05;
      }
      return wajah;
    }

    private double poswammy(double value){
      if (value == 1){return 1;}
      value = Math.log(((1.264241118*Math.E*value)/2) + 1 );
      return value;
    }

    private double negwammy(double value){
      if (value == 1){return -1;}
      value = Math.log(((1.264241118*Math.E*value)/2) + 1 ) * -1;
      return value;
    }
//dont even worry about this
    /*private double adjustUp(int currentPos, double initPos){
      //adjust is the position its moving to
      multiplyer = (adjust - initPos)/adjust;
      if (multiplyer > 0.4){multiplyer = 0.4;}
      else if (multiplyer < 0.05){multiplyer = 0.05;}
      double speed;
      double fthird = (adjust - initPos) / 3;
      double sthird = fthird * 2;
      if (currentPos < fthird){
        speed = currentPos / (fthird);
      }
      else if (currentPos < sthird){
        speed = 1;
      }
      else if (currentPos > adjust - 0.5 || currentPos < adjust + 0.5){
        speed = 0;
      }
      else{
        speed = (currentPos / (-1 * adjust)) + 1;
      }
      if (speed * multiplyer < 0.05){
        return 0.05;
      }
      return speed * multiplyer;
    }

    private double adjustDown(int currentPos, double initPos){
      multiplyer = (adjust - initPos)/adjust;
      if (multiplyer > 0.4){multiplyer = 0.4;}
      else if (multiplyer < 0.05){multiplyer = 0.05;}
      double speed;
      double fthird = (initPos - adjust) / 3;
      double sthird = fthird * 2;
      if (currentPos < fthird){
        speed = (currentPos / (-1 * adjust)) + 1;
      }
      else if (currentPos < sthird){
        speed = 1;
      }
      else if (currentPos > adjust - 0.5 || currentPos < adjust + 0.5){
        speed = 0;
      }
      else{
        speed = currentPos / (fthird);
      }
      if ((speed * multiplyer)/2 < 0.05){
         return -0.05;
      }
      return speed * multiplyer * -0.5;
    }*/

    /*double controlslb(double speed) {

      if (RobotContainer.controller.getLeftBumperPressed()){speed = speed * -1;}

      return 0;

    }*/
}
