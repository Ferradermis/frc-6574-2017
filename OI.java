package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * The Operator Interface class. Connects physical interface to command groups.
 * 
 * @author Ferradermis-6574
 *
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	//Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	JoystickController joystick = new JoystickController(0);
	Button joystick_button_2 = new JoystickButton(joystick, JoystickController.BUTTON_2);
	Button joystick_button_3 = new JoystickButton(joystick, JoystickController.BUTTON_3);
	Button joystick_button_4 = new JoystickButton(joystick, JoystickController.BUTTON_4);
	Button joystick_button_5 = new JoystickButton(joystick, JoystickController.BUTTON_5);
	Button joystick_button_6 = new JoystickButton(joystick, JoystickController.BUTTON_6);
	Button joystick_button_7 = new JoystickButton(joystick, JoystickController.BUTTON_7);
	Button joystick_button_8 = new JoystickButton(joystick, JoystickController.BUTTON_8);
	Button joystick_button_9 = new JoystickButton(joystick, JoystickController.BUTTON_9);
	Button joystick_button_10 = new JoystickButton(joystick, JoystickController.BUTTON_10);
	Button joystick_button_11 = new JoystickButton(joystick, JoystickController.BUTTON_11);
	Button joystick_button_12 = new JoystickButton(joystick, JoystickController.BUTTON_12);
	//Trigger joystick_trigger = FIGURE OUT LATER AND MAKE CLASS
	
	XboxController xbox = new XboxController(1);
	
	
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

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
