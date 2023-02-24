package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmExtenderSubsystem;

public class ArmExtenderCommand extends CommandBase {

    ArmExtenderSubsystem armExtenderSubsystem;

    int x = 0;

    public ArmExtenderCommand(ArmExtenderSubsystem armExtenderSubsystem) {
        this.armExtenderSubsystem = armExtenderSubsystem;
        addRequirements(armExtenderSubsystem);

    }

    @Override
    public void initialize() {

    }

    
    @Override
    public void execute() {
      double speed = 0;

      if (RobotContainer.controller.getLeftBumperPressed()){x = 1;}
      else if(RobotContainer.controller.getRightBumperPressed()){x = 2;}
      else if (RobotContainer.controller.getBackButtonPressed()){x = 0;}

      //if(RobotContainer.controller.getBackButtonPressed()){Constants.armthings.morecontrol = true;}

      //if (Constants.armthings.morecontrol){speed = controlslb(speed);}

      if (x == 1){speed = Constants.armthings.armspeed;}
      else if (x == 2){speed = Constants.armthings.armspeed * -1;}
      else if (x == 0) {speed = Constants.armthings.armstopspeed;}
      SmartDashboard.putNumber("aksdjhfaksjbhf", speed);

      armExtenderSubsystem.movemotor(speed);
        
    }

    double controlslb(double speed) {

      if (RobotContainer.controller.getLeftBumperPressed()){speed = speed * -1;}

      return 0;

    }

}
