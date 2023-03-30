package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

public class ArmSubsystem extends SubsystemBase {
    
    CANSparkMax firstJoint = new CANSparkMax(Constants.armthings.jointoneCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax twooooooo = new CANSparkMax(Constants.armthings.twoooooooCANID, CANSparkMaxLowLevel.MotorType.kBrushless);

    MotorControllerGroup armGroup = new MotorControllerGroup(firstJoint, twooooooo);//might cause issues delete if necessary

    public RelativeEncoder firstJointEncoder = firstJoint.getEncoder();

    public SparkMaxPIDController armPid = firstJoint.getPIDController();

    //private SparkMaxLimitSwitch firstlimitforward;
    //private SparkMaxLimitSwitch firstlimitreverse;

    //Spark firstJointSpark = new Spark(0);

    //Victor firstJointVictor = new Victor(0);

    public ArmSubsystem(){
        //firstJoint.restoreFactoryDefaults();
        
        firstJointEncoder.setPosition(0);

        firstJoint.setInverted(true);


        twooooooo.follow(firstJoint, true);

        //twooooooo.follow(firstJoint);
        //firstlimitforward = firstJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
        //firstlimitreverse = firstJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);

        //firstlimitforward.enableLimitSwitch(true);
        //firstlimitreverse.enableLimitSwitch(true);

    }

    private void rmPidsetOutputRange(double kMinOutput2, double kMaxOutput2) {
        //uh oh shouldnt be here
    }

    public void setBreakMode() {
        //firstJoint.setIdleMode(IdleMode.kBrake);
    }

    public void movemotor(double value) {
        //firstJointSpark.set(value);


        //firstJoint.set(value);
        //twooooooo.set(value);
        firstJoint.set(value);//if group is causing issues just change armGroup to firstJoint
    }

    @Override
    public void periodic() {
        //run fightGod.exe;
    }


    public void calc(double setPoint) {
        armPid.setReference(setPoint, CANSparkMax.ControlType.kSmartVelocity);
    }


    public double getEncoder(){
       return firstJointEncoder.getPosition();
    }
    public void resetEncoder(RelativeEncoder firstJointEncoder2) {
        firstJointEncoder2.setPosition(0);
    }

    public double encoderPercentConversion(RelativeEncoder firstJointEncoder2) {
        return 0;
    }
    public double encoderValue(RelativeEncoder firstJointEncoder2) {
        return firstJointEncoder2.getPosition();
    }

    public double god() {
        return firstJoint.get();
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
