package gui;

import hangMan.HangMan;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <Description of this class goes here>
 * 
 * @author Bryan Franklin
 * @author Spencer Rudnick
 * 
 */
public class HangmanUI extends JFrame {
	private static final long serialVersionUID = -6215774992938009947L;
	HangMan game;
	Controller ctrl;

	public HangmanUI() {
		String dictionaryFile = "up-goer-5.txt";
		ctrl = new Controller(dictionaryFile);
		game = ctrl.getGame();
	}

	public void init() {

		setSize(640, 480);
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 0;
		c.fill = GridBagConstraints.BOTH;

		// players config stuff
		JLabel players = new JLabel("Players");
		players.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(players, c);

		// start row of single width buttons
		c.gridwidth = 1;
		c.gridy = 1;

		JButton playersDown = new JButton("-");
		c.gridx = 0;
		pane.add(playersDown, c);

		JLabel numPlayers = new JLabel("" + game.getNumPlayers());
		c.gridx = 1;
		pane.add(numPlayers, c);
		ctrl.setPlayersLabel(numPlayers);

		JButton playersUp = new JButton("+");
		c.gridx = 2;
		pane.add(playersUp, c);
		ctrl.setPlayerButtons(playersDown, playersUp);

		// pass
		JButton pass = new JButton("Pass");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		pane.add(pass, c);
		ctrl.setPassButton(pass);

		// create score label
		String scoreLabel = "";
		for (int i = 0; i < game.getNumPlayers(); i += 1){
			scoreLabel += "Player " + (i + 1) + ": " + game.getPlayerScore(i) + "<br>";
		}
		JLabel scores = new JLabel("<html>" + scoreLabel + "</html>");
		scores.setHorizontalAlignment(SwingConstants.LEFT);
		scores.setVerticalAlignment(SwingConstants.TOP);
		c.gridwidth = 3;
		c.gridheight = 1;
		c.gridy = 3;
		c.gridx = 0;
		c.weighty = 1; // make it fill extra space
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pane.add(scores, c);
		ctrl.setScoreLabel(scores);
		c.weighty = 0;
		
		// create give up button
		JButton giveUp = new JButton("Give Up");
		c.gridwidth = 3;
		c.gridheight = 1;
		c.gridy = 4;
		c.gridx = 0;
		pane.add(giveUp, c);
		ctrl.setGiveUpButton(giveUp);
		
		// create word length label and buttons
		JLabel wordLength = new JLabel("Word Length");
		wordLength.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridy = 5;
		pane.add(wordLength, c);
		
		JButton lengthDown = new JButton("-");
		c.gridy = 6;
		c.gridx = 0;
		c.gridwidth = 1;
		pane.add(lengthDown, c);

		JLabel wordLengthLabel = new JLabel("" + game.getWordLength());
		c.gridx = 1;
		pane.add(wordLengthLabel, c);
		ctrl.setWordLengthLabel(wordLengthLabel);

		JButton lengthUp = new JButton("+");
		c.gridx = 2;
		pane.add(lengthUp, c);
		ctrl.setWordLengthButtons(lengthDown, lengthUp);

		// create place to draw game state
		JPanel drawPanel = new HangmanPanel(game);
		drawPanel.setBackground(Color.LIGHT_GRAY);
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 1;
		c.gridheight = 7;
		c.fill = GridBagConstraints.BOTH;
		this.addKeyListener(ctrl);
		pane.add(drawPanel, c);
		ctrl.setGamePanel(drawPanel);

		// start game
		game.changeWord();

		// make sure UI is updated
		ctrl.updateAll();
	}

	public static void main(String[] args) {
		HangmanUI app = new HangmanUI();
		app.init();
		app.setPreferredSize(new Dimension(640, 480));
		app.pack();
		app.setVisible(true);
	}

}
