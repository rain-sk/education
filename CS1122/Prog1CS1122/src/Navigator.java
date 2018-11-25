import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class defines a Navigator as being able to store
 * map data and perform simple computations with said data.
 * 
 * @author unascribed
 * @author Spencer Rudnick
 */
public class Navigator {


	private String[] _locations;    // Contains names of locations

	private double[][] _distances;  // Contains distance data

	private Map _map;  			    // Road map


	/**
	 * This method reads location names from the given file and stores the names in an array.
	 * 
	 * @param fname        The name of the file containing locations.
	 * @throws Exception   Could throw an IOException.
	 */
	public void loadLocations(String fname) throws Exception {
		Scanner in = new Scanner(new File(fname));

		_locations = new String[in.nextInt()];


		// Read in location names
		in.nextLine();
		for (int i = 0; i < _locations.length; i += 1){
			_locations[i] = in.nextLine();
		}
		in.close();
	}

	/**
	 * This method returns the array containing the names of all the locations.
	 * 
	 * @return An array containing the names of all the locations.
	 */
	public String[] locationList() {
		return _locations;
	}

	/**
	 * This method reads distance data from the given file and stores it in two arrays for later use.
	 * 
	 * @param fname        The name of the file containing distances
	 * @throws Exception   Could throw an IOException
	 */
	public void loadDistances(String fname) throws Exception {
		Scanner in = new Scanner(new File(fname));

		_distances = new double[_locations.length][_locations.length];
		_map = new Map();


		// Fill the distances array
		for (int i = 0; i < _locations.length; i += 1){
			for (int j = 0; j < _locations.length; j += 1){
				if (i == j){
					_distances[i][j] = 0;
				}
				else {
					_distances[i][j] = Double.MAX_VALUE/2.0;
				}
			}
		}


		// Copy distances.txt into the map
		while (in.hasNext()){
			int start = in.nextInt();
			int end = in.nextInt();
			double distance = in.nextDouble();

			_map.addRoad(new Road(start,end,distance));
		}


		// Copy the map into the distances array
		for (int i = 0; i < _map.getNumRoads(); i += 1){
			_distances[_map.getRoad(i).getStart()][_map.getRoad(i).getEnd()] = _map.getRoad(i).getDistance();
		}
		in.close();
	}

	/**
	 * This method minimizes the distances array.
	 * 
	 * @return The minimized version of the distances array.
	 */
	public double[][] minDistanceArray() {
		for (int k = 0; k < _distances.length; k += 1){
			for (int i = 0; i < _distances.length; i += 1){
				for (int j = 0; j < _distances.length; j += 1){
					if (i != j)
						_distances[i][j] = minDistBetween(i,j);
				}
			}
		}
		return _distances;
	}

	/**
	 * This method returns the number of cities in the Navigator.
	 * 
	 * @return The number of locations in the locations array.
	 */
	public int getNumLocations() {
		return _locations.length;
	}

	/**
	 * This method returns the name of the location at the given index.
	 * 
	 * @param id   The index to lookup in the locations array.
	 * @return     The name of the location at the index given.
	 */
	String getLocationName(int id) {
		return _locations[id];
	}

	/**
	 * This method returns the index of the location with the given name.
	 * 
	 * @param name   The location to find the index of within the locations array.
	 * @return       The index of the given location; -1 if the location is not found.
	 */
	int idOfLocation(String name) {
		for (int i = 0; i < _locations.length; i += 1){
			if (_locations[i].equals(name)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method finds the minimum distance between two locations in the Navigator.
	 * 
	 * @param start    The index of the starting location.
	 * @param end      The index of the ending location.
	 * @return         The minimum distance from start to end.
	 */
	public double minDistBetween(int start, int end) {
		if (start == end)
			return 0;

		double shortestDistance = Double.MAX_VALUE;
		for (int k = 0; k < _distances.length; k += 1){
			if (k != start && k != end){
				shortestDistance = Math.min(shortestDistance, Math.min(_distances[start][end], _distances[start][k] + _distances[k][end]));
			}
		}
		return shortestDistance;
	}

	/**
	 * This method processes an input file and outputs something to an output file.
	 * 
	 * @param input        The name of a file to read and process.
	 * @param output       The name of a file to print to based on input data.
	 * @throws Exception   Could throw an IOException.
	 */
	public void processFile(String input, String output) throws Exception {
		// Set up IO
		Scanner in = new Scanner(new File(input));
		PrintWriter out = new PrintWriter(new FileWriter(output));

		while(in.hasNext()){
			int start = in.nextInt();
			int end = in.nextInt();
			out.println(getLocationName(start) + "," + getLocationName(end) + "," + _distances[start][end]);
		}
		in.close();
		out.close();
	}

	/**
	 * This method uses a stupid amount of brute force to solve a search problem.
	 * 
	 * @param start  The name of the starting location.
	 * @param end    The name of the ending location.
	 * @return       A string describing the best path from start to end.
	 */
	public String bestPath(String start, String end) {

		String ret = "";
		
		int s = idOfLocation(start);
		int e = idOfLocation(end);
		
		ret = start;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		if (idOfLocation(start) == idOfLocation(end)){
			return start + " -> " + end;
		}

		double shortPath = Double.MAX_VALUE/2.0;
		String path = "";

		// God, if you're there, please forgive me.
		for (int a = 0; a < _distances.length; a += 1){
			for (int b = 0; b < _distances.length; b += 1){
				for (int c = 0; c < _distances.length; c += 1){
					for (int d = 0; d < _distances.length; d += 1){
						for (int e = 0; e < _distances.length; e += 1){
							for (int f = 0; f < _distances.length; f += 1){
								for (int g = 0; g < _distances.length; g += 1){
									double direct;
									double oneStop;
									double twoStops;
									double threeStops;
									double fourStops;
									double fiveStops;
									double sixStops;
									double sevenStops;

									if (_map.roadExists(idOfLocation(start), idOfLocation(end)) > 0){
										direct = _map.roadExists(idOfLocation(start), idOfLocation(end));
									}
									else{
										direct = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, idOfLocation(end)) > 0){
										oneStop = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, idOfLocation(end));
									}
									else{
										oneStop = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, b) > 0 
											&& _map.roadExists(b, idOfLocation(end)) > 0){
										twoStops = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, b) 
												+ _map.roadExists(b, idOfLocation(end));
									}
									else{
										twoStops = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, b) > 0 
											&& _map.roadExists(b, c) > 0 
											&& _map.roadExists(c, idOfLocation(end)) > 0){
										threeStops = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, b) 
												+ _map.roadExists(b, c) 
												+ _map.roadExists(c, idOfLocation(end));
									}
									else{
										threeStops = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, b) > 0 
											&& _map.roadExists(b, c) > 0 
											&& _map.roadExists(c, d) > 0 
											&& _map.roadExists(d, idOfLocation(end)) > 0){
										fourStops = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, b) 
												+ _map.roadExists(b, c) 
												+ _map.roadExists(c, d) 
												+ _map.roadExists(d, idOfLocation(end));
									}
									else{
										fourStops = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, b) > 0 
											&& _map.roadExists(b, c) > 0 
											&& _map.roadExists(c, d) > 0 
											&& _map.roadExists(d, e) > 0 
											&& _map.roadExists(e, idOfLocation(end)) > 0){
										fiveStops = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, b) 
												+ _map.roadExists(b, c) 
												+ _map.roadExists(c, d) 
												+ _map.roadExists(d, e) 
												+ _map.roadExists(e, idOfLocation(end));
									}
									else{
										fiveStops = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, b) > 0 
											&& _map.roadExists(b, c) > 0 
											&& _map.roadExists(c, d) > 0 
											&& _map.roadExists(d, e) > 0 
											&& _map.roadExists(e, f) > 0 
											&& _map.roadExists(f, idOfLocation(end)) > 0){
										sixStops = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, b) 
												+ _map.roadExists(b, c) 
												+ _map.roadExists(c, d) 
												+ _map.roadExists(d, e) 
												+ _map.roadExists(e, f) 
												+ _map.roadExists(f, idOfLocation(end));
									}
									else{
										sixStops = Double.MAX_VALUE/2.0;
									}
									if (_map.roadExists(idOfLocation(start), a) > 0 
											&& _map.roadExists(a, b) > 0 
											&& _map.roadExists(b, c) > 0 
											&& _map.roadExists(c, d) > 0 
											&& _map.roadExists(d, e) > 0 
											&& _map.roadExists(e, f) > 0 
											&& _map.roadExists(f, g) > 0 
											&& _map.roadExists(g, idOfLocation(end)) > 0){
										sevenStops = _map.roadExists(idOfLocation(start), a) 
												+ _map.roadExists(a, b) 
												+ _map.roadExists(b, c) 
												+ _map.roadExists(c, d) 
												+ _map.roadExists(d, e) 
												+ _map.roadExists(e, f) 
												+ _map.roadExists(f, g) 
												+ _map.roadExists(g, idOfLocation(end));
									}
									else{
										sevenStops = Double.MAX_VALUE/2.0;
									}

									if (direct < shortPath 
											&& direct < oneStop 
											&& direct < twoStops 
											&& direct < threeStops 
											&& direct < fourStops 
											&& direct < fiveStops 
											&& direct < sixStops 
											&& direct < sevenStops){
										shortPath = direct;
										path = start + " -> " + end;
									}
									else if (oneStop < shortPath 
											&& oneStop < twoStops 
											&& oneStop < threeStops 
											&& oneStop < fourStops 
											&& oneStop < fiveStops 
											&& oneStop < sixStops 
											&& oneStop < sevenStops){
										shortPath = oneStop;
										path = start + " -> " 
												+ getLocationName(a) + " -> " + end;
									}
									else if (twoStops < shortPath 
											&& twoStops < threeStops 
											&& twoStops < fourStops 
											&& twoStops < fiveStops 
											&& twoStops < sixStops 
											&& twoStops < sevenStops){
										shortPath = twoStops;
										path = start + " -> " 
												+ getLocationName(a) + " -> " 
												+ getLocationName(b) + " -> " + end;
									}
									else if (threeStops < shortPath 
											&& threeStops < fourStops 
											&& threeStops < fiveStops 
											&& threeStops < sixStops 
											&& threeStops < sevenStops){
										shortPath = threeStops;
										path = start + " -> " 
												+ getLocationName(a) + " -> " 
												+ getLocationName(b) + " -> " 
												+ getLocationName(c) + " -> " + end;
									}
									else if (fourStops < shortPath 
											&& fourStops < fiveStops 
											&& fourStops < sixStops 
											&& fourStops < sevenStops){
										shortPath = fourStops;
										path = start + " -> " 
												+ getLocationName(a) + " -> " 
												+ getLocationName(b) + " -> " 
												+ getLocationName(c) + " -> " 
												+ getLocationName(d) + " -> " + end;
									}
									else if (fiveStops < shortPath 
											&& fiveStops < sixStops 
											&& fiveStops < sevenStops){
										shortPath = fiveStops;
										path = start + " -> " 
												+ getLocationName(a) + " -> " 
												+ getLocationName(b) + " -> " 
												+ getLocationName(c) + " -> " 
												+ getLocationName(d) + " -> " 
												+ getLocationName(e) + " -> " + end;
									}
									else if (sixStops < shortPath 
											&& sixStops < sevenStops){
										shortPath = sixStops;
										path = start + " -> " 
												+ getLocationName(a) + " -> " 
												+ getLocationName(b) + " -> " 
												+ getLocationName(c) + " -> " 
												+ getLocationName(d) + " -> " 
												+ getLocationName(e) + " -> " 
												+ getLocationName(f) + " -> " + end;
									}
									else if (sevenStops < shortPath){
										shortPath = sevenStops;
										path = start + " -> " 
												+ getLocationName(a) + " -> " 
												+ getLocationName(b) + " -> " 
												+ getLocationName(c) + " -> " 
												+ getLocationName(d) + " -> " 
												+ getLocationName(e) + " -> " 
												+ getLocationName(f) + " -> " 
												+ getLocationName(g) + " -> " + end;
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Sorry mom
		return path;
		*/

	}

	/**
	 * Prints the contents of a two dimensional array of doubles to console for
	 * debugging purposes.
	 * 
	 * @param arr
	 *            The array to be printed
	 */
	public static void printArray(double[][] arr) {
		if (arr.length == 0) {
			System.out.println("Zero sized array.");
			return;
		}
		System.out.println(arr.length + " by " + arr[0].length + " array:");
		for (int r = 0; r < arr.length; ++r) {
			for (int c = 0; c < arr[r].length; ++c) {
				System.out.printf("% 8.2f", arr[r][c]);
			}
			System.out.println();
		}
	}

	public void printMap(){
		for (int i = 0; i < _map.getNumRoads(); i += 1){
			System.out.println("Road from " + getLocationName(_map.getRoad(i).getStart()) + " to " + getLocationName(_map.getRoad(i).getEnd()) + " with distance " + _map.getRoad(i).getDistance());
		}
	}

}
