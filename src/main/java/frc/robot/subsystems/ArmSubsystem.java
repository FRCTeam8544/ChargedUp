package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

public class ArmSubsystem extends SubsystemBase {
    
    CANSparkMax firstJoint = new CANSparkMax(Constants.armthings.jointoneCANID, CANSparkMaxLowLevel.MotorType.kBrushless);

    RelativeEncoder firstJointEncoder = firstJoint.getEncoder();

    Spark firstJointSpark = new Spark(0);

    Victor firstJointVictor = new Victor(0);

    public void DrivetrainSubsystem(){
        firstJoint.restoreFactoryDefaults();
        
        firstJointEncoder.setPosition(0);

        firstJoint.setInverted(false);
    }

    public void setBreakMode() {
        firstJoint.setIdleMode(IdleMode.kBrake);
    }

    public void movejointsspark(int whichone, double howfar) {
        switch(whichone) {
            case 1:
                firstJointSpark.set(howfar);
                break;
        }
    }

    public void movejointsv(int whichone, double howfar) {
        switch (whichone) {
            case 1:
                firstJointVictor.set(howfar);
                break;
        }
    }

}
