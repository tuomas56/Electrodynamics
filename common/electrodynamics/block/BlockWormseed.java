package electrodynamics.block;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class BlockWormseed extends BlockFlower {

	private Icon texture;
	
	public BlockWormseed(int id) {
		super(id);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public Icon getIcon(int meta, int side) {
		return texture;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}
	
	public void registerIcons(IconRegister icon) {
//		textures = new Icon[2];
		
		texture = icon.registerIcon(ModInfo.ICON_PREFIX + "world/plant/plantWormseed");
//		textures[1] = icon.registerIcon(ModInfo.ICON_PREFIX + "world/plant/plantWormseedDried");
	}
	
}
