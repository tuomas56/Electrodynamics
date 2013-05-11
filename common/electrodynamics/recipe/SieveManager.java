package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Dust;
import electrodynamics.lib.item.Grinding;

public class SieveManager {

	public static ArrayList<RecipeBasicSieve> sieveRecipes = new ArrayList<RecipeBasicSieve>();
	
	public void registerRecipe(RecipeBasicSieve recipe) {
		sieveRecipes.add(recipe);
	}

	public RecipeBasicSieve getRecipe(ItemStack input) {
		if (input == null) return null;
		
		for (RecipeBasicSieve recipe : sieveRecipes) {
			if (recipe.isInput(input)) {
				return recipe;
			}
		}
		
		return null;
	}

	public RecipeBasicSieve[] getRecipes() {
		return sieveRecipes.toArray(new RecipeBasicSieve[sieveRecipes.size()]);
	}
	
	public void initRecipes() {
		registerRecipe(new RecipeBasicSieve(Grinding.COBALTITE.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Component.ARSENIC.toItemStack(), 0.05F));
				outputs.add(new WeightedRecipeOutput(Dust.COBALT.toItemStack(), 1F));
				outputs.add(new WeightedRecipeOutput(Dust.SULFUR.toItemStack(), 0.05F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.CHALCOPYRITE.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.GOLD, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.COPPER, 0.95F));
				outputs.add(new WeightedRecipeOutput(Dust.NICKEL, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.SULFUR, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.COBALT, 0.01F));
				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.01F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.GALENA.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Component.ARSENIC, 0.05F));
				outputs.add(new WeightedRecipeOutput(Dust.LEAD, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.COPPER, 0.05F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.LITHIUM.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.CLAY, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.LITHIUM, 1F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.MAGNETITE.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Component.MAGNETITE_CHUNK, 0.2F));
				outputs.add(new WeightedRecipeOutput(Dust.MAGNETITE, 1F));
				outputs.add(new WeightedRecipeOutput(Component.LODESTONE_PEBBLE, 0.1F));
				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.1F));
				outputs.add(new WeightedRecipeOutput(Dust.NICKEL, 0.05F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.NICKEL.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.5F));
				outputs.add(new WeightedRecipeOutput(Dust.NICKEL, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.SULFUR, 0.05F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.WOLFRAMITE.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.TELLURIUM, 0.001F));
				outputs.add(new WeightedRecipeOutput(Dust.TUNGSTEN, 1F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Dust.LIMESTONE.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.LIME_PURE, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.IRON, 0.05F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.VOIDSTONE.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.VOIDSTONE, 1F));
				outputs.add(new WeightedRecipeOutput(Component.VOIDSTONE_MAGNET, 0.001F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.GOLD.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.GOLD, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.LEAD, 0.05F));
				outputs.add(new WeightedRecipeOutput(Dust.TELLURIUM, 0.001F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.IRON.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.IRON, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.LEAD, 0.05F));
				outputs.add(new WeightedRecipeOutput(Dust.TELLURIUM, 0.001F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.COPPER.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.COPPER, 1F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.TIN.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.TIN, 1F));
			}
		});
		
		registerRecipe(new RecipeBasicSieve(Grinding.URANIUM.toItemStack(), 100) {
			@Override
			public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {
				outputs.add(new WeightedRecipeOutput(Dust.URANIUM, 1F));
				outputs.add(new WeightedRecipeOutput(Dust.TELLURIUM, 0.001F));
			}
		});
	}
	
}
