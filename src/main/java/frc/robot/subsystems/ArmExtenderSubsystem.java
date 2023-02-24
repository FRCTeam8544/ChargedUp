package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXPIDSetConfigUtil;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ArmExtenderSubsystem extends SubsystemBase {
    
    VictorSPX armExtender = new VictorSPX(Constants.armthings.extenderCANID);

    public ArmExtenderSubsystem() {

        armExtender.configFactoryDefault();

        armExtender.setInverted(true);
    }

    public void setBreakMode() {
        armExtender.setNeutralMode(NeutralMode.Brake);
    }


    public void movemotor(double value) {
        
        armExtender.set(ControlMode.PercentOutput, value);
    }




}
