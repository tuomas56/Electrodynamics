package electrodynamics.addons.thermalexpansion;

import cpw.mods.fml.common.Loader;
import thermalexpansion.api.crafting.CraftingManagers;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import electrodynamics.addons.EDAddon;
import electrodynamics.item.ItemRedstoneEmitter;

public class EDAddonThermalExpansion extends EDAddon {

	public static final int POWER_USAGE = 100;
	
	public void init() {
		LiquidStack liquidRedstone = LiquidDictionary.getLiquid("redstone", LiquidContainerRegistry.BUCKET_VOLUME);
		CraftingManagers.transposerManager.addFillRecipe(POWER_USAGE, ItemRedstoneEmitter.getRemote(0), ItemRedstoneEmitter.getRemote(ItemRedstoneEmitter.MAX_POWER), liquidRedstone, false);
	}
	
}
