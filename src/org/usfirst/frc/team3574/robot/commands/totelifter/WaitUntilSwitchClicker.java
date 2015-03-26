package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.NoDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *	
 */
public class WaitUntilSwitchClicker extends Command {


	public WaitUntilSwitchClicker() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.toteandrecyclelifterupper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		
		System.out.println("wait" + Robot.toteandrecyclelifterupper.isToteInRobot());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.toteandrecyclelifterupper.isToteInRobot()) {
			Scheduler.getInstance().add(new NoDrive());
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
