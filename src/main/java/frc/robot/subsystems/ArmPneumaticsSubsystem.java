package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmPneumaticsSubsystem extends SubsystemBase {

    DoubleSolenoid armpush;
    
    public ArmPneumaticsSubsystem() {
        armpush = new DoubleSolenoid(Constants.armthings.PNEMATICTYPE, Constants.armthings.armout, Constants.armthings.armin);
        off();
    }

    public void moveoutdaway() {
        armpush.set(Value.kForward);
    }

    public void moveback() {
        armpush.set(Value.kReverse);
    }

    public void off() {
        armpush.set(Value.kOff);
    }


}
