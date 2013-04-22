package nanocircuit;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import nanocircuit.core.Config;
import nanocircuit.core.Reference;
import nanocircuit.world.BlockOre;
import nanocircuit.world.BlockStorage;
import nanocircuit.world.CommonProxy;
import nanocircuit.world.WorldGenOre;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

@Mod
		(
				modid = "NCWorld",
				name = "NanoCircuit World",
				version = Reference.VERSION,
				dependencies = "required-after:NCCore"
		)
@NetworkMod
		(
				clientSideRequired = false,
				serverSideRequired = false
		)
public class NanoCircuitWorld {

	@Instance("NCWorld")
	public static NanoCircuitWorld instance;

	@SidedProxy
			(
					clientSide = "nanocircuit.world.ClientProxy",
					serverSide = "nanocircuit.world.CommonProxy"
			)
	public static CommonProxy proxy;

	public static WorldGenOre genOre;

	public static BlockOre blockOre;
	public static BlockStorage blockStorage;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		//proxy.registerRenderers();
	}

	@Init
	public void load(FMLInitializationEvent event) {
		this.initBlocks();
		this.initRecipes();
		this.initWorld();

		//NetworkRegistry.instance().registerGuiHandler(this.instance, proxy);
	}

	public void initBlocks() {
		blockOre = new BlockOre( Config.BLOCK_ID.oreBlock );
		blockStorage = new BlockStorage( Config.BLOCK_ID.storageBlock );
		GameRegistry.registerBlock( blockOre, nanocircuit.world.ItemOre.class, "ore" );
		GameRegistry.registerBlock( blockStorage, nanocircuit.world.ItemStorage.class, "storage" );
		blockOre.setUnlocalizedName( "tile.ncmOre" );
		blockStorage.setUnlocalizedName( "tile.ncmStorage" );
		MinecraftForge.setBlockHarvestLevel( blockOre, Reference.ORE_META.MAGNETITE, "pickaxe", 2 );
		MinecraftForge.setBlockHarvestLevel( blockOre, Reference.ORE_META.NICKEL, "pickaxe", 2 );
		OreDictionary.registerOre( "oreMagnetite", new ItemStack( blockOre, 1, Reference.ORE_META.MAGNETITE ) );
		OreDictionary.registerOre( "oreNickel", new ItemStack( blockOre, 1, Reference.ORE_META.NICKEL ) );

		LanguageRegistry.instance().addStringLocalization( "tile.oreMagnetite.name", "en_US", "Magnetite Ore" );
		LanguageRegistry.instance().addStringLocalization( "tile.oreNickel.name", "en_US", "Nickel Ore" );
		LanguageRegistry.instance().addStringLocalization( "tile.strgLodestone.name", "en_US", "Lodestone Block" );
	}

	public void initWorld() {
		genOre = new WorldGenOre();
		GameRegistry.registerWorldGenerator( genOre );
	}

	public void initRecipes() {
		GameRegistry.addRecipe( new ItemStack( this.blockStorage, 1, Reference.STORAGE_META.LODESTONE ), "III", "III", "III", 'I', new ItemStack( NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.LODESTONE_INGOT ) );
		FurnaceRecipes.smelting().addSmelting( NanoCircuitCore.itemComponent.itemID, Reference.COMPONENT_META.LODESTONE_DUST, new ItemStack( NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.LODESTONE_INGOT ), 0.1f );
	}

}
