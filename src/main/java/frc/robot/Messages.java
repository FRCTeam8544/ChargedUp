package frc.robot;

import java.util.ArrayList;
import java.util.Random;

public class Messages {
    public ArrayList<String> strings = new ArrayList<>();
    
    public Messages() {
    strings.add("Schnee is big smart.");
    strings.add("eat ham");
    strings.add("eat ham");
    }

    public String getNewMessage() {
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }
    
}
