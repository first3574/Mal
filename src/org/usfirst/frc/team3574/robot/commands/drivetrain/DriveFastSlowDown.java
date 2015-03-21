package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFastSlowDown extends Command {
	boolean isDone = false;
    public DriveFastSlowDown() {
    	requires(Robot.drivetrain);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timeSinceInitialized() > 1.5) {
        	Robot.drivetrain.driveFieldOrientated(0.5 / timeSinceInitialized(), 0.35 / timeSinceInitialized(), 0);
        	if (Robot.drivetrain.getMinMotorVolt() < 0.5) {
        		isDone = true;
    		}
        } else if (timeSinceInitialized() > 0.5) {
        	Robot.drivetrain.driveFieldOrientated( 0.5, 0.35, 0);
        	
        } else if (timeSinceInitialized() > 0) {
        	Robot.drivetrain.driveFieldOrientated( 1.0, 0.35, 0);
    
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
