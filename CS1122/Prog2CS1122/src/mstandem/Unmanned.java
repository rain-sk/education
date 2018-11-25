package mstandem;

/**
 * Defines an Unmanned which is a Vehicle.
 * 
 * @author Agent Q.T.¹
 * @author Spencer Rudnick
 *
 */
public class Unmanned extends Vehicle {
	
	private String autonomousProgram;
	
	/**
	 * Constructor for an Unmanned Vehicle
	 * 
	 * @param ammunitionIn
	 * 					The amount of ammunition this Unmanned Vehicle has.
	 * 
	 * @param armsSupplierIn
	 * 					The arms supplier of this Unmanned Vehicle.
	 * 
	 * @param weaponsIn
	 * 					The weapons this Unmanned Vehicle has.
	 * 
	 * @param armorPlatingIn
	 * 					The armor plating level of this Unmanned Vehicle.
	 * 
	 * @param fuelIn
	 * 					The fuel level of this Unmanned Vehicle.
	 * 
	 * @param manufacturerIn
	 * 					The manufacturer of this Unmanned Vehicle.
	 * 
	 * @param autonomousProgramIn
	 * 					The autonomous program of this Unmanned Vehicle.
	 */
	public Unmanned(int ammunitionIn, String armsSupplierIn, String weaponsIn,
			double armorPlatingIn, double fuelIn, String manufacturerIn,
			String autonomousProgramIn)
	{
		/*
		 * Construct a Vehicle.
		 */
		super(ammunitionIn, armsSupplierIn, weaponsIn, armorPlatingIn, fuelIn, manufacturerIn);
		
		/*
		 * Set this Unmanned Vehicle's autonomous program.
		 */
		setAutonomousProgram(autonomousProgramIn);
	}
	
	/**
	 * @return This Unmanned Vehicle's autonomous program.
	 */
	public String getAutonomousProgram() {
		return autonomousProgram;
	}

	/**
	 * @param autonomousProgramIn
	 * 					The new autonomous program for this Unmanned Vehicle.
	 */
	public void setAutonomousProgram(String autonomousProgramIn) {
		autonomousProgram = autonomousProgramIn;
	}

	// Scramble program so it cannot be copied and set auto-destruct sequence
	public void selfDestruct() {
		setAutonomousProgram("MY COMRADES WILL AVENGE ME");
		setArmorPlating(0);
	}

}
