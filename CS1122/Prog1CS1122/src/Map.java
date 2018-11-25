/**
 * This class defines a Map, which a navigator uses to store information about roads.
 * 
 * @author Spencer Rudnick
 */
public class Map extends Navigator {

	private Road[] _roads;  // All the roads in the Map
	
	/**
	 * Map constructor with no parameters.
	 */
	public Map(){
		_roads = new Road[0];
	}
	
	/**
	 * This method adds a Road to the Map.
	 * 
	 * @param newRoad		The Road to be added.
	 */
	public void addRoad(Road newRoad){
		Road[] newRoads = new Road[_roads.length + 1];
		for (int i = 0; i < newRoads.length; i += 1){
			if (i == _roads.length){
				newRoads[i] = newRoad;
			}
			else {
				newRoads[i] = _roads[i];
			}
		}
		_roads = newRoads;
	}
	
	/**
	 * This method checks whether a Road exists in the Map.
	 * 
	 * @param start		The index of the city at the start of the Road.
	 * @param end		The index of the city at the end of the Road.
	 * @return			The distance of the Road being looked for, or -1 if the Road does not exist.
	 */
	public double roadExists(int start, int end){
		for (int i = 0; i < _roads.length; i += 1){
			if (_roads[i].getStart() == start && _roads[i].getEnd() == end){
				return _roads[i].getDistance();
			}
		}
		return -1;
	}
	
	/**
	 * This method returns the number of Roads in the Map.
	 * 
	 * @return The number of Roads in the Map.
	 */
	public int getNumRoads(){
		return _roads.length;
	}
	
	/**
	 * This method returns a Road object.
	 * 
	 * @param index		The index of the Road to be returned.
	 * @return			The Road object at _roads[index]
	 */
	public Road getRoad(int index){
		return _roads[index];
	}
	
}
