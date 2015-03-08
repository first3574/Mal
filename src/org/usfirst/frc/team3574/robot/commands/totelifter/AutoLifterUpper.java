package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLifterUpper extends Command {
	
	private double bSP = 0.0;
	private double tSP = 0.0;

    public AutoLifterUpper(double bottomSetPoint, double topSetPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.totelifterupper);
    	bSP = bottomSetPoint;
    	tSP = topSetPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("TrigerHappy");
    	if (Robot.totelifterupper.stopLiftSwitch() == false) {
    			Robot.totelifterupper.setSetpointOffset(bSP);
    			Robot.totelifterupper.setSetpointOffset(tSP);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
