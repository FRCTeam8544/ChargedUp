package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveAndBalance extends CommandBase{

    DrivetrainSubsystem ar_drivetrain;
    private static final double navXRollinit = RobotContainer.ahrs.getRoll();
    private static final double navXPitchinit = RobotContainer.ahrs.getPitch();
    private static final double navXYawinit = RobotContainer.ahrs.getYaw();
    
      boolean balance = false;
    
    public DriveAndBalance(DrivetrainSubsystem ar_drivetrain) {
        this.ar_drivetrain = ar_drivetrain;
        addRequirements(ar_drivetrain);
    }

    @Override
    public void initialize(){
      
      
    }

    @Override
    public void execute() {
      double navXRoll = RobotContainer.ahrs.getRoll();
        if (navXRoll > 15 || navXRoll < -15 || balance) {
          double balanceSpeed = balancepwease();
          ar_drivetrain.tankDrive(balanceSpeed, balanceSpeed);
          balance = true;
        }
        else{ar_drivetrain.tankDrive(0.3, 0.3);}
    }
    
    @Override
    public boolean isFinished(){

      return false;

    }

    @Override
    public void end(boolean interrupted) {
      ar_drivetrain.leftControllerGroup.set(0);
      ar_drivetrain.righControllerGroup.set(0);
    }

    public static double balancepwease() {
      double navXPitch = RobotContainer.ahrs.getPitch();
      double navXRoll = RobotContainer.ahrs.getRoll();
      double navXYaw = RobotContainer.ahrs.getYaw();
      double value = 0;
      navXRoll = navXRoll - navXRollinit;
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
