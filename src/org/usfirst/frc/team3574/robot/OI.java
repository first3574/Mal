package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.CollectWithJoy;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveByCameraRotate;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForHalfSecond;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.totelifter.AutoLifterUpper;
import org.usfirst.frc.team3574.robot.commands.totelifter.Calibrate;
import org.usfirst.frc.team3574.robot.commands.totelifter.CalibrateAndGoToStart;
import org.usfirst.frc.team3574.robot.commands.totelifter.ManualLifterDowner;
import org.usfirst.frc.team3574.robot.commands.totelifter.ManualLifterUper;
import org.usfirst.frc.team3574.robot.commands.totelifter.MoveElevatorTo;
import org.usfirst.frc.team3574.robot.subsystems.Collector;
import org.usfirst.frc.team3574.robot.subsystems.ToteLifterUpper;
import org.usfirst.frc.team3574.util.MakeJoyTrig;
import org.usfirst.frc.team3574.util.TrigerButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
//import org.usfirst.frc.team3574.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.NetworkButton;
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
    	
//    	InternalButton iB = new InternalButton();
//    	iB.whenPressed(new Calibrate());
    	
    	// For PS3 control mode
    	
//    	JoystickButton buttonTrigger = new JoystickButton(stick, 1);  						
//    	buttonTrigger.whenPressed(new MoveElevatorTo(ToteLifterUpper.STACK_LEVEL_OFFSET));
    	
    	JoystickButton button3 = new JoystickButton(stick, 3);  						
    	button3.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
    	
    	JoystickButton button4 = new JoystickButton(stick, 4);  						
    	button4.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT));
    	
    	JoystickButton buttonX = new JoystickButton(stick, 7);   						//PS3 = 2
    	buttonX.whenPressed(new MoveElevatorTo(ToteLifterUpper.STACK_LEVEL_OFFSET));
    	
    	JoystickButton buttonST = new JoystickButton(stick, 8);  						 //PS3 = 10
    	buttonST.whenPressed(new ResetYaw());
    	
    	JoystickButton button9 = new JoystickButton(stick, 9);  						
    	button9.whenPressed(new MoveElevatorTo(ToteLifterUpper.CARRY_LEVEL_OFFSET));
    	
    	JoystickButton buttonCircle = new JoystickButton(stick, 11);						//PS3 = 3
    	buttonCircle.whenPressed(new MoveElevatorTo(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET));
    	
       	JoystickButton button6 = new JoystickButton(stick, 6);				
    	button6.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_OUT));
    	
    	JoystickButton button5 = new JoystickButton(stick, 5);		
    	button5.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_IN));
    	
    	
    	JoystickButton buttonR2 = new JoystickButton(stick, 10);							//PS3 = 8
    	buttonR2.whileHeld(new ManualLifterUper());
    	JoystickButton button1L2 = new JoystickButton(stick, 12);						//PS3 = 7
    	button1L2.whileHeld(new ManualLifterDowner());
//
//    	

//    	
//    	JoystickButton buttonX = new JoystickButton(stick, 6);   						//PS3 = 2
//    	buttonX.whenPressed(new MoveElevatorTo(ToteLifterUpper.CARRY_LEVEL_OFFSET));
//    	
//    	JoystickButton buttonCircle = new JoystickButton(stick, 7);						//PS3 = 3
//    	buttonCircle.whenPressed(new MoveElevatorTo(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET));
//    	
//    	JoystickButton buttonSquare = new JoystickButton(stick, 5); 					//PS3 = 1
//    	buttonSquare.whenPressed(new MoveElevatorTo(ToteLifterUpper.STACK_LEVEL_OFFSET));
//    	
//    	JoystickButton buttonL1 = new JoystickButton(stick, 2);							//PS3 = 5
//    	buttonL1.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
//    	
//    	JoystickButton buttonL3 = new JoystickButton(stick, 4);						//PS3 = 11
//    	buttonL3.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT));
//    	
//    	
//    	JoystickButton buttonR2 = new JoystickButton(stick, 9);							//PS3 = 8
//    	buttonR2.whileHeld(new ManualLifterUper());
//    	JoystickButton button1L2 = new JoystickButton(stick, 10);						//PS3 = 7
//    	button1L2.whileHeld(new ManualLifterDowner());
//    	
////    	JoystickButton povFor = new JoystickButton(stick, stick.)
//    	
    	JoystickButton buttonA2 = new JoystickButton(stick2, 1);			
    	buttonA2.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
    	
    	JoystickButton buttonB2 = new JoystickButton(stick2, 2);				
    	buttonB2.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_OUT));
    	
    	JoystickButton buttonX2 = new JoystickButton(stick2, 3);		
    	buttonX2.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_IN));
    	
    	JoystickButton buttonY2 = new JoystickButton(stick2, 4);					
    	buttonY2.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT));
    	
    	JoystickButton leftBumper = new JoystickButton(stick2, 5);  						
    	leftBumper.whenPressed(new MoveElevatorTo(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET));
    	JoystickButton back = new JoystickButton(stick2, 7);  						
    	back.whenPressed(new MoveElevatorTo(ToteLifterUpper.CARRY_LEVEL_OFFSET));
    	JoystickButton rightBummper = new JoystickButton(stick2, 6);  						
    	rightBummper.whenPressed(new MoveElevatorTo(ToteLifterUpper.STACK_LEVEL_OFFSET));
  	
    	JoystickButton buttonStart2 = new JoystickButton(stick2, 8);		
//    	buttonStart2.whenPressed(new Calibrate());
    	buttonStart2.whenPressed(new CalibrateAndGoToStart());
    	
    	TrigerButton rightTrig = new TrigerButton(stick2, 3);
    	rightTrig.whileActive(new AutoLifterUpper(ToteLifterUpper.ALLOW_PICKUP_LEVEL_OFFSET, ToteLifterUpper.STACK_LEVEL_OFFSET));
     	
    	
//    	JoystickButton buttonLB2 = new JoystickButton(stick2, 5);						//PS3 = 5
//    	buttonLB2.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_OUT, Collector.RIGHT_MOTOR_OUT));
//    	
//    	JoystickButton buttonRB2 = new JoystickButton(stick2, 6);						//PS3 = 6
//    	buttonRB2.whileActive(new CollectWithJoy(Collector.LEFT_MOTOR_IN, Collector.RIGHT_MOTOR_IN));
    	
//    	JoystickButton buttonStick2 = new JoystickButton(stick2, 10);					//PS3 = 10
//    	buttonStick2.whileActive(new DriveByCamera());
    	
//    	JoystickButton buttonTriangle = new JoystickButton(stick, 4);  					//PS3 = 10
//    	buttonTriangle.whileActive(new DriveByCamera());
    	
//    	useing the D Pad


    	SmartDashboard.putData("Drive By Camera", new DriveByCameraRotate());
    	
    }

    public int dPadControl() {
		if (stick.getPOV() == 0) {
			return 0;
		} else if (stick.getPOV() >= 45 && stick.getPOV() <= 135) {
			return 1;
		} else if (stick.getPOV() == 180) {
			return 2;
		} else if (stick.getPOV() >= 225 && stick.getPOV() <= 315) {
			return 3;
		}
		return -1;
	}
    
    
    public double joystickX() {
		SmartDashboard.putNumber("X_Axis", stick.getRawAxis(0));
		SmartDashboard.putNumber("POV values", stick.getPOV());
		
    	return stick.getRawAxis(0); //+ stick2.getRawAxis(0);
    }
    
    public double joystickY() {
		SmartDashboard.putNumber("Y Axis", stick.getRawAxis(1));
		
    	return stick.getRawAxis(1); //+ stick2.getRawAxis(1);

    }
    
    public double joystickZ() {
    	return -stick.getRawAxis(2);// + -stick.getRawAxis(4) + (stick2.getRawAxis(3) - stick2.getRawAxis(2)) + stick2.getRawAxis(4);		//PS3 was 2 
    }																											
    
    public double joystickThrottle() {									//PS3 was -3
    	return stick.getRawAxis(3);									    
    }
    
    public boolean isTriggerPulled() {
    	return stick.getRawButton(1);								   //PS3 was 6
    }
}

