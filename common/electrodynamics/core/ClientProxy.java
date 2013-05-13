package electrodynamics.core;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.fx.FXLightningBolt;
import electrodynamics.client.render.block.RenderBlockOre;
import electrodynamics.client.render.item.RenderItemMachine;
import electrodynamics.client.render.item.RenderItemTable;
import electrodynamics.client.render.tileentity.RenderBasicSieve;
import electrodynamics.client.render.tileentity.RenderSinteringOven;
import electrodynamics.client.render.tileentity.RenderTable;
import electrodynamics.client.render.tileentity.RenderTreetap;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;
import electrodynamics.core.handler.SoundHandler;
import electrodynamics.lib.client.FXType;
import electrodynamics.tileentity.TileEntityBasicSieve;
import electrodynamics.tileentity.TileEntitySinteringOven;
import electrodynamics.tileentity.TileEntityTable;
import electrodynamics.tileentity.TileEntityTreetap;

public class ClientProxy extends CommonProxy {

	public void preInitClient() {
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}

	public void initClient() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySinteringOven.class, new RenderSinteringOven());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBasicSieve.class, new RenderBasicSieve());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreetap.class, new RenderTreetap());
		
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockTable.blockID, new RenderItemTable());
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockMachine.blockID, new RenderItemMachine());
//		MinecraftForgeClient.registerItemRenderer(EDItems.itemPlasmaRifle.itemID, new RenderItemPlasmaRifle());
		
//		RenderingRegistry.registerBlockHandler(new RenderBlockRedWire());
		RenderingRegistry.registerBlockHandler(new RenderBlockOre());

//		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new RenderBeam());
//		RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaBeam.class, new RenderBeam());
	}

	@Override
	public void setKeyBinding(String name, int value, boolean repeats) {
		KeyBindingHelper.addKeyBinding(name, value, repeats);
	}

	@Override
	public void registerKeyBindings() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
	}

	public void handleFXPacket(FXType type, double x, double y, double z, int[] extraInfo) {
		switch (type) {
			case BLOCK_BREAK: {
				addBlockDestroyParticles((int)x, (int)y, (int)z, extraInfo[0], extraInfo[1]);
				break;
			}
			case ENDER_PARTICLES: {
				addEnderParticles(x, y, z);
				break;
			}
		}
	}
	
	public void handleSoundPacket(String sound, double x, double y, double z) {
		FMLClientHandler.instance().getClient().theWorld.playSound(x, y, z, sound, 1F, 1F, false);
	}
	
	public void handleSFXPacket(int sfx, double x, double y, double z) {
		FMLClientHandler.instance().getClient().theWorld.playAuxSFX(sfx, (int)x, (int)y, (int)z, 0);
	}
	
	@Override
	public void addBlockDestroyParticles(int x, int y, int z, int blockID, int meta) {
		if (blockID > 0) {
			Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(x, y, z, blockID, meta);
		}
	}

	@Override
	public void addEnderParticles(double x, double y, double z) {
		short short1 = 128;

		Random rand = new Random();
		
		for (int l = 0; l < short1; ++l) {
			double d6 = (double) l / ((double) short1 - 1.0D);
			float f = (rand.nextFloat() - 0.5F) * 0.2F;
			float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
			float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
			double d7 = x + (x - x) * d6 + (rand.nextDouble() - 0.5D) * (double) .2 * 2.0D;
			double d8 = y + (y - y) * d6 + rand.nextDouble() * (double) .2;
			double d9 = z + (z - z) * d6 + (rand.nextDouble() - 0.5D) * (double) .2 * 2.0D;
			FMLClientHandler.instance().getClient().theWorld.spawnParticle("portal", d7, d8, d9, (double) f, (double) f1, (double) f2);
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
