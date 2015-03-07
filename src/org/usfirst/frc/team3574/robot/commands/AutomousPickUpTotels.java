package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.totelifter.Calibrate;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.ToteLifterUpper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomousPickUpTotels extends CommandGroup {
    
    public  AutomousPickUpTotels() {
    	addSequential(new Calibrate());
    	addSequential(new ResetYaw());
    	addParallel(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
    	addSequential(new DriveWithDistance(0, -0.7, 0, 900));
    	addSequential(new MoveElevatorTo(ToteLifterUpper.STACK_LEVEL_OFFSET), 0.2);
    	addSequential(new DriveWithDistance(0, 0.7, 0, 900));
    	addSequential(new DriveWithDistance(0.7, 0, 0, 1500));
    	addSequential(new DriveWithDistance(0, -0.7, 0, 900));
    	addSequential(new MoveElevatorTo(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET), 0.2);
    	addSequential(new MoveElevatorTo(ToteLifterUpper.STACK_LEVEL_OFFSET), 0.2);
    	addSequential(new DriveWithDistance(0, 0.7, 0, 900));
    	addSequential(new DriveWithDistance(0.7, 0, 0, 1500));
    	addSequential(new DriveWithDistance(0, -0.7, 0, 900));
    	addSequential(new MoveElevatorTo(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET), 0.2);
    	addSequential(new MoveElevatorTo(ToteLifterUpper.CARRY_LEVEL_OFFSET), 0.2);
    	addSequential(new DriveWithDistance(0, 0.7, -0.8, 800));
    	addSequential(new MoveElevatorTo(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET), 0.2);
    	addParallel(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT), 1);
    	addSequential(new DriveWithDistance(0, -0.7, 0, 400));

    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
//              addSequential(new Command2());
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
