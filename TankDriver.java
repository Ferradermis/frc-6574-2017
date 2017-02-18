package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.Spark;

public class TankDriver {

	Spark frontLeft;
	Spark frontRight;
	Spark backLeft;
	Spark backRight;
	
	public TankDriver(Spark fl, Spark fr, Spark bl, Spark br) {
		frontLeft = fl;
		frontRight = fr;
		backLeft = bl;
		backRight = br;
	}
	
	public void turnLeft(double twist) {
		frontLeft.set(twist + Constants.joystickTwistThreshold);
		backLeft.set(twist + Constants.joystickTwistThreshold);
		frontRight.set(twist + Constants.joystickTwistThreshold);
		backRight.set(twist + Constants.joystickTwistThreshold);
	}
	
	public void turnRight(double twist) {
		frontLeft.set(twist - Constants.joystickTwistThreshold);
		backLeft.set(twist - Constants.joystickTwistThreshold);
		frontRight.set(twist - Constants.joystickTwistThreshold);
		backRight.set(twist - Constants.joystickTwistThreshold);
	}
	
	public void forward(double y) {
		if (y >= 0) {
			frontLeft.set(y - Constants.joystickThreshold);
			backLeft.set(y - Constants.joystickThreshold);
			frontRight.set(-y + Constants.joystickThreshold);
			backRight.set(-y + Constants.joystickThreshold);
		}
	}
	
	public void backward(double y) {
		if (y >= 0) {
			frontLeft.set(-y + Constants.joystickThreshold);
			backLeft.set(-y + Constants.joystickThreshold);
			frontRight.set(y - Constants.joystickThreshold);
			backRight.set(y - Constants.joystickThreshold);
		}
	}
	
	public void stop() {
		frontLeft.stopMotor();
		frontRight.stopMotor();
		backLeft.stopMotor();
		backRight.stopMotor();
	}
	
}
