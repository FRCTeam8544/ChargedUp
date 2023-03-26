package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LedSubsystem extends SubsystemBase{


    AddressableLED led = new AddressableLED(Constants.led.ledport);

    AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(Constants.led.length);

    int m_rainbowFirstPixelHue;
    public int eyesOfHeaven = 0;
    public boolean other = false;
    public boolean slay = false;

    public String name = "que pasa";
    
    public boolean bitesTheDust = false;
    public boolean zaWauldo = false;
    public boolean kingCrimson = false;
    public boolean stoneFree = false;

    
    // purple is one yellow is two red is three and blue is yellow
    public LedSubsystem(){
        led.setLength(ledBuffer.getLength());

        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 0, 0);
         }

         madeInHeaven(69);

        led.setData(ledBuffer);
        led.start();
    }

    public void madeInHeaven(int keep){
        switch (keep){
            case 1:
                bitesTheDust = true;
                zaWauldo = false; kingCrimson = false; stoneFree = false;
                bitesTheDust();
                break;
            case 2:
                zaWauldo = true;
                bitesTheDust = false; kingCrimson = false; stoneFree = false;
                zaWauldo();
                break;
            case 3:
                kingCrimson = true;
                bitesTheDust = false; zaWauldo = false; stoneFree = false;
                kingCrimson();
                break;
            case 4:
                stoneFree = true;
                bitesTheDust = false; zaWauldo = false; kingCrimson = false;
                stoneFree();
                break;
            default:
                GER();
        }
    }

    public void bitesTheDust(){name = "Bites the Dust";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 102, 178);
         }
    }

    public void zaWauldo(){name = "ZAWARLDO";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 0, 252, 128);
         }
    }

    public void kingCrimson(){name = "King Crimson";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 0, 0);
         }
    }

    public void stoneFree(){name = "StoneFree";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 0, 0, 255);
         }
    }

    public void GER(){name = "This is Requiem";
        
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
        if (slay){GER();}
        led.setData(ledBuffer);
    }

    public void setdata(){
        led.setData(ledBuffer);
    }
    
}
