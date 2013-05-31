package electrodynamics.module;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.block.BlockDecorative;
import electrodynamics.block.BlockLithiumClay;
import electrodynamics.block.BlockOre;
import electrodynamics.block.BlockRubberLeaves;
import electrodynamics.block.BlockRubberSapling;
import electrodynamics.block.BlockRubberWood;
import electrodynamics.block.BlockTreetap;
import electrodynamics.block.BlockWormwood;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.item.ItemBlockDecorative;
import electrodynamics.block.item.ItemBlockOre;
import electrodynamics.block.item.ItemBlockTreetap;
import electrodynamics.block.item.ItemBlockWormwood;
import electrodynamics.client.render.block.RenderBlockOre;
import electrodynamics.client.render.block.RenderBlockStructure;
import electrodynamics.client.render.tileentity.RenderTreetap;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemLatexBucket;
import electrodynamics.item.ItemLiquidLatex;
import electrodynamics.item.ItemPeelingSpud;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Decorative;
import electrodynamics.lib.block.Ore;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.ItemIDs;
import electrodynamics.tileentity.TileEntityTreetap;
import electrodynamics.util.BiomeHelper;
import electrodynamics.world.gen.WorldGenBlock;
import electrodynamics.world.gen.WorldGenClay;
import electrodynamics.world.gen.WorldGenLimestone;
import electrodynamics.world.gen.WorldGenNear;
import electrodynamics.world.gen.WorldGenOre;
import electrodynamics.world.gen.WorldGenPlant;
import electrodynamics.world.gen.WorldGenRubberTree;
import electrodynamics.world.handler.BonemealEventHandler;

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

		EDBlocks.blockWormwood = new BlockWormwood( BlockIDs.BLOCK_WORMWOOD_ID ).setUnlocalizedName( Strings.BLOCK_WORMWOOD );
		GameRegistry.registerBlock( EDBlocks.blockWormwood, ItemBlockWormwood.class, Strings.BLOCK_WORMWOOD );
		EDLanguage.getInstance().registerBlock(EDBlocks.blockWormwood);

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
		
		EDItems.itemLatexBucket = new ItemLatexBucket(ItemIDs.ITEM_LATEX_BUCKET_ID).setUnlocalizedName(Strings.ITEM_LATEX_BUCKET);
		GameRegistry.registerItem(EDItems.itemLatexBucket, Strings.ITEM_LATEX_BUCKET);
		EDLanguage.getInstance().registerItem(EDItems.itemLatexBucket);
		
		EDItems.itemLiquidLatex = new ItemLiquidLatex(ItemIDs.ITEM_LIQUID_LATEX_ID).setUnlocalizedName(Strings.ITEM_LIQUID_LATEX);
		GameRegistry.registerItem(EDItems.itemLiquidLatex, Strings.ITEM_LIQUID_LATEX);
		EDLanguage.getInstance().registerItem(EDItems.itemLiquidLatex);
	}

	@Override
	public void init() {
		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		
		LiquidDictionary.getOrCreateLiquid("Latex", new LiquidStack(EDItems.itemLiquidLatex, LiquidContainerRegistry.BUCKET_VOLUME));
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(EDItems.itemLiquidLatex, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(EDItems.itemLiquidLatex), new ItemStack(Item.bucketEmpty)));

		GameRegistry.registerTileEntity(TileEntityTreetap.class, Strings.BLOCK_TREETAP);
		
		FurnaceRecipes.smelting().addSmelting(BlockIDs.BLOCK_DECORATIVE_ID, 3, new ItemStack(EDBlocks.blockDecorative, 1, 0), 0F);
		FurnaceRecipes.smelting().addSmelting(ItemIDs.ITEM_COMPONENT_ID + 256, Component.WORMWOOD_LEAF.ordinal(), Component.SAP.toItemStack(), 0F);
		
		GameRegistry.addRecipe(new ItemStack(EDBlocks.blockDecorative, 4, 1), "XX", "XX", 'X', new ItemStack(EDBlocks.blockDecorative, 1, 0));
		GameRegistry.addRecipe(new ItemStack(EDBlocks.blockDecorative, 4, 2), "XX", "XX", 'X', new ItemStack(EDBlocks.blockDecorative, 1, 1));
		
		GameRegistry.addRecipe(new ItemStack(Item.silk), "TTT", 'T', Component.TWINE.toItemStack());
		
		// Cobaltite
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 0, 8, 16, 64, 4));
		// Chalcopyrite
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 1, 8, 32, 78, 8));
		// Galena
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 2, 4, 16, 32, 6));
		// Lithium
		GameRegistry.registerWorldGenerator(new WorldGenClay(BlockIDs.BLOCK_LITHIUM_CLAY_ID, 4));
		//MinecraftForge.TERRAIN_GEN_BUS.register(new electrodynamics.world.handler.CreateDecoratorHandler());
		// Magnetite
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 3, 8, 16, 32, 4));
		// Nickel
		GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, 4, 8, 16, 64, 8));
		// Wolframite
		GameRegistry.registerWorldGenerator(new WorldGenNear(BlockIDs.BLOCK_ORE_ID, Ore.WOLFRAMITE.ordinal(), 4, 4).setTarget(Block.lavaStill.blockID, 0).setYValues(6, 16));
		// Limestone
		GameRegistry.registerWorldGenerator(new WorldGenLimestone(BlockIDs.BLOCK_DECORATIVE_ID, 5));
		// Voidstone
		GameRegistry.registerWorldGenerator(new WorldGenBlock(BlockIDs.BLOCK_ORE_ID, Ore.VOIDSTONE.ordinal(), 3, 10) {
			@Override
			public void onGenned(World world, int x, int y, int z, Random random) {
				for (int ix = x - 2; ix < x + 2; ix++) {
					for (int iy = y - 2; iy < y + 2; iy++) {
						for (int iz = z - 2; iz < z + 2; iz++) {
							if (ix != x && iy != y && iz != z) {
								world.setBlockToAir(ix, iy, iz);
							}
						}
					}
				}
			}
		});
		// Wormwood
		GameRegistry.registerWorldGenerator(new WorldGenPlant(BlockIDs.BLOCK_WORMWOOD_ID, 0, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.HILLS, Type.FOREST, Type.JUNGLE, Type.MOUNTAIN)));
		GameRegistry.registerWorldGenerator(new WorldGenPlant(BlockIDs.BLOCK_WORMWOOD_ID, 1, BiomeHelper.getBiomesForTypes(Type.DESERT, Type.WASTELAND)));
	
		// Rubber Trees
		GameRegistry.registerWorldGenerator(new WorldGenRubberTree(10, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.JUNGLE)));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInitClient() {
		LiquidStack latex = LiquidDictionary.getCanonicalLiquid("Latex");
		latex.setTextureSheet("/gui/items.png");
		latex.setRenderingIcon(EDItems.itemLiquidLatex.getIconFromDamage(0));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void initClient() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreetap.class, new RenderTreetap());
		
		RenderingRegistry.registerBlockHandler(new RenderBlockOre());
		RenderingRegistry.registerBlockHandler(new RenderBlockStructure());
	}
	
	@Override
	public void postInit() {
	}

}
