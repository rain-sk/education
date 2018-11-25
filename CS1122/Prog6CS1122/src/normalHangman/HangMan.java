package normalHangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * This class implements the game hangman.
 * 
 * WARNING: You are not allowed to modify this file in any way. All changed must
 * go into the EvilHangMan class that extends this one.
 * 
 * @author Bryan Franklin
 * 
 */
public class HangMan {
	protected String[] words;
	protected String currentWord;
	protected int wordLength = 5;
	protected char[] letters = new char[26];
	protected int failures;
	protected boolean gameLost = true;
	protected boolean gameWon = false;
	protected int bodyParts = 6;
	protected int currentPlayer = 0;
	protected int numPlayers = 2;
	protected int[] scores = new int[7];

	/**
	 * HangMan constructor that loads a dictionary of words from a file.
	 * 
	 * @param fileName
	 *            Name of the dictionary file to load.
	 */
	public HangMan(String fileName) {
		try {
			// open dictionary file
			Scanner in = new Scanner(new File(fileName));

			// read length of file
			String numWordsStr = in.next();
			int numWords = Integer.parseInt(numWordsStr);

			// allocate dictionary array
			words = new String[numWords];

			// read remainder of file into dictionary array
			int i = 0;
			while (in.hasNext()) {
				words[i] = in.next();
				i += 1;
			}

			// close dictionary file
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Selects a new word from the dictionary and resets the game state for the
	 * new word.
	 * 
	 * @return true if a new word is selected, false otherwise.
	 */
	public boolean newWord() {
		pickNewWord();
		resetLetters();
		gameWon = false;
		gameLost = false;
		failures = 0;
		return true;
	}

	/**
	 * Resets the list of guessed letters.
	 * 
	 * @return true if the letters are reset, false otherwise
	 */
	protected boolean resetLetters() {
		for (int i = 0; i < 26; ++i) {
			letters[i] = (char) ('A' + i);
		}
		return true;
	}

	/**
	 * Helper method that picks a new word when a new game is started.
	 */
	private void pickNewWord() {
		// pick a random position in the dictionary
		int startPos = (int) (Math.random() * words.length);
		int pos = startPos;
		int minLength = Integer.MAX_VALUE;
		int maxLength = Integer.MIN_VALUE;

		// scan until a word with the correct length is found
		while (words[pos].length() != wordLength) {
			if (words[pos].length() > maxLength)
				maxLength = words[pos].length();
			if (words[pos].length() < minLength)
				minLength = words[pos].length();
			pos = (pos + 1) % words.length;

			// pos looped back to original position, decrease length
			if (pos == startPos) {
				wordLength -= 1;
				if (wordLength < minLength)
					wordLength = minLength;
			}
		}
		currentWord = words[pos];
		System.out.println("picked word: " + currentWord);
	}

	/**
	 * Determines if a letter is available to be guessed.
	 * 
	 * @param ch
	 *            The letter being guessed.
	 * @return true if the letter hasn't been guessed yet, false otherwise
	 */
	public boolean letterAvailable(char ch) {
		char upper = Character.toUpperCase(ch);

		// make sure the character is a letter
		if (upper < 'A' || upper > 'Z')
			return false;

		// return true if the letter is upper case in the letters array
		return (letters[upper - 'A'] == upper);
	}

	/**
	 * Gets the length of the current word.
	 * 
	 * @return length of the current word.
	 */
	public int getWordLength() {
		return wordLength;
	}

	/**
	 * Modified the length of word to use.
	 * 
	 * @param newLength
	 *            Updated length to use.
	 * @return true of the length is updated, false otherwise.
	 */
	public boolean setWordLength(int newLength) {
		if (newLength > 0 && newLength != wordLength) {
			wordLength = newLength;
			newWord();
			return true;
		}
		return false;
	}

	/**
	 * Updated game state when a user guesses a letter.
	 * 
	 * @param ch
	 *            the letter being guessed.
	 * @return true if the letter is available and in the word, false otherwise.
	 */
	public boolean guessLetter(char ch) {
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

		// check to see if the letter is in the word
		for (int i = 0; i < currentWord.length(); ++i) {
			if (upper == Character.toUpperCase(currentWord.charAt(i))) {
				gameWon = victoryCheck();
				return true;
			}
		}

		// test for losing
		failures += 1;
		if (failures >= bodyParts) {
			gameLost = true;
		} else {
			nextPlayer();
		}

		return false;
	}

	/**
	 * Checks to see if the game has been won.
	 * 
	 * @return true is the game has been won, false otherwise.
	 */
	private boolean victoryCheck() {
		for (int i = 0; i < currentWord.length(); ++i) {
			if (letterAvailable(currentWord.charAt(i)))
				return false;
		}
		scores[currentPlayer] += 1;
		return true;
	}

	/**
	 * Get the score of a particular player. Players are numbered starting at 0
	 * (zero).
	 * 
	 * @param player
	 *            Number id of the player
	 * @return The player's score.
	 */
	public int getPlayerScore(int player) {
		if (player >= numPlayers || player < 0)
			return -1;
		return scores[player];
	}

	/**
	 * Get the number of incorrect guessed on the current word.
	 * 
	 * @return number of incorrect guessed.
	 */
	public int getFailures() {
		return failures;
	}

	/**
	 * Update the game state to lose the current game. The current player will
	 * lose one point, and the next player will start the next word.
	 */
	public void giveUp() {
		failures = bodyParts;
		gameLost = true;
		scores[currentPlayer] -= 1;
		nextPlayer();
	}

	/**
	 * Get the currently exposed portions of the word. Unexposed letters will be
	 * represented by '_' (underscore).
	 * 
	 * @return String containing exposed portions of the word
	 */
	public String getExposedLetters() {
		String ret = "";
		for (int i = 0; i < currentWord.length(); ++i) {
			char ch = currentWord.charAt(i);
			if (!letterAvailable(ch) || gameLost)
				ret += currentWord.charAt(i);
			else
				ret += "_";
		}

		return ret;
	}

	/**
	 * Test for victory.
	 * 
	 * @return true if the current game as been won, false otherwise.
	 */
	public boolean gameWon() {
		return gameWon;
	}

	/**
	 * Test for defeat.
	 * 
	 * @return true if the current game as been lost, false otherwise.
	 */
	public boolean gameLost() {
		return gameLost;
	}

	/**
	 * Test for currently playable game (i.e. neither won, nor lost).
	 * 
	 * @return true if the current game as playable, false otherwise.
	 */
	public boolean gameActive() {
		return (!gameWon && !gameLost);
	}

	/**
	 * Set the number of players in the current game.
	 * 
	 * @param players
	 *            new number of players (at least 1, but no more than 6)
	 * @return true if the requested number of players is valid, false otherwise
	 */
	public boolean setNumPlayers(int players) {
		if (players < 1 || players >= scores.length)
			return false;
		numPlayers = players;
		for (int i = numPlayers; i < scores.length; ++i)
			scores[i] = 0;
		if (currentPlayer >= numPlayers)
			currentPlayer = 0;

		return true;
	}

	/**
	 * Get the number of players playing the game.
	 * 
	 * @return number of players in the current game
	 */
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * Cause the next player in the game to get a turn, skipping the current
	 * player.
	 * 
	 * @return the player id of the player who's turn it is upon return
	 */
	public int nextPlayer() {
		currentPlayer = (currentPlayer + 1) % numPlayers;
		return currentPlayer;
	}

	/**
	 * Get the player id of the player who can make a guess.
	 * 
	 * @return the player id of the guessing player
	 */
	public int currentPlayer() {
		return currentPlayer;
	}

}
