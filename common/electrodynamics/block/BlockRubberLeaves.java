package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.ModInfo;

public class BlockRubberLeaves extends BlockLeaves {

	private Icon[] textures;

	public BlockRubberLeaves(int id) {
		super(id);
		setCreativeTab(CreativeTabED.block);
	}

	public int idDropped(int i, Random d, int k) {
		return BlockIDs.BLOCK_RUBBER_SAPLING_ID;
	}

	public int quantityDropped(Random rand) {
		return rand.nextInt(30) == 0 ? 1 : 0;
	}
	
	public int damageDropped(int meta) {
		return 0;
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return Minecraft.getMinecraft().gameSettings.fancyGraphics == true ? textures[1] : textures[0];
	}

	@Override
	public boolean isOpaqueCube() {
		return !Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
	}

	@Override
	public void registerIcons(IconRegister register) {
		this.textures = new Icon[2];

		this.textures[0] = register.registerIcon(ModInfo.ICON_PREFIX + "world/plant/rubberLeavesFast");
		this.textures[1] = register.registerIcon(ModInfo.ICON_PREFIX + "world/plant/rubberLeavesFancy");
	}

}
