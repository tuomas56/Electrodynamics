package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import electrodynamics.block.EDBlocks;
import electrodynamics.lib.block.Ore;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Dust;
import electrodynamics.lib.item.Grinding;

public class SmashingManager {

	public ArrayList<RecipeSmashingTable> smashingTableRecipes = new ArrayList<RecipeSmashingTable>();
	
	public void registerRecipe(RecipeSmashingTable recipe) {
		smashingTableRecipes.add(recipe);
	}
	
	public void registerRecipe(ItemStack in, ItemStack out, int damage) {
		smashingTableRecipes.add(new RecipeSmashingTable(in, out, damage));
	}
	
	public RecipeSmashingTable getRecipe(ItemStack in) {
		if (in == null) return null;
		
		for (RecipeSmashingTable recipe : smashingTableRecipes) {
			if (recipe.inputItem.isItemEqual(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public RecipeSmashingTable[] getRecipes() {
		return smashingTableRecipes.toArray(new RecipeSmashingTable[smashingTableRecipes.size()]);
	}
	
	public void initRecipes() {
		/* Various vanilla recipes */
		registerRecipe(new ItemStack(Block.stone), new ItemStack(Block.cobblestone), 1);
		registerRecipe(new ItemStack(Block.brick), new ItemStack(Item.brick, 4, 0), 1);
		
		//First 6 ore/grinding ordinals are equal
		for (int i=0; i<6; i++) {
			registerRecipe(Ore.get(i).toItemStack(), Grinding.get(i).toItemStack(), 1);
		}
		
		//Various other item smash recipes
		registerRecipe(Component.LITHIUM_CLAY.toItemStack(), Grinding.LITHIUM.toItemStack(), 1);
		registerRecipe(new ItemStack(EDBlocks.blockDecorative, 1, 0), Dust.LIMESTONE.toItemStack(), 1);
		
		//Vanilla ores
		registerRecipe(new ItemStack(Block.oreDiamond), Grinding.DIAMOND.toItemStack(), 1);
		registerRecipe(new ItemStack(Block.oreEmerald), Grinding.EMERALD.toItemStack(), 1);
		registerRecipe(new ItemStack(Block.oreGold), Grinding.GOLD.toItemStack(), 1);
		registerRecipe(new ItemStack(Block.oreIron), Grinding.IRON.toItemStack(), 1);
		registerRecipe(new ItemStack(Block.oreLapis), Grinding.LAPIS.toItemStack(), 1);
		registerRecipe(new ItemStack(Block.oreRedstone), Grinding.REDSTONE.toItemStack(), 1);
	}
	
}
