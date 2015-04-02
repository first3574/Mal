package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveRobotOrentated extends Command {
	double x = 0;
	double y = 0;
	double z = 0;
	double time = 0;

    public DriveRobotOrentated(double xSpeed, double ySpeed, double zSpeed, double timeInSec) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	x = xSpeed;
    	y = ySpeed;
    	z = zSpeed;
    	time = timeInSec;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.drivetrain.driveRobotOriented( x, y, z, 1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timeSinceInitialized() > time) {
        	return true; 
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
