package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.RobotConstants;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.totelifter.CalibrateAndGoToStart;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo3;
import org.usfirst.frc.team3574.robot.commands.totelifter.StackRecyclingContainerOpenArms;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomousStrafeRecycleToScore extends CommandGroup {
    
    public  AutomousStrafeRecycleToScore() {
    	addSequential(new CalibrateAndGoToStart());
    	addSequential(new ResetYaw(270.0));
    	addSequential(new StackRecyclingContainerOpenArms(false));
    	addSequential(new MoveElevatorTo3());
    	addSequential(new DriveWithDistance(0, RobotConstants.AUTO_SPEED, 0, RobotConstants.AUTO_DISTANCE));
    	
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
