package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.DriveByCamera;
import org.usfirst.frc.team3574.robot.commands.DriveForHalfSecond;
import org.usfirst.frc.team3574.robot.commands.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.ManualLifterDowner;
import org.usfirst.frc.team3574.robot.commands.ManualLifterUper;
import org.usfirst.frc.team3574.robot.commands.MoveElevatorTo;
import org.usfirst.frc.team3574.robot.commands.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.RunCollectorWheels;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.ToteLifterUpper;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
//import org.usfirst.frc.team3574.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    Joystick stick = new Joystick(0);
    Joystick stick2 = new Joystick(1);
    // Button button = new JoystickButton(stick, buttonNumber);
     
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public OI() {
//    	JoystickButton buttonA = new JoystickButton(stick, 1);
//    	buttonA.whenPressed(new DriveForHalfSecond());
    	
    	JoystickButton buttonST = new JoystickButton(stick, 10);
    	buttonST.whenPressed(new ResetYaw());
    	
    	JoystickButton buttonX = new JoystickButton(stick, 2);
    	buttonX.whenPressed(new MoveElevatorTo(ToteLifterUpper.CARRY_LEVEL));
    	
    	JoystickButton buttonCircle = new JoystickButton(stick, 3);
    	buttonCircle.whenPressed(new MoveElevatorTo(ToteLifterUpper.START_LEVEL));
    	
    	JoystickButton buttonSquare = new JoystickButton(stick, 1);
    	buttonSquare.whenPressed(new MoveElevatorTo(ToteLifterUpper.LOAD_LEVEL));
    	
    	JoystickButton buttonA2 = new JoystickButton(stick2, 1);
    	buttonA2.whileActive(new RunCollectorWheels(Collector.DONT_DO_ANYTHING, Collector.RIGHT_MOTOR_IN));
    	
    	JoystickButton buttonR2 = new JoystickButton(stick, 8);
    	buttonR2.whenPressed(new ManualLifterUper());
    	JoystickButton buttonL2 = new JoystickButton(stick, 7);
    	buttonL2.whenPressed(new ManualLifterDowner());
    	
    	
    	JoystickButton buttonB2 = new JoystickButton(stick2, 2);
    	buttonB2.whileActive(new RunCollectorWheels(Collector.LEFT_MOTOR_IN, Collector.DONT_DO_ANYTHING));
    	
    	JoystickButton buttonX2 = new JoystickButton(stick2, 3);
    	buttonX2.whileActive(new RunCollectorWheels(Collector.DONT_DO_ANYTHING, Collector.RIGHT_MOTOR_OUT));
    	
    	JoystickButton buttonY2 = new JoystickButton(stick2, 4);
    	buttonY2.whileActive(new RunCollectorWheels(Collector.LEFT_MOTOR_OUT, Collector.DONT_DO_ANYTHING));
    	
    	JoystickButton buttonLB = new JoystickButton(stick2, 5);
    	buttonLB.whileActive(new RunCollectorWheels(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT));
    	
    	JoystickButton buttonRB = new JoystickButton(stick2, 6);
    	buttonRB.whileActive(new RunCollectorWheels(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
    	
    	JoystickButton buttonStick2 = new JoystickButton(stick2, 10);
    	buttonStick2.whileActive(new DriveByCamera());
    	
    	SmartDashboard.putData("Drive By Camera", new DriveByCamera());
    	
    }

    
    public double joystickX() {
		SmartDashboard.putNumber("X_Axis", stick.getRawAxis(0));
		
    	return stick.getRawAxis(0) + stick2.getRawAxis(0);
    }
    
    public double joystickY() {
		SmartDashboard.putNumber("Y Axis", stick.getRawAxis(1));
		
    	return stick.getRawAxis(1) + stick2.getRawAxis(1);

    }
    
    public double joystickZ() {
    	return -stick.getRawAxis(2) + (stick2.getRawAxis(3) - stick2.getRawAxis(2)) + stick2.getRawAxis(4);
    }
    
    public double joystickThrottle() {
    	return stick.getRawAxis(3);
    }
    
}

