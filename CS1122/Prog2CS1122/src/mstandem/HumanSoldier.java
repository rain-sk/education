package mstandem;

/**
 * Defines a HumanSoldier which is a CombatUnit.
 * 
 * @author Agent Q.T.¹
 * @author Spencer Rudnick
 *
 */
public class HumanSoldier extends CombatUnit {
	
	private double physicalFitness;
	private String placeOfBirth;
	private String rank;
	private double rationRequirements;
	
	/**
	 * Constructor for a HumanSoldier
	 * 
	 * @param ammunitionIn
	 * 					The amount of ammunition this HumanSolder has.
	 * 
	 * @param armsSupplierIn
	 * 					The name of the arms supplier for this HumanSoldier.
	 * 
	 * @param weaponsIn
	 * 					The weapons this HumanSoldier uses.
	 * 
	 * @param physicalFitnessIn
	 * 					This HumanSoldier's physical fitness level.
	 * 
	 * @param placeOfBirthIn
	 * 					This HumanSoldier's place of birth.
	 * 
	 * @param rankIn
	 * 					This HumanSoldier's rank.
	 * 
	 * @param rationRequirementsIn
	 * 					This HumanSoldier's ration requirements.
	 */
	public HumanSoldier(int ammunitionIn, String armsSupplierIn, String weaponsIn,
			double physicalFitnessIn, String placeOfBirthIn,
			String rankIn, double rationRequirementsIn)
	{
		/*
		 * Instantiate a CombatUnit.
		 */
		super(ammunitionIn,armsSupplierIn,weaponsIn);
		
		/*
		 * Set this HumanSoldier's fitness level, place of
		 * birth, rank, and ration requirements.
		 */
		setPhysicalFitness(physicalFitnessIn);
		setPlaceOfBirth(placeOfBirthIn);
		setRank(rankIn);
		setRationRequirements(rationRequirementsIn);
	}
	
	/**
	 * Used when the HumanSoldier is feeling angsty.
	 */
	public void angst() {
		System.out.println("Soldier says, 'What am I fighting for?'");
	}
	
	/**
	 * @return This HumanSoldier's physical fitness level.
	 */
	public double getPhysicalFitness() {
		return physicalFitness;
	}
	
	/**
	 * @param physicalFitnessIn
	 * 					The new physical fitness level for this HumanSoldier.
	 */
	public void setPhysicalFitness(double physicalFitnessIn) {
		physicalFitness = physicalFitnessIn;
	}
	
	/**
	 * @return This HumanSoldier's place of birth.
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	
	/**
	 * Only ever used when creating a new HumanSoldier.
	 * 
	 * @param placeOfBirthIn
	 * 					The new place of birth for this HumanSoldier.
	 */
	public void setPlaceOfBirth(String placeOfBirthIn) {
		placeOfBirth = placeOfBirthIn;
	}
	
	/**
	 * @return This HumanSoldier's rank.
	 */
	public String getRank() {
		return rank;
	}
	
	/**
	 * @param rankIn
	 * 					The new rank for this HumanSoldier.
	 */
	public void setRank(String rankIn) {
		rank = rankIn;
	}
	
	/**
	 * @return This HumanSoldier's ration requirements.
	 */
	public double getRationRequirements() {
		return rationRequirements;
	}
	
	/**
	 * @param rationRequirementsIn
	 * 					The new ration requirements for this HumanSoldier.
	 */
	public void setRationRequirements(double rationRequirementsIn) {
		rationRequirements = rationRequirementsIn;
	}
	
	/**
	 * @return The cost of feeding this HumanSoldier.
	 */
	public double monthlyMaintenanceCost() {
		return getRationRequirements() * 100.0;
	}
	
}
