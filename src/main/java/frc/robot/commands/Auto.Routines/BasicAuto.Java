
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BasicAutoRoutine extends CommandBase {

    private DrivetrainSubsystem m_drivetrain;//makes drive train
    private double m_distance = 5; //makes thingy to mesure distance

    private boolean m_finished;//true or false for when you are done

    public void DriveForwardAndBackwardCommand(DrivetrainSubsystem drivetrain, double distance) {
        m_drivetrain = drivetrain;//defines drivetrain
        m_distance = distance;//redefines distance
        addRequirements(m_drivetrain);//Has a req for the drivetrain
    }

    @Override
    public void initialize() {// when intitalized 
        m_drivetrain.leftEncoder.setPosition(0);//giving a zero position
        m_drivetrain.rightEncoder.setPosition(0);
        
     

        m_finished = false;//defines a bool as false giving the auto the status it isnt done
    }

    @Override
    public void execute() {//when executed
        if (m_drivetrain.leftEncoder.getPosition() <= m_distance && m_drivetrain.rightEncoder.getPosition() <= m_distance) { //it says if less than the mesure of distance then
            m_drivetrain.tankDrive(Left:0.5,Right: 0.5); //set motors forward to .5 power
        } else if (m_drivetrain.leftEncoder.getPosition() > m_distance && m_drivetrain.rightEncoder.getPosition() > m_distance) {
            m_drivetrain.tankDrive(Left:-0.5,Right: -0.5);//same thing in reverse 
        } else {
            m_drivetrain.tankDrive(Left:0,Right: 0);// you will stop
            m_finished = true;//tell you you are done
        }
    }

    @Override
    public boolean isFinished() {
        return m_finished; //
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.setCoastMode();
        m_drivetrain.tankDrive(0, 0);
    }
}
