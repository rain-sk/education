package applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import normalHangman.HangMan;


public class HangmanPanel extends JPanel {
	private static final long serialVersionUID = 7734877696044080629L;
	HangMan game;
	int groundLine = 0;

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

		// draw ground
		groundLine = super.getHeight() - 50;
		g.setColor(Color.green);
		g.fillRect(0, groundLine, super.getWidth(), 50);

		// draw platform
		g.setColor(Color.RED);
		g.fillRect(100, groundLine - 50, 300, 50);
		g.fillRect(125, 50, 25, groundLine - 50);
		g.fillRect(125, 50, 175, 25);

		int failures = game.getFailures();
		g.setColor(Color.yellow);
		g.fillRect(273, 75, 5, 25);
		if (failures == 0) {
			g.drawOval(250, 100, 50, 50);
		}

		// draw body parts
		g.setColor(Color.BLUE);
		switch (failures) {
		default:
		case 6:
			// draw right leg
			g.drawLine(275, 275, 300, 350);
		case 5:
			// draw right leg
			g.drawLine(275, 275, 250, 350);
		case 4:
			// draw right arm
			g.drawLine(275, 200, 225, 175);
		case 3:
			// draw left arm
			g.drawLine(275, 200, 325, 175);
		case 2:
			// draw body
			g.drawLine(275, 160, 275, 275);
		case 1:
			// draw head
			g.fillOval(242, 95, 65, 65);
			break;
		case 0:
			// do nothing
			break;
		}

		// add letters
		g.setColor(Color.black);
		for (char ch = 'A'; ch <= 'Z'; ++ch) {
			if (game.letterAvailable(ch)) {
				g.drawString("" + ch, (ch - 'A') * 15 + 50, groundLine + 30);
			}
		}

		// display victory/defeat conditions
		int msgX = 200;
		int msgY = 20;
		if (game.gameWon()) {
			g.drawString("Player " + (game.currentPlayer() + 1) + " Wins!!!",
					msgX, msgY);
		}
		if (game.gameLost()) {
			g.drawString("You Lose!!!", msgX, msgY);
		}
		if (game.gameActive()) {
			g.drawString("Player " + (game.currentPlayer() + 1) + "'s Turn",
					msgX, msgY);
		} else {
			g.drawString("Click for new word.", msgX - 25, msgY + 20);
		}

		// draw current word
		Font serifFont = new Font("Times", Font.PLAIN, 30);
		g.setFont(serifFont);
		g.setColor(Color.black);
		g.drawString(game.getExposedLetters(), 200, groundLine - 10);

	} // end of paintComponent method

}
