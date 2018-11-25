package textUI;

import hangMan.HangMan;

import java.io.IOException;
import java.util.Scanner;


/**
 * This class provides a text based interface to the hangman game implemented by
 * the HangMan class.
 * 
 * @author Bryan Franklin
 * 
 */
public class TextUI {

	/**
	 * Display all of the unguessed letters.
	 * 
	 * @param game
	 *            A reference to an active HangMan game instance
	 */
	public static void showLetters(HangMan game) {
		for (char ch = 'a'; ch <= 'z'; ++ch) {
			if (game.letterAvailable(ch))
				System.out.print(ch + " ");
			else
				System.out.print("_ ");
		}
		System.out.println("");
	}

	/**
	 * Display the current state of the game, including exposed letters in the
	 * word, current player scores, which player is currently taking a turn, and
	 * how many incorrect guesses have been made.
	 * 
	 * @param game
	 *            A reference to an active HangMan game instance
	 */
	public static void displayGame(HangMan game) {
		String[] bodyPart = { "Head", "Body", "Left Arm", "Right Arm",
				"Left Leg", "Right Leg" };
		for (int i = 0; i < game.getNumPlayers(); ++i) {
			System.out.println("Player " + (i + 1) + ": "
					+ game.getPlayerScore(i));
		}

		System.out.println("\nFailures: " + game.getFailures());
		System.out.println("Body parts to show: ");
		for (int i = 0; i < game.getFailures(); ++i)
			System.out.println("\t" + bodyPart[i]);
		System.out.println("Word: " + game.getExposedLetters());

		// check to see if the game is still going
		if (game.gameActive()) {
			showLetters(game);
			System.out.println("Player " + game.currentPlayer() + "'s turn");
		}

		// check for victory
		if (game.gameWon()) {
			System.out.println("WINNER!!!!");
			System.out.println("The word was: " + game.getExposedLetters());

			System.out.println("\nPlayer " + game.currentPlayer() + " won!");
		}

		// check for lost game
		if (game.gameLost()) {
			System.out.println("GAME OVER!");
			System.out.println("The word was \"" + game.getExposedLetters()
					+ "\"");
		}

	}

	/**
	 * Get player input from a Scanner object.
	 * 
	 * @param source
	 *            a Scanner that will contain user input
	 * @return a single letter that is the player's guess
	 */
	public static char getLetter(Scanner source) {
		System.out.print("\nEnter a letter: ");
		String guess = source.nextLine();
		if (guess.length() > 1) {
			System.err.println("you can only guess one letter at a time!");
		}
		char ch = guess.charAt(0);

		return ch;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// create a HangMan game instance using the up-goer-5.txt dictionary
		HangMan game = new HangMan("up-goer-5.txt");

		// create a scanner that will read keyboard input
		Scanner keyboard = new Scanner(System.in);

		while (true) {
			// pick a new word
			game.changeWord();

			// display initial game state
			displayGame(game);

			while (game.gameActive()) {

				// get a letter from a player
				char ch = getLetter(keyboard);

				// send the letter to the game
				boolean correct = game.makeGuess(ch);
				if (correct) {
					System.out.println("Correct, there is a " + ch + "!");
				} else {
					System.out.println("Sorry, no " + ch + "!");
				}

				// display updated game state
				displayGame(game);
			}

			System.out.println("Hit enter to start a new word.");
			keyboard.nextLine();

			/* modify word length */
			if (Math.random() < 0.5) {
				game.setWordLength(game.getWordLength() - 1);
			} else {
				game.setWordLength(game.getWordLength() + 1);
			}

		}
	}

}
