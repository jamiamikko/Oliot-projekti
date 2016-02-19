package sensor;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.utility.Delay;


public class InfraredSignalCheckerThread extends Thread {

	
    private EV3IRSensor infraredSensor;
    EV3LargeRegulatedMotor largeMotor;
    EV3LargeRegulatedMotor largeMotor2;
    EV3MediumRegulatedMotor largeMotor3;
    
    public InfraredSignalCheckerThread(final EV3IRSensor sensor, EV3LargeRegulatedMotor largeMotor, EV3LargeRegulatedMotor largeMotor2,EV3MediumRegulatedMotor largeMotor3){
    	this.largeMotor=largeMotor;
    	this.largeMotor3=largeMotor3;
    	this.largeMotor2=largeMotor2;
        this.infraredSensor = sensor;
    }	

    @Override
    public void run() {
    	
    	
    	largeMotor.setSpeed(800);
    	largeMotor2.setSpeed(800);
    	largeMotor3.setSpeed(800);
    	
    	

    	while(Button.ESCAPE.isUp()){
            final int remoteCommand = infraredSensor.getRemoteCommand(0);
            switch (remoteCommand){
                case 1:
                	largeMotor.backward();
                	largeMotor2.backward();
                	Button.LEDPattern(1);

               
    
                	
                    break;
                case 2:
                	largeMotor.forward();
                	largeMotor2.forward();
                	Button.LEDPattern(8);

                	
                	break;
                case 3:
                	largeMotor2.setSpeed(200);
                	largeMotor3.rotate(10);
                	
                	
                	
                	break;
                case 4:
                	largeMotor.setSpeed(200);
                	largeMotor3.rotate(-10);
                	
                	break;
                case 9:
                	largeMotor2.stop(true);
                	largeMotor.stop(true);
                	Button.LEDPattern(0);

                	break;
                default:
                	//largeMotor2.stop(true);
                	//largeMotor.stop(true);
                	largeMotor3.stop(true);
                	largeMotor3.rotateTo(0);
                	largeMotor.setSpeed(800);
                	largeMotor2.setSpeed(800);
                	largeMotor3.setSpeed(800);
                	

                	
            }
        }
    }    
    
}