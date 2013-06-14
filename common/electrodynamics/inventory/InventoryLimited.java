package electrodynamics.inventory;

import net.minecraft.inventory.InventoryBasic;

public class InventoryLimited extends InventoryBasic {

	public int stackLimit;
	
	public InventoryLimited(String name, int size, int stackLimit) {
		super(name, false, size);
		
		this.stackLimit = stackLimit;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return stackLimit;
	}
	
}
