package electrodynamics.module;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockMachine;
import electrodynamics.block.BlockTable;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.item.ItemBlockMachine;
import electrodynamics.block.item.ItemBlockTable;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemDust;
import electrodynamics.item.ItemHandheldSieve;
import electrodynamics.item.ItemIngot;
import electrodynamics.item.ItemTray;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Machine;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Dust;
import electrodynamics.lib.item.Grinding;
import electrodynamics.lib.item.Ingot;
import electrodynamics.lib.item.ItemIDs;
import electrodynamics.module.ModuleManager.Module;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.recipe.SieveManager;
import electrodynamics.recipe.SmashingManager;
import electrodynamics.tileentity.TileEntityBasicSieve;
import electrodynamics.tileentity.TileEntitySinteringOven;
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
		EDItems.itemDust = new ItemDust( ItemIDs.ITEM_DUST_ID ).setUnlocalizedName( Strings.ITEM_DUST_NAME );
		GameRegistry.registerItem( EDItems.itemDust, Strings.ITEM_DUST_NAME );
		for( Dust dust : Dust.values() ) {
			LanguageRegistry.addName( new ItemStack( EDItems.itemDust, 1, dust.ordinal() ), dust.localizedName );
		}
		for( Grinding grind : Grinding.values() ) {
			LanguageRegistry.addName( new ItemStack( EDItems.itemDust, 1, grind.ordinal() + Dust.values().length ), grind.localizedName );
		}
		
		EDItems.itemIngot = new ItemIngot(ItemIDs.ITEM_INGOT_ID).setUnlocalizedName(Strings.ITEM_INGOT_NAME);
		GameRegistry.registerItem(EDItems.itemIngot, Strings.ITEM_INGOT_NAME);
		for(Ingot ingot : Ingot.values()) {
			LanguageRegistry.addName(new ItemStack(EDItems.itemIngot, 1, ingot.ordinal()), ingot.localizedName);
		}
		
		EDItems.itemHandheldSieve = new ItemHandheldSieve(ItemIDs.ITEM_HANDHELD_SIEVE_ID).setUnlocalizedName(Strings.ITEM_HANDHELD_SIEVE_NAME);
		GameRegistry.registerItem(EDItems.itemHandheldSieve, Strings.ITEM_HANDHELD_SIEVE_NAME);
		LanguageRegistry.addName(EDItems.itemHandheldSieve, "Handheld Sieve");
		
		EDItems.itemTray = new ItemTray(ItemIDs.ITEM_TRAY_ID).setUnlocalizedName(Strings.ITEM_TRAY_NAME);
		GameRegistry.registerItem(EDItems.itemTray, Strings.ITEM_TRAY_NAME);
		LanguageRegistry.addName(EDItems.itemTray, "Tray");
	}

	@Override
	public void init() {
		CraftingManager.getInstance().smashingManager = new SmashingManager();
		CraftingManager.getInstance().smashingManager.initRecipes();
		
		CraftingManager.getInstance().sieveManager = new SieveManager();
		CraftingManager.getInstance().sieveManager.initRecipes();
		
		// Stone Hammer
		GameRegistry.addRecipe(new ItemStack(EDItems.itemStoneHammer), "C", "T", "S", 'C', Block.cobblestone, 'T', Component.TWINE.toItemStack(), 'S', Item.stick);
		
		// Steel Hammer
		GameRegistry.addRecipe(new ItemStack(EDItems.itemSteelHammer), "I", "A", "S", 'I', Ingot.STEEL.toItemStack(), 'A', Component.SAP.toItemStack(), 'S', Item.stick);
		
		// Sap Torches
		GameRegistry.addRecipe(new ItemStack(Block.torchWood, 16), "W", "A", "S", 'W', new ItemStack(Block.cloth, OreDictionary.WILDCARD_VALUE), 'A', Component.SAP.toItemStack(), 'S', Item.stick);
		
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_COMPONENT_ID + 256, Component.LITHIUM_CLAY_WET.ordinal(), Component.LITHIUM_CLAY.toItemStack(), 0F);
		
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.COBALT.ordinal(), Ingot.COBALT.toItemStack(), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.COPPER.ordinal(), Ingot.COPPER.toItemStack(), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.LEAD.ordinal(), Ingot.LEAD.toItemStack(), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.NICKEL.ordinal(), Ingot.NICKEL.toItemStack(), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.TELLURIUM.ordinal(), Ingot.TELLURIUM.toItemStack(), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.TUNGSTEN.ordinal(), Ingot.TUNGSTEN.toItemStack(), 0F);
		
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.IRON.ordinal(), new ItemStack(Item.ingotIron), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_DUST_ID + 256, Dust.GOLD.ordinal(), new ItemStack(Item.ingotGold), 0F);
		
		GameRegistry.registerTileEntity(TileEntitySinteringOven.class, Strings.MACHINE_SINTERING_FURNACE);
		GameRegistry.registerTileEntity(TileEntityTable.class, Strings.BLOCK_TABLE_NAME);
		GameRegistry.registerTileEntity(TileEntityBasicSieve.class, Strings.MACHINE_BASIC_SIEVE);
	}

	@Override
	public void postInit() {
		
	}

	public EnumSet<Module> dependencies() {
		return EnumSet.of(Module.CORE, Module.WORLD);
	}
	
}
