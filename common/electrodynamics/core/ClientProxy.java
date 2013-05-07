package electrodynamics.core;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.fx.FXLightningBolt;
import electrodynamics.client.render.block.RenderBlockRedWire;
import electrodynamics.client.render.entity.RenderBeam;
import electrodynamics.client.render.item.RenderItemSinteringOven;
import electrodynamics.client.render.item.RenderItemTable;
import electrodynamics.client.render.tileentity.RenderSinteringFurnace;
import electrodynamics.client.render.tileentity.RenderTable;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;
import electrodynamics.entity.EntityBeam;
import electrodynamics.tileentity.TileEntitySinteringOven;
import electrodynamics.tileentity.TileEntityTable;

public class ClientProxy extends CommonProxy {

	public void preInitClient() {

	}

	public void initClient() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySinteringOven.class, new RenderSinteringFurnace());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTable());

		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockTable.blockID, new RenderItemTable());
		// TODO make modular, fit all machines
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockMachine.blockID, new RenderItemSinteringOven());

		RenderingRegistry.registerBlockHandler(new RenderBlockRedWire());

		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new RenderBeam());
	}

	@Override
	public void setKeyBinding(String name, int value, boolean repeats) {
		KeyBindingHelper.addKeyBinding(name, value, repeats);
	}

	@Override
	public void registerKeyBindings() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
	}

	@Override
	public void addBlockDestroyParticles(int x, int y, int z, int blockID, int meta) {
		if (blockID > 0) {
			Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(x, y + 1, z, blockID, meta);
		}
	}

	public void addLightningFX(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration) {
		FXLightningBolt bolt = new FXLightningBolt(world, x1, y1, z1, x, y, z, seed, duration);
		bolt.defaultFractal();
		//TODO add this to method params
		bolt.setRandomType(4, 7);
		bolt.finalizeBolt();
	}
	
}
