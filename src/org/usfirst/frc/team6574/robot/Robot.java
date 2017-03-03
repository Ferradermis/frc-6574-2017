
package org.usfirst.frc.team6574.robot;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static OI oi;
	
	public static boolean frontCamera = false;
	public static boolean gyroDrive = Constants.DEFAULT_GYRO;
	public static boolean dualJoystick = Constants.DEFAULT_DUAL;
	public static boolean turbo = false;
	public static boolean slow = false;
	
	public static int autoStage;
	public static double xPos = -1;
	public static double yPos = -1;
	/*public static PIDConstant k_fl;
	public static PIDConstant k_fr;
	public static PIDConstant k_bl;
	public static PIDConstant k_br;*/
	
	/*double flMax = 0;
	double frMax = 0;
	double blMax = 0;
	double brMax = 0;*/
	
	//Motor controllers
	Spark[] motors;
	Victor climbMotor;
	Victor climbLockMotor;
	/*Spark frontLeft;
	Spark frontRight;
	Spark backLeft;
	Spark backRight;*/
	
	//Encoders
	public static Encoder[] encoders;
	/*Encoder eFrontLeft;
	Encoder eFrontRight;
	Encoder eBackLeft;
	Encoder eBackRight;*/
	
	//PID controllers
	public static PIDController[] pidControl;
	/*PIDController pFrontLeft;
	PIDController pFrontRight;
	PIDController pBackLeft;
	PIDController pBackRight;*/
	
	//Controls
	//RobotDrive myRobot;
	//TankDriver tankDrive;
	MecanumDriver mecanumDrive;
	//MecanumTest mecanumDrive2;
	JoystickController joystick;
	JoystickController joystick2;
	//XboxController xbox;
	Timer timer;
	
	//Sensors
	Accelerometer accelerometer;
	Gyro gyro;
	
	//Camera
	/*UsbCamera camera;
	CvSink cvSink;
	CvSource outputStream;
	Mat source;
	Mat output;*/
	
	/*
	Pneumatics
	Compressor compressor;
	DoubleSolenoid solenoid;*/
	
	//Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//oi = new OI();
		
		//Initialization
		motors = new Spark[4];
		encoders = new Encoder[4];
		pidControl = new PIDController[4];

		/*k_fl = new PIDConstant();
		k_fr = new PIDConstant();
		k_bl = new PIDConstant();
		k_br = new PIDConstant();*/
		
		//Motors
		motors[Constants.FRONT_LEFT] = new Spark(Constants.FRONT_LEFT_PIN);
		motors[Constants.FRONT_RIGHT] = new Spark(Constants.FRONT_RIGHT_PIN);
		motors[Constants.BACK_LEFT] = new Spark(Constants.BACK_LEFT_PIN);
		motors[Constants.BACK_RIGHT] = new Spark(Constants.BACK_RIGHT_PIN);
		climbMotor = new Victor(Constants.CLIMB_PIN);
		climbLockMotor = new Victor(Constants.CLIMB_LOCK_PIN);
		
		//Encoders
		encoders[Constants.FRONT_LEFT] = new Encoder(2, 3, true);
		//eFrontLeft.setDistancePerPulse(Math.PI / 2);
		//eFrontLeft.reset();
		//eFrontLeft.setPIDSourceType(PIDSourceType.kRate);
		encoders[Constants.FRONT_RIGHT] = new Encoder(0, 1, false);
		//eFrontRight.setDistancePerPulse(Math.PI / 2);
		//eFrontRight.reset();
		//eFrontRight.setPIDSourceType(PIDSourceType.kRate);
		encoders[Constants.BACK_LEFT] = new Encoder(6, 7, true);
		//eBackLeft.setDistancePerPulse(Math.PI / 2);
		//eBackLeft.reset();
		//eBackLeft.setPIDSourceType(PIDSourceType.kRate);
		encoders[Constants.BACK_RIGHT] = new Encoder(4, 5, true);
		for (int i = 0; i < 4; i++) {
			encoders[i].reset();
		}
		//eBackRight.setDistancePerPulse(Math.PI / 2)
		//eBackRight.reset();
		//eBackRight.setPIDSourceType(PIDSourceType.kRate);
		
		//PID
		double p = 0.1;
		double i = 0.0;
		double d = 0.0;
		//pidControl[Constants.FRONT_LEFT] = new PIDController(p, i, d, new EncoderPIDSource(encoders[Constants.FRONT_LEFT]), motors[Constants.FRONT_LEFT]);
		pidControl[Constants.FRONT_LEFT] = new PIDController(p, i, d, encoders[Constants.FRONT_LEFT], motors[Constants.FRONT_LEFT]);
		pidControl[Constants.FRONT_LEFT].setInputRange(-1000.0, 1000.0);
		pidControl[Constants.FRONT_LEFT].setOutputRange(-1.0, 1.0);
		pidControl[Constants.FRONT_LEFT].setPercentTolerance(Constants.PID_TOLERANCE);
		//pidControl[Constants.FRONT_RIGHT] = new PIDController(p, i, d, new EncoderPIDSource(encoders[Constants.FRONT_RIGHT]), motors[Constants.FRONT_RIGHT]);
		pidControl[Constants.FRONT_RIGHT] = new PIDController(p, i, d, encoders[Constants.FRONT_RIGHT], motors[Constants.FRONT_RIGHT]);
		pidControl[Constants.FRONT_RIGHT].setInputRange(-1000.0, 1000.0);
		pidControl[Constants.FRONT_RIGHT].setOutputRange(-1.0, 1.0);
		pidControl[Constants.FRONT_RIGHT].setPercentTolerance(Constants.PID_TOLERANCE);
		//pidControl[Constants.BACK_LEFT] = new PIDController(p, i, d, new EncoderPIDSource(encoders[Constants.BACK_LEFT]), motors[Constants.BACK_LEFT]);
		pidControl[Constants.BACK_LEFT] = new PIDController(p, i, d, encoders[Constants.BACK_LEFT], motors[Constants.BACK_LEFT]);
		pidControl[Constants.BACK_LEFT].setInputRange(-1000.0, 1000.0);
		pidControl[Constants.BACK_LEFT].setOutputRange(-1.0, 1.0);
		pidControl[Constants.BACK_LEFT].setPercentTolerance(Constants.PID_TOLERANCE);
		//pidControl[Constants.BACK_RIGHT] = new PIDController(p, i, d, new EncoderPIDSource(encoders[Constants.BACK_RIGHT]), motors[Constants.BACK_RIGHT]);
		pidControl[Constants.BACK_RIGHT] = new PIDController(p, i, d, encoders[Constants.BACK_RIGHT], motors[Constants.BACK_RIGHT]);
		pidControl[Constants.BACK_RIGHT].setInputRange(-1000.0, 1000.0);
		pidControl[Constants.BACK_RIGHT].setOutputRange(-1.0, 1.0);
		pidControl[Constants.BACK_RIGHT].setPercentTolerance(Constants.PID_TOLERANCE);
		
		//Controllers
		mecanumDrive = new MecanumDriver(motors[Constants.FRONT_LEFT], motors[Constants.FRONT_RIGHT], motors[Constants.BACK_LEFT], motors[Constants.BACK_RIGHT]);
		joystick = new JoystickController(0);
		joystick2 = new JoystickController(1);
		timer = new Timer();
		
		//Sensors
		accelerometer = new BuiltInAccelerometer();
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		
		//Camera
		new Thread(() -> {
			UsbCamera autonCamera = CameraServer.getInstance().startAutomaticCapture(0);
			UsbCamera teleCamera = CameraServer.getInstance().startAutomaticCapture(1);
			autonCamera.setResolution(Constants.resolutions[Constants.resolution][0], Constants.resolutions[Constants.resolution][1]);
			teleCamera.setResolution(Constants.resolutions[Constants.resolution][0], Constants.resolutions[Constants.resolution][1]);
			CvSink autonCvSink = CameraServer.getInstance().getVideo(autonCamera);
			CvSink teleCvSink = CameraServer.getInstance().getVideo(teleCamera);
			CvSource outputStream = CameraServer.getInstance().putVideo("Camera Feed", Constants.resolutions[Constants.resolution][0], Constants.resolutions[Constants.resolution][1]);
			//CvSource autonOutputStream = CameraServer.getInstance().putVideo("Auton stream", Constants.resolutions[Constants.resolution][0], Constants.resolutions[Constants.resolution][1]);
			//CvSource teleOutputStream = CameraServer.getInstance().putVideo("Tele stream", Constants.resolutions[Constants.resolution][0], Constants.resolutions[Constants.resolution][1]);
			//CvSource outputStream2 = CameraServer.getInstance().putVideo("Stream 2", Constants.resolutions[Constants.resolution][0], Constants.resolutions[Constants.resolution][1]);
			Mat autonImage = new Mat();
			Mat teleImage = new Mat();
			while (!Thread.interrupted()) {
				if (!Robot.frontCamera) {
					autonCvSink.grabFrame(autonImage);
					System.out.println(autonImage.width());
					//Scalar min = new Scalar(0, 200, 0);
					//Scalar max = new Scalar(180, 255, 180);
					Scalar min = new Scalar(245, 245, 245);
					Scalar max = new Scalar(255, 255, 255);
					Mat filter = new Mat();
					//Imgproc.cvtColor(autonImage, filter, Imgproc.COLOR_RGB2GRAY);
					//Core.inRange(filter, min, max, filter);
					
					
					outputStream.putFrame(autonImage);
					
					//outputStream.putFrame(filter);
					//outputStream.putFrame(image);
					/*
					List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
					Mat hierarchy = new Mat();
					Imgproc.findContours(filter, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
					
					ArrayList<Point> centers = new ArrayList<Point>();
					if (contours.size() == 2) {
						int newX = (int)((contours.get(0).toArray()[0].x + contours.get(1).toArray()[1].x) / 2);
						int newY = (int)((contours.get(0).toArray()[0].y + contours.get(1).toArray()[1].y) / 2);
						Imgproc.rectangle(autonImage, new Point(newX - 5, newY - 5), new Point(newX + 5, newY + 5), new Scalar(255, 255, 255));
					} else {
						for (int i1 = 0; i1 < contours.size(); i1++) {
							Point[] points = contours.get(i1).toArray();
						
							double x = 0;
							double y = 0;
							for (int j = 0; j < points.length; j++) {
								x += points[j].x;
								y += points[j].y;
							}
							x /= points.length;
							y /= points.length;
							centers.add(new Point(x, y));
							Imgproc.rectangle(autonImage, new Point(x - 1, y - 1), new Point(x + 1, y + 1), new Scalar(255, 255, 255));
						}
						top:for (int j = 0; j < centers.size(); j++) {
							for (int k = 0; k < centers.size(); k++) {
								if (j == k) {
									continue;
								}
								if (Math.abs(centers.get(j).y - centers.get(k).y) < 2) {
									int newX = (int)((centers.get(j).x + centers.get(k).x) / 2);
									int newY = (int)((centers.get(j).y + centers.get(k).y) / 2);
									xPos = newX;
									yPos = newY;
									Imgproc.rectangle(autonImage, new Point(newX - 5, newY - 5), new Point(newX + 5, newY + 5), new Scalar(255, 255, 255));
									break top;
								}
							}
						}
					}
					//Imgproc.rectangle(teleImage, new Point(10, 400), new Point(175, 475), new Scalar(0, 0, 0));
					Imgproc.putText(autonImage, "BACK", new Point(20, 450), Core.FONT_HERSHEY_COMPLEX, 1, new Scalar(0, 255, 0), 2, Core.LINE_8, false);
					outputStream.putFrame(filter);*/
				} else {
					teleCvSink.grabFrame(teleImage);
					//Imgproc.rectangle(teleImage, new Point(10, 400), new Point(175, 475), new Scalar(0, 0, 0));
					Imgproc.putText(teleImage, "FRONT", new Point(20, 450), Core.FONT_HERSHEY_COMPLEX, 1, new Scalar(0, 255, 0), 2, Core.LINE_8, false);
					outputStream.putFrame(teleImage);
				}
			}
		}).start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.turbo = false;
		Robot.slow = false;
		for (int i = 0; i < 4; i++) {
			motors[i].stopMotor();
			encoders[i].reset();
			pidControl[i].disable();
		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();
		
		timer.reset();
		timer.start();
		
		resetEncoders();
		gyro.reset();

		
		autoStage = 0;
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		/*if (autonomousCommand != null)
			autonomousCommand.start();*/
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putDouble("eFL: ", encoders[Constants.FRONT_LEFT].getRaw());
		SmartDashboard.putDouble("eFL rate: ", encoders[Constants.FRONT_LEFT].getRate());
		//SmartDashboard.putDouble("eFL max: ", flMax);
		SmartDashboard.putDouble("eFR: ", encoders[Constants.FRONT_RIGHT].getRaw());
		SmartDashboard.putDouble("eFR rate: ", encoders[Constants.FRONT_RIGHT].getRate());
		//SmartDashboard.putDouble("eFR max: ", frMax);
		SmartDashboard.putDouble("eBL: ", encoders[Constants.BACK_LEFT].getRaw());
		SmartDashboard.putDouble("eBL rate: ", encoders[Constants.BACK_LEFT].getRate());
		//SmartDashboard.putDouble("eBL max: ", blMax);
		SmartDashboard.putDouble("eBR: ", encoders[Constants.BACK_RIGHT].getRaw());
		SmartDashboard.putDouble("eBR rate: ", encoders[Constants.BACK_RIGHT].getRate());
		
		
		if (Constants.AUTO_POSITION == 1 || Constants.AUTO_POSITION == 3) {
			if (autoStage == 0) {
				resetEncoders();
				autoStage = 1;
			} else if (autoStage == 1) {
				if (averageEncoderValue() < Constants.AUTO_FORWARD_LONG) {
					autoForward();
				} else {
					autoStop();
					autoStage = 2;
					gyro.reset();
				}
			} else if (autoStage == 2) {
				if (Constants.AUTO_POSITION == 1) {
					if (Math.abs(gyro.getAngle()) < Constants.AUTO_DEGREE_ROTATE) {
						autoRotate(false);
					} else {
						resetEncoders();
						autoStop();
						autoStage = 3;
					}
				} else if (Constants.AUTO_POSITION == 3) {
					if (Math.abs(gyro.getAngle()) < Constants.AUTO_DEGREE_ROTATE) {
						autoRotate(true);
					} else {
						resetEncoders();
						autoStop();
						autoStage = 3;
					}
				}
			} else if (autoStage == 3) {
				if (averageEncoderValue() < Constants.AUTO_FORWARD_SHORTER) {
					autoForward();
				} else {
					autoStop();
					autoStage = 4;
				}
			} /*else if (autoStage == 4) {
				resetEncoders();
				//vision placeholder
				autoStage = 5;
			} else if (autoStage == 5) {
				if (averageEncoderValue() < Constants.AUTO_FORWARD_SHORTER) {
					autoForward();
				}
			}*/
		} else if (Constants.AUTO_POSITION == 2) {
			if (autoStage == 0) {
				resetEncoders();
				autoStage = 1;
			} else if (autoStage == 1) {
				if (averageEncoderValue() < Constants.AUTO_FORWARD_SHORTER) {
					autoForward();
				} else {
					autoStage = 2;
				}
			} else if (autoStage == 2) {
				autoStop();
				
			}
		}
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		gyro.reset();
		
		/*pFrontLeft.enable();
		pFrontRight.enable();
		pBackLeft.enable();
		pBackRight.enable();*/
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//compressor.setClosedLoopControl(true);
		/*if (Constants.useCompressor) {
			compressor.start();
		}*/
		/*if (autonomousCommand != null)
			autonomousCommand.cancel();*/
	}

	/**
	 * This function is called periodically during operator control
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void teleopPeriodic() {
		
		/*pFrontLeft.setSetpoint(mecanumDrive.getValues()[0]);
		pFrontRight.setSetpoint(mecanumDrive.getValues()[1]);
		pBackLeft.setSetpoint(mecanumDrive.getValues()[2]);
		pBackRight.setSetpoint(mecanumDrive.getValues()[3]);*/
		
		if (!Constants.useXbox) {
			if (joystick.getRawButton(Constants.MOTOR_SHUTDOWN)) {
				mecanumDrive.stop();
				climbMotor.stopMotor();
			}
			if (joystick.getRawButton(Constants.CAMERA_BACK)) {
				Robot.frontCamera = false;
			}
			if (joystick.getRawButton(Constants.CAMERA_FRONT)) {
				Robot.frontCamera = true;
			}
			if (joystick.getRawButton(Constants.CLIMB_UP)) {
				climbMotor.set(joystick.getThrottle());
			} else if (joystick.getRawButton(Constants.CLIMB_DOWN)) {
				climbMotor.set(-joystick.getThrottle());
			} else {
				climbMotor.stopMotor();
			}
			if (joystick2.getRawButton(Constants.CLIMB_UP)) {
				climbLockMotor.set(Constants.MOTOR_LOCK_SPEED);
			} else if (joystick2.getRawButton(Constants.CLIMB_DOWN)) {
				climbLockMotor.set(-Constants.MOTOR_LOCK_SPEED);
			} else {
				climbLockMotor.stopMotor();
			}
			if (joystick.getRawButton(Constants.GYRO_RESET)) {
				gyro.reset();
			}
			if (joystick.getRawButton(Constants.DRIVE_STANDARD)) {
				Robot.gyroDrive = false;
			}
			if (joystick.getRawButton(Constants.DRIVE_GYRO)) {
				Robot.gyroDrive = true;
			}
			if (joystick.getRawButton(Constants.SPEED_UP)) {
				Robot.turbo = true;
			} else {
				Robot.turbo = false;
			}
			if (joystick.getRawButton(Constants.SPEED_DOWN)) {
				Robot.slow = true;
			} else {
				Robot.slow = false;
			}
			/*if (joystick2 != null && joystick2.getTrigger()) {
				Robot.dualJoystick = true;
			}
			if (Robot.dualJoystick && joystick2.getRawButton(2)) {
				Robot.dualJoystick = false;
			}*/
			/*if (joystick.getPOV() == 0) {
				ROPE CLIMBER
			}*/
		}
		
		//Controller information
		Scheduler.getInstance().run();
		SmartDashboard.putDouble("Joystick Y Axis (-)", -joystick.getRawAxis(1));
		SmartDashboard.putDouble("Joystick X Axis", joystick.getRawAxis(0));
		SmartDashboard.putDouble("Joystick Rotation", joystick.getTwist());
		
		//Sensor information
		SmartDashboard.putDouble("Accelerometer X: ", accelerometer.getX());
		SmartDashboard.putDouble("Accelerometer Y: ", accelerometer.getY());
		SmartDashboard.putDouble("Accelerometer Z: ", accelerometer.getZ());
		
		SmartDashboard.putDouble("Gyroscope angle: ", gyro.getAngle());
		SmartDashboard.putDouble("Gyroscope ROC: ", gyro.getRate());
		
		SmartDashboard.putDouble("eFL: ", encoders[Constants.FRONT_LEFT].getRaw());
		SmartDashboard.putDouble("eFL rate: ", encoders[Constants.FRONT_LEFT].getRate());
		//SmartDashboard.putDouble("eFL max: ", flMax);
		SmartDashboard.putDouble("eFR: ", encoders[Constants.FRONT_RIGHT].getRaw());
		SmartDashboard.putDouble("eFR rate: ", encoders[Constants.FRONT_RIGHT].getRate());
		//SmartDashboard.putDouble("eFR max: ", frMax);
		SmartDashboard.putDouble("eBL: ", encoders[Constants.BACK_LEFT].getRaw());
		SmartDashboard.putDouble("eBL rate: ", encoders[Constants.BACK_LEFT].getRate());
		//SmartDashboard.putDouble("eBL max: ", blMax);
		SmartDashboard.putDouble("eBR: ", encoders[Constants.BACK_RIGHT].getRaw());
		SmartDashboard.putDouble("eBR rate: ", encoders[Constants.BACK_RIGHT].getRate());
		//SmartDashboard.putDouble("eBR max: ", brMax);
		
		/*if (eFrontLeft.getRate() > flMax) {
		flMax = eFrontLeft.getRate();
		}
		if (eFrontRight.getRate() > frMax) {
			frMax = eFrontRight.getRate();
		}
		if (eBackLeft.getRate() > blMax) {
			blMax = eBackLeft.getRate();
		}
		if (eBackRight.getRate() > brMax) {
			brMax = eBackRight.getRate();
		}*/
		
		SmartDashboard.putData("pFL: ", pidControl[Constants.FRONT_LEFT]);
		SmartDashboard.putData("pFR: ", pidControl[Constants.FRONT_RIGHT]);
		SmartDashboard.putData("pBL: ", pidControl[Constants.BACK_LEFT]);
		SmartDashboard.putData("pBR: ", pidControl[Constants.BACK_RIGHT]);
		
		double twist = joystick.getTwist();
		double y = joystick.getYAxis();
		double x = joystick.getXAxis();
		double angle = joystick.getDirectionDegrees();
		double x2 = 0.0;
		if (Robot.dualJoystick) {
			x2 = joystick2.getXAxis();
		}
		
		if (Robot.dualJoystick) {
			if (joystick.getRawButton(Constants.MOTOR_SHUTDOWN)) {
				mecanumDrive.stop();
			} else {
				if (Math.abs(x2) > 0.3 && !(Math.abs(x) > Constants.joystickThreshold || Math.abs(y) > Constants.joystickThreshold)) { 
					mecanumDrive.twist(-x2);
				} else {
					if (Math.abs(x) > Constants.joystickThreshold || Math.abs(y) > Constants.joystickThreshold) {
						mecanumDrive.move(angle, 180 + gyro.getAngle(), x, y, x2);
					} else {
						mecanumDrive.stop();
					}
				}
			}
		} else {
			if (joystick.getRawButton(Constants.MOTOR_SHUTDOWN)) {
				mecanumDrive.stop();
			} else {
				if (Math.abs(twist) > Constants.joystickTwistThreshold && Math.abs(x) < Constants.joystickThreshold && Math.abs(y) < Constants.joystickThreshold) { 
					mecanumDrive.twist(-twist);
				} else {
					if (Math.abs(x) > Constants.joystickThreshold || Math.abs(y) > Constants.joystickThreshold) {
						mecanumDrive.move(angle, 180 + gyro.getAngle(), x, y, twist);
					} else {
						mecanumDrive.stop();
					}
				}
			}
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void resetEncoders() {
		for (int i = 0; i < 4; i++) {
			encoders[i].reset();
		}
	}
	
	public int averageEncoderValue() {
		return Math.abs((encoders[0].getRaw() + encoders[1].getRaw() + encoders[2].getRaw() + encoders[3].getRaw()) / 4);
	 }
	
	public void autoStop() {
		for (int i = 0; i < 4; i++) {
			motors[i].stopMotor();
		}
	}
	
	public void autoForward() {
		for (int i = 0; i < 4; i++) {
			motors[i].set(Constants.AUTO_FORWARD_SPEED);
		}
	}
	
	public void autoBackward() {
		for (int i = 0; i < 4; i++) {
			motors[i].set(Constants.AUTO_BACKWARD_SPEED);
		}
	}
	
	public void autoRotate(boolean clockwise) {
		if (clockwise) {
			motors[0].set(Constants.AUTO_ROTATE_SPEED);
			motors[1].set(-Constants.AUTO_ROTATE_SPEED);
			motors[2].set(Constants.AUTO_ROTATE_SPEED);
			motors[3].set(-Constants.AUTO_ROTATE_SPEED);
		} else {
			motors[0].set(-Constants.AUTO_ROTATE_SPEED);
			motors[1].set(Constants.AUTO_ROTATE_SPEED);
			motors[2].set(-Constants.AUTO_ROTATE_SPEED);
			motors[3].set(Constants.AUTO_ROTATE_SPEED);
		}
	}
	
}
