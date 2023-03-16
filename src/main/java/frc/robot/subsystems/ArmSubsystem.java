package frc.robot.subsystems;

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

    public RelativeEncoder firstJointEncoder = firstJoint.getEncoder();

    public SparkMaxPIDController armPid = firstJoint.getPIDController();
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

    //private SparkMaxLimitSwitch firstlimitforward;
    //private SparkMaxLimitSwitch firstlimitreverse;

    //Spark firstJointSpark = new Spark(0);

    //Victor firstJointVictor = new Victor(0);

    public ArmSubsystem(){
        //firstJoint.restoreFactoryDefaults();
        
        firstJointEncoder.setPosition(0);

        firstJoint.setInverted(false);

        kP = 5e-5; 
        kI = 1e-6;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000156; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;

        armPid.setP(kP);
        armPid.setI(kI);
        armPid.setD(kD);
        armPid.setIZone(kIz);
        armPid.setFF(kFF);
        armPid.setOutputRange(kMinOutput, kMaxOutput);

        //firstlimitforward = firstJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
        //firstlimitreverse = firstJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);

        //firstlimitforward.enableLimitSwitch(true);
        //firstlimitreverse.enableLimitSwitch(true);

    }

    private void rmPidsetOutputRange(double kMinOutput2, double kMaxOutput2) {
        //uh oh shouldnt be here
    }

    public void setBreakMode() {
        firstJoint.setIdleMode(IdleMode.kBrake);
    }

    public void movemotor(double value) {
        //firstJointSpark.set(value);

        firstJoint.set(value);
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
