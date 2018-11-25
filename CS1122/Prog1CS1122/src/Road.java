/**
 * This class defines a Road, an object with a start, an end, and a distance.
 * 
 * @author Spencer Rudnick
 */
public class Road {
	
	private City from;
	private City to;
	private double distance;

	/**
	 * Road constructor that takes two ints and a double.
	 * 
	 * @param start			The index of the starting city.
	 * @param end			The index of the ending city.
	 * @param distance		The distance of the road.
	 */
	public Road(City from, City to, double distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	/**
	 * @return The index of the starting city.
	 */
	public City getStart() {
		return from;
	}

	/**
	 * @return The index of the ending city.
	 */
	public City getEnd() {
		return to;
	}

	/**
	 * @return The distance of the road.
	 */
	public double getDistance() {
		return distance;
	}
	
	public Road getThis(){
		return this;
	}

}
