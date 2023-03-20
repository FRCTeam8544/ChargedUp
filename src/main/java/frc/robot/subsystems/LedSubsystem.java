package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LedSubsystem extends SubsystemBase{


    AddressableLED led = new AddressableLED(Constants.led.ledport);

    AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(Constants.led.length);

    int m_rainbowFirstPixelHue;
    

    public LedSubsystem(){
        led.setLength(ledBuffer.getLength());

        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 0, 0);
         }

        led.setData(ledBuffer);
        led.start();
    }

    public void bitesTheDust(){
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 102, 178);
         }
    }

    public void zaWauldo(){
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 0, 252, 128);
         }
    }

    public void drive(){
        
        //in Rainbows by radiohead amirite
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final var hue = (m_rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            // Set the value
            ledBuffer.setHSV(i, hue, 255, 128);
          }
          // Increase by to make the rainbow "move"
          m_rainbowFirstPixelHue += 3;
          // Check bounds
          m_rainbowFirstPixelHue %= 180;
    }


    @Override
    public void periodic(){

        if (Constants.DriveTrainConstantants.drive){
            drive();
        }
        led.setData(ledBuffer);
    }
    
}
