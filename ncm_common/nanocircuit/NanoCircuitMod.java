package nanocircuit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;

import nanocircuit.core.Config;
import nanocircuit.core.CommonProxy;
import nanocircuit.core.Reference;
import nanocircuit.core.Reflect;
import nanocircuit.blocks.BlockManager;
import nanocircuit.items.ItemManager;
import nanocircuit.world.WorldManager;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies="after:IC2")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitMod 
{
	
	@Instance(Reference.MOD_ID)
	public static NanoCircuitMod instance;
	
	@SidedProxy(clientSide="nanocircuit.core.ClientProxy", serverSide="nanocircuit.core.CommonProxy")
	public static CommonProxy proxy;
	
	public boolean isIC2Installed = false;
	
	public static CreativeTabs tabsNCM = 
			new CreativeTabs(CreativeTabs.getNextID(), Reference.MOD_ID)
			{
				@SideOnly(Side.CLIENT)
				public int getTabIconItemIndex() 
				{
					return ItemManager.itemPcb.shiftedIndex;
				}
			};
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());		
		proxy.registerRenderers();
	}
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		//TODO: check if we can detect IC2 from here
		isIC2Installed = Loader.isModLoaded("IC2");
		
		BlockManager.initBlocks();
		BlockManager.initTiles();
		ItemManager.initItems();
		WorldManager.initWorld();
		
		BlockManager.initRecipes();
		ItemManager.initRecipes();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.NCM", "en_US", "NanoCircuit");
		
		NetworkRegistry.instance().registerGuiHandler(this.instance, proxy);
	}
}
