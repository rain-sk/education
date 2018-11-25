
public class Position extends Route {
	
	private int location;
	
	private int prevLocation;
	
	public Position(int location, int prevLocation){
		this.location = location;
		this.prevLocation = prevLocation;
	}
	
	public int getLocation(){
		return location;
	}
	
	public int getPrevLocation(){
		return prevLocation;
	}
	
	public String toString(){
		return super.getLocationName(location);
	}

}
