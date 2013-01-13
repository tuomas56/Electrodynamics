package nanocircuit;

import java.io.File;

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
import net.minecraft.item.ItemStack;

import nanocircuit.core.Config;
import nanocircuit.core.Reference;

import nanocircuit.machine.CommonProxy;

import nanocircuit.NanoCircuitCore;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.FurnaceRecipes;

@Mod
(
		modid = "NCMachine", 
		name = "NanoCircuit Machine", 
		version = Reference.VERSION,
		dependencies = "required-after:NCCore;required-after:NCWorld"
)
@NetworkMod
(
		clientSideRequired = false, 
		serverSideRequired = false
)
public class NanoCircuitMachine
{
	
	@Instance("NCMachine")
	public static NanoCircuitMachine instance;
	
	@SidedProxy
	(
			clientSide="nanocircuit.machine.ClientProxy", 
			serverSide="nanocircuit.machine.CommonProxy"
	)
	public static CommonProxy proxy;	
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		//proxy.registerRenderers();
	}
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		this.initBlocks();
		this.initRecipes();
		
		//NetworkRegistry.instance().registerGuiHandler(this.instance, proxy);
	}
	
	public void initBlocks()
	{
	}
	
	public void initRecipes()
	{
		ItemStack ironIngot = new ItemStack(Item.ingotIron, 1);
		ItemStack ironRod = new ItemStack(NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.IRON_ROD);
		ItemStack ironFanblade = new ItemStack(NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.IRON_FANBLADE);
		
		//IRON ROD RECIPE (user-friendly, can be crafted both ways
		GameRegistry.addRecipe(ironRod, "  I", " I ", "I  ", 'I', ironIngot);
		GameRegistry.addRecipe(ironRod, "I  ", " I ", "  I", 'I', ironIngot);
		
		//IRON FANBLADE RECIPE
		GameRegistry.addRecipe(ironFanblade, "I I", " R ", "I I", 'I', ironIngot, 'R', ironRod);
		
		//CLEAN DOOR RECIPE
		//GameRegistry.addRecipe(new ItemStack(itemDoor), "IGI", "IGI", "IGI", 'I', ironIngot, 'G', new ItemStack(Block.glass));
	}
	
}
