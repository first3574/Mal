package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveFastSlowDown;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoRotateDriveByCameraRightOrLeft;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.totelifter.Calibrate;
import org.usfirst.frc.team3574.robot.commands.totelifter.CalibrateAndGoToStart;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo1;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo2;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo3;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomousPickUpTotes extends CommandGroup {
    
    public  AutomousPickUpTotes() {
    	addSequential(new CalibrateAndGoToStart());
    	addSequential(new ResetYaw());
    	addParallel(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
    	addSequential(new DriveWithDistance(0, -1.0, 0, 1000));// 900
    	addParallel(new MoveElevatorTo3());
//    	addSequential(new DriveWithDistance(0.5, 0.75, 0, 1824));
//    	addSequential(new DriveWithDistance(0, 1.0, 0, 1000));
//    	addSequential(new DriveWithDistance(0.5, 0, 0, 1525));
//    	addSequential(new NoRotateDriveByCameraRightOrLeft(0.5));
    	addSequential(new DriveFastSlowDown());
    	addSequential(new DriveWithDistance(0, -1.0, 0, 1000));
    	addSequential(new MoveElevatorTo1());
    	addParallel(new MoveElevatorTo3());
//    	addSequential(new DriveWithDistance(0, 1.0, 0, 1000));
//    	addSequential(new DriveWithDistance(0.5, 0, 0, 1525));
//    	addSequential(new NoRotateDriveByCameraRightOrLeft(0.5));
    	addSequential(new DriveFastSlowDown());
    	addSequential(new DriveWithDistance(0, -1.0, 0, 1000));
    	addSequential(new MoveElevatorTo1());
    	addParallel(new MoveElevatorTo2());
    	addSequential(new DriveWithDistance(0, 1.0, -0.8, 1000));
    	addSequential(new MoveElevatorTo1());
    	addParallel(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT));
    	addSequential(new DriveWithDistance(0, -1.0, 0, 700));

    	
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
