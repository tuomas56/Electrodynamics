package electrodynamics.module;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockSignalDimmer;
import electrodynamics.block.BlockWire;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.render.item.ItemBlockSignalDimmerRenderer;
import electrodynamics.client.render.item.ItemBlockWireRenderer;
import electrodynamics.client.render.tileentity.RenderBlockSignalDimmer;
import electrodynamics.client.render.tileentity.RenderBlockWire;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntityRedWire;
import electrodynamics.tileentity.TileEntitySignalDimmer;

public class EDModuleLogic extends EDModule{
	@Override
	public void preInit(){
		EDBlocks.blockRedWire = new BlockWire(3000);
		EDBlocks.blockSignalDimmer = new BlockSignalDimmer(3001);
		GameRegistry.registerBlock(EDBlocks.blockRedWire, Strings.BLOCK_RED_WIRE_NAME);
		GameRegistry.registerBlock(EDBlocks.blockSignalDimmer, Strings.BLOCK_SIGNAL_DIMMER_NAME);
		LanguageRegistry.addName(EDBlocks.blockSignalDimmer, EDLanguage.getInstance().translate("tile." + Strings.BLOCK_SIGNAL_DIMMER_NAME));
		LanguageRegistry.addName(EDBlocks.blockRedWire, EDLanguage.getInstance().translate("tile." + Strings.BLOCK_RED_WIRE_NAME));
	}

	@Override
	public void init(){
		
	}

	@Override
	public void initClient(){
		GameRegistry.registerTileEntity(TileEntityRedWire.class, "edTileEntityRedWire");
		GameRegistry.registerTileEntity(TileEntitySignalDimmer.class, "edTileEntitySignalDimmer");
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockRedWire.blockID, new ItemBlockWireRenderer());
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockSignalDimmer.blockID, new ItemBlockSignalDimmerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedWire.class, new RenderBlockWire());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySignalDimmer.class, new RenderBlockSignalDimmer());
	}
}