package UI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import game.GameOfLife;

/**
 * Controller class coordinates user interaction with the state of the GameOfLife class
 * 
 * @author Spencer Rudnick
 */
public class Controller implements MouseListener{

	private GameOfLife game;
	private JPanel gamePanel;
	private AnimationWorker worker;


	/**
	 * Controller constructor instantiates a new GameOfLife
	 */
	public Controller(){
		game = new GameOfLife();
	} // end of Controller constructor


	/**
	 * setGamePanel method stores a reference to the GameOfLife panel
	 * 
	 * @param panelRef
	 * 					- a reference to the GameOfLife panel
	 */
	public void setGamePanel(JPanel panelRef){
		gamePanel = panelRef;
		gamePanel.setFocusable(false);
		gamePanel.addMouseListener(this);
	} // end of setGamePanel method


	/**
	 * getGame method used for getting a reference to the Controller's game
	 * 
	 * @return the GameOfLife this Controller is using
	 */
	public GameOfLife getGame(){
		return game;
	} // end of getGame method


	/**
	 * update method forces the game panel to redraw
	 */
	public void update(){
		if (gamePanel != null){
			gamePanel.invalidate();
			((JPanel) gamePanel.getParent()).paintImmediately(0, 0, 640, 480);
		}
	} // end of update method


	public void start(){
		game.start();
		worker = new AnimationWorker();
		try {
			worker.execute();
		} catch (Exception e1) {
		}
	}


	@Override
	/**
	 * mouseClicked method receives a MouseEvent and does something
	 * based on the x and y coordinates of the click
	 */
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// toggle the clicked cell
		if (y < 400){
			game.toggle(x,y);
		}

		// crazy logic to determine which button was pressed
		if (412 < y && y < 442){
			if (!game.isActive()){
				if (20 < x && x < 110)
					game.next();
				if (120 < x && x < 210)
					start();
			}
			else if (220 < x && x < 310){
				game.stop();
				worker.cancel(true);
			}
			if (525 < x && x < 615)
				game.clear();
		}

		update();

	} // end of mouseClicked method

	/**
	 * unused method
	 */
	public void mouseEntered(MouseEvent arg0){}

	/**
	 * unused method
	 */
	public void mouseExited(MouseEvent arg0){}

	/**
	 * unused method
	 */
	public void mousePressed(MouseEvent e){}

	/**
	 * unused method
	 */
	public void mouseReleased(MouseEvent arg0){}


	/**
	 * AnimationWorker class is used to run the animation loop in the background
	 * 
	 * @author Spencer Rudnick
	 */
	class AnimationWorker extends SwingWorker<Integer, Integer>{

		/**
		 * doInBackground method defines what the worker does when it is told to execute
		 */
		protected Integer doInBackground() throws Exception{
			while(game.isActive() && game.getAlive().size() > 0){
				game.next();
				update();
				try {
					Thread.sleep(100);
				} catch (InterruptedException iE) {
				}
			}
			return game.getGeneration();
		} // end of doInBackground method

	} // end of AnimationWorker class

} // end of Controller class