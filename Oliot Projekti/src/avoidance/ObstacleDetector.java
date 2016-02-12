package avoidance;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RangeFinderAdaptor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.utility.Delay;

public class ObstacleDetector {
	protected final static float maxDistance = 100.0f;
	protected final static int interval = 500;
	protected final static double wheelDiameter = 30.0f;
	protected final static double wheelDistance = 170.0;

	public static void main(String[] args) {
		
		final DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, wheelDistance, Motor.D, Motor.B);
		
		final EV3IRSensor infraredSensor = new EV3IRSensor(SensorPort.S2);

		configurePilot(pilot);
		configureInfraredSensor(infraredSensor, pilot);

		// wait for the sensor to be completely initialized and start the robot
		Delay.msDelay(5000);
		System.out.println("    Starting!");
		pilot.backward();
		Button.waitForAnyPress();
	}

	private static void configureInfraredSensor(final EV3IRSensor infraredSensor, final DifferentialPilot pilot) {
		final RangeFinderAdaptor rangeFinderAdaptor = new RangeFinderAdaptor(infraredSensor.getDistanceMode());
		
		final RangeFeatureDetector rangeFeatureDetector = new RangeFeatureDetector(rangeFinderAdaptor, maxDistance, interval);
		
		final FeatureListener detectedObjectListener = new DetectedObjectListener(pilot);
		
		rangeFeatureDetector.addListener(detectedObjectListener);
	}

	private static void configurePilot(final DifferentialPilot pilot) {
		//pilot.setAcceleration(pilotAcceleration);
		pilot.setRotateSpeed(100);
		pilot.setTravelSpeed(150);
	}
}
