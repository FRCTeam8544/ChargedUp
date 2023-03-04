package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

public class ArmSubsystem extends SubsystemBase {
    
    CANSparkMax firstJoint = new CANSparkMax(Constants.armthings.jointoneCANID, CANSparkMaxLowLevel.MotorType.kBrushless);

    public RelativeEncoder firstJointEncoder = firstJoint.getEncoder();

    //private SparkMaxLimitSwitch firstlimitforward;
    //private SparkMaxLimitSwitch firstlimitreverse;

    //Spark firstJointSpark = new Spark(0);

    //Victor firstJointVictor = new Victor(0);

    public ArmSubsystem(){
        firstJoint.restoreFactoryDefaults();
        
        firstJointEncoder.setPosition(0);

        firstJoint.setInverted(true);

        //firstlimitforward = firstJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
        //firstlimitreverse = firstJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);

        //firstlimitforward.enableLimitSwitch(true);
        //firstlimitreverse.enableLimitSwitch(true);

    }

    public void setBreakMode() {
        firstJoint.setIdleMode(IdleMode.kBrake);
    }

    public void movemotor(double value) {
        //firstJointSpark.set(value);

        
        firstJoint.set(value);
    }

    public void resetEncoder(RelativeEncoder firstJointEncoder2) {
    }


    /*public void movejointssparktrue(int whichone, double howfar, boolean isspark) {
        if (firstlimitforward.isPressed() == false || firstlimitreverse.isPressed() == false){
            if (isspark){
                switch(whichone) {
                    case 1:
                        firstJointSpark.set(howfar);
                        break;
                }
            }
            else{
                switch (whichone) {
                    case 1:
                        firstJointVictor.set(howfar);
                        break;
                }
            }
        }
        else if (firstlimitforward.isPressed()){
            if(firstlimitforward.isPressed() && howfar < 0) {
                if (isspark){
                    switch(whichone) {
                        case 1:
                            firstJointSpark.set(howfar);
                            break;
                    }
                }
                else{
                    switch (whichone) {
                        case 1:
                            firstJointVictor.set(howfar);
                            break;
                    }
                }
            }
        }
        else if (firstlimitreverse.isPressed()){
            if(firstlimitreverse.isPressed() && howfar > 0) {
                if (isspark){
                    switch(whichone) {
                        case 1:
                            firstJointSpark.set(howfar);
                            break;
                    }
                }
                else{
                    switch (whichone) {
                        case 1:
                            firstJointVictor.set(howfar);
                            break;
                    }
                }
            }
        }
    }*/
}
