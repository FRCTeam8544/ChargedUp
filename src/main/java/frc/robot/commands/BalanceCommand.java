package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class BalanceCommand extends CommandBase {
    
    private DrivetrainSubsystem drivetrainSubsystem;

    double left;
    double right;
    static double NavXRollinit = RobotContainer.ahrs.getRoll();
    static double navXPitchinit = RobotContainer.ahrs.getPitch();
    static double navXYawinit = RobotContainer.ahrs.getYaw();

    public BalanceCommand(DrivetrainSubsystem drivetrainSubsystem){
        this.drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
      }

      @Override
      public void initialize() {
        
      }

      @Override
      public void execute() {
        left = balancepwease();
        right = left;
        drivetrainSubsystem.tankDrive(left, right);
      }

      public static double balancepwease() {
        double navXPitch = RobotContainer.ahrs.getPitch();
        double navXRoll = RobotContainer.ahrs.getRoll();
        double navXYaw = RobotContainer.ahrs.getYaw();
        double value = 0;
        navXRoll = navXRoll - NavXRollinit;
        navXPitch = navXPitch - navXPitchinit;
        navXYaw = navXYaw - navXYawinit;
        /**
         * 17.5 max angle when fully on
         * 0.25 keeps balanced 
         * ^ old robot data ^
         */
        value = ((Constants.BASESPEED + Constants.CURRENTRAMPSPEED) / Constants.MAXANGLE ) * navXRoll ;
        SmartDashboard.putNumber("constant ramp speed", Constants.CURRENTRAMPSPEED);
        SmartDashboard.putNumber("ahrs pitch", navXPitch);
        SmartDashboard.putNumber("ahrs roll", navXRoll);
        SmartDashboard.putNumber("ahrs yaw", navXYaw);
        return value;
      }
    }
