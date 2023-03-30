package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmExtenderSubsystem;

public class ExtenderCommand extends CommandBase{
    ArmExtenderSubsystem a_extender;
    double distance;//seconds
    boolean finish = false;
    double time;
    double speed;

    Timer timer = new Timer();

    boolean direction;



    public ExtenderCommand(double time, ArmExtenderSubsystem a_extender, boolean direction, double speed){
        this.a_extender = a_extender;
        this.direction = direction;
        this.time = time;
        this.speed = speed;
        addRequirements(a_extender);
    }
    
    @Override
    public void initialize() {
        timer.start();

        
    }

    @Override
    public void execute() {
        if (direction){a_extender.movemotor(speed);}
        else{a_extender.movemotor(speed * -1);}
        isFinished();
    }
    @Override
    public void end(boolean interrupted){
        a_extender.movemotor(0);
    }
    @Override
    public boolean isFinished(){

        System.out.println(time);
        if (timer.get() > time){//once time is greater than the necesary time it returns true
            timer.stop();
            return true;
        }
        else{
            return false;
        }

    }
}
