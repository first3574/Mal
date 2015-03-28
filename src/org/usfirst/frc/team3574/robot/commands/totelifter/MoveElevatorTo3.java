package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorTo3 extends Command {
	double pastPos;
	double futureStopTime = 0;
	int state;
	boolean isDone = false;
	double targetOffset = ToteAndRecycleLifterUpper.STACK_LEVEL_OFFSET;

	public MoveElevatorTo3() {
		requires(Robot.toteandrecyclelifterupper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("MoveTo3Init");
		state = 0;
		isDone = false;
		pastPos = Robot.toteandrecyclelifterupper.getSetpointOffset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.out.println("MT3" + Robot.toteandrecyclelifterupper.isToteInRobot());
		
		if(pastPos > (Robot.toteandrecyclelifterupper.ENGAGE_TAB_OFFEST)) {
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

		} else {
			//we are at 2
			Robot.toteandrecyclelifterupper.setSetpointOffset(targetOffset);

			if (Robot.toteandrecyclelifterupper.getElevatorOffest() <= (-targetOffset + 7.5) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-targetOffset - 7.5)) {
				isDone = true;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("MoveTo3End");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("MoveTo3Interrupted");
	}
}
