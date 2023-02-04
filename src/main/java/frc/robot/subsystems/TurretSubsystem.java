package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;

public class TurretSubsystem {
    public CANSparkMax turretMotor = new CANSparkMax(Constants.TurretConstants.turretCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    RelativeEncoder turretEncoder = turretMotor.getEncoder();
    Spark turretSpark = new Spark(0);


    public TurretSubsystem(){
        turretMotor.restoreFactoryDefaults();
        turretEncoder.setPosition(0);

    }

    public void turretMove(double speed){
        turretSpark.set(speed);
    }

    public void setCoastModeT(){
        turretMotor.setIdleMode(IdleMode.kCoast);
    }
    public void setBreakModeT(){
        turretMotor.setIdleMode(IdleMode.kBrake);
    }
}