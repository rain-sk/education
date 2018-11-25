
public class City {
	
	private String name;
	
	private List roadsFrom;
	
	public City(String name, List roadsFrom){
		this.name = name;
		this.roadsFrom = roadsFrom;
	}
	
	public int numRoadsFrom(){
		return roadsFrom.size();
	}
	
	private Road getRoad(int index) throws IndexOutOfBoundsException{
		try{
			return (Road)roadsFrom.get(index);
		}
		catch(IndexOutOfBoundsException e){
			return e;
		}
	}

}
