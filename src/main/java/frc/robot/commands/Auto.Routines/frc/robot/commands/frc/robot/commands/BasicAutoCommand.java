

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class BasicAutoCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrain;
    private final double m_distance;

    private boolean m_finished;

    public BasicAutoCommand(DrivetrainSubsystem drivetrain, double distance) {
        m_drivetrain = drivetrain;
        m_distance = distance;
        addRequirements(m_drivetrain);
    }

    @Override
    public void initialize() {
        m_drivetrain.setBreakMode();
        m_drivetrain.leftEncoder.setPosition(0);
        m_drivetrain.rightEncoder.setPosition(0);
        m_finished = false;
    }

    @Override
    public void execute() {
        if (m_drivetrain.leftEncoder.getPosition() <= m_distance && m_drivetrain.rightEncoder.getPosition() <= m_distance) {
            m_drivetrain.tankDrive(0.5, 0.5);
        } else if (m_drivetrain.leftEncoder.getPosition() > m_distance && m_drivetrain.rightEncoder.getPosition() > m_distance) {
            m_drivetrain.tankDrive(-0.5, -0.5);
        } else {
            m_drivetrain.tankDrive(0, 0);
            m_finished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return m_finished;
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.setCoastMode();
        m_drivetrain.tankDrive(0, 0);
    }
}
