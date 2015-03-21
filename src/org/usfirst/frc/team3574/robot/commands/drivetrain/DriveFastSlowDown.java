package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFastSlowDown extends Command {
	double selfStraght;
	double iMUStart;
	double iMUDiffrence;
	boolean isDone = false;
    public DriveFastSlowDown() {
    	requires(Robot.drivetrain);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    	iMUStart = Robot.drivetrain.getOrigIMUGetYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	iMUDiffrence = Robot.drivetrain.getOrigIMUGetYaw() - iMUStart;
		selfStraght = iMUDiffrence*.05;
		
    	if(timeSinceInitialized() > 1.5) {
        	Robot.drivetrain.driveFieldOrientated(0.5 / timeSinceInitialized(), 0.35 / timeSinceInitialized(), selfStraght);
        	if (Robot.drivetrain.getMinMotorVolt() < 0.5) {
        		isDone = true;
    		}
        } else if (timeSinceInitialized() > 0.5) {
        	Robot.drivetrain.driveFieldOrientated( 0.5, 0.35, selfStraght);
        	
        } else if (timeSinceInitialized() > 0) {
        	Robot.drivetrain.driveFieldOrientated( 1.0, 0.35, selfStraght);
        }		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveFieldOrientated(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
