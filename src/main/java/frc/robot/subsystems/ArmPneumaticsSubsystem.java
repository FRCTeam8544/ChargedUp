package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmPneumaticsSubsystem extends SubsystemBase {

    DoubleSolenoid armpush;
    Compressor thehub = new Compressor(16, PneumaticsModuleType.REVPH);//probably not needed
    public double phub;

    public ArmPneumaticsSubsystem() {
        armpush = new DoubleSolenoid(16, PneumaticsModuleType.REVPH, 8, 9);
        //off();
        thehub.enableAnalog(90, 115);//delete if issues
        armpush.set(Value.kForward);
    }


    public void apush() {
        armpush.toggle();
    }

    public void out() {
        armpush.set(Value.kReverse);
    }

    public void in() {
        armpush.set(Value.kForward);
    }

    @Override
    public void periodic() {
        //phub = thehub.getPressure();//gave voltage for some reason
        //SmartDashboard.putNumber("pnumatic pressure", phub);
    }

    public void off() {
        armpush.set(Value.kOff);
    }


}
