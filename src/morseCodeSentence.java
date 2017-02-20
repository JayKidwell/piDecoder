package src;
import java.util.Scanner;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class morseCodeSentence {

	static Scanner input;
	public static String ogSentence, letter, morseSentence;

	public static void main(String[] args) throws InterruptedException {
    	
        // get a handle to the GPIO controller
    	final GpioController gpio = GpioFactory.getInstance();
        
        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "PinLED", PinState.LOW);
		input = new Scanner(System.in);
		prompt();
		while(!ogSentence.equals("done")){
			translateSentence(pin);
			output();
	        //pin.high();        
	        //System.out.println("light ON");
	        // wait 1seconds
	        //Thread.sleep(1000);
	        // turn off GPIO 1
	        //pin.low();
	        //System.out.println("light is: OFF");
	        // wait 1 second
	        //Thread.sleep(1000);
	        // turn on GPIO 1 for 1 second and then off
	        //System.out.println("light is: ON for 1 second");
	        //pin.pulse(1000, true);
			
			prompt();
		}
        // release the GPIO controller resources
        gpio.shutdown();

	}

	public static void prompt() {
		System.out.print("Enter a sentence to translate: ");
		ogSentence = input.nextLine();
		
	}

	public static void translateSentence(GpioPinDigitalOutput pin) throws InterruptedException{
		morseSentence = "";

		for (int x = 0; x < ogSentence.length(); x++) {
			switch (ogSentence.substring(x, x + 1).toLowerCase()) {
			case ("a"):
				letter = ".-";
				dot(pin);
				dash(pin);
				break;
			case ("b"):
				letter = "-...";
				dash(pin);
				dot(pin);
				dot(pin);
				dot(pin);
				break;
			case ("c"):
				letter = "-.-.";
				dash(pin);
				dot(pin);
				dash(pin);
				dot(pin);
				break;
			case ("d"):
				letter = "-..";
				dash(pin);
				dot(pin);
				dot(pin);
				break;
			case ("e"):
				letter = ".";
			break;
			case ("f"):
				letter = "..-.";
			break;
			case ("g"):
				letter = "--.";
			break;
			case ("h"):
				letter = "....";
			break;
			case ("i"):
				letter = "..";
			break;
			case ("j"):
				letter = ".---";
			break;
			case ("k"):
				letter = "-.-.";
			break;
			case ("l"):
				letter = ".-..";
			break;
			case ("m"):
				letter = "--";
			break;
			case ("n"):
				letter = "-.";
			break;
			case ("o"):
				letter = "---";
			break;
			case ("p"):
				letter = ".--.";
			break;
			case ("q"):
				letter = "--.-";
			break;
			case ("r"):
				letter = ".-.";
			break;
			case ("s"):
				letter = "...";
			break;
			case ("t"):
				letter = "-";
			break;
			case ("u"):
				letter = "..-";
			break;
			case ("v"):
				letter = "...-";
			break;
			case ("w"):
				letter = ".--";
			break;
			case ("x"):
				letter = "-..-";
			break;
			case ("y"):
				letter = "-.--";
			break;
			case ("z"):
				letter = "--..";
			break;
			case(" "):
			letter = " ";
			break;
			case ("done"):
				letter = "bye!";
			break;
			default:
				letter = "invalid";
				break;
			}
			if(!letter.equals(" ")){
				morseSentence += "" + letter + " ";
			}
			else{
				morseSentence += "" + letter + "      ";
			}


		}

	}
	public static void output(){
		System.out.println("Original sentence:" + ogSentence);
		System.out.println("Morse code sentence: " + morseSentence);
	}
	private static void dot(GpioPinDigitalOutput pin) throws InterruptedException{
        System.out.println("dot");
        pin.high();        
        Thread.sleep(300);
        pin.low();
        Thread.sleep(300);
	}
	private static void dash(GpioPinDigitalOutput pin) throws InterruptedException{
        System.out.println("dash");
        pin.high();        
        Thread.sleep(600);
        pin.low();
        Thread.sleep(600);
	}

}
