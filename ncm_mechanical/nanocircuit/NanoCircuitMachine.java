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
import nanocircuit.core.Reference;
import nanocircuit.machine.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
public class NanoCircuitMachine {

	@Instance("NCMachine")
	public static NanoCircuitMachine instance;

	@SidedProxy
			(
					clientSide = "nanocircuit.machine.ClientProxy",
					serverSide = "nanocircuit.machine.CommonProxy"
			)
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		//proxy.registerRenderers();
	}

	@Init
	public void load(FMLInitializationEvent event) {
		this.initBlocks();
		this.initRecipes();

		//NetworkRegistry.instance().registerGuiHandler(this.instance, proxy);
	}

	public void initBlocks() {
	}

	public void initRecipes() {
		ItemStack ironIngot = new ItemStack( Item.ingotIron, 1 );
		ItemStack ironRod = new ItemStack( NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.IRON_ROD );
		ItemStack ironFanblade = new ItemStack( NanoCircuitCore.itemComponent, 1, Reference.COMPONENT_META.IRON_FANBLADE );

		//IRON ROD RECIPE (user-friendly, can be crafted both ways
		GameRegistry.addRecipe( ironRod, "  I", " I ", "I  ", 'I', ironIngot );
		GameRegistry.addRecipe( ironRod, "I  ", " I ", "  I", 'I', ironIngot );

		//IRON FANBLADE RECIPE
		GameRegistry.addRecipe( ironFanblade, "I I", " R ", "I I", 'I', ironIngot, 'R', ironRod );

		//CLEAN DOOR RECIPE
		//GameRegistry.addRecipe(new ItemStack(itemDoor), "IGI", "IGI", "IGI", 'I', ironIngot, 'G', new ItemStack(Block.glass));
	}

}
