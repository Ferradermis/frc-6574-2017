package org.usfirst.frc.team6574.robot;

public class Constants {

	//public static final String VISION_HOSTNAME = "";
	//public static final int VISION_PORT_NUMBER = 0;
	
	//Motor inputs
	public static final int FRONT_LEFT_PIN = 1;
	public static final int FRONT_RIGHT_PIN = 2;
	public static final int BACK_LEFT_PIN = 3;
	public static final int BACK_RIGHT_PIN = 0;
	public static final int CLIMB_PIN = 4;
	public static final int CLIMB_LOCK_PIN = 5;
	
	//Indices
	public static final int FRONT_LEFT = 0;
	public static final int FRONT_RIGHT = 1;
	public static final int BACK_LEFT = 2;
	public static final int BACK_RIGHT = 3;
	
	public static final double CLIMB_INTENSITY = 0.6;
	public static final double PID_TOLERANCE = 15.0;
	public static final double ENCODER_PULSE_DISTANCE = Math.PI / 2;
	public static final double ENCODER_DISTANCE_INCH = 23.8;
	
	public static final double MOTOR_LOCK_SPEED = 0.5;

	//Autonomous
	public static final int AUTO_POSITION = 1; //Starting position from left to right, denoted 1, 2, or 3
	public static final int AUTO_FORWARD_LONG = 1750;
	public static final int AUTO_FORWARD_SHORT = 500;
	public static final int AUTO_FORWARD_SHORTER = 688;
	public static final double AUTO_FORWARD_SPEED = 0.4;
	public static final double AUTO_BACKWARD_SPEED = 0.3;
	public static final double AUTO_ROTATE_SPEED = 0.4;
	public static final int AUTO_DEGREE_ROTATE = 40;
	//public static final double AUTO_ACCELEROMETER_THRESH = 0.1;
	
	//Joystick buttons
	public static final int CAMERA_FRONT = 6;
	public static final int CAMERA_BACK = 4;
	public static final int GYRO_RESET = 12;
	public static final int MOTOR_SHUTDOWN = 11;
	public static final int CLIMB_UP = 5;
	public static final int CLIMB_DOWN = 3;
	public static final int SPEED_UP = 1;
	public static final int SPEED_DOWN = 2;
	public static final int DRIVE_STANDARD = 9;
	public static final int DRIVE_GYRO = 10;
	
	public static final double SLOW_MULTIPLIER = 0.5;
	public static final double SPEED_CAP = 0.7;
	
	//Thresholds
	public static final double XboxJoystickThreshold = 0.25;
	public static final double joystickThreshold = 0.2;
	public static final double joystickTwistThreshold = 0.5;
	
	//Booleans
	public static final boolean useXbox = false;
	public static final boolean useMechanum = true;
	public static final boolean useCompressor = false;
	
	public static final boolean DEFAULT_DUAL = true;
	public static final boolean DEFAULT_GYRO = true;
	
	//Camera settings
	public static final int[][] resolutions = new int[][]{/*0*/new int[]{640, 480}, /*1*/new int[]{176, 144}, /*2*/new int[]{}};
	public static final int resolution = 0;
	
}
