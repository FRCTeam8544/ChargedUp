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
    Compressor thehub = new Compressor(16, PneumaticsModuleType.REVPH);
    public double pressurehub = thehub.getPressure();


    public ArmPneumaticsSubsystem() {
        armpush = new DoubleSolenoid(16, PneumaticsModuleType.REVPH, 8, 9);
        //off(); no idea what this does so its commented out
        armpush.set(Value.kForward);
        thehub.enableAnalog(80, 115);
    }

    
    public void apush() {
        armpush.toggle();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("pnumatic pressure", pressurehub);
    }

    public void off() {
        armpush.set(Value.kOff);
    }


}
