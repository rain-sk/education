package mstandem;

/**
 * Defines a MotorizedSuit which is a Manned Vehicle.
 * 
 * @author Agent Q.T.¹
 * @author Spencer Rudnick
 *
 */
public class MotorizedSuit extends Manned {

	private boolean isPrototype;
	private double pilotSkill;
	
	/**
	 * Constructor for a MotorizedSuit.
	 * 
	 * @param ammunitionIn
	 * 					The amount of ammunition this MotorizedSuit has.
	 * 
	 * @param armsSupplierIn
	 * 					The name of the arms supplier for this MotorizedSuit.
	 * 
	 * @param weaponsIn
	 * 					The weapons this MotorizedSuit uses.
	 * 
	 * @param armorPlatingIn
	 * 					The armor plating level of this MotorizedSuit.
	 * 
	 * @param fuelIn
	 * 					The fuel level of this MotorizedSuit.
	 * 
	 * @param manufacturerIn
	 * 					The manufacturer of this MotorizedSuit.
	 * 
	 * @param numberOfCrewIn
	 * 					The number of crew this MotorizedSuit contains.
	 * 
	 * @param isPrototypeIn
	 * 					Whether or not this MotorizedSuit is a prototype.
	 * 
	 * @param pilotSkillIn
	 * 					The skill level of the pilot of this MotorizedSuit.
	 */
	public MotorizedSuit(int ammunitionIn, String armsSupplierIn, String weaponsIn,
			double armorPlatingIn, double fuelIn, String manufacturerIn,
			int numberOfCrewIn, boolean isPrototypeIn, double pilotSkillIn)
	{
		/*
		 * Instantiate a Manned Vehicle.
		 */
		super(ammunitionIn, armsSupplierIn, weaponsIn, armorPlatingIn, fuelIn, manufacturerIn, numberOfCrewIn);
		
		/*
		 * Set this MotorizedSuit's prototype status and pilot skill level.
		 */
		setIsPrototype(isPrototypeIn);
		setPilotSkill(pilotSkillIn);
	}

	/**
	 * @return Whether or not this MotorizedSuit is a prototype.
	 */
	public boolean getIsPrototype() {
		return isPrototype;
	}

	/**
	 * @param isPrototypeIn
	 * 					This MotorizedSuit's new prototype status.
	 */
	public void setIsPrototype(boolean isPrototypeIn) {
		isPrototype = isPrototypeIn;
	}

	/**
	 * @return This skill level of this MotorizedSuit's pilot.
	 */
	public double getPilotSkill() {
		return pilotSkill;
	}

	/**
	 * @param pilotSkillIn
	 * 					The new skill level of this MotorizedSuit's pilot.
	 */
	public void setPilotSkill(double pilotSkillIn) {
		pilotSkill = pilotSkillIn;
	}
	
}
