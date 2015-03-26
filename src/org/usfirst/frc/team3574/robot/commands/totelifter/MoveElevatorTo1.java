package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorTo1 extends Command {
	double pastPos;
	int state = 0;
	double futureStopTime = 0;
	boolean isDone = false;
	double targetOffset = ToteAndRecycleLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET;
	boolean toteInRobotAtStart = false;
	boolean speedCommandSent = false;

	public MoveElevatorTo1() {
		requires(Robot.toteandrecyclelifterupper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		state = 0;
		isDone = false;
		pastPos = Robot.toteandrecyclelifterupper.getSetpointOffset();
		toteInRobotAtStart = Robot.toteandrecyclelifterupper.isToteInRobot();
		speedCommandSent = false;
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("MT1 Live" + Robot.toteandrecyclelifterupper.isToteInRobot());
		System.out.println("MT! Stored" + toteInRobotAtStart);
		
		if(pastPos < (Robot.toteandrecyclelifterupper.DISENGAGE_TAB_OFFSET)) {
			//we are at 3
			
//			if(!speedCommandSent) {
//				Robot.toteandrecyclelifterupper.setElevatorMotorSpeed(0.3);
//				speedCommandSent = true;
//				
//			} else if() {
//				Robot.toteandrecyclelifterupper.setElevatorMotorSpeed(0.0);
//				Robot.toteandrecyclelifterupper.
//				}
				
				
			
			switch (state) {
			case 0:
				Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
				futureStopTime = super.timeSinceInitialized() + .25;
				state++;

				break;	

			case 1:
				if (futureStopTime <= super.timeSinceInitialized()) {
					if (toteInRobotAtStart) {
						Robot.toteandrecyclelifterupper.setSetpointOffset(targetOffset);
					} else {
						Robot.toteandrecyclelifterupper.setSetpointOffset(targetOffset);
					}

					state++;
				}
				break;
			case 2:
				if (Robot.toteandrecyclelifterupper.getElevatorOffest() <= (-targetOffset + 7.5) && Robot.toteandrecyclelifterupper.getElevatorOffest() >=(-targetOffset - 7.5)) {
					if (toteInRobotAtStart) {
						Robot.toteandrecyclelifterupper.openRecycle();
					}

					isDone = true;
				}
				break;
			default:
				//ToDo: Make Robot Explode >:)
				break;
			}

		} else {
			//we are at 2
			switch (state) {
			case 0:
				Robot.toteandrecyclelifterupper.tabSolenoidFreeTote();
				futureStopTime = super.timeSinceInitialized() + .25;
				state++;

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
		System.out.println("end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("int");
	}
}
