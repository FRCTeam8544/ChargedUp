package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase{
    
    CANSparkMax wristMotor = new CANSparkMax(Constants.armthings.wristCANID, MotorType.kBrushless);
    RelativeEncoder wristEncoder = wristMotor.getEncoder();

    public WristSubsystem() {
        wristEncoder.setPosition(0);
        wristMotor.setIdleMode(IdleMode.kBrake);
        wristMotor.setInverted(true);
    }

    public void wristWatch(double speed) {//Who are to watch the watchmen?
        wristMotor.set(speed);
    }
}
