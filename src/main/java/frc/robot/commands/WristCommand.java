package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.WristSubsystem;

public class WristCommand extends CommandBase{
 
    WristSubsystem wristSubsystem;
    double watchmen = 0;
    public WristCommand(WristSubsystem wristSubsystem){
        this.wristSubsystem = wristSubsystem;
        addRequirements(wristSubsystem);
      }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {

        double who = 0;

        if (RobotContainer.controller.getBButtonPressed() == true) {
            who = Constants.armthings.wrist + watchmen;
        }

        wristSubsystem.wristWatch(who);

    }

}
