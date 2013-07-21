package electrodynamics.gas;

public class GasStack {

	/** ID of the gas this stack holds */
	public String gas;
	
	/** Amount of gas in this stack */
	public int amount;
	
	public GasStack(String gas, int amout) {
		this.gas = gas;
		this.amount = amout;
	}
	
	public boolean areTypesEqual(GasStack gas2) {
		return areTypesEqual(gas2.gas);
	}
	
	public boolean areTypesEqual(String gas2) {
		return gas.equalsIgnoreCase(gas2);
	}
	
}
