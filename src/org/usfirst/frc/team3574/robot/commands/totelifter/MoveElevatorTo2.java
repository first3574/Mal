package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorTo2 extends Command {
	double pastPos;
	int state = 0;
	double futureStopTime = 0;
	boolean isDone = false;
	double targetOffset = ToteAndRecycleLifterUpper.CARRY_LEVEL_OFFSET;

	public MoveElevatorTo2() {
		requires(Robot.toteandrecyclelifterupper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
		isDone = false;
		pastPos = Robot.toteandrecyclelifterupper.getSetpointOffset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(pastPos < (ToteAndRecycleLifterUpper.CARRY_LEVEL_OFFSET)) {
			//we are at 3

			if (Robot.toteandrecyclelifterupper.isToteInRobot()) {
				isDone = true;
				return;
			}

			switch (state) {
			case 0:
//				Robot.toteandrecyclelifterupper.tabSolenoidHoldTote();
				Robot.toteandrecyclelifterupper.setSetpointOffset(targetOffset);
				state++;

				break;
			case 1:
				if (Robot.toteandrecyclelifterupper.getElevatorOffest() <= (-targetOffset + 7.5) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-targetOffset - 7.5)) {
					isDone = true;
				}
				break;
			}

		} else {
			//we are at 1

			switch (state) {
			case 0:
				Robot.toteandrecyclelifterupper.setSetpointOffset(Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST);
				if (Robot.toteandrecyclelifterupper.getElevatorOffest() <=(-Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST+7.5) 
						&& Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST-7.5)) {
					Robot.toteandrecyclelifterupper.setElevatorPosAtCurrentGet(); //setSetpointOffset(Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST);
					Robot.toteandrecyclelifterupper.tabSolenoidHoldTote();
					futureStopTime = super.timeSinceInitialized() + .25;
					state++;
				}

				break;	

			case 1:
				if (futureStopTime <= super.timeSinceInitialized()) {
					Robot.toteandrecyclelifterupper.setSetpointOffset(targetOffset);
					state++;
				}
				break;
			case 2:
				if (Robot.toteandrecyclelifterupper.getElevatorOffest() <= (-targetOffset + 7.5) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-targetOffset - 7.5)) {
					isDone = true;
				}
				break;
			default:
				//ToDo: Make Robot Explode >:)
				break;
			}

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
