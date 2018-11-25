package gui;

import hangMan.HangMan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 * This class is used to draw the graphical portion of the user interface (i.e.
 * the gallows, letters, current word, etc.)
 * 
 * @author Bryan Franklin
 * @author Spencer Rudnick
 * 
 */
public class HangmanPanel extends JPanel {
	private static final long serialVersionUID = 7734877696044080629L;
	HangMan game;

	public HangmanPanel(HangMan gameRef) {
		game = gameRef;
	}

	/**
	 * paintComponent method is part of the JPanel class and is called to draw
	 * things to the JPanel
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// redraw background
		drawBackground(g);
		
		// update available letters
		updateAlphabet(g);
		
		updateMan(g);
		
		// update player label if the game is still on,
		if (game.gameActive())
			updatePlayer(g);
		else // otherwise, indicate which player is the winner or if the game was lost
			endGame(g);
		
		// update the revealed letters
		updateWord(g);

	} // end of paintComponent method
	
	/**
	 * drawBackground method is called to redraw the basic
	 * visual characteristics of the game
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	public void drawBackground(Graphics g) {
		
		// draw background
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 600, 600);
		
		// draw letter boxes
		g.setColor(Color.GREEN);
		g.fillRect(0, 400, 485, 60);
		g.fillRect(120, 0, 240, 30);
		
		// draw gallows
		g.setColor(Color.RED);
		g.fillRect(40, 360, 400, 50);
		g.fillRect(80, 60, 30, 300);
		g.fillRect(80, 40, 200, 20);
		
		// draw noose
		g.setColor(Color.YELLOW);
		g.fillRect(235, 60, 10, 40);
		g.drawOval(220, 100, 40, 40);
		
	} // end of drawBackground method

	/**
	 * updateAlphabet method redraws the available letters and inserts
	 * the '_' character in place of used letters
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	private void updateAlphabet(Graphics g) {
		
		g.setColor(Color.BLACK);
		for (char c = 'A'; c <= 'Z'; c += 1){
			if (game.letterAvailable(c)){
				g.drawString("" + c, 75 + 13 * ((int) c - 65), 437);
			}
			else{
				g.drawString("_", 75 + 13 * ((int) c - 65), 437);
			}
		}
	} // end of updateAlphabet method

	/**
	 * updatePlayer method updates label which indicates the current player
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	private void updatePlayer(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawString("Player " + (game.currentPlayer() + 1) + "'s Turn", 195, 20);
		
	} // end of updatePlayer method
	
	/**
	 * updateMan method draws the parts of the hanging man
	 * based on how many times an incorrect letter has been guessed
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	public void updateMan(Graphics g){
		g.setColor(Color.PINK);
		if (game.getFailures() > 0){
			g.fillOval(215, 85, 50, 60);
		}
		if (game.getFailures() > 1){
			g.fillRect(235, 140, 10, 100);
		}
		if (game.getFailures() > 2){
			int[] leftArmX = new int[4];
			int[] leftArmY = new int[4];
			leftArmX[0] = 235;
			leftArmY[0] = 180;
			leftArmX[1] = 190;
			leftArmY[1] = 160;
			leftArmX[2] = 195;
			leftArmY[2] = 155;
			leftArmX[3] = 235;
			leftArmY[3] = 175;
			g.fillPolygon(leftArmX, leftArmY, 4);
		}
		if (game.getFailures() > 3){
			int[] rightArmX = new int[4];
			int[] rightArmY = new int[4];
			rightArmX[0] = 245;
			rightArmY[0] = 180;
			rightArmX[1] = 290;
			rightArmY[1] = 160;
			rightArmX[2] = 285;
			rightArmY[2] = 155;
			rightArmX[3] = 245;
			rightArmY[3] = 175;
			g.fillPolygon(rightArmX, rightArmY, 4);
		}
		if (game.getFailures() > 4){
			int[] leftLegX = new int[4];
			int[] leftLegY = new int[4];
			leftLegX[0] = 235;
			leftLegY[0] = 240;
			leftLegX[1] = 190;
			leftLegY[1] = 300;
			leftLegX[2] = 195;
			leftLegY[2] = 305;
			leftLegX[3] = 245;
			leftLegY[3] = 240;
			g.fillPolygon(leftLegX, leftLegY, 4);
		}
		if (game.getFailures() > 5){
			int[] rightLegX = new int[4];
			int[] rightLegY = new int[4];
			rightLegX[0] = 245;
			rightLegY[0] = 240;
			rightLegX[1] = 290;
			rightLegY[1] = 300;
			rightLegX[2] = 285;
			rightLegY[2] = 305;
			rightLegX[3] = 235;
			rightLegY[3] = 240;
			g.fillPolygon(rightLegX, rightLegY, 4);
		}
	} // end of updateMan method
	
	/**
	 * updateWord method updates the visualization of the revealed letters
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	private void updateWord(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.PLAIN, 33));
		g.drawString(game.getExposedLetters(), 130, 395);
	} // end of updateWord method
	
	/**
	 * endGame method updates the JPanel to reflect the game state
	 * and prompt the user to click for a new word
	 * 
	 * @param g
	 *            - graphics object use for drawing to the JPanel
	 */
	private void endGame(Graphics g) {
		
		// announce which player won the round
		if (game.gameWon()){
			g.setColor(Color.BLACK);
			g.drawString("Player " + (game.currentPlayer() + 1) + " Wins!", 195, 20);
		}
		else{ // or tell the player they are a loser
			g.setColor(Color.BLACK);
			g.drawString("You Lose!!!", 205, 20);
		}
		
		// prompt user to click for a new word
		g.setColor(Color.WHITE);
		g.fillRect(170, 35, 140, 30);
		g.setColor(Color.BLACK);
		g.drawString("Click for new word.", 180, 55);
		
	} // end of endGame method
}
