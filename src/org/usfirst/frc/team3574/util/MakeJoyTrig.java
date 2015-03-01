package org.usfirst.frc.team3574.util;

import edu.wpi.first.wpilibj.Joystick;

public class MakeJoyTrig {

	Joystick joystick;
	int axis;


	/**
	 * Create a joystick button for triggering commands
	 *
	 * @param joystick The GenericHID object that has the button (e.g. Joystick,
	 * KinectStick, etc)
	 * }
	 */
	public MakeJoyTrig(Joystick joystick, int axis) {
	}

	/**
	 * Gets the value of the joystick button
	 * @return The value of the joystick button
	 */
	public boolean get() {

		if (joystick.getRawAxis(2) <= 0.75) {
			return true;
		} else if (joystick.getRawAxis(3) <= 0.75){
			return true;
		} else {
			return false;
		}
	}
}

