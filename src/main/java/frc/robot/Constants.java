// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

// who's in paris
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  //Programing >>>>>>>>>
  public static final class DriveTrainConstantants{
    //change depending on what port the CAN is
    public static final int frontLeftCANID = 2; //one for old robot | new is 2
    public static final int frontRightCANID = 4;//2 for old robot | new is 4
    public static final int backLeftCANID = 1; //old robot is 3 | new is 1
    public static final int backRightCANID = 3;//old robot is 4 | new is 3

  }

  public static final class turret{
    public static final int turretCANID = 9;
  }

  public static final class armthings{
    public static final int jointoneCANID = 7;
    public static final int extenderCANID = 8;
    public static final int wristCANID = 9;

    public static boolean yes = false;

    public static final int firstnum = 1;

    public static final boolean issparkFirst = true;

    public static boolean morecontrol = false;

    public static final double armspeed = 0.15;
    public static final double armstopspeed = 0.01;
    public static final double armexespeed = 0.2;
    public static final double wrist = 0.2;

    public static final PneumaticsModuleType PNEMATICTYPE = PneumaticsModuleType.REVPH;
    public static final int armout = 1;
    public static final int armin = 0;
  }

  public static final double BASESPEED = 0.22; //speed at which robot should be stable when it is fully on the ramp
  public static final double MAXANGLE = 17; // max ramp angle is 17 degrees
  public static final double RAMPSPEEDADJUSTMENT = .01; //speed adjustment when auto mode is on
  public static double CURRENTRAMPSPEED = 0.04; // starting adjustment speed

  public static final class JoystickConstants {
    public static final int butEnableBreakmode = 8;
  }

}
