package electrodynamics.block;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;

public class BlockDecorativeSlab extends BlockHalfSlab {

	public static final String[] slabTypes = new String[] {"Limestone"};
	
	private Icon[] textures;
	
	public BlockDecorativeSlab(int id) {
		super(id, false, Material.rock);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(CreativeTabED.block);
	}

	public Icon getIcon(int meta, int side) {
		if (side == ForgeDirection.UP.ordinal() || side == ForgeDirection.DOWN.ordinal()) {
			return textures[1];
		}
		
		return textures[0];
	}
	
	@Override
	public String getFullSlabName(int meta) {
		return slabTypes[meta];
	}

	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[2];
		
		textures[0] = register.registerIcon(ModInfo.ICON_PREFIX + "decorative/blockLimestoneSlab");
		textures[1] = register.registerIcon(ModInfo.ICON_PREFIX + "decorative/blockLimestoneSlabTop");
	}
	
}
