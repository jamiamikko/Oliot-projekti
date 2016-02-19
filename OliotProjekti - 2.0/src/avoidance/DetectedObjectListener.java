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
 
    @Override
    public void featureDetected(final Feature feature, final FeatureDetector detector) {
        int range = (int)feature.getRangeReading().getRange();
        
        if(range <= 30){
            
        	if(range <=3){
                System.out.println("Recognized signal directly on front of me: exiting!");
                System.exit(0);
            }
            
            pilot.stop();
            Button.LEDPattern(2);

            
            
            LCD.drawString("STOP!", 0, 0);
            
            pilot.forward();
            Delay.msDelay(2000);
            pilot.stop();
                 

        } else {
            System.out.println("range: "+range);
        }
    }
}