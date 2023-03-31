package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class WristCommand extends CommandBase{

    WristSubsystem wristSubsystem;
    double setPoint;
    boolean down;

    public WristCommand(double setPoint, WristSubsystem wristSubsystem, boolean down){
        this.setPoint = setPoint;
        this.wristSubsystem = wristSubsystem;
        this.down = down;

    }

    @Override
    public void initialize(){


    }

    @Override
    public void execute(){
        if (down){
            wristSubsystem.wristWatch(-0.5);
        }
        else{
            wristSubsystem.wristWatch(0.5);
        }

    }

    @Override
    public boolean isFinished(){
        if (wristSubsystem.getEncoder() < setPoint + 1 && wristSubsystem.getEncoder() > setPoint - 1){
            return true;
        }
        else{
            return false;
        }
        
    }

    @Override
    public void end(boolean interrupted){
        wristSubsystem.wristWatch(0);

    }
    
}
