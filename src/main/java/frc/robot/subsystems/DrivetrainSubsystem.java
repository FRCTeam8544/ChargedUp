// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

//import java.lang.Math;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
//import frc.robot.RobotContainer;

public class DrivetrainSubsystem extends SubsystemBase {


    CANSparkMax frontLeftMotor = new CANSparkMax(Constants.DriveTrainConstantants.frontLeftCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax frontRightMotor = new CANSparkMax(Constants.DriveTrainConstantants.frontRightCANID, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax backLeftMotor = new CANSparkMax(Constants.DriveTrainConstantants.backLeftCANID,CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax backRightMotor = new CANSparkMax(Constants.DriveTrainConstantants.backRightCANID,CANSparkMaxLowLevel.MotorType.kBrushless);

    RelativeEncoder leftEncoder = frontLeftMotor.getEncoder();
    RelativeEncoder rightEncoder = frontRightMotor.getEncoder();

    MotorControllerGroup leftControllerGroup = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
    MotorControllerGroup righControllerGroup = new MotorControllerGroup(frontRightMotor, backRightMotor);

    DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, righControllerGroup);

    public DrivetrainSubsystem(){
        frontLeftMotor.restoreFactoryDefaults();
        frontRightMotor.restoreFactoryDefaults();
        backLeftMotor.restoreFactoryDefaults();
        backRightMotor.restoreFactoryDefaults();
        
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        righControllerGroup.setInverted(true);//play with these until it works
        leftControllerGroup.setInverted(false);
    }
    
    public void tankDrive(double left, double right) {
      differentialDrive.tankDrive(left, right);
    }
    /*public void arcadeDrive(double forward, double rotation) {
      differentialDrive.arcadeDrive(forward, rotation);
    }

    public static void mode(double l, double r) {
      if ( == true) {
        
      }
    }

    

    public static double formulas(double side) {
      
      double exponentialincrease(double sideEx) {
        sideEx = sideEx*sideEx*sideEx;
        return sideEx;
      }

      double squareroot(double sideSq) {
        sideSq = Math.sqrt(sideSq);
        return sideSq;
      }
      
      double wackystuff(double sideWs) {
        for (int i = 1000; i > 0; i-- ) {
          double i_sideWs = sideWs/i;
          return i_sideWs;
        }
      }
    }

*/
    public void setBreakMode() {
      backLeftMotor.setIdleMode(IdleMode.kBrake);
      frontLeftMotor.setIdleMode(IdleMode.kBrake);
      backRightMotor.setIdleMode(IdleMode.kBrake);
      frontRightMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setCoastMode() {
      backLeftMotor.setIdleMode(IdleMode.kCoast);
      frontLeftMotor.setIdleMode(IdleMode.kCoast);
      backRightMotor.setIdleMode(IdleMode.kCoast);
      frontRightMotor.setIdleMode(IdleMode.kCoast);
    }
    @Override
  public void periodic() {
    SmartDashboard.putNumber("Drivetrain Left Speed", leftEncoder.getVelocity());
    SmartDashboard.putString("Message", "Hi");
    // This method will be called once per scheduler run
  }
}