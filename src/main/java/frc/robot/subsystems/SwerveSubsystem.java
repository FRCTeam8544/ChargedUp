package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveSubsystem extends SubsystemBase{

    static CANSparkMax frontLeftMotor = new CANSparkMax(Constants.DriveTrainConstantants.frontLeftCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax frontRightMotor = new CANSparkMax(Constants.DriveTrainConstantants.frontRightCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backLeftMotor = new CANSparkMax(Constants.DriveTrainConstantants.backLeftCANID,CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backRightMotor = new CANSparkMax(Constants.DriveTrainConstantants.backRightCANID,CANSparkMaxLowLevel.MotorType.kBrushless);

    static CANSparkMax frontLeftSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax frontRightSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backLeftSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    static CANSparkMax backRightSwerve = new CANSparkMax(Constants.DriveTrainConstantants.flSwerveCANID, CANSparkMaxLowLevel.MotorType.kBrushless);

    RelativeEncoder bottomEncoder = frontLeftMotor.getEncoder();;
    RelativeEncoder topEncoder = frontLeftSwerve.getEncoder();

    SparkMaxPIDController swervePID = frontLeftMotor.getPIDController();

    public SwerveSubsystem(){
        frontRightMotor.follow(frontLeftMotor);
        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontLeftMotor);

        bottomEncoder.setPosition(0);
        topEncoder.setPosition(90);

        frontRightSwerve.follow(frontLeftSwerve);
        backLeftSwerve.follow(frontLeftSwerve);
        backRightSwerve.follow(frontLeftSwerve);

    }

    public double swerveEncoder(){
        return topEncoder.getPosition();
    }

    public double bottomEncoder(){
        return bottomEncoder.getPosition();
    }

    public void lightning(double degrees){
        frontLeftSwerve.set(degrees);
    }

    public void mcQeen(double speed){
        frontLeftMotor.set(speed);
    }
    
}
