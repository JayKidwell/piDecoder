import java.util.Scanner;

public class morseCodeSentence {

	static Scanner input;
	public static String ogSentence, letter, morseSentence;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		
		prompt();
		
		while(!ogSentence.equals("done")){
			translateSentence();
			output();
			
			prompt();
		}

	}

	public static void prompt() {
		System.out.print("Enter a sentence to translate: ");
		ogSentence = input.nextLine();
		
	}

	public static void translateSentence(){
		morseSentence = "";

		for (int x = 0; x < ogSentence.length(); x++) {
			switch (ogSentence.substring(x, x + 1).toLowerCase()) {
			case ("a"):
				letter = ".-";
			break;
			case ("b"):
				letter = "-...";
			break;
			case ("c"):
				letter = "-.-.";
			break;
			case ("d"):
				letter = "-..";
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

}
