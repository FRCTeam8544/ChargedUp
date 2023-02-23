package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmSubsystem;

public class ArmControls extends CommandBase{

    private final ArmSubsystem armSubsystem;

    public ArmControls(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
      }

    private int porque = 0;


    @Override
    public void initialize() {
        armSubsystem.setBreakMode();
    }
    
    @Override
    public void execute() {
        double firstJointSpeedup = RobotContainer.controller.getRightTriggerAxis();
        double firstJointSpeeddown = RobotContainer.controller.getLeftTriggerAxis();
        double actualfirstjoint = (firstJointSpeedup - firstJointSpeeddown) / 2;

        if (RobotContainer.controller.getAButtonPressed()) {
            if (porque == 4 ||porque == 1) {
              porque = 1;
              actualfirstjoint = controlslb(firstJointSpeedup);
            }
            else if (porque == 2 || porque == 3) {
              porque = 3;
            }
          }
          else if (RobotContainer.controller.getAButtonReleased()) {
            if(porque == 1) {
              porque = 2;
            }  
            if (porque == 3) {
              porque = 4;
            }
          }
        

        armSubsystem.movemotor(actualfirstjoint);
        
    }

    double controlslb(double rt) {

        if (RobotContainer.controller.getLeftBumper()) {
            rt = (rt * -1)/2;
        }

        return rt;
    }

}
