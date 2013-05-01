package electrodynamics;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.techne.TechneModelLoader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.lib.ModInfo;
import electrodynamics.machine.core.CommonProxy;

@Mod(modid = "NC|Machine", name = "NanoCircuit - Machine", version = ModInfo.VERSION, dependencies = "required-after:NC|Core;required-after:NC|World")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ElectroDynamicsMachine {
	@Instance("NC|Machine")
	public static ElectroDynamicsMachine instance;
	@SidedProxy(clientSide = "electrodynamics.machine.core.ClientProxy", serverSide = "electrodynamics.machine.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			AdvancedModelLoader.registerModelHandler(new TechneModelLoader());
		}
	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

}
