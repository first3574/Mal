package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoy extends Command {
	double deadZone;
	double throttle;
	double scaledX, scaledY, scaledZ;
	OI oI = Robot.oi;
	final private double MAKE_GO_STRAIT = 0.9;

    public DriveWithJoy() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        System.out.println("drive with joy started");
    }

    // Called just before this Command runs the first time
    protected void initialize()  {
    	throttle = 1.0;
    	deadZone = 0.05;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Math.abs(oI.joystickX()) > MAKE_GO_STRAIT || (Math.abs(oI.joystickY()) > MAKE_GO_STRAIT)){
    		deadZone = .3;
    	} else {
    		deadZone = .05;
    	}
        
    	throttle = (-oI.joystickThrottle() + 1) / 2;
    	
		scaledX = Math.abs(-oI.joystickX()) < deadZone ? 0.0 : -oI.joystickX() * throttle;
		scaledY = Math.abs(-oI.joystickY()) < deadZone ? 0.0 : -oI.joystickY() * throttle; 
		scaledZ = Math.abs(oI.joystickZ()) < deadZone ? 0.0 : oI.joystickZ() * throttle;
		scaledX = joystickScale(scaledX);
		scaledY = joystickScale(scaledY);				
		scaledZ = joystickScale(scaledZ);
		
		SmartDashboard.putNumber("Scaled Y", scaledY);
		
		Robot.drivetrain.driveFieldOrientated(scaledX, scaledY, scaledZ);
	}
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public double joystickScale(double input) {
		boolean isNegative = false;
		if (input < 0.0) {
			isNegative = true;
		}
		input = Math.abs(input);
		double result = (Math.pow(Math.E, input) - 1) / 1.718;
		if (isNegative) {
			result *= -1.0;
		}
		return result;
	}
}
