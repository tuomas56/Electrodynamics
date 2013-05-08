package electrodynamics.recipe;

import java.util.ArrayList;

import electrodynamics.lib.block.Ore;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Dust;
import electrodynamics.lib.item.Grinding;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeManager {

	public static ArrayList<RecipeSmashingTable> smashingTableRecipes = new ArrayList<RecipeSmashingTable>();
	public static ArrayList<RecipeBasicSieve> sieveRecipes = new ArrayList<RecipeBasicSieve>();
	
	public static void registerSmashingTableRecipe(RecipeSmashingTable recipe) {
		smashingTableRecipes.add(recipe);
	}
	
	public static void registerSmashingTableRecipe(ItemStack in, ItemStack out, int damage) {
		smashingTableRecipes.add(new RecipeSmashingTable(in, out, damage));
	}
	
	public static RecipeSmashingTable getSmashingTableRecipe(ItemStack in) {
		if (in == null) return null;
		
		for (RecipeSmashingTable recipe : smashingTableRecipes) {
			if (recipe.inputItem.isItemEqual(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public static void registerSieveRecipe(RecipeBasicSieve recipe) {
		sieveRecipes.add(recipe);
	}
	
	
	public static RecipeBasicSieve getSieveRecipe(ItemStack in) {
		if (in == null) return null;
		
		for (RecipeBasicSieve recipe : sieveRecipes) {
			if (recipe.itemInput.isItemEqual(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public static void initializeSmashingRecipes() {
		/* Various vanilla recipes */
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.stone), new ItemStack(Block.cobblestone), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.brick), new ItemStack(Item.brick, 4, 0), 1);
		
		//First 6 ore/grinding ordinals are equal
		for (int i=0; i<6; i++) {
			RecipeManager.registerSmashingTableRecipe(Ore.get(i).toItemStack(), Grinding.get(i).toItemStack(), 1);
		}
		
		//Various other item smash recipes
		RecipeManager.registerSmashingTableRecipe(Component.LITHIUM_CLAY.toItemStack(), Grinding.LITHIUM.toItemStack(), 1);
		
		//Vanilla ores
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreDiamond), Grinding.DIAMOND.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreEmerald), Grinding.EMERALD.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreGold), Grinding.GOLD.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreIron), Grinding.IRON.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreLapis), Grinding.LAPIS.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreRedstone), Grinding.REDSTONE.toItemStack(), 1);
	}
	
	public static void initializeSieveRecipes() {
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.COBALTITE.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Component.ARSENIC.toItemStack(), 0.05F));
				outputs.add(new WeightedRecipeOutput(Dust.COBALT.toItemStack(), 1F));
				outputs.add(new WeightedRecipeOutput(Dust.SULFUR.toItemStack(), 0.05F));
			}
		});
		
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.CHARCOPYRITE.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
//				outputs.add(new WeightedRecipeOutput(Dust.GOLD, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.COPPER, 0.95F));
				outputs.add(new WeightedRecipeOutput(Dust.NICKEL, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.SULFUR, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.COBALT, 0.01F));
//				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.01F));
			}
		});
		
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.GALENA.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Component.ARSENIC, 0.05F));
				outputs.add(new WeightedRecipeOutput(Dust.LEAD, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.COPPER, 0.05F));
			}
		});
		
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.LITHIUM.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.CLAY, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.LITHIUM, 1F));
			}
		});
		
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.MAGNETITE.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Component.MAGNETITE_CHUNK, 0.2F));
				outputs.add(new WeightedRecipeOutput(Dust.MAGNETITE, 1F));
				outputs.add(new WeightedRecipeOutput(Component.LODESTONE_PEBBLE, 0.1F));
//				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.1F));
				outputs.add(new WeightedRecipeOutput(Dust.NICKEL, 0.5F));
			}
		});
		
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.NICKEL.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
//				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.5F));
				outputs.add(new WeightedRecipeOutput(Dust.NICKEL, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.SULFUR, 0.05F));
			}
		});
		
		RecipeManager.registerSieveRecipe(new RecipeBasicSieve(Grinding.WOLFRAMITE.toItemStack(), 1) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.TELLURIUM, 0.001F));
				outputs.add(new WeightedRecipeOutput(Dust.TUNGSTEN, 1F));
			}
		});
	}
	
}
