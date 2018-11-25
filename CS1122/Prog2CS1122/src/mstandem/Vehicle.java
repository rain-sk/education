package mstandem;

/**
 * Defines a Vehicle which is a CombatUnit.
 * 
 * @author Agent Q.T.¹
 * @author Spencer Rudnick
 *
 */
public abstract class Vehicle extends CombatUnit {
	
	protected double armorPlating;
	protected double fuel;
	protected String manufacturer;
	
	/**
	 * Constructor for a Vehicle.
	 * 
	 * @param ammunitionIn
	 * 					The ammunition level of this Vehicle.
	 * 
	 * @param armsSupplierIn
	 * 					The arms supplier for this Vehicle.
	 * 
	 * @param weaponsIn
	 * 					The weapons for this Vehicle.
	 * 
	 * @param armorPlatingIn
	 * 					The armor plating level for this Vehicle.
	 * 
	 * @param fuelIn
	 * 					The fuel level of this Vehicle.
	 * 
	 * @param manufacturerIn
	 * 					The manufacturer of this Vehicle.
	 */
	public Vehicle(int ammunitionIn, String armsSupplierIn, String weaponsIn, double armorPlatingIn, double fuelIn, String manufacturerIn){
		/*
		 * Construct a CombatUnit.
		 */
		super(ammunitionIn, armsSupplierIn, weaponsIn);
		
		/*
		 * Set this Vehicle's armor plating level, fuel level, and manufacturer.
		 */
		setArmorPlating(armorPlatingIn);
		setFuel(fuelIn);
		setManufacturer(manufacturerIn);
	}
	
	/**
	 * @return This Vehicle's armor plating level.
	 */
	public double getArmorPlating() {
		return armorPlating;
	}

	/**
	 * @param armorPlatingIn
	 * 					The new armor plating level for this Vehicle.
	 */
	public void setArmorPlating(double armorPlatingIn) {
		armorPlating = armorPlatingIn;
	}
	
	/**
	 * @return The fuel level for this Vehicle.
	 */
	public double getFuel() {
		return fuel;
	}

	/**
	 * @param fuelIn
	 * 					The new fuel level for this Vehicle.
	 */
	public void setFuel(double fuelIn) {
		fuel = fuelIn;
	}
	
	/**
	 * @return This Vehicle's manufacturer.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturerIn
	 * 					This Vehicle's new manufacturer.
	 */
	public void setManufacturer(String manufacturerIn) {
		manufacturer = manufacturerIn;
	}
	
	/**
	 * @return The monthly cost of maintaining this Vehicle.
	 */
	public double monthlyMaintenanceCost() {
		return (100.0 - getFuel()) * 10000.0;
	}
	
}
