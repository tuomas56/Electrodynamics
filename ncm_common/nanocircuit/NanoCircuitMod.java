package nanocircuit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

import nanocircuit.core.Reference;
import nanocircuit.core.Reflect;
import nanocircuit.core.CommonProxy;
import nanocircuit.blocks.BlockManager;
import nanocircuit.items.ItemManager;
import nanocircuit.world.WorldManager;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitMod 
{
	
	@Instance(Reference.MOD_ID)
	public static NanoCircuitMod instance;
	
	@SidedProxy(clientSide="nanocircuit.core.ClientProxy", serverSide="nanocircuit.core.CommonProxy")
	public static CommonProxy proxy;
	

	
	public boolean isIC2Installed = false;
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		//TODO: check if we can detect IC2 from here
		isIC2Installed = Reflect.classExist("ic2.common.IC2");
		
		BlockManager.initBlocks();
		ItemManager.initItems();
		WorldManager.initWorld();
		
		BlockManager.initRecipes();
		ItemManager.initRecipes();
			
		proxy.registerRenderers();
	}
}
