package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.WristSubsystem;

public class WristCommand extends CommandBase{
 
    //uh oh its gone
    //the horror - francis bacon when reading heart of darkness

    /*WristSubsystem wristSubsystem;
    double who = 0;
    int watches = 0;
    //the
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

        

        if (RobotContainer.controller.getBButtonPressed()) {
            who = Constants.armthings.wrist + watchmen;
        }
        else if (RobotContainer.controller.getXButtonPressed()) {
            who = -1 * (Constants.armthings.wrist + watchmen);
        }
        else if (RobotContainer.controller.getYButtonPressed()) {
            watchmen = who(true);
        }
        else if (RobotContainer.controller.getAButtonPressed()) {
            watchmen = who(false);
        }

        wristSubsystem.wristWatch(who);

    }


    private double who (boolean up) {
        if (watches == 5) {
            if (up == true){who = who + 0.05;}else{who = who - 0.05;}
            watches = 0;
        }        
        else if (watches < 5) {
            watches ++;
        }
        else {watches = 0;}

        return who;
    }*/

}
