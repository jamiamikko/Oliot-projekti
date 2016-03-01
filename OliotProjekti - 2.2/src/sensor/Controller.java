package sensor;

import avoidance.DetectedObjectListener;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RangeFinderAdaptor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class Controller {
	protected final static float maxDistance = 1000.0f;
	protected final static int interval = 500;
	protected final static double wheelDiameter = 30.0f;
	protected final static double wheelDistance = 170.0;
	final EV3IRSensor infraredSensor = new EV3IRSensor(SensorPort.S1);
	final EV3IRSensor infraredSensor2 = new EV3IRSensor(SensorPort.S4);
	EV3LargeRegulatedMotor largeMotor;
	EV3LargeRegulatedMotor largeMotor2;
	EV3MediumRegulatedMotor largeMotor3;

	public Controller() {
	
		largeMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		largeMotor2 = new EV3LargeRegulatedMotor(MotorPort.D);
		largeMotor3 = new EV3MediumRegulatedMotor(MotorPort.A);
		startohjaus();
		startvaisto();
	}

	private void startohjaus() {
		LCD.clear();
		LCD.drawString("EV3 IR Beacon", 0, 5);

		

		InfraredSignalCheckerThread checkerThread = new InfraredSignalCheckerThread(infraredSensor2, largeMotor,
				largeMotor2, largeMotor3);
		checkerThread.start();
	}

	private void startvaisto() {
		final DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, wheelDistance, largeMotor, largeMotor2);
		

		final RangeFinderAdaptor rangeFinderAdaptor = new RangeFinderAdaptor(infraredSensor.getDistanceMode());

		final RangeFeatureDetector rangeFeatureDetector = new RangeFeatureDetector(rangeFinderAdaptor, maxDistance,
				interval);

		final FeatureListener detectedObjectListener = new DetectedObjectListener(pilot);

		rangeFeatureDetector.addListener(detectedObjectListener);

	}
}
