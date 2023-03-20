package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
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

  int x = 0;
  int y = 0;
  int w = 0;
  int phd = 0;
  int fellowship = 1;
  boolean ofTheRing = false;
  boolean stopnow = false;
  boolean openClaw = false;
  boolean needForSpeed = false;
  boolean sickdrift = false;
  //boolean pid = false;
  PIDController pid;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, setPoint;

    public ArmControls(ArmSubsystem armSubsystem, WristSubsystem wristSubsystem, ArmExtenderSubsystem armExtenderSubsystem, ArmPneumaticsSubsystem armPneumaticsSubsystem, WristGoBRRR wristGoBRRR, LedSubsystem ledSubsystem){
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
        addRequirements(armSubsystem, wristSubsystem, armExtenderSubsystem, armPneumaticsSubsystem, wristGoBRRR, ledSubsystem);
      }


    @Override
    public void initialize() {
        armSubsystem.setBreakMode();
        armExtenderSubsystem.setBreakMode();
        pid = new PIDController(kP, kI, kD);
        pid.enableContinuousInput(0, 110);
    }
    
    @Override
    public void execute() {
      double speed = 0;
      double speedw = Constants.armthings.wriststopspeed;//make later
      double speede = 0;

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
        y = 0;
        x = 0;
      }
      //else if (RobotContainer.controller.getPOV() > 45 && RobotContainer.controller.getPOV() <= 135){y = 1;}//exe
      //else if(RobotContainer.controller.getPOV() > 225 && RobotContainer.controller.getPOV() <= 315){y = 2;}

      else if (RobotContainer.controller.getPOV() > 135 && RobotContainer.controller.getPOV() <= 225) {x = 1;}//was both now noth (im a poet)
      else if (RobotContainer.controller.getPOV() > 315 || RobotContainer.controller.getPOV() < 45) {x = 2;}//tis thy high and the low

      else{//does this do anything? no it doesnt
        y = 0;
        x = 0;
      }

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

      if (x == 1){
        speed = Constants.armthings.armspeed * 0.5;
        //speedw = Constants.armthings.wristspeed;
      }
      else if (x == 2){
        speed = Constants.armthings.armspeeddown * -1;
        //speedw = Constants.armthings.wristspeed * -1;
      }
      else if (x == 0) {
        //speed = Constants.armthings.armstopspeed;
        //speedw = Constants.armthings.wriststopspeed;
      }

      /*if (y == 1) {
        speede = Constants.armthings.armexespeed;
      }
      else if (y == 2) {
        speede = Constants.armthings.armexespeed * -1;
      }
      else {speede = 0;}*/


      //if (w == 1) {speedw = Constants.armthings.wristspeed;}
      //else if (w == 2) {speedw = Constants.armthings.wristspeed * -1;}

      if (RobotContainer.controller.getRawButton(7)) {speedw = Constants.armthings.wristspeed;}
      else if (RobotContainer.controller.getRawButton(8)) {speedw = Constants.armthings.wristspeed * -1;}

      if (RobotContainer.controller.getRawButton(6)){wristGoBrrr.iveGotANeed();}
      else if (RobotContainer.controller.getRawButton(5)){wristGoBrrr.forSpeed();}
      

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
          Constants.DriveTrainConstantants.drive = true;
        }
        else{
          ofTheRing = false;
          Constants.DriveTrainConstantants.drive = false;
        }
        
      }

      if (ofTheRing && speed != 0) {
        if (speed > 0){speedw = armSubsystem.god() * 0.4;}
        else {speedw = armSubsystem.god() * 0.3;}

      }

      if (speed == 0){
        armSubsystem.movemotor(pid.calculate(armSubsystem.getEncoder(), setPoint));
        ledSubsystem.bitesTheDust();
      }
      else{
        armSubsystem.movemotor(speed);
        setPoint = armSubsystem.getEncoder();
        SmartDashboard.putNumber("set point", setPoint);
        System.out.println("set point "+setPoint);
        ledSubsystem.zaWauldo();
      }
      wristSubsystem.wristWatch(speedw);
      armExtenderSubsystem.movemotor(speede);
      

      
    }

    /*double controlslb(double speed) {

      if (RobotContainer.controller.getLeftBumperPressed()){speed = speed * -1;}

      return 0;

    }*/
}
