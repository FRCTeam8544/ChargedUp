package frc.robot.commands.AutonomousCommands;

import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class PIDArm extends CommandBase{

    ArmSubsystem armSubsystem;
    PIDController pid;

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, setPoint;

    public PIDArm(ArmSubsystem armSubsystem, double setPoint) {
        this.armSubsystem = armSubsystem;
        this.setPoint = setPoint;
        
        //kP = 5e-5;
        kP = 0.1; 
        //kI = 1e-6;
        kI = 0;
        kD = 0; 
        
       

       
        /*kIz = 0; 
        kFF = 0.000156; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;*/
    
        /*armPid.setP(kP);
        armPid.setI(kI);
        armPid.setD(kD);*/
       /* armPid.setIZone(kIz);
        armPid.setFF(kFF);
        armPid.setOutputRange(kMinOutput, kMaxOutput);*/

    }

    @Override
    public void initialize() {
        pid = new PIDController(kP, kI, kD);
        pid.enableContinuousInput(0, 90);
        //pid.
        //pid.setTolerance(1);
        
    }

    @Override
    public void execute() {
        double pidval = pid.calculate(Math.abs(armSubsystem.getEncoder()), setPoint) * 0.2;
        armSubsystem.movemotor(pidval);
        SmartDashboard.putNumber("PID val: Encoder Pos :", armSubsystem.getEncoder());
    }

    @Override
    public void end(boolean interupted){
        armSubsystem.movemotor(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
   

}