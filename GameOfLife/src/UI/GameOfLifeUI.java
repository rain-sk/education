package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GameOfLifeUI class starts the Game of Life program and defines its visual constraints
 * 
 * @author Spencer Rudnick
 */
public class GameOfLifeUI extends JFrame{
	private static final long serialVersionUID = -6215774992938009947L;

	// the Controller which coordinates user interaction with the game state
	Controller control;


	/**
	 * GameOfLifeUI constructor instantiates a new Controller
	 */
	public GameOfLifeUI(){
		control = new Controller();
	} // end of GameOfLifeUI constructor


	/**
	 * init method initializes the window where the application will be shown
	 */
	public void init(){
		setSize(640,480);
		Container pane = getContentPane();

		JPanel panel = new GameOfLifePanel(control.getGame());
		panel.setBackground(Color.BLACK);
		panel.setSize(640,480);
		pane.add(panel);
		control.setGamePanel(panel);
	} // end of init method


	/**
	 * main method makes any calls necessary to start the program properly
	 * 
	 * @param args
	 * 				- stupid thing
	 */
	public static void main(String[] args){
		GameOfLifeUI app = new GameOfLifeUI();
		app.init();
		app.setPreferredSize(new Dimension(640, 480));
		app.pack();
		app.setVisible(true);
	} // end of main method

} // end of GameOfLifeUI class