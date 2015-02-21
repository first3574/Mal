package org.usfirst.frc.team3574.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteLifterUpper extends Subsystem {
	CANTalon elevatorMotor;
	
	// Production Robot start carry load contact

	public static final double CONTACT_LEVEL = 949;
	public static final double START_LEVEL = CONTACT_LEVEL + 38;
	public static final double CARRY_LEVEL = CONTACT_LEVEL - 86;
	public static final double LOAD_LEVEL = CONTACT_LEVEL - 336;
	
	public ToteLifterUpper() {
//		 postion 633.00 
		SmartDashboard.putNumber("scale", 1.0);
	    SmartDashboard.putNumber("P", 3.4);
	    SmartDashboard.putNumber("I", 0.0);
	    SmartDashboard.putNumber("D", 0.0);
	    SmartDashboard.putNumber("F", 0.0);
	    
		elevatorMotor = new CANTalon(5);
		
		elevatorMotor.changeControlMode(CANTalon.ControlMode.Position);
		
		elevatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		
		elevatorMotor.enableBrakeMode(true);
		
		elevatorMotor.setPID(3.4, 0.0, 0.0);
		
		elevatorMotor.ConfigFwdLimitSwitchNormallyOpen(false);
		
		elevatorMotor.ConfigRevLimitSwitchNormallyOpen(false);
		
		
	}
	
	public void setElevatorPosAtCurent() {
		elevatorMotor.set(elevatorMotor.getAnalogInRaw());
	}
	
	public void UpperOrDowner(double amountToMove) {
		elevatorMotor.set(elevatorMotor.getSetpoint() + amountToMove);
	}
	
	public void setSetpoint(double setpoint) {
		elevatorMotor.set(setpoint);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Log() {
    	SmartDashboard.putNumber("Elavator Petentiometer Value", elevatorMotor.getAnalogInRaw());
    	
    	if (SmartDashboard.getNumber("P") != elevatorMotor.getP()) {
    		elevatorMotor.setP(SmartDashboard.getNumber("P"));
    	}
    	if (SmartDashboard.getNumber("I") != elevatorMotor.getI()) {
    		elevatorMotor.setI(SmartDashboard.getNumber("I"));
    	}
    	if (SmartDashboard.getNumber("D") != elevatorMotor.getD()) {
    		elevatorMotor.setD(SmartDashboard.getNumber("D"));
    	}
    	if (SmartDashboard.getNumber("F") != elevatorMotor.getF()) {
    		elevatorMotor.setF(SmartDashboard.getNumber("F"));
    	}
    }
}

