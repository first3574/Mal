package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithDistance extends Command {
	
	private double x;
	private double y;
	private double rot;
	private int dis;
	String name;
	boolean goTime = false;
	int loopCount = 0;
	
    public DriveWithDistance(double xSpeed, double ySpeed, double rotate, int distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);at
    	requires(Robot.drivetrain);
    	
    	x = xSpeed;
    	y = ySpeed;	
    	rot = rotate;
    	dis = distance;
    	name = this.getClass().getName() + " " + this.hashCode();
		System.out.println(name + " instantiation");
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	goTime = false;
    	loopCount = 0;
    	Robot.drivetrain.resetEncoderValues();
    	System.out.println(name + " resetEncoderValues " + Robot.drivetrain.backLeftEncoderValue());
    	
//    	try {
//    		Thread.sleep(500);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
    	
		System.out.println(name + " init");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		System.out.println(name + " exec start");
		System.out.println(name + Robot.drivetrain.backLeftEncoderValue());
    	if(!goTime && Math.abs(Robot.drivetrain.backLeftEncoderValue()) > 50 && loopCount < 50) {
    		loopCount++;
    		return;
    	}
    	goTime = true;
    	Robot.drivetrain.driveFieldOrientated(x, y, rot);
		System.out.println(name + " exec end");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	int leftMotorsMin = Math.min(Math.abs(Robot.drivetrain.frontLeftEncoderValue()), Math.abs(Robot.drivetrain.backLeftEncoderValue()));
    	int rightMotorsMin = Math.min(Math.abs(Robot.drivetrain.frontRightEncoderValue()), Math.abs(Robot.drivetrain.backRightEncoderValue()));
    	
    	SmartDashboard.putNumber("LeftMotorsMin", leftMotorsMin);
    	SmartDashboard.putNumber("RightMotorsMin", rightMotorsMin);
    	
    	if(Math.min(leftMotorsMin, rightMotorsMin) >= dis && goTime) {
        	Robot.drivetrain.driveFieldOrientated(0.0, 0.0, 0.0);
			System.out.println(name + " isFinished");
        	return true; 
        } else {
        	return false;
        }
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println(name + " end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println(name + " interrupted");
    }
}
