package game;

import java.util.ArrayList;

/**
 * GameOfLife class defines the behavior and state of the Game Of Life
 * 
 * @author Spencer Rudnick
 */
public class GameOfLife {

	// an actual representation of the state of the universe
	private boolean[][] universe;

	// a list containing all the coordinates of living cells
	private ArrayList<Coordinate> alive;

	// whether or not the game is active
	private boolean active = false;

	// the generation of the game
	private int generation = 0;


	/**
	 * GameOfLife constructor fills the universe with dead cells
	 */
	public GameOfLife(){

		// instantiate the universe
		universe = new boolean[64][40];

		// instantiate the alive list
		alive = new ArrayList<Coordinate>();

		// fill the universe with dead cells
		for (int i = 0; i < universe.length; i += 1){
			for (int j = 0; j < universe[0].length; j += 1){
				universe[i][j] = false;
			}
		}
	} // end of GameOfLife constructor


	/**
	 * toggle method toggles the alive status of a cell at the coordinates given to it
	 * 
	 * @param xCoord
	 * 					- the x-coordinate of the cell to be toggled
	 * @param yCoord
	 * 					- the y-coordinate of the cell to be toggled
	 */
	public void toggle(int xCoord, int yCoord){
		// return if the game is active
		if (active)
			return;

		// trim the input
		int x = (xCoord - (xCoord % 10))/10;
		int y = (yCoord - (yCoord % 10))/10;

		// make the cell at the given coordinates alive
		if (!universe[x][y]){
			universe[x][y] = true;
			alive.add(new Coordinate(x,y));
		}
		// or make it dead
		else{
			universe[x][y] = false;
			kill(x,y);
		}
	} // end of toggle method


	/**
	 * kill method removes the cell with the given coordinates from alive
	 * 
	 * @param xCoord
	 * 					- the x-coordinate of the cell to be killed
	 * @param yCoord
	 * 					- the y-coordinate of the cell to be killed
	 */
	public void kill(int xCoord, int yCoord){
		for (int i = 0; i < alive.size(); i += 1){
			if (alive.get(i).getX() == xCoord && alive.get(i).getY() == yCoord){
				alive.remove(i);
				return;
			}
		}
	} // end of remove method


	/**
	 * next method performs the logic for the next update of the universe
	 */
	public void next(){

		// check each cell for the number of living cells adjacent to it
		for (int x = 0; x < 64; x += 1){
			for (int y = 0; y < 40; y += 1){
				// if the cell is alive
				if (isAlive(x,y)){
					// and the cell has fewer than 2 or greater than 3 adjacent living cells
					if (adjacencyCount(x,y) < 2 || 3 < adjacencyCount(x,y)){
						// kill the cell
						kill(x,y);
					}
				}
				// if the cell is dead and has 3 adjacent living cells
				else if (adjacencyCount(x,y) == 3){
					// add the cell to alive
					alive.add(new Coordinate(x,y));
				}
			}
		}

		generation += 1;

		updateUniverse();
	} // end of next method


	/**
	 * start method sets active to true
	 */
	public void start(){
		active = true;
	} // end of start method


	/**
	 * stop method sets active to false
	 */
	public void stop(){
		active = false;
	} // end of stop method


	/**
	 * isActive method is used to check whether the game is active
	 * @return whether or not the game is active
	 */
	public boolean isActive(){
		return active;
	} // end of isActive method


	/**
	 * getAlive method returns the list of living cells
	 * 
	 * @return alive
	 */
	public ArrayList<Coordinate> getAlive(){
		return alive;
	} // end of getAlive method


	/**
	 * @return the generation of cells that are currently alive
	 */
	public int getGeneration(){
		return generation;
	} // end of getGeneration method


	/**
	 * clear method resets the game state
	 */
	public void clear(){
		generation = 0;
		active = false;
		alive = new ArrayList<Coordinate>();
		updateUniverse();
	} // end of clear method

	/**
	 * adjacencyCount method takes two parameters (x and y positions)
	 * and counts the number of living cells adjacent to the one passed
	 * 
	 * @param xPos
	 * 					- the x-position of the cell
	 * @param yPos
	 * 					- the y-position of the cell
	 * 
	 * @return the number of living cells adjacent to the passed cell
	 */
	private int adjacencyCount(int xPos, int yPos){
		int count = 0;
		for (int x = xPos - 1; x <= xPos + 1; x += 1){
			for (int y = yPos - 1; y <= yPos + 1; y += 1){
				if (isAlive(x,y)){
					count += 1;
				}
			}
		}
		if (isAlive(xPos,yPos))
			count -= 1;
		return count;
	} // end of adjacencyCount method


	/**
	 * updateUniverse reads alive and updates the state of the universe accordingly
	 */
	private void updateUniverse(){
		// empty the universe
		for (int i = 0; i < universe.length; i += 1){
			for (int j = 0; j < universe[0].length; j += 1){
				universe[i][j] = false;
			}
		}

		for (Coordinate cell : alive){
			universe[cell.getX()][cell.getY()] = true;
		}
	} // end of updateUniverse method


	/**
	 * isAlive method checks to see if the given cell is alive
	 * 
	 * @param x
	 * 				- the x-coordinate of the cell
	 * @param y
	 * 				- the y-coordinate of the cell
	 * 
	 * @return whether or not the cell is alive
	 */
	private boolean isAlive(int x, int y){
		// logic to allow for wrapping around the edges of the universe
		if (x < 0)
			x += 64;
		if (y < 0)
			y += 40;
		if (x > 63)
			x -= 64;
		if (y > 39)
			y -= 40;
		return universe[x][y];
	} // end of isAlive method


	/**
	 * Coordinate class defines a set of y and x coordinates in the universe
	 * 
	 * @author Spencer Rudnick
	 *
	 */
	public class Coordinate{

		// the x and y coordinates
		private int x;
		private int y;

		/**
		 * constructor for the Coordinate object, takes two parameters
		 * 
		 * @param xIn
		 * 				- the x coordinate of the cell
		 * @param yIn
		 * 				- the y coordinate of the cell
		 */
		public Coordinate(int xIn, int yIn){
			x = xIn;
			y = yIn;
		} // end of Coordinate constructor

		/**
		 * @return the y coordinate of the cell
		 */
		public int getY(){
			return y;
		} // end of getY method

		/**
		 * @return the x coordinate of the cell
		 */
		public int getX(){
			return x;
		} // end of getX method

		public String toString(){
			return "[" + x + "," + y + "]";
		}

	} // end of Coordinate class

} // end of GameOfLife class