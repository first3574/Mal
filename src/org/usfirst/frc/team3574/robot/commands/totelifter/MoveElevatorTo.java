package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveElevatorTo extends Command {
	
	private double sP = 0.0;
	int count;
	boolean isDone;

    public MoveElevatorTo(double setPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteandrecyclelifterupper);
    	sP = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	switch (count) {
//		case 0:
//			Robot.toteandrecyclelifterupper.setSetpointOffset(sP/2);
//			
//			break;
//
//		default:
//			break;
//		}  p 20 D 6
    	Robot.toteandrecyclelifterupper.setSetpointOffset(sP);
    	SmartDashboard.putNumber("Set Point", -sP);
    	SmartDashboard.putBoolean("-sP+10", (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-sP+10)));
    	SmartDashboard.putBoolean("-sP-10", (Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-sP-10)));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-sP+15) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-sP-15)) {
    		isDone = true;
    	}
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
