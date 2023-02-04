package frc.robot.commands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class TurretMoveCommand extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private TurretSubsystem turretSubsystem;

    public TurretMoveCommand (TurretSubsystem turretSubsystem){
        this.turretSubsystem = turretSubsystem;
        addRequirements (turretSubsystem);
        //I require blood sacrifices
      }

    @Override
      public void initialize(){
        System.out.println("use controller to move turret");
        turretSubsystem.setCoastModeT();
        //bit nippy innit bruv
      }
      @Override
      public void execute() {
        double xspeed = RobotContainer.controller.getLeftX();
        xspeed = xspeed / 2;//added so the turret doesnt spin too fast (caps it at 50% power)
        xspeed = InvertDirection(true, xspeed); // if not inerted either comment out or change true to false
        turretSubsystem.turretMove(xspeed);
        //execute order 66
      }

      private double InvertDirection(boolean x, double xspeed) {
        if (x == true) {
            xspeed = xspeed * -1;
        }
        return xspeed;
        //imagine sisyphus smiling
      }
    
}