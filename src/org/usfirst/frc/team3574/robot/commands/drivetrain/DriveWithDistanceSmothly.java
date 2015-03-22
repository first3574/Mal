package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithDistanceSmothly extends Command {
	
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
	double[] fLEncHistory = {-1, -1, -1, -1, -1};
	double[] fREncHistory = {-1, -1, -1, -1, -1};
	double[] bLEncHistory = {-1, -1, -1, -1, -1};
	double[] bREncHistory = {-1, -1, -1, -1, -1};
	int fLEncHistoryP = 0;
	int fREncHistoryP = 0;
	int bLEncHistoryP = 0;
	int bREncHistoryP = 0;
	boolean fLEncBroken = false;
	boolean fREncBroken = false;
	boolean bLEncBroken = false;
	boolean bREncBroken = false;
	boolean isDone = false;
	
    public DriveWithDistanceSmothly(double xSpeed, double ySpeed, double rotate, int distance) {
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
    	
    	fLEncBroken = false;
    	fREncBroken = false;
    	bLEncBroken = false;
    	bREncBroken = false;
    	
    	iMUStart = Robot.drivetrain.getOrigIMUGetYaw();
    	System.out.println("initialize" + Robot.drivetrain.backRightEncoderValue() + "		" + Robot.drivetrain.frontRightEncoderValue() + "		" + Robot.drivetrain.backLeftEncoderValue() + "	" + Robot.drivetrain.frontLeftEncoderValue());
    	
    	isDone = false;
    	
		System.out.println(name + " init");
    	
    }
    
    boolean areAllSame(double[] array) {
    	double firstItem = array[0];
    	int binPos = 1;
    	while (binPos < array.length && array[binPos] == firstItem) {
    		binPos++;
    	}
    	
    	if (binPos >= array.length){
    		return true;
    	}
    	return false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//		System.out.println(name + " exec start");
//		System.out.println(name + Robot.drivetrain.backLeftEncoderValue());
    	
    	fLEncHistory[fLEncHistoryP % 5] = Robot.drivetrain.frontLeftEncoderValue();
    	fREncHistory[fREncHistoryP % 5] = Robot.drivetrain.frontRightEncoderValue();
    	bLEncHistory[bLEncHistoryP % 5] = Robot.drivetrain.backLeftEncoderValue();
    	bREncHistory[bREncHistoryP % 5] = Robot.drivetrain.backRightEncoderValue();
    	fLEncHistoryP++;    //    //
    	fREncHistoryP++;  //    ///
    	bLEncHistoryP++;///////
    	bREncHistoryP++;
    	
    	if (areAllSame(fLEncHistory))
    		fLEncBroken = true;
    	else
    		fLEncBroken = false;
    	if (areAllSame(fREncHistory))
    		fREncBroken = true;
    	else
    		fLEncBroken = false;
    	if (areAllSame(bLEncHistory))
    		bLEncBroken = true;
    	else
    		fLEncBroken = false;
    	if (areAllSame(bREncHistory))
    		bREncBroken = true;
    	else
    		fLEncBroken = false;
    	
		iMUDiffrence = Robot.drivetrain.getOrigIMUGetYaw() - iMUStart;
		
		if (rot == 0.0) {
			selfStraght = iMUDiffrence*.05;
		}
		
    	int fLEncV = fLEncBroken ? 9999999 : Math.abs(Robot.drivetrain.frontLeftEncoderValue()-startEncoderValueFrontLeft);
    	int fREncV = fREncBroken ? 9999999 : Math.abs(Robot.drivetrain.frontRightEncoderValue()-startEncoderValueFrontRight);
    	int bLEncV = bLEncBroken ? 9999999 : Math.abs(Robot.drivetrain.backLeftEncoderValue()-startEncoderValueBackLeft);
    	int bREncV = bREncBroken ? 9999999 : Math.abs(Robot.drivetrain.frontRightEncoderValue()-startEncoderValueBackRight);
    	int leftMotorsMin = Math.min(fLEncV, bLEncV);
    	int rightMotorsMin = Math.min(fREncV, bREncV);
    	SmartDashboard.putNumber("LeftMotorsMin", leftMotorsMin);
    	SmartDashboard.putNumber("RightMotorsMin", rightMotorsMin);
    	
    	int rAndLMin = Math.min(leftMotorsMin, rightMotorsMin);
    	
    	if((dis*.95) < rAndLMin) {
        	Robot.drivetrain.driveFieldOrientated((x*.3), (y*.3), selfStraght);
        	
        } else if ((dis*.9) < rAndLMin) {
        	Robot.drivetrain.driveFieldOrientated((x*.4), (y*.4), selfStraght);
        	
        } else if ((dis*.8) > 0) {
        	Robot.drivetrain.driveFieldOrientated((x*.5), (y*.5), selfStraght);
        }		
    	
    	if(rAndLMin >= dis) {
        	Robot.drivetrain.driveFieldOrientated(0.0, 0.0, 0.0);
			System.out.println(name + " isFinished");
//			System.out.println("Finished" + Robot.drivetrain.backRightEncoderValue() + "		" + Robot.drivetrain.frontRightEncoderValue() + "		" + Robot.drivetrain.backLeftEncoderValue() + "	" + Robot.drivetrain.frontLeftEncoderValue());
        	isDone = true; 
        }
    	
    	Robot.drivetrain.driveFieldOrientated(x, y, selfStraght);
//		System.out.println(name + " exec end");
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isDone;
    	
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
