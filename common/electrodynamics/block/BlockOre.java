package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Ore;

public class BlockOre extends Block {

	public Icon[] textures;
	
	/** Textures specifically for Voidstone. <br /> 0 = transparent, 1 = fake star field, 2 = glowing animation */
	public Icon[] voidstoneTextures;

	@SideOnly(Side.CLIENT)
	public long soundDelay;
	
	public BlockOre(int i) {
		super(i, Material.rock);
		setHardness(3F);
		setResistance(5F);
		setCreativeTab(CreativeTabED.block);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		
		switch(meta) {
		case 3: return 0.8F;
		case 6: return 4F;
		default: return 3F;
		}
	}
	
	@Override
	public int idDropped(int meta, Random random, int j) {
		return BlockIDs.BLOCK_ORE_ID;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 1;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (Ore ore : Ore.values()) {
			subItems.add(ore.toItemStack());
		}
	}

	@Override
	public Icon getIcon(int side, int metadata) {
		return (metadata == Ore.VOIDSTONE.ordinal() ? ConfigurationSettings.VOIDSTONE_FANCY_GRAPHICS == true ? voidstoneTextures[0] : voidstoneTextures[1] : textures[metadata]);
	}

	@Override
	public int getRenderType() {
		return ConfigurationSettings.VOIDSTONE_RENDER_ID;
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Ore.values().length];
		voidstoneTextures = new Icon[3];
		
		for (int i = 0; i < Ore.values().length; i++) {
			textures[i] = registry.registerIcon(Ore.get(i).getTextureFile());
		}
		
		voidstoneTextures[2] = registry.registerIcon(Ore.VOIDSTONE.getTextureFile() + "Effect");
		voidstoneTextures[1] = registry.registerIcon(Ore.VOIDSTONE.getTextureFile() + "Fast");
		voidstoneTextures[0] = registry.registerIcon(Ore.VOIDSTONE.getTextureFile() + "Fancy");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (world.getBlockMetadata(x, y, z) == Ore.VOIDSTONE.ordinal()) {
			if (ConfigurationSettings.VOIDSTONE_AMBIENT_SOUND) {
				//Hard coded limit of ten blocks for sound to play.
				final int DISTANCE_LIMIT = 10;
				double distanceToPlayer = Minecraft.getMinecraft().thePlayer.getDistanceSq(x, y, z);
				
				if (distanceToPlayer <= DISTANCE_LIMIT) {
					if (this.soundDelay < System.currentTimeMillis()) {
						this.soundDelay = (System.currentTimeMillis() + 5000 + (rand.nextInt(10)) * 1000);
						world.playSound(x, y, z, "electrodynamics.block.voidstoneAmbient", 0.75F, 1.1F, false);
					}
				}
			}
		}
	}
	
}
