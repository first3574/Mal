package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveElevatorSlowerTo extends Command {

	private double sP = 0.0;
	int state;
	int state1;
	boolean isDone;
	boolean isMovingUp;
	double futureStopTime;
	boolean speedCommandSent = false;
	boolean doneRamping = false;
	double startPoint;

	public MoveElevatorSlowerTo(double setPoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.toteandrecyclelifterupper);
		sP = setPoint;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
		isDone = false;
		speedCommandSent = false;
		doneRamping = false;
		futureStopTime = 0;


		if(sP > Robot.toteandrecyclelifterupper.getElevatorOffest()) {
			isMovingUp = true;
		}else {
			isMovingUp = false;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Elevator offset from beginning",  Math.abs(Robot.toteandrecyclelifterupper.getElevatorOffest() - startPoint));
		if(!speedCommandSent) {
			Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
			Robot.toteandrecyclelifterupper.setElevatorMotorSpeed(0.3);
			speedCommandSent = true;
			startPoint = Robot.toteandrecyclelifterupper.getElevatorOffest();
			SmartDashboard.putBoolean("Elevator ramp finished", false);
		} else if (super.timeSinceInitialized() > .5 && !doneRamping) {
			Robot.toteandrecyclelifterupper.setElevatorMotorSpeed(0.0);
			Robot.toteandrecyclelifterupper.setElevatorMotorToPIDMode();
			SmartDashboard.putBoolean("Elevator ramp finished", true);
			Robot.toteandrecyclelifterupper.setSetpointOffset(sP);
			doneRamping = true;
			return;
		}

		SmartDashboard.putNumber("Set Point", -sP);
		SmartDashboard.putBoolean("-sP+7.5", (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-sP+7.5)));
		SmartDashboard.putBoolean("-sP-7.5", (Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-sP-7.5)));



		if (Robot.toteandrecyclelifterupper.isGoingDown) {
			Robot.toteandrecyclelifterupper.openRecycle();
			Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
		}


		switch (state) {
		case 0:
			if (Robot.toteandrecyclelifterupper.isGoingDown ){
				//						&& Robot.toteandrecyclelifterupper.getElevatorOffest() > -Robot.toteandrecyclelifterupper.DISENGAGE_TAB_OFFSET) {
				//	    		Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.DISENGAGE_TAB_OFFSET);
				//	    		if (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-Robot.toteandrecyclelifterupper.DISENGAGE_TAB_OFFSET)+20) {
				//	    			Robot.toteandrecyclelifterupper.setElevatorPosAtCurrentGet(); //setSetpointOffset(Robot.toteandrecyclelifterupper.DISENGAGE_TAB_OFFSET);
				//	    			Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
				futureStopTime = super.timeSinceInitialized() + .25;
				state++;
				//	    		}
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
