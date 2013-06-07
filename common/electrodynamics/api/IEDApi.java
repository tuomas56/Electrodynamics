package electrodynamics.api;

import electrodynamics.api.crafting.ICraftingManager;

public interface IEDApi {

	public ICraftingManager getCraftingManager();
	
	public void addToDollyBlacklist(int id, int meta);
	
}
