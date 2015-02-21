package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveByCamera extends Command {

	boolean isDone = false;
			
    public DriveByCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (SmartDashboard.getNumber("Camera") < -.2) {
    		Robot.drivetrain.driveFieldOrientated(0, 0, .2);
    	} else if (SmartDashboard.getNumber("Camera") > .2) {
    		Robot.drivetrain.driveFieldOrientated(0, 0, -.2);
    	} else {
    		Robot.drivetrain.driveFieldOrientated(0, 0, 0);
    		isDone = true;
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
