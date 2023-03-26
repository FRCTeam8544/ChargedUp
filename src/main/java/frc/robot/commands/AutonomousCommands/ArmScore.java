package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class ArmScore extends CommandBase{//eyes of heaven

    private ArmSubsystem ar_armSubsystem;
    private WristSubsystem ar_wristSubsystem;
    private ArmPneumaticsSubsystem ar_armPneumaticsSubsystem;

    ArmScore(ArmSubsystem ar_armSubsystem, WristSubsystem ar_wristSubsystem, ArmPneumaticsSubsystem ar_armPneumaticsSubsystem) {
        this.ar_armSubsystem = ar_armSubsystem;
        this.ar_wristSubsystem = ar_wristSubsystem;
        this.ar_armPneumaticsSubsystem = ar_armPneumaticsSubsystem;
        addRequirements(ar_armSubsystem, ar_wristSubsystem, ar_armPneumaticsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double speed = 0;
        double speedw = 0;
        
        



        ar_armSubsystem.movemotor(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public void end(boolean interrupted) {
      ar_armPneumaticsSubsystem.out();
      ar_wristSubsystem.wristWatch(0);
      ar_armSubsystem.movemotor(0);
    }
}
