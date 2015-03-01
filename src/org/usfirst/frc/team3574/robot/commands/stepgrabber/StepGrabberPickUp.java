package org.usfirst.frc.team3574.robot.commands.stepgrabber;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StepGrabberPickUp extends Command {

	Timer time = new Timer();   
	int state;
	boolean isDone = false;

	public StepGrabberPickUp() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	} 


	// Called just before this Command runs the first time
	protected void initialize() { 
		time.reset();
		time.start();
		state = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (state) {
		case 0:
			Robot.stepgrabber.pusherThingOut();
			state++;
			break;
		case 1:
			if (time.get() > .1){
				Robot.stepgrabber.lifterThingUp();
				time.reset();
				state++;
			}
			break;
		case 2:
			if (time.get() > .1){
				isDone = true;
			}
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
