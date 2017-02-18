package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * An Xbox controller.
 * 
 * @author Ferradermis-6574
 *
 */
public class XboxController extends Joystick {

	public XboxController(int port) {
		super(port);
	}
	
	public double getLeftTrigger() {
		return getRawAxis(2);
	}
	
	public double getRightTrigger() {
		return getRawAxis(3);
	}
	
	public double getLeftY() {
		return -getRawAxis(1);
	}
	
	public double getRightY() {
		return getRawAxis(5);
	}
	
}