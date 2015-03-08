package org.usfirst.frc.team3574.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteLifterUpper extends Subsystem {
	CANTalon elevatorMotor;
	double bottomLimitSwitchPosition = 1000;
	boolean isGoingDown;
	DigitalInput stopLiftSwitch = new DigitalInput(0);
	

	// Production Robot start carry load contact
	public static final double ALLOW_PICKUP_LEVEL_OFFSET = -7;
	public static final double CARRY_LEVEL_OFFSET = -172;
	public static final double STACK_LEVEL_OFFSET = -495;

	
	
	public ToteLifterUpper() {
//		 postion 633.00 
		SmartDashboard.putNumber("scale", 1.0);
	    SmartDashboard.putNumber("P", 20);
	    SmartDashboard.putNumber("I", 0.05);
	    SmartDashboard.putNumber("D", 0.1);
	    
		elevatorMotor = new CANTalon(5);
		
		elevatorMotor.changeControlMode(CANTalon.ControlMode.Position);
		
		elevatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		
		elevatorMotor.enableBrakeMode(true);
		
		elevatorMotor.setPID(20.0, 0.05, 0.1, 0.0, 50, 0.0, 0);
		elevatorMotor.setCloseLoopRampRate(0.00000005);
		
		elevatorMotor.ConfigFwdLimitSwitchNormallyOpen(false);
		
		elevatorMotor.ConfigRevLimitSwitchNormallyOpen(false);
		
		SmartDashboard.putData(this);
		
	}
	
	public boolean stopLiftSwitch() {
		return stopLiftSwitch.get();
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
    
    public double getElevatorOffest() {
    	return (this.bottomLimitSwitchPosition - elevatorMotor.getAnalogInRaw());
    }
    
    public void Log() {
    	double diffOfPid = (elevatorMotor.getSetpoint()-elevatorMotor.getAnalogInRaw());
    	if (diffOfPid > 12.5) {
    		diffOfPid = 12.5;
    	} else if (diffOfPid < -12.5) {
    		diffOfPid = -12.5;
    	}
    	SmartDashboard.putNumber("Diff of Elavator Set Point and Actual Point", diffOfPid);
		SmartDashboard.putNumber("Elavator Motor Current", elevatorMotor.getOutputCurrent());
    	SmartDashboard.putNumber("Elavator Petentiometer Value", elevatorMotor.getAnalogInRaw());
    	SmartDashboard.putNumber("Elavator Petentiometer Offset", getElevatorOffest());
    	SmartDashboard.putNumber("Elavator Set Point", elevatorMotor.getSetpoint());
    	SmartDashboard.putBoolean("Bottom Elavator Limit Switch", elevatorMotor.isFwdLimitSwitchClosed());
    	SmartDashboard.putBoolean("Top Elavator Limit Switch", elevatorMotor.isRevLimitSwitchClosed());
    	SmartDashboard.putNumber("out put Volt", elevatorMotor.getOutputVoltage());
    	SmartDashboard.putNumber("bus Volt", elevatorMotor.getBusVoltage());
    	SmartDashboard.putNumber("currint Motor", elevatorMotor.getOutputCurrent());
    	SmartDashboard.putBoolean("Ato Lift Limit Switch", stopLiftSwitch());
    	
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

