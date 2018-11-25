package mstandem;

/**
 * Defines a Manned Vehicle.
 * 
 * @author Agent Q.T.¹
 * @author Spencer Rudnick
 *
 */
public class Manned extends Vehicle {
	
	protected int numberOfCrew;
	
	/**
	 * Constructor for a Manned Vehicle.
	 * 
	 * @param ammunitionIn
	 * 					The ammunition level for this Manned Vehicle.
	 * 
	 * @param armsSupplierIn
	 * 					The arms supplier for this Manned Vehicle.
	 * 
	 * @param weaponsIn
	 * 					The weapons this Manned Vehicle has.
	 * 
	 * @param armorPlatingIn
	 * 					The armor plating level of this Manned Vehicle.
	 * 
	 * @param fuelIn
	 * 					The fuel level of this Manned Vehicle.
	 * 
	 * @param manufacturerIn
	 * 					The manufacturer of this Manned Vehicle.
	 * 
	 * @param numberOfCrewIn
	 * 					The number of crew this Manned Vehicle contains.
	 */
	public Manned(int ammunitionIn, String armsSupplierIn, String weaponsIn,
			double armorPlatingIn, double fuelIn, String manufacturerIn,
			int numberOfCrewIn)
	{
		/*
		 * Construct a Vehicle.
		 */
		super(ammunitionIn, armsSupplierIn, weaponsIn, armorPlatingIn, fuelIn, manufacturerIn);
		
		/*
		 * Set this Manned Vehicle's number of crew.
		 */
		setNumberOfCrew(numberOfCrewIn);
	}

	/**
	 * @return The number of crew in this Manned Vehicle.
	 */
	public int getNumberOfCrew() {
		return numberOfCrew;
	}

	/**
	 * @param numberOfCrewIn
	 * 					The new number of crew in this Manned Vehicle.
	 */
	public void setNumberOfCrew(int numberOfCrewIn) {
		numberOfCrew = numberOfCrewIn;
	}
	
	/**
	 * Rev the engine. Used to intimidate enemies.
	 */
	public void revEngine() {
		System.out.println("Vroom vroom");
	}
	
	// Eject and set auto-destruct sequence
	public void bailOut() {
		setNumberOfCrew(0);
		setArmorPlating(0);
	}

}
