package UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.GameOfLife.Coordinate;
import game.GameOfLife;

/**
 * GameOfLifePanel class updates the graphical representation of
 * the GameOfLife based on the state of the Game itself
 * 
 * @author Spencer Rudnick
 */
public class GameOfLifePanel extends JPanel{
	private static final long serialVersionUID = 7734877696044080629L;

	// the same GameOfLife the Controller is using
	private GameOfLife game;


	/**
	 * GameOfLifePanel constructor instantiates the GameOfLifePanel
	 * 
	 * @param gameRef
	 * 					- a reference to the game object the Controller is using
	 */
	public GameOfLifePanel(GameOfLife gameRef){
		game = gameRef;
	} // end of GameOfLifePanel constructor


	/**
	 * paintComponent method paints to the JPanel
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		// draw buttons
		drawStepButton(g);
		drawStartButton(g);
		drawStopButton(g);
		drawClearButton(g);

		// update the text
		updateText(g);

		// update the graphical representation of the universe
		updateGrid(g);
		drawGridLines(g);

	} // end of paintComponent method


	/**
	 * drawStepButton method draws the step button
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void drawStepButton(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(20, 412, 90, 30);
		if (!game.isActive()){
			g.setColor(Color.WHITE);
			g.fillRect(22, 414, 86, 26);
		}
		g.setColor(Color.BLACK);
		g.drawString("Step", 50, 431);
	} // end of drawStepButton method


	/**
	 * drawStartButton method draws the start button
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void drawStartButton(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(120, 412, 90, 30);
		if (!game.isActive()){
			g.setColor(Color.WHITE);
			g.fillRect(122, 414, 86, 26);
		}
		g.setColor(Color.BLACK);
		g.drawString("Start", 150, 431);
	} // end of drawStartButton method


	/**
	 * drawStopButton method draws the stop button
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void drawStopButton(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(220, 412, 90, 30);
		if (game.isActive()){
			g.setColor(Color.WHITE);
			g.fillRect(222, 414, 86, 26);
		}
		g.setColor(Color.BLACK);
		g.drawString("Stop", 250, 431);
	} // end of drawStopButton method


	/**
	 * updateText method updates the text indicating the state of the universe
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void updateText(Graphics g){
		g.setColor(Color.WHITE);
		g.drawString("Live Cells: " + game.getAlive().size(), 320, 421);
		g.drawString("Generation: " + game.getGeneration(), 320, 440);
	} // end of updateText method


	/**
	 * drawClearButton method draws the clear button
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void drawClearButton(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(525, 412, 90, 30);
		g.setColor(Color.WHITE);
		g.fillRect(527, 414, 86, 26);
		g.setColor(Color.BLACK);
		g.drawString("Clear", 555, 431);
	} // end of drawClearButton method


	/**
	 * updateGrid method draws red squares to represent living cells
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void updateGrid(Graphics g){
		g.setColor(Color.RED);
		for (Coordinate c : game.getAlive()){
			g.fillRect(10 * c.getX(), 10 * c.getY(), 10, 10);
		}
	} // end of updateGrid method


	/**
	 * drawGridLines method draws grid lines over the universe to separate cells
	 * 
	 * @param g
	 * 			- Graphics object used to paint to the JPanel
	 */
	public void drawGridLines(Graphics g){
		g.setColor(Color.GRAY);
		for (int i = 0; i <= 640; i += 10){
			g.drawLine(i, 0, i, 400);
		}
		g.drawLine(639, 0, 639, 400);
		for (int i = 0; i <= 400; i += 10){
			g.drawLine(0, i, 640, i);
		}
	} // end of drawGridLines method

} // end of GameOfLifePanel class