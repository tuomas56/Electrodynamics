package electrodynamics.module;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockOre;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.item.ItemBlockOre;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.Ore;
import electrodynamics.lib.Strings;

public class EDModuleWorld extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockOre = new BlockOre(BlockIDs.BLOCK_ORE_ID).setUnlocalizedName(Strings.BLOCK_ORE_NAME);
		GameRegistry.registerBlock(EDBlocks.blockOre, ItemBlockOre.class, Strings.BLOCK_ORE_NAME);
		for (int i=0; i<Ore.values().length; i++) {
			ItemStack ore = new ItemStack(EDBlocks.blockOre, 1, i);
			
			LanguageRegistry.addName(ore, Ore.get(i).getLocalizedName("en_US"));
			MinecraftForge.setBlockHarvestLevel(EDBlocks.blockOre, i, "pickaxe", Ore.get(i).harvestLevel);
			OreDictionary.registerOre(Ore.get(i).oreDictionaryName, ore);
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

}
