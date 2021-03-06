package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.totelifter.CalibrateAndGoToStart;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomousPushToteToScore extends CommandGroup {
    
    public  AutomousPushToteToScore() {
    	addSequential(new CalibrateAndGoToStart());
    	addSequential(new ResetYaw());
    	addSequential(new DriveWithDistance(0, 1, 0, 5000));
    	addSequential(new DriveWithDistance(0, -.5, 0, 200));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
