package electrodynamics.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.ItemIDs;

public class BlockLithiumClay extends Block {

	private Icon texture;
	
	public BlockLithiumClay(int id) {
		super(id, Material.clay);
		setHardness(0.6F);
		setStepSound(soundGravelFootstep);
		setCreativeTab(CreativeTabED.resource);
	}

	public Icon getIcon(int side, int meta) {
		return texture;
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return ItemIDs.ITEM_COMPONENT_ID + 256;
	}

	public int damageDropped(int damage) {
		return Component.LITHIUM_CLAY_WET.ordinal();
	}
	
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "world/ore/clayLithium");
	}
	
}
