package org.usfirst.frc.team6574.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderPIDSource implements PIDSource {

	private Encoder encoder;
	
	public EncoderPIDSource(Encoder e) {
		encoder = e;
		encoder.setDistancePerPulse(Constants.ENCODER_PULSE_DISTANCE);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		//#LOL GET REKT
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	}

	@Override
	public double pidGet() {
		return encoder.getRate();
	}
	
	
}
