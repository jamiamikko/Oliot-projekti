package sensor;
import lejos.hardware.sensor.EV3IRSensor;

public class InfraredSignalCheckerThread extends Thread {
	
	private EV3IRSensor infraredSensor;
	
	public InfraredSignalCheckerThread(final EV3IRSensor sensor) {

		this.infraredSensor = sensor;

	}
}
