package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristGoBRRR extends SubsystemBase{

    VictorSPX tokyo = new VictorSPX(Constants.armthings.TOKYOID);
    VictorSPX drift = new VictorSPX(Constants.armthings.DRIFTID);
    
    public WristGoBRRR(){//I wonder if you know how they live in tokyo
        tokyo.setInverted(false);
        drift.setInverted(true);

    }

    public void iveGotANeed(){
        tokyo.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT);
        drift.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT);
    }
    public void forSpeed(){
        tokyo.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT * -1);
        drift.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT * -1);
    }
    public void skrrttt(){
        tokyo.set(ControlMode.PercentOutput, 0);
        drift.set(ControlMode.PercentOutput, 0);
    }
}
