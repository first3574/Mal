package org.usfirst.frc.team3574.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StepGrabber extends Subsystem {
    Solenoid lifterThing;
    Solenoid pusherThing;
    Timer time;
    
    //ToDo:oreang9uej0tiirjgieqnriwjehignj0iasejerjg0tiwneiugeirng9wNGINE0QNT9JEJ9REIETGDS
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public StepGrabber(){
    	lifterThing = new Solenoid(1);
    	pusherThing = new Solenoid(2);
    	time = new Timer();
    	
    	time.reset();
    	time.start();
    }
    
    
    public void lifterThingUp() {
    	lifterThing.set(true);
    }
    public void lifterThingDown() {
    	lifterThing.set(false);
    }
    public void pusherThingOut() {
    	pusherThing.set(true);
    }
    public void pusherThingIn() {
    	pusherThing.set(false);
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Log() {
//		SmartDashboard.putNumber("Orentation", imu.getYaw());
		SmartDashboard.putNumber("Time", time.get());
    }
}

