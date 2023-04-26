package frc.robot.utils;

import edu.wpi.first.wpilibj.Timer;

public class RampUp{
    //Josh had something similar last year but I didnt like it so Im making one with slight modifications
    //his is probably better though
    //this also acts differently than his
    double timeTillSpeed;Timer timer; double target; double speed;
    public RampUp(double timeTillSpeed){
        this.timeTillSpeed = timeTillSpeed;
        timer.start();
    }

    public boolean running(){
        if (target == 0){return false;}
        else{return true;}
    }

    public void setTarg(double targ, int running){
        target = targ;
        if (running == 0){timer.reset();;}
    }

    public double getVal(){
        if (timer.get() < timeTillSpeed){speed = (timer.get() / timeTillSpeed) * target;}
        else{speed = target;}
        return speed;
    }
    
    
}
