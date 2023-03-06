// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.commands.ArmControls;
import frc.robot.commands.BalanceCommand;
//import frc.robot.Constants.OperatorConstants;
//import frc.robot.commands.Autos;
import frc.robot.commands.DriveWithJoystickCommand;
import frc.robot.commands.HoldCommand;
import frc.robot.commands.ReleaseCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.WristCommand;
import frc.robot.subsystems.ArmExtenderSubsystem;
import frc.robot.subsystems.ArmPneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.AutonomousCommands.AutonomousMiddleCommand;
import frc.robot.commands.AutonomousCommands.AutonomusRed1Command;
import frc.robot.commands.AutonomousCommands.AutonomousForwardTest;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.RobotBase;
//import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  public static Joystick rightJoystick = new Joystick(0);// add a way to change it through smart dashboard
  public static XboxController controller = new XboxController(2);
  public static AHRS ahrs = new AHRS(SPI.Port.kMXP);
  public static boolean autobalance = false;
    /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
    /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
    /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
 
  public final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();//who knows
  public final DriveWithJoystickCommand driveWithJoystickCommand = new DriveWithJoystickCommand(drivetrainSubsystem);
  public final BalanceCommand balanceCommand = new BalanceCommand(drivetrainSubsystem);

  public final WristSubsystem wristSubsystem = new WristSubsystem();
  public final ArmSubsystem armSubsystem = new ArmSubsystem();
  public final ArmExtenderSubsystem armExtenderSubsystem = new ArmExtenderSubsystem();
  public final ArmPneumaticsSubsystem armPneumaticsSubsystem = new ArmPneumaticsSubsystem();

  public final ArmControls armControls = new ArmControls(armSubsystem, wristSubsystem, armExtenderSubsystem, armPneumaticsSubsystem);
  
  //public final WristCommand wristCommand = new WristCommand(wristSubsystem);


  public final TurretSubsystem turretSubsystem = new TurretSubsystem();
  //public final TurretCommand turretCommand = new TurretCommand(turretSubsystem);

  //CommandXboxController controllerCommand = new CommandXboxController(2);
 // Trigger aButton = controllerCommand.a();
  //Trigger xButton = controllerCommand.x();

  private final ArmExtenderCommand armExtenderControls = new ArmExtenderCommand(armExtenderSubsystem);

  // Automation Classes
    // Toggle to pick automation mode
  private SendableChooser<Command> toggle = new SendableChooser<>();
    // Our first test automation routine for this bot
    private final AutonomousForwardTest a_AutonomousForwardTest = new AutonomousForwardTest(drivetrainSubsystem);
    //first real command
    private final AutonomousMiddleCommand a_MiddleCommand = new AutonomousMiddleCommand(drivetrainSubsystem);
    private final AutonomusRed1Command a_AutonomusRed1Command = new AutonomusRed1Command(drivetrainSubsystem);
  //initializes class

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Add the autonomous selection toggle:
    toggle.setDefaultOption("Forward Test", a_AutonomousForwardTest);
    toggle.addOption("Middle Command", a_MiddleCommand);
    toggle.addOption("Red1", a_AutonomusRed1Command);
   // toggle.addOption("red3",a_AutonomusRed3Command);
   
    //toggle.addOption("Middle command", a_AutonomousMiddleCommand);
    SmartDashboard.putData("Select Autonomous", toggle);

    // Configure the trigger bindings
    configureBindings();
    drivetrainSubsystem.setDefaultCommand(driveWithJoystickCommand); //sets default controller bindings
    armSubsystem.setDefaultCommand(armControls);


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
    //what is the point of this

    //aButton.onTrue(new HoldCommand(armPneumaticsSubsystem));
    //xButton.onTrue(new ReleaseCommand(armPneumaticsSubsystem));
    
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    
    // An example command will be run in autonomous
    return toggle.getSelected();
  }
}
