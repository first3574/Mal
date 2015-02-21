package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.commands.RunCollectorWheels;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem {
	CANTalon rightMotor;
	CANTalon leftMotor;
	public static final double DONT_DO_ANYTHING = 0.0;
	public static final double LEFT_MOTOR_IN = 0.5;
	public static final double RIGHT_MOTOR_IN = 0.5;
	public static final double LEFT_MOTOR_OUT = -0.5;
	public static final double RIGHT_MOTOR_OUT = -0.5;

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RunCollectorWheels(DONT_DO_ANYTHING, DONT_DO_ANYTHING));
    }
	
	public Collector() {
		rightMotor = new CANTalon(6);
		leftMotor = new CANTalon(7);
		
	}

	public void SetCollectorMotors(double leftMotorSpeed, double rightMotorSpeed) {
	
		leftMotor.set(leftMotorSpeed);
		rightMotor.set(rightMotorSpeed);
		
	}
}
