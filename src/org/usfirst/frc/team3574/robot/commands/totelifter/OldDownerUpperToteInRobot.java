package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoy;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OldDownerUpperToteInRobot extends CommandGroup {
    
    public  OldDownerUpperToteInRobot() {
    	addParallel(new DriveWithJoy());
    	addSequential(new WaitUntilSwitchClicker());
//    	addParallel(new NoDrive(), 1.5);
    	addSequential(new MoveElevatorTo(ToteAndRecycleLifterUpper.STACK_LEVEL_OFFSET));
    	addSequential(new MoveElevatorTo(ToteAndRecycleLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET));
//    	addSequential(targetHeightCommand);
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
