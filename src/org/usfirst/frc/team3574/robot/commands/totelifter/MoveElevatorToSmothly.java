package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveElevatorToSmothly extends Command {
	
	private double sP = 0.0;
	double sSP = 5;
	double startP;
	int state;
	boolean isDone;
	double futureStopTime;

    public MoveElevatorToSmothly(double setPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteandrecyclelifterupper);
    	sP = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
    	isDone = false;
    	startP = Robot.toteandrecyclelifterupper.getElevatorPos();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Robot.toteandrecyclelifterupper.isGoingDown) {
    		Robot.toteandrecyclelifterupper.openRecycle();
    	}
    	
    	if (Robot.toteandrecyclelifterupper.getElevatorPos() < (sP+30)) {
        	Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.getElevatorOffest()-sSP);
    	} else if (Robot.toteandrecyclelifterupper.getElevatorPos() < (sP-30)) {
        	Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.getElevatorOffest()+sSP);
    	} else if (Robot.toteandrecyclelifterupper.getElevatorPos() < (startP+30) && Robot.toteandrecyclelifterupper.getElevatorPos() > (startP+5)) {
        	Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.getElevatorOffest()+ sSP);
    	} else if (Robot.toteandrecyclelifterupper.getElevatorPos() > (startP-30) && Robot.toteandrecyclelifterupper.getElevatorPos() < (startP-5)) {
        	Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.getElevatorOffest()- sSP);
    	} else {
        	Robot.toteandrecyclelifterupper.setSetpointOffset(sP);
    	}
    	
//    	switch (state) {
//		case 0:
//			if (Robot.toteandrecyclelifterupper.isOpenSwitchForTabSystemTripped() && Robot.toteandrecyclelifterupper.isGoingDown) {
//	    		Robot.toteandrecyclelifterupper.setElevatorPosAtCurent();
//	    		Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
//	    		futureStopTime = super.timeSinceInitialized() + .25;
//	    		state++;
//			} else if(Robot.toteandrecyclelifterupper.isCloseSwitchForTabSystemTripped() && !Robot.toteandrecyclelifterupper.isGoingDown) {
//				Robot.toteandrecyclelifterupper.setElevatorPosAtCurent();
//	    		Robot.toteandrecyclelifterupper.tabSolenoidHoldTote();
//	    		futureStopTime = super.timeSinceInitialized() + .25;
//	    		state++;
//			}
//			break;
//			
//		case 1:
//			if (futureStopTime <= super.timeSinceInitialized()) {
//	    		Robot.toteandrecyclelifterupper.setSetpointOffset(sP);
//	    		state++;
//			}
//			break;
//
//		default:
//			//ToDo: Make Robot Explode >:)
//			break;
//		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-sP+7.5) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-sP-7.5)) {
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
