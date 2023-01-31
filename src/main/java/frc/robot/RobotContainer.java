// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.Constants.OperatorConstants;
//import frc.robot.commands.Autos;
import frc.robot.commands.DriveWithJoystickCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.RobotBase;
//import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

    /**left is used for arcade both are used for tank*/
  public static Joystick leftJoystick = new Joystick(1); // ports were just guessed who knows if its right
  public static Joystick rightJoystick = new Joystick(0);

  public final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem(); // probably initializes class

  private final DriveWithJoystickCommand driveWithJoystickCommand = new DriveWithJoystickCommand(drivetrainSubsystem);
  //initializes class

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    drivetrainSubsystem.setDefaultCommand(driveWithJoystickCommand); //sets default controller bindings
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    JoystickButton button8 = new JoystickButton(leftJoystick, 8);
    JoystickButton button10 = new JoystickButton(leftJoystick, 10);
    JoystickButton button12 = new JoystickButton(leftJoystick, 12);

    button12.onTrue(drivetrainSubsystem.testingThings());
    button8.onTrue(drivetrainSubsystem.setBreakMode());
    button10.onTrue(drivetrainSubsystem.setCoastMode());
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    
    // An example command will be run in autonomous
    return null;
  }
}
