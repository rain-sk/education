package evilHangman;

import java.util.HashMap;
import java.util.Iterator;

import normalHangman.HangMan;

/**
 * This class implements the game Hangman.
 * 
 * @author Spencer Rudnick
 * @author Bryan Franklin
 */
public class EvilHangMan extends HangMan{
	
	// a Trie used to store the dictionary
	private Trie trie;
	
	// the exposed letters
	private String exposed;
	
	/**
	 * EvilHangMan constructor that loads a dictionary of words from a file.
	 * 
	 * @param fileName
	 *            Name of the dictionary file to load.
	 */
	public EvilHangMan(String fileName){
		
		// construct a HangMan object
		super(fileName);
		
		// filter words with punctuation marks
		filterWords();
		
		// EvilHangMan doesn't use this
		currentWord = "";
		
		// initialize evil (muahahahaha!!!!)
		trie = new Trie();
		
		// set up exposed
		exposed = "";
		for (int i = 0; i < wordLength; i += 1) exposed += "_";
		
	} // end of EvilHangMan constructor

	/**
	 * To simplify things, words that contain non-letters (e.g. contractions)
	 * are forbidden.
	 */
	private void filterWords(){
		int numValid = 0;

		// count valid words
		for (String word : words) {
			if (word.matches("^[a-zA-Z]*$"))
				numValid += 1;
		}

		// copy valid words to new list
		String[] validWords = new String[numValid];
		int pos = 0;
		for (String word : words) {
			if (word.matches("^[a-zA-Z]*$"))
				validWords[pos++] = word;
		}

		// replace old word list with just valid words
		words = validWords;
		
	} // end of filterWords method

	/**
	 * Update the game state to lose the current game. The current player will
	 * lose one point, and the next player will start the next word.
	 */
	public void giveUp(){
		
		// do the stuff
		failures = bodyParts;
		gameLost = true;
		scores[currentPlayer] -= wordLength;
		nextPlayer();
		
	} // end of giveUp method

	/**
	 * Selects a new word from the dictionary and resets the game state for the
	 * new word.
	 * 
	 * @return true if a new word is selected, false otherwise.
	 */
	public boolean newWord(){
		
		// do the stuff
		resetLetters();
		gameWon = false;
		gameLost = false;
		failures = 0;

		// clear the Trie
		trie.clear();
		
		// fill the Trie with all valid words
		for (String word : words) if (word.length() == wordLength) trie.addWord(word);

		// return true if the Trie contains words (a secret word was "chosen"), false otherwise
		return trie.size() > 0;
		
	} // end of newWord method

	/**
	 * Updated game state when a user guesses a letter.
	 * 
	 * @param ch
	 *            the letter being guessed.
	 * @return true if the letter is available and in the word, false otherwise.
	 */
	public boolean guessLetter(char ch){
		
		// make the letter lower-case
		ch = Character.toLowerCase(ch);

		// simply return a failure is the game is not active
		if (gameWon || gameLost)
			return false;

		// return a failure if the guess isn't a letter
		if (!Character.isLetter(ch))
			return false;

		// return a failure is the guess is a repeat
		if (!letterAvailable(ch))
			return false;

		// mark the letter as guessed
		char upper = Character.toUpperCase(ch);
		letters[upper - 'A'] = Character.toLowerCase(ch);

		// create a map of families pointing to integer counters
		HashMap<WordFamily, Integer> families = new HashMap<WordFamily, Integer>();
		
		// iterate through the dictionary
		for (String word : trie){
			
			// count this word's family
			boolean addFamily = true;
			for (WordFamily fam : families.keySet()){
				if (fam.matches(word, ch)){
					families.put(fam, families.get(fam) + 1);
					addFamily = false;
				}
			}
			if (addFamily){
				boolean[] thisWord = new boolean[wordLength];
				for (int i = 0; i < wordLength; i += 1)
					thisWord[i] = word.charAt(i) == ch;
				WordFamily family = new WordFamily(thisWord);
				families.put(family, 1);
			}
		}
		
		// find the most common family
		WordFamily mostCommon = null;
		for (WordFamily family : families.keySet())
			if (mostCommon == null || families.get(mostCommon) < families.get(family)) mostCommon = family;
		
		// remove all words conflicting with the most common family
		for (Iterator<String> iter = trie.iterator(); iter.hasNext();)
			if (!mostCommon.matches(iter.next(), ch)) iter.remove();
		
		// update exposed
		String newExposed = "";
		for (int i = 0; i < exposed.length(); i += 1){
			if (mostCommon.family[i]) newExposed += ch;
			else newExposed += exposed.charAt(i);
		}
		exposed = newExposed;
		
		// return true if a letter was exposed
		for (boolean exposed : mostCommon.family) if (exposed) return true;
		
		// record failure and test for losing
		failures += 1;
		scores[currentPlayer] -= 1;
		if (failures >= bodyParts) {
			gameLost = true;
		} else {
			nextPlayer();
		}

		return false;
		
	} // end of guessLetter method

	/**
	 * Get the currently exposed portions of the word. Unexposed letters will be
	 * represented by '_' (underscore).
	 * 
	 * @return String containing exposed portions of the word
	 */
	public String getExposedLetters(){
		
		// if the game isn't over, return the letters which have been exposed
		if (!gameWon && !gameLost) return exposed;
		
		// if the game is over, return the first word in the dictionary of possible words
		Iterator<String> iter = trie.iterator();
		String out = iter.next();
		return out;
		
	} // end of getExposedLetters method

	/**
	 * Returns the list of words that the answer could still be chosen from.
	 * 
	 * @return Array of words that still match the exposed pattern of letters
	 */
	public String[] remainingWords(){
		
		// copy all the words in the dictionary to an array
		String[] remaining = new String[trie.size()];
		Iterator<String> iter = trie.iterator();
		for (int i = 0; i < remaining.length; i += 1){
			remaining[i] = iter.next();
		}
		
		// return the array
		return remaining;
		
	} // end of remainingWords method
	
	
	
	/**
	 * This class defines a WordFamily object, which is comparable to other WordFamily objects,
	 * and can be checked against a word (String) and a character to see if they match.
	 * 
	 * @author Spencer Rudnick
	 */
	private class WordFamily implements Comparable<WordFamily>{

		// the boolean array which defines this family
		boolean[] family;

		/**
		 * Constructor for a WordFamily.
		 * 
		 * @param fam - the boolean array which defines this family
		 */
		private WordFamily(boolean[] fam){
			
			// store a pointer to the new family array
			family = fam;
			
		} // end of WordFamily constructor
		
		/**
		 * This method checks a word and letter to see if they match the WordFamily.
		 * 
		 * @param word - the word to be checked
		 * 
		 * @param ch - the letter to check for
		 * 
		 * @return true if the word and character match this family, false otherwise
		 */
		public boolean matches(String word, char c){
			
			// sanity check
			if (family.length != word.length()) return false;
			
			// make sure the word matches the family
			for (int i = 0; i < family.length; i += 1)
				if (family[i] != (word.charAt(i) == c)) return false;
			
			// return true if no discrepancies are found
			return true;
			
		} // end of matches method

		/**
		 * This method overrides the Comparable interface's compareTo method.
		 * 
		 * @return true if the WordFamily matches this word family (their boolean arrays are identical),
		 *         false otherwise
		 */
		@Override
		public int compareTo(WordFamily fam){

			// sanity check
			if (family.length != fam.family.length) return -1;

			// make sure the families match
			for (int i = 0; i < family.length; i += 1)
				if (family[i] != fam.family[i]) return -1;

			// return 0 if no discrepancies are found
			return 0;
			
		} // end of compareTo method

	} // end of WordFamily class
	
} // end of EvilHangMan class
