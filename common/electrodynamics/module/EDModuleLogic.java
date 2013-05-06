package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockRedWire;
import electrodynamics.block.EDBlocks;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemPCB;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.ItemIDs;
import electrodynamics.lib.Strings;

public class EDModuleLogic extends EDModule {

	@Override
	public void preInit() {
		/* BLOCK */
		EDBlocks.blockRedWire = new BlockRedWire(BlockIDs.BLOCK_RED_WIRE_ID).setUnlocalizedName(Strings.BLOCK_RED_WIRE_NAME);
		GameRegistry.registerBlock(EDBlocks.blockRedWire, Strings.BLOCK_RED_WIRE_NAME);
		LanguageRegistry.addName(EDBlocks.blockRedWire, "Red Wire");
		
		/* ITEM */
		EDItems.itemPCB = new ItemPCB(ItemIDs.ITEM_PCB_ID).setUnlocalizedName(Strings.ITEM_PCB_NAME);
		GameRegistry.registerItem(EDItems.itemPCB, Strings.ITEM_PCB_NAME);
		LanguageRegistry.addName(EDItems.itemPCB, "PCB");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

}
