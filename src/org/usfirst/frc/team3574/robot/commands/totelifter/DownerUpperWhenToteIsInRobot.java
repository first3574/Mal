package org.usfirst.frc.team3574.robot.commands.totelifter;

import org.usfirst.frc.team3574.robot.commands.CollectWithJoy;
import org.usfirst.frc.team3574.robot.commands.Log;
import org.usfirst.frc.team3574.robot.commands.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoy;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
    
public class DownerUpperWhenToteIsInRobot extends CommandGroup {
    public  DownerUpperWhenToteIsInRobot(Command targetHeightCommand) {
//    	addSequential(new Log("before", .0001));
//    	addParallel(new DriveWithJoy());
    	addSequential(new WaitUntilSwitchClicker());
//    	addParallel(new NoDrive(), 1.5);
    	addSequential(new MoveElevatorTo1());
//    	addSequential(new Log("after 1", .0001));
    	addSequential(targetHeightCommand);
//    	addSequential(new Log("after 3", .0001));
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
