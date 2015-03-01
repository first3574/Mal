package org.usfirst.frc.team3574.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteLifterUpper extends Subsystem {
	CANTalon elevatorMotor;
	double bottomLimitSwitchPosition = 1000;
	boolean isGoingDown;
	

	// Production Robot start carry load contact
	public static final double PICKUP_LEVEL_OFFSET = -12;
	public static final double CARRY_LEVEL_OFFSET = -172;
	public static final double STACK_LEVEL_OFFSET = -495;

	
	
	public ToteLifterUpper() {
//		 postion 633.00 
		SmartDashboard.putNumber("scale", 1.0);
	    SmartDashboard.putNumber("P", 25);
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
		
		SmartDashboard.putData(this);
		
	}
	
	public void calibrateBottomToCurrentPos() {
		bottomLimitSwitchPosition = elevatorMotor.getAnalogInRaw();
	}
	
	public void setElevatorPosAtCurent() {
		elevatorMotor.set(elevatorMotor.getAnalogInRaw());
	}
	
	public void UpperOrDowner(double amountToMove) {
		SmartDashboard.putNumber("AmountToMove", amountToMove);
		elevatorMotor.set(elevatorMotor.getSetpoint() + amountToMove);
	}
	
	public void setSetpointOffset(double offset) {
		int pastPos = elevatorMotor.getAnalogInRaw();
		int newSetPoint = (int) (bottomLimitSwitchPosition + offset);
		
		isGoingDown = false;
				
		if (pastPos  < newSetPoint) {
			isGoingDown = true;
		}
		
		SmartDashboard.putBoolean("Is Going Down", isGoingDown);
		
		elevatorMotor.set(newSetPoint);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isBottomLimitTriped() {
    	return ! elevatorMotor.isFwdLimitSwitchClosed();
    }
    
    public void Log() {
    	SmartDashboard.putNumber("Elavator Petentiometer Value", elevatorMotor.getAnalogInRaw());
    	SmartDashboard.putNumber("Elavator Petentiometer Offset", this.bottomLimitSwitchPosition - elevatorMotor.getAnalogInRaw());
    	SmartDashboard.putNumber("Elavator Set Point", elevatorMotor.getSetpoint());
    	SmartDashboard.putBoolean("Bottom Elavator Limit Switch", elevatorMotor.isFwdLimitSwitchClosed());
    	SmartDashboard.putBoolean("Top Elavator Limit Switch", elevatorMotor.isRevLimitSwitchClosed());
    	
    	
    	if (SmartDashboard.getNumber("P") != elevatorMotor.getP()) {
    		double pValue = SmartDashboard.getNumber("P");
    		if (isGoingDown == true) {
    			//pValue *= .50;
    			//elevatorMotor.setCloseLoopRampRate(.01);
    		}
    		elevatorMotor.setP(pValue);
    		
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

