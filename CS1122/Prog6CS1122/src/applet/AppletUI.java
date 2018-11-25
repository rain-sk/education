package applet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import normalHangman.HangMan;


/**
 * 
 * @author Bryan Franklin
 * 
 */
public class AppletUI extends JApplet {
	private static final long serialVersionUID = -6215774992938009947L;
	HangMan game;
	Controller ctrl;

	public AppletUI() {
		String dictionaryFile = "../up-goer-5.txt";
		// Uncomment this next line to use a very simple dictionary
		// dictionaryFile = "../example.txt";

		ctrl = new Controller(dictionaryFile);
		game = ctrl.getGame();
		game.setWordLength(4);
	}

	public void init() {

		setSize(640, 480);
		setMinimumSize(new Dimension(400, 300));

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
		c.anchor = GridBagConstraints.CENTER;
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

		// score
		JLabel scores = new JLabel("Scores");
		scores.setHorizontalAlignment(SwingConstants.LEFT);
		scores.setVerticalAlignment(SwingConstants.TOP);
		c.gridwidth = 3;
		c.gridheight = 1;
		c.gridy = 3;
		c.gridx = 0;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pane.add(scores, c);
		ctrl.setScoreLabel(scores);
		c.weighty = 0;
		c.ipady = 0;

		// give up
		JButton giveUp = new JButton("Give Up");
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		pane.add(giveUp, c);
		ctrl.setGiveUpButton(giveUp);

		// start row of single width buttons
		c.gridwidth = 1;
		c.gridy = 5;

		// word config stuff
		JLabel length = new JLabel("Word Length");
		length.setHorizontalAlignment(SwingConstants.CENTER);
		c.ipady = 0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(length, c);

		c.gridy = 6;
		JButton lengthDown = new JButton("-");
		c.gridwidth = 1;
		c.gridx = 0;
		pane.add(lengthDown, c);

		JLabel wordLength = new JLabel(" " + game.getWordLength() + " ");
		c.gridx = 1;
		pane.add(wordLength, c);
		ctrl.setLengthLabel(wordLength);
		JButton lengthUp = new JButton("+");
		c.gridx = 2;
		pane.add(lengthUp, c);
		ctrl.setLengthButtons(lengthDown, lengthUp);

		// place to draw game state
		JPanel drawPanel = new HangmanPanel(game);
		drawPanel.setBackground(Color.LIGHT_GRAY);
		drawPanel.setSize(300, 300);
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 1;
		c.gridheight = 7;
		c.fill = GridBagConstraints.BOTH;
		this.addKeyListener(ctrl);

		pane.add(drawPanel, c);
		ctrl.setGamePanel(drawPanel);

		game.newWord();

		ctrl.updateAll();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
