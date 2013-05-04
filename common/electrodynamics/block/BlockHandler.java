package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.item.ItemBlockOre;
import electrodynamics.block.item.ItemBlockTable;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.Ore;
import electrodynamics.lib.Strings;

public class BlockHandler {

	public static Block blockOre;
	public static Block blockRedWire;
	public static Block blockLaserEmitter;
	public static Block blockTable;
	
	public static void createBlockOre() {
		blockOre = new BlockOre(BlockIDs.BLOCK_ORE_ID).setUnlocalizedName(Strings.BLOCK_ORE_NAME);
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, Strings.BLOCK_ORE_NAME);
		for (int i=0; i<Ore.values().length; i++) {
			ItemStack ore = new ItemStack(blockOre, 1, i);
			
			LanguageRegistry.addName(ore, Ore.get(i).getLocalizedName("en_US"));
			MinecraftForge.setBlockHarvestLevel(blockOre, i, "pickaxe", Ore.get(i).harvestLevel);
			OreDictionary.registerOre(Ore.get(i).oreDictionaryName, ore);
		}
	}
	
	public static void createBlockRedWire() {
		blockRedWire = new BlockRedWire(BlockIDs.BLOCK_RED_WIRE_ID).setUnlocalizedName(Strings.BLOCK_RED_WIRE_NAME);
		GameRegistry.registerBlock(blockRedWire, Strings.BLOCK_RED_WIRE_NAME);
		LanguageRegistry.addName(blockRedWire, "Red Wire");
	}
	
	public static void createNewLaserEmitter() {
		blockLaserEmitter = new BlockLaserEmitter(BlockIDs.BLOCK_LASER_EMITTER_ID).setUnlocalizedName(Strings.BLOCK_LASER_EMITTER);
		GameRegistry.registerBlock(blockLaserEmitter, Strings.BLOCK_LASER_EMITTER);
		LanguageRegistry.addName(blockLaserEmitter, "Laser Emitter");
	}
	
	public static void createNewBlockTable() {
		blockTable = new BlockTable(BlockIDs.BLOCK_TABLE_ID).setUnlocalizedName(Strings.BLOCK_TABLE_NAME);
		GameRegistry.registerBlock(blockTable, ItemBlockTable.class, Strings.BLOCK_TABLE_NAME);
		for (int i=0; i<BlockTable.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockTable, 1, i), BlockTable.blockNames[i]);
		}
	}
	
}
