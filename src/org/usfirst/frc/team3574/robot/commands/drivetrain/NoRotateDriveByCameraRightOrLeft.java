package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NoRotateDriveByCameraRightOrLeft extends Command {

	boolean isDone = false;
	
	NetworkTable net;
	double disFromCen;
	double posY;
	double posX;
	double selfStraght;
	double iMUStart;
	double iMUDiffrence;
	double speed;
	double framecount;
	
	/**
	 * 
	 * @param lateralSpeed - Left is positive right is negative
	 */
    public NoRotateDriveByCameraRightOrLeft(double lateralSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);rightOrLeft
    	requires (Robot.drivetrain);
    	net = NetworkTable.getTable("Vision");
    	speed = lateralSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    	iMUStart = Robot.drivetrain.getOrigIMUGetYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	disFromCen = net.getNumber("Distance From Center");
    	posX = net.getNumber("Position X");
    	posY = net.getNumber("Position Y");
    	framecount = net.getNumber("Frame Count");
//    	SmartDashboard.putBoolean("disFromCen < -5", disFromCen < -5);
//    	SmartDashboard.putBoolean("disFromCen > 5", disFromCen > 5);
//    	SmartDashboard.putNumber("disFromCen", disFromCen);
		iMUDiffrence = Robot.drivetrain.getOrigIMUGetYaw() - iMUStart;
		
			selfStraght = iMUDiffrence*.05;
    	
		SmartDashboard.putNumber("framecount", framecount);
    	if (posX == -1 && posY == -1) {
    		Robot.drivetrain.driveFieldOrientated(speed, 0, selfStraght);
    	} else if (disFromCen < -5) {
    		Robot.drivetrain.driveFieldOrientated(-.1, 0, selfStraght);
    	} else if (disFromCen > 5) {
    		Robot.drivetrain.driveFieldOrientated(.1, 0, selfStraght);
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
