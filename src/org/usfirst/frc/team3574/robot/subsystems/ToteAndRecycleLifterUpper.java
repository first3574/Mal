package org.usfirst.frc.team3574.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteAndRecycleLifterUpper extends Subsystem {
	CANTalon elevatorMotor;
	Solenoid recycleSolenoid;
	Solenoid tabSolenoid;
	DigitalInput openSwitchForTabSystem = new DigitalInput(1);
	DigitalInput closeSwithForTabSystem = new DigitalInput(2);
	
	double bottomLimitSwitchPosition = 1000;
	public boolean isGoingDown;
	DigitalInput switchForCrateInRobot = new DigitalInput(0);
	

	// Production Robot start carry load contact
	public static final double ALLOW_PICKUP_LEVEL_OFFSET = -3;
	public static final double CARRY_LEVEL_OFFSET = -172;
	public static final double STACK_LEVEL_OFFSET = -495;

	
	
	public ToteAndRecycleLifterUpper() {
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
		
    	recycleSolenoid = new Solenoid(0);
    	tabSolenoid = new Solenoid(1);
		
	}
	
	public boolean isToteInRobot() {
		return !switchForCrateInRobot.get();// switch is false when crate is in robot
	}
	
	public void calibrateBottomToCurrentPos() {
		bottomLimitSwitchPosition = elevatorMotor.getAnalogInRaw();
	}
	
	public void setElevatorPosAtCurent() {
		elevatorMotor.set(elevatorMotor.get());
	}
	
	public void UpperOrDowner(double amountToMove) {
		SmartDashboard.putNumber("AmountToMove", amountToMove);
		elevatorMotor.set(elevatorMotor.getSetpoint() + amountToMove);
	}
	
	public void setSetpointOffset(double offset) {
		double currentPos = elevatorMotor.getAnalogInPosition();
		double newSetPoint = (bottomLimitSwitchPosition + offset);
		
		isGoingDown = currentPos  < newSetPoint;
		SmartDashboard.putBoolean("Is Going Down", isGoingDown);
		
		elevatorMotor.set(newSetPoint);
	}
	
	public void openRecycle() {
    	recycleSolenoid.set(true);
    }
    public void closeRecycle() {
    	recycleSolenoid.set(false);
    }
    public void tabSolenoidHoldTote() {
    	tabSolenoid.set(false);
    }
    public void tabSolenoidFreeTote() {
    	tabSolenoid.set(true);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public boolean isOpenSwitchForTabSystemTripped() {
    	return openSwitchForTabSystem.get();
    }
    
    public boolean isCloseSwitchForTabSystemTripped() {
		return closeSwithForTabSystem.get();
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
    	
//    	SmartDashboard.putNumber("Elavator Set Point vs. Actual Point", diffOfPid);
//    	SmartDashboard.putNumber("Elavator Petentiometer Offset", getElevatorOffest());
//    	SmartDashboard.putNumber("Elavator Set Point", elevatorMotor.getSetpoint());
//    	SmartDashboard.putBoolean("Elavator Bottom Limit Switch", elevatorMotor.isFwdLimitSwitchClosed());
//    	SmartDashboard.putBoolean("Elavator Top Limit Switch", elevatorMotor.isRevLimitSwitchClosed());
//    	SmartDashboard.putBoolean("Auto Lift Limit Switch", isToteInRobot());
//		SmartDashboard.putNumber("Elevator Motor Current", elevatorMotor.getOutputCurrent());
//    	SmartDashboard.putNumber("Elevator Motor Volt", elevatorMotor.getOutputVoltage());
//    	SmartDashboard.putBoolean("Solenoid State", recycleSolenoid.get());

//		System.out.println("Getpos " + elevatorMotor.get());

    	if (!elevatorMotor.isRevLimitSwitchClosed() || !elevatorMotor.isFwdLimitSwitchClosed()) {
    		System.out.println("clicked");
    	} 
    	
//    	if (SmartDashboard.getNumber("P") != elevatorMotor.getP()) {
//    		double pValue = SmartDashboard.getNumber("P");
//    		if (isGoingDown == true) {
//    			//pValue *= .50;
//    			//elevatorMotor.setCloseLoopRampRate(.01);
//    		}
//    		elevatorMotor.setP(pValue);
//    		
//    	}
//    	if (SmartDashboard.getNumber("I") != elevatorMotor.getI()) {
//    		elevatorMotor.setI(SmartDashboard.getNumber("I"));
//    	}
//    	if (SmartDashboard.getNumber("D") != elevatorMotor.getD()) {
//    		elevatorMotor.setD(SmartDashboard.getNumber("D"));
//    	}
    }
}

