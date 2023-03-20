package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase{//even the a-10 would be jealous
    /* 
    CANSparkMax turretMotor = new CANSparkMax(Constants.turret.turretCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    RelativeEncoder turretEncoder = turretMotor.getEncoder();

    public TurretSubsystem() {
        turretMotor.setInverted(false);
        turretMotor.setIdleMode(IdleMode.kBrake);
    }

    public void turnTurret(double value) {
        turretMotor.set(value);
    }

    */
}
