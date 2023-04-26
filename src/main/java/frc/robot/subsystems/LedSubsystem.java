package frc.robot.subsystems;

import java.util.Random;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LedSubsystem extends SubsystemBase{


    AddressableLED led = new AddressableLED(Constants.led.ledport);

    AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(Constants.led.length);

    Random rand = new Random();

    int m_rainbowFirstPixelHue;
    public boolean eyesOfHeaven = false;
    public boolean other = false;
    public boolean slay = true;
    boolean forward = true;
    boolean current = forward;
    int lovetrain = 0;
    int r = 0; 
    int g = 0; 
    int b = 0;
    int randcolorr = 255;
    int randcolorg = 255;
    int randcolorb = 255;

    public String name = "que pasa";
    
    public boolean bitesTheDust = false;
    public boolean zaWauldo = false;
    public boolean kingCrimson = false;
    public boolean stoneFree = false;

    
    // purple is one yellow is two red is three and blue is yellow
    public LedSubsystem(){
        led.setLength(ledBuffer.getLength());

        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 255, 100, 0);
         }

         //madeInHeaven(69);

        led.setData(ledBuffer);
        led.start();
    }

    public void madeInHeaven(int keep){//could this be optemized by setting everything false in the begining?
        //yes but the booleans could also just be removed
        switch (keep){
            case 1:
                bitesTheDust = true;
                zaWauldo = false; kingCrimson = false; stoneFree = false; eyesOfHeaven = false;
                bitesTheDust();
                break;
            case 2:
                zaWauldo = true;
                bitesTheDust = false; kingCrimson = false; stoneFree = false; eyesOfHeaven = false;
                zaWauldo();
                break;
            case 3:
                kingCrimson = true;
                bitesTheDust = false; zaWauldo = false; stoneFree = false; eyesOfHeaven = false;
                kingCrimson();
                break;
            case 4:
                stoneFree = true;
                bitesTheDust = false; zaWauldo = false; kingCrimson = false; eyesOfHeaven = false;
                stoneFree();
                break;
            default:
                GER();
        }
        
    }

    public void bitesTheDust(){name = "Bites the Dust";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 177, 14, 227);
         }
    }

    public void zaWauldo(){name = "ZAWARLDO";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 223, 227, 14);
         }
    }

    public void kingCrimson(){name = "King Crimson";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 168, 25, 35);
         }
    }

    public void stoneFree(){name = "StoneFree";
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 25, 68, 168);
         }
    }

    public void lovetrain(){
        System.out.println("we are in quite a bad position");
        if (lovetrain >= 1 && !forward){
            lovetrain -= 1;
            r = randcolorr;
            g = randcolorg;
            b = randcolorb;
        }
        else{
            forward = true;
        }
        if (lovetrain < ledBuffer.getLength() - 1 && forward){
            lovetrain++;
            r = randcolorr;
            g = randcolorg;
            b = randcolorb;
        }
        else{
            forward = false;
        }
        if (current != forward){
            randcolorr = rand.nextInt(255);
            randcolorb = rand.nextInt(255 - randcolorr);
            randcolorg = rand.nextInt(255 - randcolorr);
        }
        ledBuffer.setRGB(lovetrain, r, g, b);
        if (forward){
            ledBuffer.setRGB(lovetrain - 1, 0, 0, 0);
        }
        else if (forward == current){
            ledBuffer.setRGB(lovetrain + 1, 0, 0, 0);
        }
        current = forward;
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
        System.out.println("we are here (that means its ians fault)");
        if (slay){GER();}
        else if (eyesOfHeaven){lovetrain();}
        led.setData(ledBuffer);
    }

    public void disabled(){//called periodically while disabled
        GER();
        led.setData(ledBuffer);
    }

    public void setdata(){
        led.setData(ledBuffer);
    }
    
}
