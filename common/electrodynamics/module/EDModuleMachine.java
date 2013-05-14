package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.*;
import electrodynamics.block.item.ItemBlockMachine;
import electrodynamics.block.item.ItemBlockStorage;
import electrodynamics.block.item.ItemBlockStructure;
import electrodynamics.block.item.ItemBlockTable;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.item.*;
import electrodynamics.lib.block.*;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.*;
import electrodynamics.module.ModuleManager.Module;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.recipe.RecipeManagerSieve;
import electrodynamics.recipe.RecipeManagerSinteringOven;
import electrodynamics.recipe.RecipeManagerTable;
import electrodynamics.tileentity.TileConveyorBelt;
import electrodynamics.tileentity.TileEntityBasicSieve;
import electrodynamics.tileentity.TileEntitySinteringOven;
import electrodynamics.tileentity.TileEntityTable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

import java.util.EnumSet;

public class EDModuleMachine extends EDModule {

	@Override
	public void preInit() {
		/* BLOCK */
		EDBlocks.blockTable = new BlockTable( BlockIDs.BLOCK_TABLE_ID ).setUnlocalizedName( Strings.BLOCK_TABLE_NAME );
		GameRegistry.registerBlock( EDBlocks.blockTable, ItemBlockTable.class, Strings.BLOCK_TABLE_NAME );
		for( int i = 0; i < 2; i++ ) {
			EDLanguage.getInstance().registerItemStack( new ItemStack( EDBlocks.blockTable, 1, i ), BlockTable.subNames[i] );
		}

		EDBlocks.blockMachine = new BlockMachine( BlockIDs.BLOCK_MACHINE_ID ).setUnlocalizedName( Strings.BLOCK_MACHINE );
		GameRegistry.registerBlock( EDBlocks.blockMachine, ItemBlockMachine.class, Strings.BLOCK_MACHINE );
		for( Machine machine : Machine.values() ) {
			EDLanguage.getInstance().registerItemStack( machine.toItemStack(), machine.unlocalizedName );
		}

		EDBlocks.blockStorage = new BlockStorage( BlockIDs.BLOCK_STORAGE_ID ).setUnlocalizedName( Strings.BLOCK_STORAGE );
		GameRegistry.registerBlock( EDBlocks.blockStorage, ItemBlockStorage.class, Strings.BLOCK_STORAGE );
		for( Storage storage : Storage.values() ) {
			EDLanguage.getInstance().registerItemStack( storage.toItemStack(), storage.unlocalizedName );
		}

		EDBlocks.blockStructureComponent = new BlockStructure( BlockIDs.BLOCK_STRUCTURE_COMPONENT_ID ).setUnlocalizedName( Strings.BLOCK_STRUCTURE_COMPONENT );
		GameRegistry.registerBlock( EDBlocks.blockStructureComponent, ItemBlockStructure.class, Strings.BLOCK_STRUCTURE_COMPONENT );
		for( StructureComponent component : StructureComponent.values() ) {
			EDLanguage.getInstance().registerItemStack( component.toItemStack(), component.getUnlocalizedName() );
		}
		LanguageRegistry.addName( EDBlocks.blockStructureComponent, "Conveyor Belt" );
		
		/* ITEM */
		EDItems.itemDust = new ItemDust( ItemIDs.ITEM_DUST_ID ).setUnlocalizedName( Strings.ITEM_DUST_NAME );
		GameRegistry.registerItem( EDItems.itemDust, Strings.ITEM_DUST_NAME );
		for( Dust dust : Dust.values() ) {
//			dust.registerWithOreDictionary();
			EDLanguage.getInstance().registerItemStack( dust.toItemStack(), dust.unlocalizedName );
		}
		for( Grinding grinding : Grinding.values() ) {
			EDLanguage.getInstance().registerItemStack( grinding.toItemStack(), grinding.unlocalizedName );
		}

		EDItems.itemIngot = new ItemIngot( ItemIDs.ITEM_INGOT_ID ).setUnlocalizedName( Strings.ITEM_INGOT_NAME );
		GameRegistry.registerItem( EDItems.itemIngot, Strings.ITEM_INGOT_NAME );
		for( Ingot ingot : Ingot.values() ) {
			EDLanguage.getInstance().registerItemStack( ingot.toItemStack(), ingot.unlocalizedName );
		}

		EDItems.itemHandheldSieve = new ItemHandheldSieve( ItemIDs.ITEM_HANDHELD_SIEVE_ID ).setUnlocalizedName( Strings.ITEM_HANDHELD_SIEVE_NAME );
		GameRegistry.registerItem( EDItems.itemHandheldSieve, Strings.ITEM_HANDHELD_SIEVE_NAME );
		EDLanguage.getInstance().registerItem( EDItems.itemHandheldSieve );

		EDItems.itemTray = new ItemTray( ItemIDs.ITEM_TRAY_ID ).setUnlocalizedName( Strings.ITEM_TRAY_NAME );
		GameRegistry.registerItem( EDItems.itemTray, Strings.ITEM_TRAY_NAME );
		EDLanguage.getInstance().registerItem( EDItems.itemTray );
	}

	@Override
	public void init() {
		CraftingManager.getInstance().tableManager = new RecipeManagerTable();
		CraftingManager.getInstance().tableManager.initRecipes();
		
		CraftingManager.getInstance().sieveManager = new RecipeManagerSieve();
		CraftingManager.getInstance().sieveManager.initRecipes();

		CraftingManager.getInstance().ovenManager = new RecipeManagerSinteringOven();
		CraftingManager.getInstance().ovenManager.initRecipes();

		for( Storage storage : Storage.values() ) {
			GameRegistry.addRecipe( storage.toItemStack(), "XXX", "XXX", "XXX", 'X', storage.ingot.toItemStack() );
			GameRegistry.addShapelessRecipe( new ItemStack( ItemIDs.ITEM_INGOT_ID + 256, 9, storage.ingot.ordinal() ), storage.toItemStack() );
		}

		// Stone Hammer
		GameRegistry.addRecipe( new ItemStack( EDItems.itemStoneHammer ), "C", "T", "S", 'C', Block.cobblestone, 'T', Component.TWINE.toItemStack(), 'S', Item.stick );
		// Steel Hammer
		GameRegistry.addRecipe( new ItemStack( EDItems.itemSteelHammer ), "I", "A", "S", 'I', Ingot.STEEL.toItemStack(), 'A', Component.SAP.toItemStack(), 'S', Item.stick );
		// Sledgehammer
		GameRegistry.addRecipe( new ItemStack( EDItems.itemSledgeHammer ), "S", "M", "M", 'S', Ingot.STEEL.toItemStack(), 'M', Component.METAL_BAR.toItemStack() );
		// Sap Torches
		GameRegistry.addRecipe( new ItemStack( Block.torchWood, 16 ), "W", "A", "S", 'W', new ItemStack( Block.cloth, OreDictionary.WILDCARD_VALUE ), 'A', Component.SAP.toItemStack(), 'S', Item.stick );
		// Metal Rod
		GameRegistry.addRecipe( Component.METAL_BAR.toItemStack(), "  I", " I ", "I  ", 'I', Item.ingotIron );
		// Basic Sieve
		GameRegistry.addRecipe( Machine.BASIC_SIEVE.toItemStack(), "S S", "RBR", "WWW", 'S', new ItemStack( Block.woodSingleSlab, 1, OreDictionary.WILDCARD_VALUE ), 'R', Component.METAL_BAR.toItemStack(), 'B', Block.fenceIron, 'W', new ItemStack( Block.planks, 1, OreDictionary.WILDCARD_VALUE ) );
		// Hand Sieve
		GameRegistry.addRecipe( new ItemStack( EDItems.itemHandheldSieve ), "SSS", "SMS", "SSS", 'S', Item.stick, 'M', Component.TWINE_MESH.toItemStack() );
		// Twine Mesh
		GameRegistry.addRecipe( Component.TWINE_MESH.toItemStack(), "TTT", "TTT", "TTT", 'T', Component.TWINE.toItemStack() );
		// Basic Table
		GameRegistry.addRecipe( new ItemStack( EDBlocks.blockTable ), "SSS", "WWW", "T T", 'S', new ItemStack( Block.woodSingleSlab, 1, OreDictionary.WILDCARD_VALUE ), 'W', new ItemStack( Block.planks, 1, OreDictionary.WILDCARD_VALUE ), 'T', Item.stick );
		// Oven Wall Component
		GameRegistry.addRecipe( Component.OVEN_WALL.toItemStack(), " IL", " IL", " IL", 'I', Item.ingotIron, 'L', Decorative.LIMESTONE.toItemStack() );
		// Sintering Oven
		GameRegistry.addRecipe( Machine.SINTERING_FURNACE.toItemStack(), "WWW", "WBW", "III", 'W', Component.OVEN_WALL.toItemStack(), 'B', Block.fenceIron, 'I', Item.ingotIron );

		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_COMPONENT_ID + 256, Component.LITHIUM_CLAY_WET.ordinal(), Component.LITHIUM_CLAY.toItemStack(), 0F );

		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.COBALT.ordinal(), Ingot.COBALT.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.COPPER.ordinal(), Ingot.COPPER.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.LEAD.ordinal(), Ingot.LEAD.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.NICKEL.ordinal(), Ingot.NICKEL.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.TELLURIUM.ordinal(), Ingot.TELLURIUM.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.TUNGSTEN.ordinal(), Ingot.TUNGSTEN.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.TIN.ordinal(), Ingot.TIN.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.SILVER.ordinal(), Ingot.SILVER.toItemStack(), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.URANIUM.ordinal(), Ingot.URANIUM.toItemStack(), 0F );

		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.IRON.ordinal(), new ItemStack( Item.ingotIron ), 0F );
		FurnaceRecipes.smelting().addSmelting( ItemIDs.ITEM_DUST_ID + 256, Dust.GOLD.ordinal(), new ItemStack( Item.ingotGold ), 0F );

		GameRegistry.registerTileEntity( TileEntitySinteringOven.class, Strings.MACHINE_SINTERING_OVEN );
		GameRegistry.registerTileEntity( TileEntityTable.class, Strings.BLOCK_TABLE_NAME );
		GameRegistry.registerTileEntity( TileEntityBasicSieve.class, Strings.MACHINE_BASIC_SIEVE );
		GameRegistry.registerTileEntity( TileConveyorBelt.class, "tile.conveyor.belt" );
	}

	@Override
	public void postInit() {

	}

	public EnumSet<Module> dependencies() {
		return EnumSet.of( Module.CORE, Module.WORLD );
	}

}
