package src;
import java.util.Scanner;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class morseCodeSentence {

	static Scanner input;
	public static String letter, morseSentence;

	public static void main(String[] args) throws InterruptedException {
		// 
		String ogSentence = "";
        // get a handle to the GPIO controller
    	GpioController gpio = GpioFactory.getInstance();
        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
        GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.LOW);
        GpioPinDigitalInput buttonPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_08);
		input = new Scanner(System.in);
		// wait for button to be high (starting state)
		
		while(buttonPin.isLow()){}
		while(!ogSentence.equals("done")){
			// wait for button push
			System.out.print("Waiting for button push...");
			while(buttonPin.isHigh()) {}
			// get a sentence and translate
			ogSentence = prompt();
			translateSentence(ledPin,ogSentence);
			output(ogSentence);
		}
        // release the GPIO controller resources
        gpio.shutdown();

	}
	//
	// get the user input
	//	phase 1 - prompt the user for text
	//  phase 2 - return a string retrieved from a google api call to translate an online sound file
	//  phase 3 - open a microphone, record a file and translate it to a string
	//
	public static String prompt() {
		return "All work and no play makes Johnny a dull boy";
		//System.out.print("Enter a sentence to translate: ");
		//return input.nextLine();		
	}
	//
	// output the sentence to the morse code system
	//
	public static void translateSentence(GpioPinDigitalOutput pin, String ogSentence) throws InterruptedException{
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
			//e is the next letter
			case ("e"):
				letter = ".";
				dot(pin);
			break;
			case ("f"):
				letter = "..-.";
				dot(pin);
				dot(pin);
				dash(pin);
				dot(pin);
			break;
			case ("g"):
				letter = "--.";
				dash(pin);
				dash(pin);
				dot(pin);
			break;
			case ("h"):
				letter = "....";
				dot(pin);
				dot(pin);
				dot(pin);
				dot(pin);
			break;
			case ("i"):
				letter = "..";
				dot(pin);
				dot(pin);
			break;
			case ("j"):
				letter = ".---";
				dot(pin);
				dash(pin);
				dash(pin);
				dash(pin);
			break;
			case ("k"):
				letter = "-.-.";
				dash(pin);
				dot(pin);
				dash(pin);
				dot(pin);
			break;
			case ("l"):
				letter = ".-..";
				dot(pin);
				dash(pin);
				dot(pin);
				dot(pin);
			break;
			case ("m"):
				letter = "--";
				dash(pin);
				dash(pin);
			break;
			case ("n"):
				letter = "-.";
				dash(pin);
				dot(pin);
			break;
			case ("o"):
				letter = "---";
				dash(pin);
				dash(pin);
				dash(pin);
			break;
			case ("p"):
				letter = ".--.";
				dot(pin);
				dash(pin);
				dash(pin);
				dot(pin);
			break;
			case ("q"):
				letter = "--.-";
				dash(pin);
				dash(pin);
				dot(pin);
				dash(pin);
			break;
			case ("r"):
				letter = ".-.";
				dot(pin);
				dash(pin);
				dot(pin);
			break;
			case ("s"):
				letter = "...";
				dot(pin);
				dot(pin);
				dot(pin);
			break;
			case ("t"):
				letter = "-";
				dash(pin);
			break;
			case ("u"):
				letter = "..-";
				dot(pin);
				dot(pin);
				dash(pin);
			break;
			case ("v"):
				letter = "...-";
				dot(pin);
				dot(pin);
				dot(pin);
				dash(pin);
			break;
			case ("w"):
				letter = ".--";
				dot(pin);
				dash(pin);
				dash(pin);
			break;
			case ("x"):
				letter = "-..-";
				dash(pin);
				dot(pin);
				dot(pin);
				dash(pin);
				break;
			case ("y"):
				letter = "-.--";
				dash(pin);
				dot(pin);
				dash(pin);
				dash(pin);
				break;
			case ("z"):
				letter = "--..";
				dash(pin);
				dash(pin);
				dot(pin);
				dot(pin);
				break;
			case(" "):
				letter = " ";
				space(pin);
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
	public static void output(String ogSentence){
		System.out.println("Original sentence:" + ogSentence);
		System.out.println("Morse code sentence: " + morseSentence);
	}
	private static void dot(GpioPinDigitalOutput pin) throws InterruptedException{
        System.out.println("dot");
        pin.high();        
        Thread.sleep(100);
        pin.low();
        Thread.sleep(100);
	}
	private static void dash(GpioPinDigitalOutput pin) throws InterruptedException{
        System.out.println("dash");
        pin.high();        
        Thread.sleep(200);
        pin.low();
        Thread.sleep(200);
	}
	
	private static void space(GpioPinDigitalOutput pin) throws InterruptedException{
		System.out.println("space");
		pin.low();
		Thread.sleep(100);
	}
	
	
	

}
