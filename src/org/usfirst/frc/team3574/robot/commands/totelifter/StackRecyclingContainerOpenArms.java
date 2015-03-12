package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StackRecyclingContainerOpenArms extends Command {
	boolean oC;

	/**
	 * Use to open/close recycle arms.
	 * @param setOpen True = Open and False = Close
	 */
    public StackRecyclingContainerOpenArms(Boolean setOpen) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//    	requires(Robot.toteandrecyclelifterupper);
    	oC = setOpen;
    	System.out.println("oc" + oC + setOpen);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (oC) {
    		Robot.toteandrecyclelifterupper.openRecycle();
    	} else {
    		Robot.toteandrecyclelifterupper.closeRecycle();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
