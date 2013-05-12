package electrodynamics.block;

import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.EDItems;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketSound;

public class BlockRubberWood extends Block {

	private Icon[] textures;
	
	public BlockRubberWood(int id) {
		super(id, Material.wood);
		setCreativeTab(CreativeTabED.block);
	}
	
	public int quantityDropped(Random rand) {
		return 1;
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return BlockIDs.BLOCK_RUBBER_WOOD_ID;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		if (!world.isRemote) {
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == EDItems.itemSpudPeeler && validTapSpot(world, x, y, z)) {
				world.setBlockMetadataWithNotify(x, y, z, side, 2);
				player.getCurrentEquippedItem().damageItem(1, player);
				
				PacketSound packet = new PacketSound("electrodynamics.block.barkCut", x, y, z);
				PacketDispatcher.sendPacketToAllAround(x, y, z, 32D, world.provider.dimensionId, PacketTypeHandler.fillPacket(packet));
				return true;
			}
		}
		
		return false;
	}
	
	private boolean validTapSpot(World world, int x, int y, int z) {
		return (world.getBlockId(x, y + 1, z) == BlockIDs.BLOCK_RUBBER_WOOD_ID && world.getBlockId(x, y - 1, z) == BlockIDs.BLOCK_RUBBER_WOOD_ID) && (world.getBlockId(x, y - 2, z) != BlockIDs.BLOCK_RUBBER_WOOD_ID) && (world.getBlockMetadata(x, y, z) == 1);
	}
	
	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean isWood(World world, int x, int y, int z) {
		return true;
	}
	
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
		return 150;
	}
	
	public Icon getIcon(int side, int meta) {
		if (side == 0 || side == 1) {
			return textures[0];
		}
		
		return side == meta ? textures[2] : textures[1];
	}
	
	public void registerIcons(IconRegister register) {
		textures = new Icon[3];
		
		textures[0] = register.registerIcon("tree_top");
		textures[1] = register.registerIcon(ModInfo.ICON_PREFIX + "world/plant/rubberTree");
		textures[2] = register.registerIcon(ModInfo.ICON_PREFIX + "world/plant/rubberTreePeeled");
	}
	
}
