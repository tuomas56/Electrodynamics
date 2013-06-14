package electrodynamics.recipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import electrodynamics.api.crafting.util.TableRecipeType;
import electrodynamics.api.tool.ITool;
import electrodynamics.tileentity.machine.TileEntityTable;

public class RecipeTable {

	public TableRecipeType type;
	
	public ItemStack inputItem;
	public ItemStack outputItem;
	
	public int hammerDamage;
	
	public RecipeTable(TableRecipeType type, ItemStack in, ItemStack out, int damage) {
		this.type = type;
		this.inputItem = in;
		this.outputItem = out;
		this.hammerDamage = damage;
	}
	
	public boolean isInput(ItemStack input, ItemStack toolUsed) {
		return ItemStack.areItemStacksEqual(input, inputItem) && (((ITool)toolUsed.getItem()).getToolType() == type.requiredTool);
	}
	
	/** Extra code to be called when the item is smashed. Can be left blank */
	public void onSmashed(EntityPlayer player, TileEntityTable tile, ItemStack stack) {}
	
}
