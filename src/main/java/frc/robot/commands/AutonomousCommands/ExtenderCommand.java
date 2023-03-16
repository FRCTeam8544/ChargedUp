package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmExtenderSubsystem;

public class ExtenderCommand extends CommandBase{
    ArmExtenderSubsystem a_extender;
    double distance;//seconds
    boolean finish = false;

    Timer timer = new Timer();



    public ExtenderCommand(double distance, ArmExtenderSubsystem a_extender){
        this.a_extender = a_extender;
        this.distance = distance;
        addRequirements(a_extender);
    }
    
    @Override
    public void initialize() {
        timer.start();
        
    }

    @Override
    public void execute() {
        a_extender.movemotor(Constants.armthings.armexespeed);

    }
    @Override
    public void end(boolean interrupted){
        a_extender.movemotor(0);
    }
    @Override
    public boolean isFinished(){

        if (timer.get() > calculate(distance)){//once time is greater than the necesary time it returns true
            timer.stop();
            return true;
        }
        else{
            return false;
        }

    }
    public double calculate(double distance){
        double time = distance/Constants.armthings.armexespeed;
        return time;
    }
}
