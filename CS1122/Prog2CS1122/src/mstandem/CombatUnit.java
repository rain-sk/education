package mstandem;

/**
 * Defines a CombatUnit.
 * 
 * @author Agent Q.T.¹
 * @author Spencer Rudnick
 *
 */
public abstract class CombatUnit {
	
	protected int ammunition;
	protected String armsSupplier;
	protected String weapons;
	
	/**
	 * Constructor for a CombatUnit.
	 * 
	 * @param ammunitionIn
	 * 					This CombatUnit's ammunition level.
	 * 
	 * @param armsSupplierIn
	 * 					The arms supplier of this CombatUnit.
	 * 
	 * @param weaponsIn
	 * 					This CombatUnit's weapons.
	 */
	public CombatUnit(int ammunitionIn, String armsSupplierIn, String weaponsIn){
		/*
		 * Set this CombatUnit's ammunition level, arms supplier, and weapons.
		 */
		setAmmunition(ammunitionIn);
		setArmsSupplier(armsSupplierIn);
		setWeapons(weaponsIn);
	}
	
	/**
	 * @return This CombatUnit's ammunition level.
	 */
	public int getAmmunition(){
		return ammunition;
	}
	
	/**
	 * @param ammunitionIn
	 * 					The new ammunition level for this CombatUnit.
	 */
	public void setAmmunition(int ammunitionIn) {
		ammunition = ammunitionIn;
	}
	
	/**
	 * @return The arms supplier of this CombatUnit.
	 */
	public String getArmsSupplier() {
		return armsSupplier;
	}
	
	/**
	 * @param armsSupplierIn
	 * 					The new arms supplier for this CombatUnit.
	 */
	public void setArmsSupplier(String armsSupplierIn) {
		armsSupplier = armsSupplierIn;
	}
	
	/**
	 * @return This CombatUnit's weapons.
	 */
	public String getWeapons() {
		return weapons;
	}
	
	/**
	 * @param weaponsIn
	 * 					The new weapons for this CombatUnit.
	 */
	public void setWeapons(String weaponsIn) {
		weapons = weaponsIn;
	}
	
	/**
	 * @return The monthly cost of maintaining this CombatUnit.
	 */
	public abstract double monthlyMaintenanceCost();
}
