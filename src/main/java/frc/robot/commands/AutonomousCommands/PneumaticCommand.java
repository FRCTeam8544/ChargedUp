package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmPneumaticsSubsystem;

public class PneumaticCommand extends CommandBase{
    ArmPneumaticsSubsystem a_pneumatics;
    boolean open;
    boolean finish = false;

    public  PneumaticCommand(boolean open, ArmPneumaticsSubsystem a_pneumatics) {

        this.open = open;
        this.a_pneumatics = a_pneumatics;
        addRequirements(a_pneumatics);
    }

    @Override
    public void initialize() {

        if (open){
            a_pneumatics.in();//opens
        }
        else{a_pneumatics.out();}//closes
        finish = !finish;
    }

    @Override
    public void execute() {
        
    }
    @Override
    public void end(boolean interrupted){
        
    }

    @Override
    public boolean isFinished(){
        return finish;
    }
    
}
