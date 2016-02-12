package motors;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3IRSensor;

public class Motors {
	
	private EV3IRSensor infraredSensor;
	
	EV3LargeRegulatedMotor largeMotor;

	EV3LargeRegulatedMotor largeMotor2;
	
	
	
	
	public void run() {

		largeMotor = new EV3LargeRegulatedMotor(MotorPort.D);

		largeMotor2 = new EV3LargeRegulatedMotor(MotorPort.A);

		largeMotor.setSpeed(200);

		largeMotor.setSpeed(200);

		while (Button.ESCAPE.isUp()) {

			final int remoteCommand = infraredSensor.getRemoteCommand(0);

			switch (remoteCommand) {

			case 1:
				largeMotor.forward();
				largeMotor2.forward();
				break;

			case 2:
				largeMotor.backward();
				largeMotor2.backward();
				break;

			case 3:
				largeMotor.stop();
				largeMotor2.forward();
				break;

			case 4:
				largeMotor2.stop();
				largeMotor.forward();
				break;

			case 9:
				largeMotor2.stop();
				largeMotor.stop();

				break;

			default:

			}

		}

	}
}
