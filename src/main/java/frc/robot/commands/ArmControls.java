package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmExtenderSubsystem;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class ArmControls extends CommandBase{

  ArmSubsystem armSubsystem;
  WristSubsystem wristSubsystem;
  ArmExtenderSubsystem armExtenderSubsystem;
  ArmPneumaticsSubsystem armPneumaticsSubsystem;

  int x = 0;
  int y = 0;
  int w = 0;
  int phd = 0;

    public ArmControls(ArmSubsystem armSubsystem, WristSubsystem wristSubsystem, ArmExtenderSubsystem armExtenderSubsystem, ArmPneumaticsSubsystem armPneumaticsSubsystem){
        this.armSubsystem = armSubsystem;
        this.wristSubsystem = wristSubsystem;
        this.armExtenderSubsystem = armExtenderSubsystem;
        addRequirements(armSubsystem, wristSubsystem, armExtenderSubsystem);
      }


    @Override
    public void initialize() {
        armSubsystem.setBreakMode();
    }
    
    @Override
    public void execute() {
      double speed = Constants.armthings.armstopspeed;
      double speedw = Constants.armthings.wriststopspeed;//make later
      double speede = 0;

      if (RobotContainer.controller.getPOV() > 45 && RobotContainer.controller.getPOV() <= 135){y = 1;}
      else if(RobotContainer.controller.getPOV() > 225 && RobotContainer.controller.getPOV() <= 315){y = 2;}
      else{y = 0;}

      if (RobotContainer.controller.getLeftBumperPressed()) {w = 1;}
      else if (RobotContainer.controller.getRightBumperPressed()) {w = 2;}
      else if (RobotContainer.controller.getLeftBumperReleased() && RobotContainer.controller.getRightBumperReleased()){
        w = 0;
      }
      
      if (RobotContainer.controller.getAButtonPressed()){x = 1;}
      else if(RobotContainer.controller.getBButtonPressed()){x = 2;}
      else if (RobotContainer.controller.getAButtonReleased() && RobotContainer.controller.getBButtonReleased()){
        x = 0;
      }
      if (RobotContainer.controller.getXButtonPressed()){
        armPneumaticsSubsystem.apush();

      }


      //if(RobotContainer.controller.getBackButtonPressed()){Constants.armthings.morecontrol = true;}

      //if (Constants.armthings.morecontrol){speed = controlslb(speed);}

      if (x == 1){
        speed = Constants.armthings.armspeed;
        speedw = Constants.armthings.wristspeed;
      }
      else if (x == 2){
        speed = Constants.armthings.armspeed * -1;
        speedw = Constants.armthings.wristspeed * -1;
      }
      else if (x == 0) {
        speed = Constants.armthings.armstopspeed;
        speedw = Constants.armthings.wriststopspeed;
      }

      if (y == 1) {
        speede = Constants.armthings.armexespeed;
      }
      else if (y == 2) {
        speede = Constants.armthings.armexespeed * -1;
      }
      else {speede = 0;}

      SmartDashboard.putNumber("aksdjhfaksjbhf", speed);


      if (w == 1) {speedw = Constants.armthings.wristspeed;}
      else if (w == 2) {speedw = Constants.armthings.wristspeed;}

      armSubsystem.movemotor(speed);
      wristSubsystem.wristWatch(speedw);
      armExtenderSubsystem.movemotor(speede);
        
    }

    /*double controlslb(double speed) {

      if (RobotContainer.controller.getLeftBumperPressed()){speed = speed * -1;}

      return 0;

    }*/

}
