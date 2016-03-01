package sensor;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.utility.Delay;
import display.Display;




public class InfraredSignalCheckerThread extends Thread {

	
    private EV3IRSensor infraredSensor;
    EV3LargeRegulatedMotor largeMotor;
    EV3LargeRegulatedMotor largeMotor2;
    EV3MediumRegulatedMotor largeMotor3;
    /**
     * määritetään tarvittavat muuttujat
     *
     */
    
    
    public InfraredSignalCheckerThread(final EV3IRSensor sensor, EV3LargeRegulatedMotor largeMotor, EV3LargeRegulatedMotor largeMotor2,EV3MediumRegulatedMotor largeMotor3){
    	this.largeMotor=largeMotor;
    	this.largeMotor3=largeMotor3;
    	this.largeMotor2=largeMotor2;
        this.infraredSensor = sensor;
    }	
    /**
     *vastaanotetaan Controllerista sensori + moottorit ja osoitetaan ne muuttujiin
     */

    @Override
    public void run() {
    	
    	
    	largeMotor.setSpeed(800);
    	largeMotor2.setSpeed(800);
    	largeMotor3.setSpeed(800);
    	/**
    	 * aloitusnopeussuureet
    	 */
    	
    	

    	while(Button.ESCAPE.isUp()){
            final int remoteCommand = infraredSensor.getRemoteCommand(0);
            switch (remoteCommand){
            /**
        	 * sääntö joka pitää loopin päällä
        	 * muuttaa kaukosäätimen komennon int muotoon
        	 */
            
                case 1:
                	largeMotor.backward();
                	largeMotor2.backward();
                	Button.LEDPattern(1);
                	Display.DrawArrowForward();

                    break;
                    /**
                     * moottorit eteenpäin,vihreä led,nuoli ylös
                     */
                    
              
                case 2:
                	largeMotor.forward();
                	largeMotor2.forward();
                	Button.LEDPattern(8);
                	Display.DrawArrowReverse();
                	
                	
                	
                	break;
                	/**
                     * moottorit taaksepäin,punainen vilkkuva led,nuoli alas
                     */
                	
                case 3:
                	
                	
                	largeMotor2.setSpeed(200);
                	largeMotor3.rotate(10);
                	Display.DrawArrowLeft();
                	
                	
                	
                	break;
                	/**
                     * kääntää kääntömoottorie 10 astetta vasempaan,hidastaa vasenta moottoria,nuoli vasempaan 
                     */
                	
                case 4:
                	largeMotor.setSpeed(200);
                	largeMotor3.rotate(-10);
                	Display.DrawArrowRight();
                	
                	break;
                	/**
                     * kääntää kääntömoottoria 10 astetta oikeaan,hidastaa oikeaa moottoria,nuoli oikeaan
                     */
                	
                case 9:
                	largeMotor2.stop(true);
                	largeMotor.stop(true);
                	Button.LEDPattern(0);
                	LCD.clear();

                	break;
                	/**
                	 * pysäyttää moottorit,sammuttaa ledit,tyhjentää näytön
                	 */
                	
                default:
                	//largeMotor2.stop(true);
                	//largeMotor.stop(true);
                	largeMotor3.stop(true);
                	largeMotor3.rotateTo(0);
                	largeMotor.setSpeed(800);
                	largeMotor2.setSpeed(800);
                	largeMotor3.setSpeed(800);
                	
                	/**
                	 * palauttaa moottorien nopeudet vakioiksi,sekä palauttaa kääntömoottorin lähtöasentoon 
                	 */
                	

                	
            }
        }
    }    
    
}
