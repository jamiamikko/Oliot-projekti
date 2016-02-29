package avoidance;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.utility.Delay;

public class DetectedObjectListener implements FeatureListener {

	/**
	 * Luodaan ja määritetään DetectedObjectListener pilotti.
	 */

	private DifferentialPilot pilot;

	public DetectedObjectListener(final DifferentialPilot pilot) {
		this.pilot = pilot;
	}

	/**
	 * Uudelleen määritetään FeatureListener rajapinnan featureDetected metodi.
	 */
	
	@Override
	public void featureDetected(final Feature feature, final FeatureDetector detector) {
		
		/**
		 * Määritetään etäisyysmuuttuja.
		 */
		
		int range = (int) feature.getRangeReading().getRange();
		
		/**
		 * Kuuntelija toimii alle 30 cm säteellä.
		 */

		if (range <= 30) {
			
			/**
			 * Jos säde on pienempi kuin 3 cm, ohjelma pysäytetään.
			 */
			if (range <= 3) {
				System.out.println("Recognized signal directly on front of me: exiting!");
				System.exit(0);
			}

			/**
			 * Etäisyyden ollessa riittävän pieni kauko-ohjaus keskeytetään.
			 */
			
			pilot.stop();
			Button.LEDPattern(2);

			LCD.drawString("STOP!", 0, 0);

			/**
			 * Keskeytyksen jälkeen ohjelma liikuttaa robottia taaksepäin 
			 */
			
			pilot.forward();
			Delay.msDelay(2000);
			pilot.stop();
			
			/**
			 * Kauko-ohjausta voidaan jatkaa nyt normaalisti
			 */

		} else {
			/**
			 * Else ehtona printataan controlleriin sensorin saama etäisyys
			 */
			System.out.println("range: " + range);
		}
	}
}