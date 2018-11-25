package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import normalHangman.HangMan;
import evilHangman.EvilHangMan;

public class Controller implements ActionListener, MouseListener, KeyListener {
	private JLabel scoreLabel;
	private JLabel playersLabel;
	private JLabel lengthLabel;
	private JPanel gamePanel;

	private JButton playerUp;
	private JButton playerDown;
	private JButton lengthUp;
	private JButton lengthDown;
	private JButton giveUp;
	private JButton pass;

	private HangMan game;

	public Controller(String filename) {
		// game = new HangMan(filename);
		game = new EvilHangMan(filename);
	}

	public void setScoreLabel(JLabel labelRef) {
		scoreLabel = labelRef;
	}

	public void setPlayersLabel(JLabel labelRef) {
		playersLabel = labelRef;
	}

	public void setPlayerButtons(JButton down, JButton up) {
		down.setFocusable(false);
		up.setFocusable(false);
		playerDown = down;
		playerUp = up;
		playerDown.addActionListener(this);
		playerUp.addActionListener(this);
	}

	public void setLengthLabel(JLabel labelRef) {
		lengthLabel = labelRef;
	}

	public void setLengthButtons(JButton down, JButton up) {
		down.setFocusable(false);
		up.setFocusable(false);
		lengthDown = down;
		lengthUp = up;
		lengthDown.addActionListener(this);
		lengthUp.addActionListener(this);
	}

	public void setGamePanel(JPanel panelRef) {
		gamePanel = panelRef;
		gamePanel.addMouseListener(this);
	}

	public void setGiveUpButton(JButton buttonRef) {
		buttonRef.setFocusable(false);
		giveUp = buttonRef;
		giveUp.addActionListener(this);
	}

	public void setPassButton(JButton buttonRef) {
		buttonRef.setFocusable(false);
		pass = buttonRef;
		pass.addActionListener(this);
	}

	public HangMan getGame() {
		return game;
	}

	public void updateAll() {
		if (scoreLabel != null) {
			String str = "<html>";
			for (int i = 0; i < game.getNumPlayers(); ++i) {
				str += "Player " + (i + 1) + ": <b>" + game.getPlayerScore(i)
						+ "</b><br>";
			}
			str += "</html>";
			scoreLabel.setText(str);
		}
		if (playersLabel != null)
			playersLabel.setText("" + game.getNumPlayers());
		if (lengthLabel != null)
			lengthLabel.setText("" + game.getWordLength());
		if (gamePanel != null)
			gamePanel.getParent().repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playerDown) {
			game.setNumPlayers(game.getNumPlayers() - 1);
		}
		if (e.getSource() == playerUp) {
			game.setNumPlayers(game.getNumPlayers() + 1);
		}
		if (e.getSource() == lengthDown) {
			game.setWordLength(game.getWordLength() - 1);
			game.newWord();
		}
		if (e.getSource() == lengthUp) {
			game.setWordLength(game.getWordLength() + 1);
			game.newWord();
		}
		if (e.getSource() == pass) {
			game.nextPlayer();
		}
		if (e.getSource() == giveUp) {
			game.giveUp();
		}
		updateAll();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!game.gameActive()) {
			// start a new game
			game.newWord();
		} else {
			// check each letter
			int x = e.getX();
			int y = e.getY();
			int groundLine = gamePanel.getHeight() - 50;
			for (char ch = 'A'; ch <= 'Z'; ++ch) {
				if (x > ((ch - 'A') * 15 + 50) && x < (ch - 'A') * 15 + 60
						&& y > groundLine + 15 && y < groundLine + 30) {
					game.guessLetter(ch);
				}
			}
		}

		// cause an immediate redraw
		updateAll();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (!game.gameActive()) {
			game.newWord();
		} else {
			game.guessLetter(e.getKeyChar());
		}

		// cause an immediate redraw
		updateAll();
	}

}
