package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLeftOrRight extends Command {
	
	double deadZone;
	double throttle;
	double scaledX;

	final private double MAKE_GO_STRAIGHT = 0.9;
	
	//90 = facing left
	//-90 =
	//
	

    public MoveLeftOrRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
		requires(Robot.drivetrain);
		System.out.println("drive with joy started");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	throttle = 1.0;
		deadZone = 0.05;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		
    	//Go fast if trigger is pulled
		if (Robot.oi.isTriggerPulled()) {
			throttle = 1;
		} else {
			throttle = ((-Robot.oi.joystickThrottle() + 1) / 4) +.5;
		}
    	
		scaledX = Math.abs(-Robot.oi.joystickX()) < deadZone ? 0.0 : -Robot.oi.joystickX() * throttle;
		scaledX = joystickScale(scaledX);
		
		// if facing left, then joystick left should move backwards in robotCentric mode
		if(!(Robot.drivetrain.getYaw() > 90.0 && Robot.drivetrain.getYaw() < 270.0)){
				scaledX = -scaledX;
			}
		
		Robot.drivetrain.driveRobotOriented( 0, scaledX, 0, throttle);
		
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
