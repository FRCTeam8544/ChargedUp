package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmSubsystem;

public class ArmControls extends CommandBase{

  ArmSubsystem armSubsystem;

  int x = 0;

    public ArmControls(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
      }


    @Override
    public void initialize() {
        armSubsystem.setBreakMode();
    }
    
    @Override
    public void execute() {
      double speed = Constants.armthings.armstopspeed;

      if (RobotContainer.controller.getPOV() > 45 && RobotContainer.controller.getPOV() <= 135){x = 1;}
      else if(RobotContainer.controller.getPOV() > 225 && RobotContainer.controller.getPOV() <= 315){x = 2;}
      else {x = 0;}

      //if(RobotContainer.controller.getBackButtonPressed()){Constants.armthings.morecontrol = true;}

      //if (Constants.armthings.morecontrol){speed = controlslb(speed);}

      if (x == 1){speed = Constants.armthings.armspeed;}
      else if (x == 2){speed = Constants.armthings.armspeed * -1;}
      else if (x == 0) {speed = Constants.armthings.armstopspeed;}
      SmartDashboard.putNumber("aksdjhfaksjbhf", speed);

      armSubsystem.movemotor(speed);
        
    }

    double controlslb(double speed) {

      if (RobotContainer.controller.getLeftBumperPressed()){speed = speed * -1;}

      return 0;

    }

}
