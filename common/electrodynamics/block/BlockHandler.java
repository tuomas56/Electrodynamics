package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.item.ItemBlockOre;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.Ore;
import electrodynamics.lib.Strings;

public class BlockHandler {

	public static Block blockHoloPad;
	public static Block blockOre;
	public static Block blockRedWire;
	public static Block blockLaserEmitter;
	
	public static void createBlockHoloPad() {
		blockHoloPad = new BlockHoloPad(BlockIDs.BLOCK_HOLO_PAD_ID).setUnlocalizedName(Strings.BLOCK_HOLO_PAD_NAME);
		GameRegistry.registerBlock(blockHoloPad, Strings.BLOCK_HOLO_PAD_NAME);
		LanguageRegistry.addName(blockHoloPad, "Holo Pad");
	}
	
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
		blockRedWire = new BlockRedstoneWire(BlockIDs.BLOCK_RED_WIRE_ID).setUnlocalizedName(Strings.BLOCK_RED_WIRE_NAME);
		GameRegistry.registerBlock(blockRedWire, Strings.BLOCK_RED_WIRE_NAME);
		LanguageRegistry.addName(blockRedWire, "Red Wire");
	}
	
	public static void createBlockLaserEmitter() {
		blockLaserEmitter = new BlockLaserEmitter(505).setUnlocalizedName("blockLaserEmitter");
		GameRegistry.registerBlock(blockLaserEmitter, "blockLaserEmitter");
		LanguageRegistry.addName(blockLaserEmitter, "Laser Emitter");
	}
	
}
