/**
 * 
 * @author Mohammad A. Kazemivarnamkhasti
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	protected static MorseCodeTree tree = new MorseCodeTree();
	
	/**
	 * An absolutely useless default constructor.
	 */
	public MorseCodeConverter() {}
	
	/**
	 * Converts code, a String of Morse code, into English. 
	 * @param code a String of Morse code
	 * @return the English representation of code
	 */
	public static String convertToEnglish(String code) {
		String eng = "";
		
		// code is split up by spaces into Morse code letters, and stored in morse
		String[] morse = code.split(" ");
		
		// the elements of morse are copied into morseLetters
		ArrayList<String> morseLetters = new ArrayList<String>();
		for(String text : morse)
			morseLetters.add(text);
		
		// the index(es) where the is a space ("/") in morseLetters are saved in spaces
		ArrayList<Integer> spaces = new ArrayList<Integer>();
		for(int k = 0; k < morseLetters.size(); k++)
			if(morseLetters.get(k).equals("/"))
				spaces.add(k);
		
		// each morseLetter is converted to English using fetch
		// and stored in engLetters, except for slashes ("/");
		// spaces are added where there are slashes.
		ArrayList<String> engLetters = new ArrayList<String>();
		for(int k = 0; k < morseLetters.size(); k++)
		{
			if(morseLetters.get(k).equals("/"))
				engLetters.add(" ");
			else
				engLetters.add(tree.fetch(morseLetters.get(k)));
		}
		
		// each character in engLetters is added to eng
		for(int k = 0; k < engLetters.size(); k++)
			eng += engLetters.get(k);
		
		return eng;
	}
	
	/**
	 * Converts a codeFile, a File containing Morse code, into English.
	 * @param codeFile a File containing Morse code
	 * @return the contents of codeFile converted to English
	 * @throws FileNotFoundException if the provided file was not found
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		
		// a Scanner object is created for reading codeFile
		Scanner reader = new Scanner(codeFile);
		
		// codeFile is read, line by line, and each line is passed to convertToEnglish
		// the resulting String is added to eng, which is finally returned
		String line = "", eng = "";
		while(reader.hasNextLine())
		{
			line = reader.nextLine();
			eng += convertToEnglish(line);
		}
		reader.close();
		return eng;
	}
	
	/**
	 * Returns the contents of the tree.
	 * @return the letters in the tree
	 */
	public static String printTree() {
		// appends every element of the tree's ArrayList to
		// a String and then returns it
		String treeData = "";
		ArrayList<String> letters = tree.toArrayList();
		for(int k = 0; k < letters.size(); k++)
			treeData += letters.get(k) + " ";
		
		return treeData.trim();
	}

}