package electrodynamics.gas;

import java.util.HashMap;

import net.minecraftforge.fluids.FluidContainerRegistry;

import electrodynamics.block.EDBlocks;
import electrodynamics.core.EDLogger;

public class GasRegistry {

	public static HashMap<String, Gas> registeredGases = new HashMap<String, Gas>();
	
	private static final Gas naturalGas = new Gas("natural").setBlock(EDBlocks.blockGas.blockID);
	
	static {
		registerGas(naturalGas);
	}
	
	/** Registers a gas with the API. Will fail if another gas with the same ID already exists 
	 * @return Whether the gas was sucessfully registered */
	public static boolean registerGas(Gas gas) {
		if (registeredGases.get(gas.stringID) != null) {
			EDLogger.warn("GAS API: A gas tried to register itself, but the unique ID [" + gas.stringID + "] is already taken!");
			return false;
		} else {
			registeredGases.put(gas.stringID, gas);
			return true;
		}
	}
	
	/** Retrieves an instance of GasStack, holding the specified Gas as well as the default bucket amount */
	public static GasStack getGasStack(String id) {
		return getGasStack(id, FluidContainerRegistry.BUCKET_VOLUME);
	}
	
	/** Retrieves an instance of GasStack, holding the specified Gas as well as the specified amount */
	public static GasStack getGasStack(String id, int amount) {
		return new GasStack(id, amount);
	}
	
}
