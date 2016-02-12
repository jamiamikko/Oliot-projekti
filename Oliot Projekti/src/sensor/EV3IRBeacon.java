package sensor;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.lcd.LCD;

public class EV3IRBeacon {

	public static void main(String[] args) {
		LCD.clear();

		// Get an instance of the IR EV3 sensor

		final EV3IRSensor infraredSensor = new EV3IRSensor(SensorPort.S1);

		InfraredSignalCheckerThread checkerThread = new InfraredSignalCheckerThread(infraredSensor);

		// TODO Auto-generated method stub

		checkerThread.start();

	}

}