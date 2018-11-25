package hangMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class implements all of the functionality for the classic children's
 * word game Hangman.
 * 
 * WARNING: You are not allowed to modify this file. If you do your submissions
 * may not compile or behave correctly when tested. We will use our own copy of
 * this class when testing.
 * 
 * @author Bryan Franklin
 * 
 */
public class HangMan {
	private String[] words;
	private String currentWord;
	private int wordLength = 5;
	private char[] letters = new char[26];
	private int failures;
	private boolean gameLost = true;
	private boolean gameWon = false;
	private int bodyParts = 6;
	private int currentPlayer = 0;
	private int numPlayers = 2;
	private int[] scores = new int[7];
	private boolean roundStarted = false;

	/**
	 * Constructs a new HangMan instance that will use the words in the
	 * specified file. The file should contain the number of words on the first
	 * line, followed by all of the words on separate lines.
	 * 
	 * @param fileName
	 *            Name of file that contains a list of words to use.
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
	 * Resets game state and chooses a new word to start a new round. Score and
	 * current player are not modified.
	 */
	public void changeWord() {
		pickDifferentWord();
		resetLetters();
		gameWon = false;
		gameLost = false;
		failures = 0;
		roundStarted = false;
	}

	/**
	 * Resets all letter to being unguessed. This should only be called when
	 * resetting the game state.
	 */
	private void resetLetters() {
		for (int i = 0; i < 26; ++i) {
			// set each letter in the letters array to uppercase to indicate
			// they are available
			letters[i] = (char) ('A' + i);
		}
	}

	/**
	 * Chooses a random word from the word list to use. This should only be
	 * called when resetting the game state.
	 */
	private void pickDifferentWord() {
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
	 * Determine if a letter is available to be guessed.
	 * 
	 * @param c
	 *            The letter being checked.
	 * @return true if the letter has not already been guessed, false otherwise.
	 */
	public boolean letterAvailable(char c) {
		char upper = Character.toUpperCase(c);

		// make sure the character is a letter
		if (upper < 'A' || upper > 'Z')
			return false;

		// return true if the letter is upper case in the letters array
		return (letters[upper - 'A'] == upper);
	}

	/**
	 * Returns the length of the word being guessed. If no word is currently
	 * selected, it will return the length of the next word to be seleted.
	 * 
	 * @return Length of the current word.
	 */
	public int getWordLength() {
		if (currentWord == null)
			return wordLength;
		return currentWord.length();
	}

	/**
	 * Set the length of the word to be used. If a word is currently being
	 * guessed, this will end the round and immediately start a new word by
	 * calling giveUp().
	 * 
	 * @param newLength
	 *            New word length
	 * @return true if the word length was changed, false otherwise
	 */
	public boolean setWordLength(int newLength) {
		if (newLength > 0 && newLength != wordLength) {
			wordLength = newLength;
			if (gameActive())
				giveUp();
			return true;
		}
		return false;
	}

	/**
	 * Updates game state to correspond to a player guessing a letter, as one
	 * would when playing a normal game of hangman. The score, current player,
	 * number of failures, victory, defeat and active game conditions are all
	 * updated accordingly.
	 * 
	 * @param c
	 *            The letter being guessed.
	 * @return true if the letter was in the word, false otherwise
	 */
	public boolean makeGuess(char c) {
		roundStarted = true;

		// simply return a failure is the game is not active
		if (gameWon || gameLost)
			return false;

		// return a failure if the guess isn't a letter
		if (!Character.isLetter(c))
			return false;

		// return a failure is the guess is a repeat
		if (!letterAvailable(c))
			return false;

		// mark the letter as guessed
		char upper = Character.toUpperCase(c);
		letters[upper - 'A'] = Character.toLowerCase(c);

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
	 * Checks to see if a player has won the game.
	 * 
	 * @return true if the game has been won, false otherwise
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
	 * Returns the score (i.e. number of words the player finished) of the
	 * specified player. Players are numbered starting with 0 (zero).
	 * 
	 * @param player
	 *            Id of the player
	 * @return Players score.
	 */
	public int getPlayerScore(int player) {
		if (player >= numPlayers || player < 0)
			return -1;
		return scores[player];
	}

	/**
	 * Return the number of incorrect guesses so far. This indicates how many
	 * body parts should be drawn.
	 * 
	 * @return Number of failures so far.
	 */
	public int getFailures() {
		return failures;
	}

	/**
	 * Updates the game state to indicate that the current player gives up on
	 * the word. This will decrement that player's score by one and the next
	 * player will get the first guess in the next round. This method has no
	 * effect until the first letter is guessed on a round.
	 */
	public void giveUp() {
		if (!roundStarted)
			return;
		failures = bodyParts;
		gameLost = true;
		scores[currentPlayer] -= 1;
		nextPlayer();
	}

	/**
	 * Returns the currently exposed letters in the current word. All unexposed
	 * letters are represented by the underscore (_) character. Words that
	 * contain non-letters such as contractions will always have the non-letters
	 * exposed. If the gameLost flag is is true, all letters will be shown.
	 * 
	 * @return String containing exposed letters
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
	 * Returns victory condition.
	 * 
	 * @return true if a player was one the current round, false otherwise
	 */
	public boolean gameWon() {
		return gameWon;
	}

	/**
	 * Returns defeat condition.
	 * 
	 * @return true the current round was lost, false otherwise
	 */
	public boolean gameLost() {
		return gameLost;
	}

	/**
	 * Returns active game condition.
	 * 
	 * @return true if a round is currently being payed, false otherwise
	 */
	public boolean gameActive() {
		return (!gameWon && !gameLost);
	}

	/**
	 * Set the number of players currently in the game.
	 * 
	 * @param players
	 *            New number of players.
	 * 
	 * @return true is the number of players is valid, false otherwise
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
	 * Get the number of players currently in the game.
	 * 
	 * @return Number of players in the game.
	 */
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * Advance control of the current round to the next player.
	 * 
	 * @return Id of the player who will make the next guess.
	 */
	public int nextPlayer() {
		currentPlayer = (currentPlayer + 1) % numPlayers;
		return currentPlayer;
	}

	/**
	 * Get id of the current player. Players are numbered starting with 0
	 * (zero).
	 * 
	 * @return Id of the currently guessing player.
	 */
	public int currentPlayer() {
		return currentPlayer;
	}

}
