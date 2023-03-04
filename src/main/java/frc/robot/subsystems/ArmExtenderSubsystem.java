package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmExtenderSubsystem extends SubsystemBase {
    
    VictorSPX armExtender = new VictorSPX(Constants.armthings.extenderCANID);
    //DigitalInput extenderLimitSwitchf = new DigitalInput(0);;//forward digital input
    //DigitalInput extenderLimitSwitchr = new DigitalInput(1);;//reverse digital input


    public ArmExtenderSubsystem() {

       // armExtender.configFactoryDefault();

        armExtender.setInverted(false);

    }

    public void setBreakMode() {
        armExtender.setNeutralMode(NeutralMode.Brake);
    }


    public void movemotor(double value) {
        //if (extenderLimitSwitchf.get() && extenderLimitSwitchr.get()){armExtender.set(ControlMode.PercentOutput, value);}
        armExtender.set(VictorSPXControlMode.PercentOutput, value);

        //System.out.println("forward:");
        //System.out.println(extenderLimitSwitchf);
        
        //System.out.println("reverse:");
        //System.out.println(extenderLimitSwitchr);

        System.out.println("extender speed:");
        System.out.println(value);
    }
}
