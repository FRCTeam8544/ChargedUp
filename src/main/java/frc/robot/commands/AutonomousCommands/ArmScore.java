package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmScore extends CommandBase{//eyes of heaven

    ArmSubsystem armSubsystem;
    double encoderinit;
    PIDController pid;

    double kP, kI, kD;

    public ArmScore(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;

        //kP = 5e-5;
        kP = 0.1; 
        //kI = 1e-6;
        kI = 0;
        kD = 0; 
    }

    @Override
    public void initialize(){
        encoderinit = armSubsystem.getEncoder();
        pid = new PIDController(kP, kI, kD);
        pid.enableContinuousInput(0, 110);
    }

    @Override
    public void execute(){
        armSubsystem.movemotor(pid.calculate(armSubsystem.getEncoder(), encoderinit));
    }

    @Override
    public void end(boolean interupted){
        
    }

    @Override
    public boolean isFinished(){

        return false;
    }
}