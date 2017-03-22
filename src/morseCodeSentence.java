package src;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
//
//import javaFlacEncoder.EncodingConfiguration;
//import javaFlacEncoder.EncodingConfiguration.ChannelConfig;
//import javaFlacEncoder.FLAC_FileEncoder;
//import javaFlacEncoder.StreamConfiguration;
//
//import com.google.cloud.speech.spi.v1beta1.SpeechClient;
//import com.google.cloud.speech.v1beta1.RecognitionAudio;
//import com.google.cloud.speech.v1beta1.RecognitionConfig;
//import com.google.cloud.speech.v1beta1.RecognitionConfig.AudioEncoding;
//import com.google.cloud.speech.v1beta1.SpeechRecognitionAlternative;
//import com.google.cloud.speech.v1beta1.SpeechRecognitionResult;
//import com.google.cloud.speech.v1beta1.SyncRecognizeResponse;
//import com.google.protobuf.ByteString;
//
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
//
//
public class morseCodeSentence{

	static Scanner input;
	public static String letter, morseSentence;

	public static void main(String[] args) throws InterruptedException, IOException {
		

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
			final JavaSoundRecorder recorder = new JavaSoundRecorder();

			// creates a new thread that waits for a specified
			// of time before stopping
			Thread stopper = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(JavaSoundRecorder.RECORD_TIME);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					recorder.finish();
				}
			});

			stopper.start();

			// start recording
			recorder.start();
			
			//recorder.wait();
			
			Thread.sleep(6000);
			System.out.println("done");
			//
			// shell out to aconv
			Process p;
			p = Runtime.getRuntime().exec("avconv -i /home/pi/test.wav -loglevel panic -y -ar 16000 -ac 1 -acodec flac /home/pi/test.flac");
		    p.waitFor();
		    //
		    // call google api
		    //
		    Translate test = new Translate();
		    String resultSentence =  test.googleApiTranslate("/home/pi/test.flac");
			
//			ChannelConfig streamConfig = 
//			
//			EncodingConfiguration encodConfig = new EncodingConfiguration();
//			encodConfig.setChannelConfig(streamConfig);
//			
//			FLAC_FileEncoder flacEncoder = new FLAC_FileEncoder();
//			//flacEncoder.setStreamConfig(streamConfig);
//	        File inputFile = new File("/home/pi/test.wav");
//	        File outputFile = new File("/home/pi/test.flac");
//
//	        flacEncoder.encode(inputFile, outputFile);
//	        System.out.println("Done");
	        
	        
	        
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
		return "Lydia";
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
		System.out.print("dot ");
		pin.high();        
		Thread.sleep(500);
		pin.low();
		Thread.sleep(500);
	}
	private static void dash(GpioPinDigitalOutput pin) throws InterruptedException{
		System.out.print("dash ");
		pin.high();        
		Thread.sleep(1000);
		pin.low();
		Thread.sleep(1000);
	}

	private static void space(GpioPinDigitalOutput pin) throws InterruptedException{
		System.out.println("space  ");
		pin.low();
		Thread.sleep(500);
	}

//	  public static String googleTranslate(String fileName) throws Exception {
//		    // Instantiates a client
//		  String result= "";
//		    SpeechClient speech = SpeechClient.create();
//
//		    // The path to the audio file to transcribe
//		    //String fileName = "./resources/test.flac";
//
//		    // Reads the audio file into memory
//		    Path path = Paths.get(fileName);
//		    byte[] data = Files.readAllBytes(path);
//		    ByteString audioBytes = ByteString.copyFrom(data);
//
//		    // Builds the sync recognize request
//		    RecognitionConfig config = RecognitionConfig.newBuilder()
//		        .setEncoding(AudioEncoding.FLAC)
//		        .setSampleRate(16000)
//		        .build();
//		    RecognitionAudio audio = RecognitionAudio.newBuilder()
//		        .setContent(audioBytes)
//		        .build();
//
//		    // Performs speech recognition on the audio file
//		    SyncRecognizeResponse response = speech.syncRecognize(config, audio);
//		    List<SpeechRecognitionResult> results = response.getResultsList();
//
//		    System.out.printf("you are here." + results.size());
//
//		    for (SpeechRecognitionResult result: results) {
//		      List<SpeechRecognitionAlternative> alternatives = result.getAlternativesList();
//		      for (SpeechRecognitionAlternative alternative: alternatives) {
//		    	  result += alternative.getTranscript();
//		        System.out.printf("Transcription: %s%n", alternative.getTranscript());
//		      }
//		    }
//		    speech.close();
//		    return result;
//		  }



}

