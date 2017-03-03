package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A Logitech Extreme 3D Pro Joystick controller.
 * 
 * @author Ferradermis-6574
 *
 */
public class JoystickController extends Joystick {

	/**
	 * Constructs a JoystickController on the indicated port.
	 * 
	 * @param port
	 */
	public JoystickController(int port) {
		super(port);
	}
	
	/**
	 * Gets amount of twist from controller.
	 * 
	 * @return Twist value of joystick.
	 */
	@Override
	public double getTwist() {
		return super.getRawAxis(2);
	}
	
	/**
	 * Gets value from throttle switch.
	 * 
	 * @return Throttle value.
	 */
	@Override
	public double getThrottle() {
		return super.getThrottle();
	}
	
	public double getXAxis() {
		return super.getRawAxis(0);
	}
	
	public double getYAxis() {
		return -super.getRawAxis(1);
	}
	/**
	 * Returns the rotation of the small secondary joystick.
	 * 
	 * @return Degree value of rotation.
	 */
	@Override
	public int getPOV() {
		return super.getPOV();
	}
	
	//Button constants
	public final static int BUTTON_2 = 2;
	public final static int BUTTON_3 = 3;
	public final static int BUTTON_4 = 4;
	public final static int BUTTON_5 = 5;
	public final static int BUTTON_6 = 6;
	public final static int BUTTON_7 = 7;
	public final static int BUTTON_8 = 8;
	public final static int BUTTON_9 = 9;
	public final static int BUTTON_10 = 10;
	public final static int BUTTON_11 = 11;
	public final static int BUTTON_12 = 12;
	
}
