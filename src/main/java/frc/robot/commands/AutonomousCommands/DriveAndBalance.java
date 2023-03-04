package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveAndBalance extends CommandBase{

    DrivetrainSubsystem ar_drivetrain;
    double navXPitch = RobotContainer.ahrs.getPitch();
    double navXRoll = RobotContainer.ahrs.getRoll();
    DriveAndBalance(DrivetrainSubsystem ar_drivetrain) {
        this.ar_drivetrain = ar_drivetrain;
        addRequirements(ar_drivetrain);
    }

    @Override
    public void execute() {
        if (navXRoll > 10) {ar_drivetrain.tankDrive(balancepwease(), balancepwease());}
        else{ar_drivetrain.tankDrive(0.5, 0.5);}
    }
    

    private double balancepwease() {
        double value = 0;
        double rolex = navXRoll / 180;
        /**
         * 17.5 max angle when fully on
         * 0.25 keeps balanced
         */
    
        if (navXRoll != 0) {
           value = (.29 / 17.5 ) * navXRoll * -1 ;
        }
        
        /*if (navXRoll > 10){
          value = -0.25;
          //value = 2*Math.pow((rolex+.5), 2) - .5; 
        }
        else if (navXRoll < -10) {
          value = 0.25;
          //value = (-2*Math.pow((rolex-.5), 2)+.5);
        }*/
          
        
    
        if (navXPitch < -10 || navXRoll > 10) {
          System.out.println("how did we get here");
        }
        SmartDashboard.putNumber("ahrs", RobotContainer.ahrs.getPitch());
        SmartDashboard.putNumber("ahrs roll", RobotContainer.ahrs.getRoll());
        return value;
      }
}
