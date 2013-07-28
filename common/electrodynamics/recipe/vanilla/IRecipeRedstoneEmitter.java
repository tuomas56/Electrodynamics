package electrodynamics.recipe.vanilla;

import electrodynamics.item.EDItems;
import electrodynamics.item.ItemPowerTool;
import electrodynamics.item.ItemRedstoneEmitter;
import electrodynamics.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class IRecipeRedstoneEmitter implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		return containsRedstone(inventory) && containsRemote(inventory);
	}

	private boolean containsRedstone(IInventory inventory) {
		return ((InventoryUtil.contains(InventoryUtil.getInvArrayFromInventory(inventory), new ItemStack(Block.blockRedstone))) || (InventoryUtil.contains(InventoryUtil.getInvArrayFromInventory(inventory), new ItemStack(Item.redstone))));
	}
	
	private boolean containsRemote(IInventory inventory) {
		return (InventoryUtil.containsCertainAmount(InventoryUtil.getInvArrayFromInventory(inventory), new ItemStack(EDItems.itemRedstoneEmitter, 1, OreDictionary.WILDCARD_VALUE), 1));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack[] inv = InventoryUtil.getInvArrayFromInventory(inventory);
		ItemStack remote = InventoryUtil.getFirstOccuranceOf(inv, new ItemStack(EDItems.itemRedstoneEmitter, 1, OreDictionary.WILDCARD_VALUE));
		int numOfRedstone = InventoryUtil.getAmountInInventory(inv, new ItemStack(Item.redstone)) + (InventoryUtil.getAmountInInventory(inv, new ItemStack(Block.blockRedstone)) * 9);
		int newPowerLevel = ItemPowerTool.getCharge(remote) + numOfRedstone;
		
		return ItemRedstoneEmitter.getRemote(newPowerLevel);
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}
