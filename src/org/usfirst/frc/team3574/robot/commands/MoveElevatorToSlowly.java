package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveElevatorToSlowly extends Command {
	
	private double sP = 0.0;
	double getToSetPointSlowly = 0.0;
	int state;
	int state1;
	boolean isDone;
	double futureStopTime;

    public MoveElevatorToSlowly(double setPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteandrecyclelifterupper);
    	sP = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = 0;
//    	state1 = 0;
    	isDone = false;
    	Robot.toteandrecyclelifterupper.setElevatorMotorToPIDMode();
    	getToSetPointSlowly = Robot.toteandrecyclelifterupper.getElevatorOffest();
    	futureStopTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if ((Robot.toteandrecyclelifterupper.getElevatorOffest() > -sP) && (-sP <= getToSetPointSlowly)) {
    		getToSetPointSlowly = (Robot.toteandrecyclelifterupper.getElevatorOffest() + 2);
    	} else if ((Robot.toteandrecyclelifterupper.getElevatorOffest() < -sP) && (-sP >= getToSetPointSlowly)) {
    		getToSetPointSlowly = (Robot.toteandrecyclelifterupper.getElevatorOffest() - 2);
    	}
    	
    	Robot.toteandrecyclelifterupper.setSetpointOffset(-getToSetPointSlowly); 	
   
    	
    	if (Robot.toteandrecyclelifterupper.isGoingDown) {
    		Robot.toteandrecyclelifterupper.openRecycle();
    		Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
    	}
    	
    	
    	switch (state) {
		case 0:
			if (Robot.toteandrecyclelifterupper.isGoingDown ){
	    			futureStopTime = super.timeSinceInitialized() + .25;
		    		state++;
			} else if(!Robot.toteandrecyclelifterupper.isGoingDown 
						&& Robot.toteandrecyclelifterupper.getElevatorOffest() < -Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST) {
				Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST);
	    		if (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST+7.5) 
	    				&& Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST-7.5)) {
	    			Robot.toteandrecyclelifterupper.setElevatorPosAtCurrentGet(); //setSetpointOffset(Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST);
	    			Robot.toteandrecyclelifterupper.tabSolenoidHoldTote();
	    			futureStopTime = super.timeSinceInitialized() + .25;
	    			state++;
	    		}
			}
			break;	
			
		case 1:
			if (futureStopTime <= super.timeSinceInitialized()) {
	    		Robot.toteandrecyclelifterupper.setSetpointOffset(sP);
	    		state++;
			}
			break;

		default:
			//ToDo: Make Robot Explode >:)
			break;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-sP+7.5) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-sP-7.5)) {
    		Robot.toteandrecyclelifterupper.setSetpointOffset(sP);
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
