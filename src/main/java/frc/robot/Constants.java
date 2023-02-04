
package frc.robot;
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
    public static final int frontLeftCANID = 1;
    public static final int frontRightCANID = 3;
    public static final int backLeftCANID = 2;
    public static final int backRightCANID = 4;

  }
  public static final class TurretConstants{
    public static final int turretCANID = 10;
  }

}
