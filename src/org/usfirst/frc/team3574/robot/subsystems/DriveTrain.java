package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoy;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	private CANTalon frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private IMUAdvanced imu;
    SerialPort serial_port;
    boolean first_iteration;
    private BuiltInAccelerometer accel;
    private double offset = 90;
    
    double framecount;
	double disFromCen;
	double posY;
	double posX;
	NetworkTable net;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoy());
        SmartDashboard.putNumber("Camera", 0);
    }
    
    public DriveTrain() {
    	backLeftMotor = new CANTalon(2);
		backRightMotor = new CANTalon(4);
		frontLeftMotor = new CANTalon(1);
		frontRightMotor = new CANTalon(3);
		accel = new BuiltInAccelerometer();

	    SmartDashboard.putNumber("Drive Train P", 1.0);
	    SmartDashboard.putNumber("Drive Train I", 0.0);
	    SmartDashboard.putNumber("Drive Train D", 0.0);
	    SmartDashboard.putNumber("Drive Train F", 3.95);
	    
		System.out.println("instantiated");
		
		talonInit(backLeftMotor);
		talonInit(frontLeftMotor);
		talonInit(backRightMotor);
		talonInit(frontRightMotor);
		
		net = NetworkTable.getTable("Vision");
		net.putNumber("Distance From Center", 0);
    	net.putNumber("Position X", -1);
    	net.putNumber("Position Y", -1);
    	net.putNumber("Frame Count", 0);
		
		try {
			serial_port = new SerialPort(57600, SerialPort.Port.kMXP);

			// You can add a second parameter to modify the
			// update rate (in hz) from 4 to 100. The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			byte update_rate_hz = 100;
			// imu = new IMU(serial_port,update_rate_hz);
			imu = new IMUAdvanced(serial_port, update_rate_hz);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		if (imu != null) {
			LiveWindow.addSensor("IMU", "Gyro", imu);
		}
		first_iteration = true;
	}
    
    public void driveFieldOrientated(double x, double y, double z) {
		driveFieldOrientated(x, y, z, 1.0);
    }
    
    public void driveFieldOrientated(double x, double y, double z, double throttle) {
		mecanumDrive_Cartesian(x, y, z, this.getYaw(), throttle);
    }
    
    public void driveRobotOrientated(double x, double y, double z, double throttle) {
		mecanumDrive_Cartesian(x, y, z, 270.0, throttle);
    }
    
	private void talonInit(CANTalon theThing) {
		theThing.changeControlMode(CANTalon.ControlMode.Speed);
		theThing.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		theThing.enableBrakeMode(true);
		theThing.setPID(1.0, 0.0, 0.0, 3.95, 0, 10.0, 0);
        //theThing.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
	}
	
	private double getYaw() {
		if (imu != null) {
			return (imu.getYaw() + offset) % 360;  // imu without mods goes -180 to 180
		} else {
			return 270;
		}
	}
	
	public double getOrigIMUGetYaw() {
		return imu.getYaw();
	}
	
	public void resetCounters() {
		frontLeftMotor.setPosition(0);
		frontRightMotor.setPosition(0);
		backLeftMotor.setPosition(0);
		backRightMotor.setPosition(0);
	}
	public void resetYaw() {
		if (imu != null) {
			imu.zeroYaw();
		}
	}
	
	public void resetYaw(double rotatedAmount) {
		offset = rotatedAmount + 90;
		resetYaw();
	}
	
	protected static double[] rotateVector(double x, double y, double angle) {
		double cosA = Math.cos(angle * (3.14159 / 180.0));
		double sinA = Math.sin(angle * (3.14159 / 180.0));
		double out[] = new double[2];
		out[0] = x * cosA - y * sinA;
		out[1] = x * sinA + y * cosA;
		return out;
	}
	
	protected static void normalize(double wheelSpeeds[]) {
		double maxMagnitude = Math.abs(wheelSpeeds[0]);
		int i;
		for (i = 1; i < 4; i++) {
			double temp = Math.abs(wheelSpeeds[i]);
			if (maxMagnitude < temp)
				maxMagnitude = temp;
		}
		if (maxMagnitude > 1.0) {
			for (i = 0; i < 4; i++) {
				wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
			}
		}
	}
	
	public void mecanumDrive_Cartesian(double x, double y, double rotation,
			double gyroAngle, double throttle) {

		double xIn = x;
		double yIn = y;
		// Negate y for the joystick.
		yIn = -yIn;
		// Compensate for gyro angle.
		double rotated[] = rotateVector(xIn, yIn, gyroAngle);
		xIn = rotated[0];
		yIn = rotated[1] * throttle;

		double wheelSpeeds[] = new double[4];
		wheelSpeeds[0] = xIn + yIn + rotation;
		wheelSpeeds[1] = -xIn + yIn - rotation;
		wheelSpeeds[2] = -xIn + yIn + rotation;
		wheelSpeeds[3] = xIn + yIn - rotation;

		normalize(wheelSpeeds);
//		
//		if (SmartDashboard.getNumber("Drive Train P") != frontLeftMotor.getP()) {
//			frontLeftMotor.setP(SmartDashboard.getNumber("Drive Train P"));
//			frontRightMotor.setP(SmartDashboard.getNumber("Drive Train P"));
//			backLeftMotor.setP(SmartDashboard.getNumber("Drive Train P"));
//			backRightMotor.setP(SmartDashboard.getNumber("Drive Train P"));
//    	}
//    	if (SmartDashboard.getNumber("Drive Train I") != frontLeftMotor.getI()) {
//    		frontLeftMotor.setI(SmartDashboard.getNumber("Drive Train I"));
//			frontRightMotor.setP(SmartDashboard.getNumber("Drive Train I"));
//			backLeftMotor.setP(SmartDashboard.getNumber("Drive Train I"));
//			backRightMotor.setP(SmartDashboard.getNumber("Drive Train I"));
//    	}
//    	if (SmartDashboard.getNumber("Drive Train D") != frontLeftMotor.getD()) {
//    		frontLeftMotor.setD(SmartDashboard.getNumber("Drive Train D"));
//			frontRightMotor.setP(SmartDashboard.getNumber("Drive Train D"));
//			backLeftMotor.setP(SmartDashboard.getNumber("Drive Train D"));
//			backRightMotor.setP(SmartDashboard.getNumber("Drive Train D"));
//    	}
//    	if (SmartDashboard.getNumber("Drive Train F") != frontLeftMotor.getF()) {
//    		frontLeftMotor.setF(SmartDashboard.getNumber("Drive Train F"));
//			frontRightMotor.setP(SmartDashboard.getNumber("Drive Train F"));
//			backLeftMotor.setP(SmartDashboard.getNumber("Drive Train F"));
//			backRightMotor.setP(SmartDashboard.getNumber("Drive Train F"));
//    	}

		frontLeftMotor.set(wheelSpeeds[0] * -275.0);
		frontRightMotor.set(wheelSpeeds[1] * 275.0);
		backLeftMotor.set(wheelSpeeds[2] * -275.0);
		backRightMotor.set(wheelSpeeds[3] * 275.0);

	}
	
	
	
	public double accelerometerGetX() {
		return accel.getX();
	}
	
	public double accelerometerGetY() {
		return accel.getY();
	}
	
	public int frontLeftEncoderValue() {
		return frontLeftMotor.getEncPosition();
	}
	
	public int frontRightEncoderValue() {
		return frontRightMotor.getEncPosition();
	}
	
	public int backLeftEncoderValue() {
		return backLeftMotor.getEncPosition();
	}
	
	public int backRightEncoderValue() {
		return backRightMotor.getEncPosition();
	}
	
	public void resetEncoderValues() {
		frontLeftMotor.setPosition(0.0);
		frontRightMotor.setPosition(0.0);
		backLeftMotor.setPosition(0.0);
		backRightMotor.setPosition(0.0);
	}
	
	public void Log() {
		if(imu != null){
			SmartDashboard.putNumber("Orentation", imu.getYaw());
		}

		SmartDashboard.putNumber("Drive Encoder F L", frontLeftMotor.getEncPosition());
		SmartDashboard.putNumber("Drive Encoder F R", frontRightMotor.getEncPosition());
		SmartDashboard.putNumber("Drive Encoder B L", backLeftMotor.getEncPosition());
		SmartDashboard.putNumber("Drive Encoder B R", backRightMotor.getEncPosition());
		SmartDashboard.putNumber("Drive Speed F L", -frontLeftMotor.getSpeed());
		SmartDashboard.putNumber("Drive Speed F R", frontRightMotor.getSpeed());
		SmartDashboard.putNumber("Drive Speed B L", -backLeftMotor.getSpeed());
		SmartDashboard.putNumber("Drive Speed B R", backRightMotor.getSpeed());
//		SmartDashboard.putNumber("frontLeftMotorCurent", frontRightMotor.getOutputCurrent());
//		SmartDashboard.putNumber("frontRightMotorCurent", frontLeftMotor.getOutputCurrent());
//		SmartDashboard.putNumber("backLeftMotorCurent", backRightMotor.getOutputCurrent());
//		SmartDashboard.putNumber("backRightMotorCurent", backLeftMotor.getOutputCurrent());
		SmartDashboard.putNumber("IMU Pich", imu.getPitch());
		SmartDashboard.putNumber("IMU Roll", imu.getRoll());
		
		
		disFromCen = net.getNumber("Distance From Center");
    	posX = net.getNumber("Position X");
    	posY = net.getNumber("Position Y");
    	framecount = net.getNumber("Frame Count");
    	SmartDashboard.putBoolean("disFromCen < -5", disFromCen < -5);
    	SmartDashboard.putBoolean("disFromCen > 5", disFromCen > 5);
    	SmartDashboard.putNumber("disFromCen", disFromCen);
	}
	
}

