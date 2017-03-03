package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.Spark;

public class MecanumDriver {
	
	Spark[] motors;
	
	double[] values;
	
	double[] errors;
	
	public MecanumDriver(Spark fl, Spark fr, Spark bl, Spark br) {
		motors = new Spark[4];
		motors[Constants.FRONT_LEFT] = fl;
		motors[Constants.FRONT_RIGHT] = fr;
		motors[Constants.BACK_LEFT] = bl;
		motors[Constants.BACK_RIGHT] = br;
		
		values = new double[4];
		errors = new double[4];
		for (int i = 0; i < 4; i++) {
			values[i] = 0.0;
			errors[i] = 0.0;
		}
	}

	public void stop() {
		for (int i = 0; i < 4; i++) {
			motors[i].stopMotor();
		}
	}
	
	private void drive(int motor, double value, double eValue) {
		//errors[motor] = eValue - values[motor];
		
		/* STUFFS */
		motors[motor].set(value);
		//Robot.pidControl[motor].setSetpoint(value);
		//values[motor] = value;
	}

	public void move(double angleJ, double angleG, double x, double y, double twist) {
		double magma = Math.sqrt(x * x + y * y);
		double angle = Robot.gyroDrive ? (angleJ - angleG) * Math.PI / 180 : angleJ * Math.PI / 180;
		if (Robot.slow)
			magma *= Constants.SLOW_MULTIPLIER;
		else if (magma > Constants.SPEED_CAP && !Robot.turbo)
			magma = Constants.SPEED_CAP;
		if (Robot.turbo) {
			drive(Constants.FRONT_LEFT, Math.sin(angle + Math.PI / 4) * magma, 0.0);
			drive(Constants.FRONT_RIGHT, Math.cos(angle + Math.PI / 4) * magma, 0.0);
			drive(Constants.BACK_LEFT, Math.cos(angle + Math.PI / 4) * magma, 0.0);
			drive(Constants.BACK_RIGHT, Math.sin(angle + Math.PI / 4) * magma, 0.0);
		} else {
			drive(Constants.FRONT_LEFT, Math.sin(angle + Math.PI / 4) * magma - (twist * 3 / 10), 0.0);
			drive(Constants.FRONT_RIGHT, Math.cos(angle + Math.PI / 4) * magma + (twist * 3 / 10), 0.0);
			drive(Constants.BACK_LEFT, Math.cos(angle + Math.PI / 4) * magma - (twist * 3 / 10), 0.0);
			drive(Constants.BACK_RIGHT, Math.sin(angle + Math.PI / 4) * magma + (twist * 3 / 10), 0.0);
		}
		
	}
	
	public void twist(double twist) {
		if (Robot.turbo) {
			drive(Constants.FRONT_LEFT, twist, 0.0);
			drive(Constants.FRONT_RIGHT, -twist, 0.0);
			drive(Constants.BACK_LEFT, twist, 0.0);
			drive(Constants.BACK_RIGHT, -twist, 0.0);
		} else {
			double slowMultiplier = 1;
			if (Robot.slow)
				slowMultiplier = 0.5;
			if (twist >= 0) {
				drive(Constants.FRONT_LEFT, slowMultiplier * (twist - Constants.joystickTwistThreshold), 0.0);
				drive(Constants.FRONT_RIGHT, slowMultiplier * (-twist + Constants.joystickTwistThreshold), 0.0);
				drive(Constants.BACK_LEFT, slowMultiplier * (twist - Constants.joystickTwistThreshold), 0.0);
				drive(Constants.BACK_RIGHT, slowMultiplier * (-twist + Constants.joystickTwistThreshold), 0.0);
			} else {	
				drive(Constants.FRONT_LEFT, slowMultiplier * (twist + Constants.joystickTwistThreshold), 0.0);
				drive(Constants.FRONT_RIGHT, slowMultiplier * (-twist - Constants.joystickTwistThreshold), 0.0);
				drive(Constants.BACK_LEFT, slowMultiplier * (twist + Constants.joystickTwistThreshold), 0.0);
				drive(Constants.BACK_RIGHT, slowMultiplier * (-twist - Constants.joystickTwistThreshold), 0.0);
			}
		}
	}
	
	public double[] getValues() {
		return new double[]{values[0], values[1], values[2], values[3]};
	}
	
}
