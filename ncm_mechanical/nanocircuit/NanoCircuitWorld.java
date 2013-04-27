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
import nanocircuit.world.*;
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

		String language = "en_US"; // temporary. Will change after localizations are implemented.

		// Register all properties of Ores.
		GameRegistry.registerBlock( blockOre, nanocircuit.world.ItemOre.class, "ore" );
		for( Ores ore : Ores.values() ) {
			ItemStack itemOre = ore.toItemStack();

			// Register Harvest Level
			MinecraftForge.setBlockHarvestLevel( blockOre, ore.ordinal(), "pickaxe", ore.getHarvestLevel() );

			// Register Localization
			LanguageRegistry.instance().addNameForObject( itemOre, language, ore.getLocalizedName( language ) );

			// Register ores in the Ore Dictionary
			OreDictionary.registerOre( ore.getUnlocalizedName(), itemOre );
		}

		// Register all properties of StorageBlocks
		GameRegistry.registerBlock( blockStorage, nanocircuit.world.ItemStorage.class, "storage" );
		for( StorageBlocks sb : StorageBlocks.values() ) {
			ItemStack storageBlock = sb.toItemStack();

			// Register Localization
			LanguageRegistry.instance().addNameForObject( storageBlock, language, sb.getLocalizedName( language ) );
		}
	}

	public void initWorld() {
		genOre = new WorldGenOre();
		GameRegistry.registerWorldGenerator( genOre );
	}

	public void initRecipes() {
		GameRegistry.addRecipe( StorageBlocks.LODESTONE.toItemStack(), "III", "III", "III", 'I', new ItemStack( NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.LODESTONE_INGOT ) );
		FurnaceRecipes.smelting().addSmelting( NanoCircuitCore.itemComponent.itemID, Reference.COMPONENT_META.LODESTONE_DUST, new ItemStack( NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.LODESTONE_INGOT ), 0.1f );
	}

}
