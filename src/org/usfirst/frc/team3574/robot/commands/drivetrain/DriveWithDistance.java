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
	int startEncoderValueFrontLeft;
	int startEncoderValueBackLeft;
	int startEncoderValueFrontRight;
	int startEncoderValueBackRight;
	double selfStraght;
	double iMUStart;
	double iMUDiffrence;
	
    public DriveWithDistance(double xSpeed, double ySpeed, double rotate, int distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);at
    	requires(Robot.drivetrain);
    	
    	x = xSpeed;
    	y = ySpeed;	
    	rot = rotate;
    	dis = distance;
    	selfStraght = rot;
    	name = this.getClass().getName() + " " + this.hashCode();
		System.out.println(name + " instantiation");
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startEncoderValueBackRight = Robot.drivetrain.backRightEncoderValue();
    	startEncoderValueFrontRight = Robot.drivetrain.frontRightEncoderValue();
    	startEncoderValueBackLeft = Robot.drivetrain.backLeftEncoderValue();
    	startEncoderValueFrontLeft = Robot.drivetrain.frontLeftEncoderValue();
    	
    	iMUStart = Robot.drivetrain.getOrigIMUGetYaw();
    	System.out.println("initialize" + Robot.drivetrain.backRightEncoderValue() + "		" + Robot.drivetrain.frontRightEncoderValue() + "		" + Robot.drivetrain.backLeftEncoderValue() + "	" + Robot.drivetrain.frontLeftEncoderValue());
    	
    	
		System.out.println(name + " init");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//		System.out.println(name + " exec start");
//		System.out.println(name + Robot.drivetrain.backLeftEncoderValue());
		
		iMUDiffrence = Robot.drivetrain.getOrigIMUGetYaw() - iMUStart;
		
		if (rot == 0.0) {
			selfStraght = iMUDiffrence*.05;
		}
		
    	Robot.drivetrain.driveFieldOrientated(x, y, selfStraght);
//		System.out.println(name + " exec end");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	int leftMotorsMin = Math.min(Math.abs(Robot.drivetrain.frontLeftEncoderValue()-startEncoderValueFrontLeft), Math.abs(Robot.drivetrain.backLeftEncoderValue()-startEncoderValueBackLeft));
    	int rightMotorsMin = Math.min(Math.abs(Robot.drivetrain.frontRightEncoderValue()-startEncoderValueFrontRight), Math.abs(Robot.drivetrain.backRightEncoderValue()-startEncoderValueBackRight));
    	
    	SmartDashboard.putNumber("LeftMotorsMin", leftMotorsMin);
    	SmartDashboard.putNumber("RightMotorsMin", rightMotorsMin);
    	
    	if(Math.min(leftMotorsMin, rightMotorsMin) >= dis) {
        	Robot.drivetrain.driveFieldOrientated(0.0, 0.0, 0.0);
			System.out.println(name + " isFinished");
			System.out.println("Finished" + Robot.drivetrain.backRightEncoderValue() + "		" + Robot.drivetrain.frontRightEncoderValue() + "		" + Robot.drivetrain.backLeftEncoderValue() + "	" + Robot.drivetrain.frontLeftEncoderValue());
	    	
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
