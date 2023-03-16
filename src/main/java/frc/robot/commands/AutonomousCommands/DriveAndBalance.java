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
      boolean tofar = false;
      private boolean goingBack;
      private boolean autoBalance;
      private int howFar;
    
    public DriveAndBalance(DrivetrainSubsystem ar_drivetrain, boolean goingBack, boolean autoBalance, int howFar) {
        this.ar_drivetrain = ar_drivetrain;
        this.goingBack = goingBack;
        this.autoBalance = autoBalance;
        this.howFar = howFar;
        addRequirements(ar_drivetrain);
    }

    @Override
    public void initialize(){
      ar_drivetrain.leftEncoder.setPosition(0);
      
    }

    @Override
    public void execute() {
      double navXRoll = RobotContainer.ahrs.getRoll();
        if (navXRoll > 16 || navXRoll < -16 || balance) {
          double balanceSpeed = balancepwease();
          ar_drivetrain.tankDrive(balanceSpeed, balanceSpeed);
          balance = true;
        }
        else if (tofar()){balance = true;}
        else if (goingBack){ar_drivetrain.tankDrive(0.5, 0.5);}
        else{ar_drivetrain.tankDrive(-0.5, -0.5);}
    }
    

    boolean tofar(){
      if (tofar){return tofar;}
      
      if (ar_drivetrain.encoderPositionToDistanceConversion(ar_drivetrain.leftEncoder) < howFar){tofar = false;}
      else{tofar = true;}

      return tofar;
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
