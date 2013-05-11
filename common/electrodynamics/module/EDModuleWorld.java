package electrodynamics.module;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.block.BlockDecorative;
import electrodynamics.block.BlockLithiumClay;
import electrodynamics.block.BlockOre;
import electrodynamics.block.BlockRubberLeaves;
import electrodynamics.block.BlockRubberSapling;
import electrodynamics.block.BlockRubberWood;
import electrodynamics.block.BlockTreetap;
import electrodynamics.block.BlockWormseed;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.item.ItemBlockDecorative;
import electrodynamics.block.item.ItemBlockOre;
import electrodynamics.block.item.ItemBlockTreetap;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemPeelingSpud;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Decorative;
import electrodynamics.lib.block.Ore;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.ItemIDs;
import electrodynamics.tileentity.TileEntityTreetap;
import electrodynamics.util.BiomeHelper;
import electrodynamics.world.TickHandlerMBS;
import electrodynamics.world.gen.WorldGenBlock;
import electrodynamics.world.gen.WorldGenClay;
import electrodynamics.world.gen.WorldGenLimestone;
import electrodynamics.world.gen.WorldGenNear;
import electrodynamics.world.gen.WorldGenOre;
import electrodynamics.world.gen.WorldGenPlant;
import electrodynamics.world.gen.WorldGenRubberTree;

public class EDModuleWorld extends EDModule {

	@Override
	public void preInit() {
		EDItems.itemSpudPeeler = new ItemPeelingSpud(ItemIDs.ITEM_SPUD_PEELER_ID).setUnlocalizedName(Strings.ITEM_SPUD_PEELER);
		GameRegistry.registerItem(EDItems.itemSpudPeeler, Strings.ITEM_SPUD_PEELER);
		EDLanguage.getInstance().registerItem(EDItems.itemSpudPeeler);
		
		EDBlocks.blockOre = new BlockOre( BlockIDs.BLOCK_ORE_ID ).setUnlocalizedName( Strings.BLOCK_ORE_NAME );
		GameRegistry.registerBlock( EDBlocks.blockOre, ItemBlockOre.class, Strings.BLOCK_ORE_NAME );
		for (Ore ore : Ore.values()) {
			MinecraftForge.setBlockHarvestLevel(EDBlocks.blockOre, ore.ordinal(), "pickaxe", ore.harvestLevel);
			EDLanguage.getInstance().registerItemStack(ore.toItemStack(), ore.unlocalizedName);
		}
		
		EDBlocks.blockDecorative = new BlockDecorative( BlockIDs.BLOCK_DECORATIVE_ID ).setUnlocalizedName( Strings.BLOCK_DECORATIVE );
		GameRegistry.registerBlock( EDBlocks.blockDecorative, ItemBlockDecorative.class, Strings.BLOCK_DECORATIVE );
		for (Decorative dec : Decorative.values()) {
			EDLanguage.getInstance().registerItemStack(dec.toItemStack(), dec.unlocalizedName);
		}

		EDBlocks.blockWormseed = new BlockWormseed( BlockIDs.BLOCK_WORMSEED_ID ).setUnlocalizedName( Strings.BLOCK_WORMSEED );
		GameRegistry.registerBlock( EDBlocks.blockWormseed, Strings.BLOCK_WORMSEED );
		EDLanguage.getInstance().registerBlock(EDBlocks.blockWormseed);

		EDBlocks.blockLithiumClay = new BlockLithiumClay(BlockIDs.BLOCK_LITHIUM_CLAY_ID).setUnlocalizedName(Strings.BLOCK_LITHIUM_CLAY);
		GameRegistry.registerBlock(EDBlocks.blockLithiumClay, Strings.BLOCK_LITHIUM_CLAY);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockLithiumClay);
		
		EDBlocks.blockRubberWood = new BlockRubberWood(BlockIDs.BLOCK_RUBBER_WOOD_ID).setUnlocalizedName(Strings.BLOCK_RUBBER_WOOD);
		GameRegistry.registerBlock(EDBlocks.blockRubberWood, Strings.BLOCK_RUBBER_WOOD);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockRubberWood);
		
		EDBlocks.blockRubberLeaves = new BlockRubberLeaves(BlockIDs.BLOCK_RUBBER_LEAVES_ID).setUnlocalizedName(Strings.BLOCK_RUBBER_LEAF);
		GameRegistry.registerBlock(EDBlocks.blockRubberLeaves, Strings.BLOCK_RUBBER_LEAF);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockRubberLeaves);
		
		EDBlocks.blockRubberSapling = new BlockRubberSapling(BlockIDs.BLOCK_RUBBER_SAPLING_ID).setUnlocalizedName(Strings.BLOCK_RUBBER_SAPLING);
		GameRegistry.registerBlock(EDBlocks.blockRubberSapling, Strings.BLOCK_RUBBER_SAPLING);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockRubberSapling);
		
		EDBlocks.blockTreetap = new BlockTreetap(BlockIDs.BLOCK_TREETAP_ID).setUnlocalizedName(Strings.BLOCK_TREETAP);
		GameRegistry.registerBlock(EDBlocks.blockTreetap, ItemBlockTreetap.class, Strings.BLOCK_TREETAP);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockTreetap);
	}

	@Override
	public void init() {
//		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		
		GameRegistry.registerTileEntity(TileEntityTreetap.class, Strings.BLOCK_TREETAP);
		
		FurnaceRecipes.smelting().addSmelting(BlockIDs.BLOCK_DECORATIVE_ID, 2, new ItemStack(EDBlocks.blockDecorative, 1, 0), 0F);
		
		GameRegistry.addRecipe(new ItemStack(EDBlocks.blockDecorative, 4, 1), "XX", "XX", 'X', new ItemStack(EDBlocks.blockDecorative, 1, 0));
		GameRegistry.addRecipe(new ItemStack(EDBlocks.blockDecorative, 4, 4), "XX", "XX", 'X', new ItemStack(EDBlocks.blockDecorative, 1, 1));
		
		// Cobaltite
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 0, 8, 16, 64, 4));
		// Chalcopyrite
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 1, 8, 32, 78, 8));
		// Galena
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 2, 4, 16, 32, 6));
		// Lithium
		GameRegistry.registerWorldGenerator(new WorldGenClay(BlockIDs.BLOCK_LITHIUM_CLAY_ID, 4));
		// Magnetite
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 3, 8, 16, 32, 4));
		// Nickel
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 4, 8, 16, 64, 8));
		// Wolframite
		GameRegistry.registerWorldGenerator(new WorldGenNear(BlockIDs.BLOCK_ORE_ID, Ore.WOLFRAMITE.ordinal(), 4, 4).setTarget(Block.lavaStill.blockID, 0).setYValues(6, 16));
		// Limestone
		GameRegistry.registerWorldGenerator(new WorldGenLimestone(BlockIDs.BLOCK_DECORATIVE_ID, 5));
		// Voidstone
		GameRegistry.registerWorldGenerator(new WorldGenBlock(BlockIDs.BLOCK_ORE_ID, Ore.VOIDSTONE.ordinal(), 1, 10));
		// Wormseed
		GameRegistry.registerWorldGenerator(new WorldGenPlant(BlockIDs.BLOCK_WORMSEED_ID, 0, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.HILLS, Type.FOREST, Type.JUNGLE, Type.MOUNTAIN)));
		GameRegistry.registerWorldGenerator(new WorldGenPlant(BlockIDs.BLOCK_WORMSEED_ID, 1, BiomeHelper.getBiomesForTypes(Type.DESERT, Type.WASTELAND)));
	
		// Rubber Trees
		GameRegistry.registerWorldGenerator(new WorldGenRubberTree(10, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.JUNGLE)));
	}

	@Override
	public void postInit() {
		// Might need some side-sensitivity for these tick handlers.
		TickRegistry.registerTickHandler(TickHandlerMBS.instance(), Side.SERVER);
		TickRegistry.registerTickHandler(TickHandlerMBS.instance(), Side.CLIENT);
	}

}
