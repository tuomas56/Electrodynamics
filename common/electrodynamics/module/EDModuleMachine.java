package electrodynamics.module;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockMachine;
import electrodynamics.block.BlockTable;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.item.ItemBlockMachine;
import electrodynamics.block.item.ItemBlockTable;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemSteelHammer;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.ItemIDs;
import electrodynamics.lib.Machine;
import electrodynamics.lib.Strings;
import electrodynamics.module.ModuleManager.Module;
import electrodynamics.tileentity.TileEntitySinteringFurnace;
import electrodynamics.tileentity.TileEntityTable;

public class EDModuleMachine extends EDModule {

	@Override
	public void preInit() {
		/* BLOCK */
		EDBlocks.blockTable = new BlockTable(BlockIDs.BLOCK_TABLE_ID).setUnlocalizedName(Strings.BLOCK_TABLE_NAME);
		GameRegistry.registerBlock(EDBlocks.blockTable, ItemBlockTable.class, Strings.BLOCK_TABLE_NAME);
		for (int i=0; i<BlockTable.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(EDBlocks.blockTable, 1, i), BlockTable.blockNames[i]);
		}
		
		EDBlocks.blockMachine = new BlockMachine(BlockIDs.BLOCK_MACHINE_ID).setUnlocalizedName(Strings.BLOCK_MACHINE);
		GameRegistry.registerBlock(EDBlocks.blockMachine, ItemBlockMachine.class, Strings.BLOCK_MACHINE);
		for (int i=0; i<Machine.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(EDBlocks.blockMachine, 1, i), Machine.values()[i].localizedName);
		}
		
		/* ITEM */
		EDItems.itemSteelHammer = new ItemSteelHammer(ItemIDs.ITEM_STEEL_HAMMER_ID).setUnlocalizedName(Strings.ITEM_STEEL_HAMMER_NAME);
		GameRegistry.registerItem(EDItems.itemSteelHammer, Strings.ITEM_STEEL_HAMMER_NAME);
		LanguageRegistry.addName(EDItems.itemSteelHammer, "Steel Hammer");
	}

	@Override
	public void init() {
		GameRegistry.registerTileEntity(TileEntitySinteringFurnace.class, Strings.MACHINE_SINTERING_FURNACE);
		GameRegistry.registerTileEntity(TileEntityTable.class, Strings.BLOCK_TABLE_NAME);
	}

	@Override
	public void postInit() {
		
	}

	public EnumSet<Module> dependencies() {
		return EnumSet.of(Module.CORE, Module.WORLD);
	}
	
}
