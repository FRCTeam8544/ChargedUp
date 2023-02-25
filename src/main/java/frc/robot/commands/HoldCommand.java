package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ArmPneumaticsSubsystem;

public class HoldCommand extends InstantCommand{

    ArmPneumaticsSubsystem armPneumaticsSubsystem;

    public HoldCommand(ArmPneumaticsSubsystem armPneumaticsSubsystem){
        this.armPneumaticsSubsystem = armPneumaticsSubsystem;
        addRequirements(armPneumaticsSubsystem);
    }

    @Override
    public void initialize() {
        armPneumaticsSubsystem.moveoutdaway();
    }
    
}
