
package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.AutomousPickUpTotels;
import org.usfirst.frc.team3574.robot.commands.AutomousPushToteToScore;
import org.usfirst.frc.team3574.robot.commands.AutomousGoSpin;
import org.usfirst.frc.team3574.robot.commands.AutomousStrafeToteAndRecycleToScore;
import org.usfirst.frc.team3574.robot.commands.AutomousStrafeToteToScore;
import org.usfirst.frc.team3574.robot.commands.AutomousVision;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3574.robot.subsystems.StepGrabber;
import org.usfirst.frc.team3574.robot.subsystems.ToteAndRecycleLifterUpper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
//	public static StepGrabber stepgrabber;
	public static DriveTrain drivetrain;
	public static ToteAndRecycleLifterUpper toteandrecyclelifterupper;
	public static Collector collector;
	SendableChooser autoChooser;

	Timer time;
    double timerLast;
	   
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	time = new Timer();
    	
    	time.reset();
    	time.start();
    	timerLast = time.get();
        
    	toteandrecyclelifterupper = new ToteAndRecycleLifterUpper();
		drivetrain = new DriveTrain();
		collector = new Collector();
//		stepgrabber = new StepGrabber(); 
		oi = new OI();


		autoChooser = new SendableChooser();
		 autoChooser.addObject("Three Totes.", new AutomousPickUpTotels());
		// autoChooser.addObject("vision", new AutomousVision());
		autoChooser.addObject("ShoveTotes", new AutomousPushToteToScore());
		autoChooser.addDefault("Grab One Tote And Move To AutoZone", new AutomousStrafeToteToScore());
		autoChooser.addObject("Grab Recycle and Tote", new AutomousStrafeToteAndRecycleToScore());
		// autoChooser.addDefault("Grab Recycle and Tote; Over Bump", new AutomousStrafeToteAndRecycleToScoreOverBump());
		
        // instantiate the command used for the autonomous period
		autonomousCommand = new AutomousPickUpTotels();

		SmartDashboard.putData("Autonomous Mode", autoChooser);
		
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(drivetrain);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("disabledPeriodic");
        this.Log();
	}

    public void autonomousInit() {
//      schedule the autonomous command (example)
//    	  autonomousCommand = new AutomousPickUpTotels();
//        if (autonomousCommand != null) autonomousCommand.start();
        toteandrecyclelifterupper.setElevatorPosAtCurent();
    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();
   	}

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        this.Log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
        	autonomousCommand.cancel();
        }
        toteandrecyclelifterupper.setElevatorPosAtCurent();
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
        
        this.Log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    public void Log() {
        drivetrain.Log();
        toteandrecyclelifterupper.Log();
//      stepgrabber.Log();
//      System.out.print(" Robot Time " + time.get());
//		SmartDashboard.putNumber("Robot Time", time.get());
//		SmartDashboard.putNumber("Robot Loop Time", time.get() - timerLast);
//		timerLast = time.get();
    }
}
