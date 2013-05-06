package electrodynamics.core;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.render.block.RenderBlockRedWire;
import electrodynamics.client.render.entity.RenderBeam;
import electrodynamics.client.render.item.RenderItemSinteringFurnace;
import electrodynamics.client.render.item.RenderItemTable;
import electrodynamics.client.render.tileentity.RenderSinteringFurnace;
import electrodynamics.client.render.tileentity.RenderTable;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;
import electrodynamics.entity.EntityBeam;
import electrodynamics.tileentity.TileEntitySinteringFurnace;
import electrodynamics.tileentity.TileEntityTable;

public class ClientProxy extends CommonProxy {

	public void preInitClient() {

	}

	public void initClient() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySinteringFurnace.class, new RenderSinteringFurnace());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTable());

		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockTable.blockID, new RenderItemTable());
		// TODO make modular, fit all machines
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockMachine.blockID, new RenderItemSinteringFurnace());

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

}
