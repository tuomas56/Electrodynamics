package electrodynamics.module;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.block.BlockLaserEmitter;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.render.entity.RenderBeam;
import electrodynamics.client.render.item.RenderItemPlasmaRifle;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.entity.EntityBeam;
import electrodynamics.entity.EntityPlasmaBeam;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemPlasmaRifle;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.ItemIDs;
import electrodynamics.tileentity.TileEntityLaserEmitter;

public class EDModuleLaser extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockLaserEmitter = new BlockLaserEmitter(BlockIDs.BLOCK_LASER_EMITTER_ID).setUnlocalizedName(Strings.BLOCK_LASER_EMITTER);
		GameRegistry.registerBlock(EDBlocks.blockLaserEmitter, Strings.BLOCK_LASER_EMITTER);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockLaserEmitter);
		
		EDItems.itemPlasmaRifle = new ItemPlasmaRifle(ItemIDs.ITEM_PLASMA_RIFLE_ID).setUnlocalizedName(Strings.ITEM_PLASMA_RIFLE);
		GameRegistry.registerItem(EDItems.itemPlasmaRifle, Strings.ITEM_PLASMA_RIFLE);
		EDLanguage.getInstance().registerItem(EDItems.itemPlasmaRifle);
	}

	@Override
	public void init() {
		GameRegistry.registerTileEntity(TileEntityLaserEmitter.class, Strings.BLOCK_LASER_EMITTER);
		
		EntityRegistry.registerGlobalEntityID(EntityBeam.class, "entityBeam", 150);
	}

	@Override
	public void initClient() {
		MinecraftForgeClient.registerItemRenderer(EDItems.itemPlasmaRifle.itemID, new RenderItemPlasmaRifle());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new RenderBeam());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaBeam.class, new RenderBeam());
	}
	
}
