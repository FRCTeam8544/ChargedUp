package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmPneumaticsSubsystem;

public class ReleaseCommand extends InstantCommand{

    ArmPneumaticsSubsystem armPneumaticsSubsystem;

    public ReleaseCommand(ArmPneumaticsSubsystem armPneumaticsSubsystem){
        this.armPneumaticsSubsystem = armPneumaticsSubsystem;
        addRequirements(armPneumaticsSubsystem);
    }

    @Override
    public void initialize() {
        //armPneumaticsSubsystem.moveback();
    }
    
}

