package gui;

import hangMan.HangMan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class serves as a gate keeper between the AppletUI and the actual
 * HangMan game
 * 
 * @author Bryan Franklin
 * @author Spencer Rudnick
 * 
 */
public class Controller implements ActionListener, MouseListener, KeyListener {
	
	// labels
	private JLabel scoreLabel;
	private JLabel playersLabel;
	private JLabel wordLengthLabel;
	
	// game panel
	private JPanel gamePanel;

	// buttons
	private JButton playerUp;
	private JButton playerDown;
	private JButton pass;
	private JButton giveUp;
	private JButton lengthDown;
	private JButton lengthUp;

	// model
	private HangMan game;

	public Controller(String filename) {
		game = new HangMan(filename);
	}

	public void setScoreLabel(JLabel labelRef) {
		scoreLabel = labelRef;
	}

	public void setPlayersLabel(JLabel labelRef) {
		playersLabel = labelRef;
	}

	public void setPlayerButtons(JButton down, JButton up) {
		playerDown = down;
		playerUp = up;
		playerDown.setFocusable(false);
		playerUp.setFocusable(false);
		playerDown.addActionListener(this);
		playerUp.addActionListener(this);
	}

	public void setGamePanel(JPanel panelRef) {
		gamePanel = panelRef;
		gamePanel.addMouseListener(this);
	}

	public void setPassButton(JButton buttonRef) {
		pass = buttonRef;
		pass.setFocusable(false);
		pass.addActionListener(this);
	}
	
	public void setGiveUpButton(JButton buttonRef) {
		giveUp = buttonRef;
		giveUp.setFocusable(false);
		giveUp.addActionListener(this);
	}
	
	public void setWordLengthLabel(JLabel labelRef) {
		wordLengthLabel = labelRef;
	}
	
	public void setWordLengthButtons(JButton down, JButton up) {
		lengthDown = down;
		lengthUp = up;
		lengthDown.setFocusable(false);
		lengthUp.setFocusable(false);
		lengthDown.addActionListener(this);
		lengthUp.addActionListener(this);
	}

	public HangMan getGame() {
		return game;
	}

	public void updateAll() {
		if (playersLabel != null)
			playersLabel.setText("" + game.getNumPlayers());
		if (wordLengthLabel != null)
			wordLengthLabel.setText("" + game.getWordLength());
		if (gamePanel != null)
			gamePanel.getParent().repaint();
		if (scoreLabel != null)
			updateScores();
	}
	
	/**
	 * Update the scores label
	 */
	public void updateScores() {
		String newLabel = "";
		for (int i = 0; i < game.getNumPlayers(); i += 1){
			newLabel += "Player " + (i + 1) + ": " + game.getPlayerScore(i) + "<br>";
		}
		scoreLabel.setText("<html>" + newLabel + "</html>");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playerDown) {
			game.setNumPlayers(game.getNumPlayers() - 1);
		}
		if (e.getSource() == playerUp) {
			game.setNumPlayers(game.getNumPlayers() + 1);
		}
		if (e.getSource() == pass) {
			game.nextPlayer();
		}
		if (e.getSource() == giveUp) {
			game.giveUp();
		}
		if (e.getSource() == lengthDown) {
			int newLength = game.getWordLength() - 1;
			game.setWordLength(newLength);
			game.changeWord();
		}
		if (e.getSource() == lengthUp) {
			int newLength = game.getWordLength() + 1;
			game.setWordLength(newLength);
			game.changeWord();
		}
		updateAll();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// check each letter
		int x = e.getX();
		int y = e.getY();
		System.out.println("Click at position " + x + "," + y);
		
		if (!game.gameActive() && (x >= 170 && x <= 310 && y >= 35 && y <= 65)){
			game.changeWord();
		}
		else if ( y < 440 && y > 425){
			if (x > 74 && x <= 87) game.makeGuess('A');
			if (x > 87 && x <= 100) game.makeGuess('B');
			if (x > 100 && x <= 113) game.makeGuess('C');
			if (x > 113 && x <= 126) game.makeGuess('D');
			if (x > 126 && x <= 139) game.makeGuess('E');
			if (x > 139 && x <= 152) game.makeGuess('F');
			if (x > 152 && x <= 165) game.makeGuess('G');
			if (x > 165 && x <= 178) game.makeGuess('H');
			if (x > 178 && x <= 191) game.makeGuess('I');
			if (x > 191 && x <= 204) game.makeGuess('J');
			if (x > 204 && x <= 217) game.makeGuess('K');
			if (x > 217 && x <= 230) game.makeGuess('L');
			if (x > 230 && x <= 243) game.makeGuess('M');
			if (x > 243 && x <= 256) game.makeGuess('N');
			if (x > 256 && x <= 269) game.makeGuess('O');
			if (x > 269 && x <= 282) game.makeGuess('P');
			if (x > 282 && x <= 295) game.makeGuess('Q');
			if (x > 295 && x <= 308) game.makeGuess('R');
			if (x > 308 && x <= 321) game.makeGuess('S');
			if (x > 321 && x <= 334) game.makeGuess('T');
			if (x > 334 && x <= 347) game.makeGuess('U');
			if (x > 347 && x <= 360) game.makeGuess('V');
			if (x > 360 && x <= 373) game.makeGuess('W');
			if (x > 373 && x <= 386) game.makeGuess('X');
			if (x > 386 && x <= 399) game.makeGuess('Y');
			if (x > 399 && x <= 412) game.makeGuess('Z');
		}

		// cause an immediate redraw
		updateAll();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		// make a guess with the letter typed
		game.makeGuess(e.getKeyChar());
		
		// cause an immediate redraw
		updateAll();
		
	}

}
