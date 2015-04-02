package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithMiniJoy extends Command {
	double deadZone;
	double throttle;
	double scaledX, scaledY, scaledZ;
	final private double MAKE_GO_STRAIGHT = 0.9;
	

	public DriveWithMiniJoy() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		System.out.println("drive with joy started");
		
	}

	// Called just before this Command runs the first time
	protected void initialize()  {
		throttle = 1.0;
		deadZone = 0.1;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
    	
		if(Math.abs(Robot.oi.joystickX()) > MAKE_GO_STRAIGHT || (Math.abs(Robot.oi.joystickY()) > MAKE_GO_STRAIGHT)){
			deadZone = .3;
		} else {
			deadZone = .05;
		}
		
		if (Robot.oi.isTriggerPulled()) {
			throttle = 1;
		} else {
			throttle = ((-Robot.oi.joystickThrottle() + 1) / 4) +.5;
		}

		scaledX = Math.abs(-Robot.oi.joystickX()) < deadZone ? 0.0 : -Robot.oi.joystickX() * throttle;
		scaledY = Math.abs(-Robot.oi.joystickY()) < deadZone ? 0.0 : -Robot.oi.joystickY() * throttle; 
		scaledZ = Math.abs(Robot.oi.joystickZ()) < deadZone ? 0.0 : Robot.oi.joystickZ() * throttle;
		scaledX = joystickScale(scaledX);
		scaledY = joystickScale(scaledY);				
		scaledZ = joystickScale(scaledZ);

		Robot.drivetrain.driveFieldOrientated(scaledX, scaledY, scaledZ, throttle);
		
		SmartDashboard.putNumber("Scaled Y", scaledY);
		SmartDashboard.putNumber("Jostick Y", Robot.oi.joystickY());
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
		double result = (Math.pow(input, .85));
		if (isNegative) {
			result *= -1.0;
		}
		return result;
	}
}
