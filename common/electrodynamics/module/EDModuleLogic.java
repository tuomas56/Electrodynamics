package electrodynamics.module;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockWire;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.render.block.ItemBlockWireRenderer;
import electrodynamics.client.render.block.TESRBlockWire;
import electrodynamics.tileentity.TileEntityWire;

public class EDModuleLogic extends EDModule{
	@Override
	public void preInit(){
		EDBlocks.blockRedWire = new BlockWire(3000);
		GameRegistry.registerBlock(EDBlocks.blockRedWire, "edBlockRedWire");
		LanguageRegistry.addName(EDBlocks.blockRedWire, "Wire");
	}

	@Override
	public void init(){
		
	}

	@Override
	public void initClient(){
		GameRegistry.registerTileEntity(TileEntityWire.class, "edTileEntityWire");
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockRedWire.blockID, new ItemBlockWireRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TESRBlockWire());
	}
}