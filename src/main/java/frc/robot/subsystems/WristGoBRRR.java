package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristGoBRRR extends SubsystemBase{
    boolean tokyoWorks = true, driftWorks = true;
    VictorSPX tokyo;
    VictorSPX drift;


    public WristGoBRRR(){//I wonder if you know how they live in tokyo
        try{
            tokyo = new VictorSPX(Constants.armthings.TOKYOID);
        }catch(Exception e){
            tokyoWorks = false;
        }
    
        try{
            drift = new VictorSPX(Constants.armthings.DRIFTID);
        } catch(Exception f) {
            driftWorks = false;
        }

        if (tokyoWorks){tokyo.setInverted(true);}
        if (driftWorks){drift.setInverted(true);}

        
    }

    public void iveGotANeed(){
        if (tokyoWorks){tokyo.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT);}
        if (driftWorks){drift.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT);}
    }
    public void forSpeed(){
        if (tokyoWorks){tokyo.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT * -1);}
        if (driftWorks){drift.set(ControlMode.PercentOutput, Constants.armthings.TOKYODRIFT * -1);}
    }
    public void skrrttt(){
        if (tokyoWorks){tokyo.set(ControlMode.PercentOutput, 0);}
        if (driftWorks){drift.set(ControlMode.PercentOutput, 0);}
    }
}
