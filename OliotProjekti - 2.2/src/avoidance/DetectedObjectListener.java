package avoidance;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.utility.Delay;

public class DetectedObjectListener implements FeatureListener {

	

	private DifferentialPilot pilot;

	public DetectedObjectListener(final DifferentialPilot pilot) {
		this.pilot = pilot;
	}
	
	/**
	 * Luodaan ja määritetään DetectedObjectListener pilotti.
	 */

	
	@Override
	public void featureDetected(final Feature feature, final FeatureDetector detector) {
		
		
		
		int range = (int) feature.getRangeReading().getRange();
		
		/**
		 * Määritetään etäisyysmuuttuja.
		 */

		if (range <= 30) {
			
			pilot.stop();
			Button.LEDPattern(2);

			LCD.drawString("STOP!", 0, 0);
			
			/**
			 * Etäisyyden ollessa riittävän pieni kauko-ohjaus keskeytetään.
			 */

			
			
			pilot.forward();
			Delay.msDelay(2000);
			pilot.stop();
			
			/**
			 * Keskeytyksen jälkeen ohjelma liikuttaa robottia taaksepäin 
			 */
			
			/**
			 * Kauko-ohjausta voidaan jatkaa nyt normaalisti
			 */

		} else {
			
			System.out.println("Etäisyys: " + range);
			
			/**
			 * Else ehtona printataan controlleriin sensorin saama etäisyys
			 */
		}
	}
	
	/**
	 * Uudelleen määritetään FeatureListener rajapinnan featureDetected metodi.
	 */
}
