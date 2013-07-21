package electrodynamics.gas;

import java.util.HashMap;

import electrodynamics.block.EDBlocks;

public class GasRegistry {

	public static HashMap<String, Gas> registeredGases = new HashMap<String, Gas>();
	
	private static final Gas naturalGas = new Gas("natural").setBlock(EDBlocks.blockGas.blockID);
	
	static {
		registerGas(naturalGas);
	}
	
	public static void registerGas(Gas gas) {
		registeredGases.put(gas.stringID, gas);
	}
	
}
