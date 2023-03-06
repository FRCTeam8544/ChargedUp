package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends CommandBase{
    /* 
    private TurretSubsystem turretSubsystem;

    public TurretCommand(TurretSubsystem turretSubsystem){
        this.turretSubsystem = turretSubsystem;
        addRequirements(turretSubsystem);
      }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double speed;        
        if (RobotContainer.controller.getPOV() > 135 && RobotContainer.controller.getPOV() <= 225){speed = -0.25;}
        else if (RobotContainer.controller.getPOV() > 315 || RobotContainer.controller.getPOV() < 45) {speed = 0.25;}
        else {speed = 0;}

        turretSubsystem.turnTurret(speed);

    }

    */ 
}
