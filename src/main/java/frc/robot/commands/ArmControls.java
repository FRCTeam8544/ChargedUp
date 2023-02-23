package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmSubsystem;

public class ArmControls extends CommandBase{

    private final ArmSubsystem armSubsystem;

    public ArmControls(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
      }

    @Override
    public void initialize() {
        armSubsystem.setBreakMode();
    }
    
    @Override
    public void execute() {
        double firstJointSpeedup = RobotContainer.controller.getRightTriggerAxis();
        double firstJointSpeeddown = RobotContainer.controller.getLeftTriggerAxis();
        double actualfirstjoint = (firstJointSpeedup - firstJointSpeeddown) / 2;



    }

}
