package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import electrodynamics.api.crafting.util.TableRecipeType;
import electrodynamics.block.EDBlocks;
import electrodynamics.lib.block.Ore;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Dust;
import electrodynamics.lib.item.Grinding;

public class TableManager {

	public ArrayList<RecipeTable> smashingTableRecipes = new ArrayList<RecipeTable>();
	
	public void registerRecipe(RecipeTable recipe) {
		smashingTableRecipes.add(recipe);
	}
	
	public void registerSmashingRecipe(ItemStack in, ItemStack out, int damage) {
		this.registerRecipe(TableRecipeType.SMASH, in, out, damage);
	}
	
	public void registerSieveRecipe(ItemStack in, ItemStack out, int damage) {
		this.registerRecipe(TableRecipeType.SIEVE, in, out, damage);
	}
	
	public void registerRecipe(TableRecipeType type, ItemStack in, ItemStack out, int damage) {
		smashingTableRecipes.add(new RecipeTable(type, in, out, damage));
	}
	
	public RecipeTable getRecipe(ItemStack in, ItemStack tool) {
		if (in == null) return null;
		
		for (RecipeTable recipe : smashingTableRecipes) {
			if (recipe.isInput(in, tool)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public RecipeTable[] getRecipes() {
		return smashingTableRecipes.toArray(new RecipeTable[smashingTableRecipes.size()]);
	}
	
	public void initRecipes() {
		/* Various vanilla recipes */
		registerSmashingRecipe(new ItemStack(Block.stone), new ItemStack(Block.cobblestone), 1);
		registerSmashingRecipe(new ItemStack(Block.brick), new ItemStack(Item.brick, 4, 0), 1);
		
		//First 9 ore/grinding ordinals are equal
		for (int i=0; i<9; i++) {
			registerSmashingRecipe(Ore.get(i).toItemStack(), Grinding.get(i).toItemStack(), 1);
		}
		
		//Various other item smash recipes
		registerSmashingRecipe(Component.LITHIUM_CLAY.toItemStack(), Grinding.LITHIUM.toItemStack(), 1);
		registerSmashingRecipe(new ItemStack(EDBlocks.blockDecorative, 1, 0), Dust.LIMESTONE.toItemStack(), 1);
		
		//Vanilla ores
		registerSmashingRecipe(new ItemStack(Block.oreDiamond), Grinding.DIAMOND.toItemStack(), 1);
		registerSmashingRecipe(new ItemStack(Block.oreEmerald), Grinding.EMERALD.toItemStack(), 1);
		registerSmashingRecipe(new ItemStack(Block.oreGold), Grinding.GOLD.toItemStack(), 1);
		registerSmashingRecipe(new ItemStack(Block.oreIron), Grinding.IRON.toItemStack(), 1);
		registerSmashingRecipe(new ItemStack(Block.oreLapis), Grinding.LAPIS.toItemStack(), 1);
		registerSmashingRecipe(new ItemStack(Block.oreRedstone), Grinding.REDSTONE.toItemStack(), 1);
		
		//Ore dictionary ores
		registerRecipe(new OreRecipeTable(TableRecipeType.SMASH, "oreCopper", Grinding.COPPER.toItemStack(), 1));
		registerRecipe(new OreRecipeTable(TableRecipeType.SMASH, "oreSilver", Grinding.SILVER.toItemStack(), 1));
		registerRecipe(new OreRecipeTable(TableRecipeType.SMASH, "oreTin", Grinding.TIN.toItemStack(), 1));
		registerRecipe(new OreRecipeTable(TableRecipeType.SMASH, "oreUranium", Grinding.URANIUM.toItemStack(), 1));
	}
	
}
