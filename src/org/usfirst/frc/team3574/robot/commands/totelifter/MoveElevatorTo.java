package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteLifterUpper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorTo extends Command {
	
	private double sP = 0.0;
	int count;

    public MoveElevatorTo(double setPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.totelifterupper);
    	sP = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	switch (count) {
//		case 0:
//			Robot.totelifterupper.setSetpointOffset(sP/2);
//			
//			break;
//
//		default:
//			break;
//		}  p 20 D 6
    	Robot.totelifterupper.setSetpointOffset(sP);
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
