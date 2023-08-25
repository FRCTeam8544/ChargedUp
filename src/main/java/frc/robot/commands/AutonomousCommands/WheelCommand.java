package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristGoBRRR;

public class WheelCommand extends CommandBase{

    WristGoBRRR tokyo;
    boolean forward;
    public WheelCommand(WristGoBRRR tokyo, boolean forward){
        this.tokyo = tokyo;
        this.forward = forward;

    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute(){
        if (forward){
            tokyo.iveGotANeed();
        }
        else{
            tokyo.forSpeed();
        }
    }

    @Override
    public void end(boolean interupted){
        tokyo.skrrttt();
    }

    @Override
    public boolean isFinished(){
        

        return false;
    }
    
}
