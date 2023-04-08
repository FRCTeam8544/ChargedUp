package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveSubsystem extends SubsystemBase{
    /**
     * what is up
     * I made this a few days after the WPI comp because I was bored
     * it has not been tested as of me finishing 2023/04/07 year month day btw
     * Some values might have to be tweaked like multiplyer in the command
     * the multiplyer should be set by twisting the swerve motors 360 degrees and putting it over 360 (because the encoder wont match the actual degrees)
     * if this is ever tested and there are issues then best of luck fixing them (I wont remember how this was made)
     * it might be easier to just use the wpilib stuff if you want to use a swerve drive 
     * 
     */

    static CANSparkMax frontLeftMotor = new CANSparkMax(Constants.DriveTrainConstantants.frontLeftCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax frontRightMotor = new CANSparkMax(Constants.DriveTrainConstantants.frontRightCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backLeftMotor = new CANSparkMax(Constants.DriveTrainConstantants.backLeftCANID,CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backRightMotor = new CANSparkMax(Constants.DriveTrainConstantants.backRightCANID,CANSparkMaxLowLevel.MotorType.kBrushless);

    static CANSparkMax frontLeftSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax frontRightSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backLeftSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backRightSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);

    RelativeEncoder wheelEncoder = frontLeftMotor.getEncoder();;
    RelativeEncoder tlEncoder = frontLeftSwerve.getEncoder();
    RelativeEncoder trEncoder = frontRightSwerve.getEncoder();
    RelativeEncoder blEncoder = backLeftSwerve.getEncoder();
    RelativeEncoder brEncoder = backRightSwerve.getEncoder();

    MotorControllerGroup swerveGroup;

    SparkMaxPIDController swervePID = frontLeftMotor.getPIDController();

    public SwerveSubsystem(){
        frontRightMotor.follow(frontLeftMotor);
        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontLeftMotor);

        wheelEncoder.setPosition(0);
        tlEncoder.setPosition(0);
        trEncoder.setPosition(0);
        blEncoder.setPosition(0);
        brEncoder.setPosition(0);

        /*frontRightSwerve.follow(frontLeftSwerve);
        backLeftSwerve.follow(frontLeftSwerve);
        backRightSwerve.follow(frontLeftSwerve);*/

        swerveGroup = new MotorControllerGroup(frontLeftSwerve, frontRightSwerve, backLeftSwerve, backRightSwerve);


    }

    public double swerveEncoders(int num){//top right
        switch(num){
            case 1:
                return tlEncoder.getPosition();
            case 2:
                return trEncoder.getPosition();
            case 3:
                return blEncoder.getPosition();
            case 4:
                return brEncoder.getPosition();
            default:
                return tlEncoder.getPosition();
        }
    }



    public double wheelEncoder(){
        return wheelEncoder.getPosition();
    }

   public void lightningMcQueen(double speed, double topLeft, double topRight, double bottomLeft, double bottomRight){
        frontLeftSwerve.set(topLeft);
        frontRightSwerve.set(topRight);
        backLeftSwerve.set(bottomLeft);
        backRightSwerve.set(bottomRight);
        frontLeftMotor.set(speed);
        
    }   
    
}
