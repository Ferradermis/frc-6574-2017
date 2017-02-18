package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.Spark;

public class MecanumTest {

	Spark frontLeft;
	Spark frontRight;
	Spark backLeft;
	Spark backRight;
	
	public MecanumTest(Spark fl, Spark fr, Spark bl, Spark br) {
		frontLeft = fl;
		frontRight = fr;
		backLeft = bl;
		backRight = br;
	}

	public void stop() {
		frontLeft.stopMotor();
		frontRight.stopMotor();
		backLeft.stopMotor();
		backRight.stopMotor();
	}
	
	public void frontLeft(double speed) {
		frontLeft.set(-speed);
	}
	
	public void frontRight(double speed) {
		frontRight.set(-speed);
	}
	
	public void backLeft(double speed) {
		backLeft.set(-speed);
	}
	
	public void backRight(double speed) {
		backRight.set(-speed);
	}
	
}
