
package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.AutomousPickUpTotels;
import org.usfirst.frc.team3574.robot.commands.AutonomousGoSpin;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3574.robot.subsystems.StepGrabber;
import org.usfirst.frc.team3574.robot.subsystems.ToteLifterUpper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import org.usfirst.frc.team3574.robot.commands.ExampleCommand;
//import org.usfirst.frc.team3574.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static StepGrabber stepgrabber;
	public static DriveTrain drivetrain;
	public static ToteLifterUpper totelifterupper;
	public static Collector collector;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	totelifterupper = new ToteLifterUpper();
		drivetrain = new DriveTrain();
		collector = new Collector();
		stepgrabber = new StepGrabber(); 
		oi = new OI();
		
		
        // instantiate the command used for the autonomous period
		autonomousCommand = new AutomousPickUpTotels();
		
        SmartDashboard.putData(drivetrain);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		drivetrain.Log();
		totelifterupper.Log();
		stepgrabber.Log();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        totelifterupper.setElevatorPosAtCurent();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        drivetrain.Log();
        totelifterupper.Log();
        stepgrabber.Log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
