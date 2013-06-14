package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.UtilityBlock;
import electrodynamics.tileentity.machine.TileEntityMachine;
import electrodynamics.util.PlayerUtil;

public class BlockUtility extends BlockContainer {

	@SideOnly(Side.CLIENT)
	public Icon[][] textures;
	
	public BlockUtility(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(CreativeTabED.block);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		// Fix to prevent crash when opening trapped chest next to block deployer
		if (id != Block.chestTrapped.blockID) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);

			if (tile != null) {
				((TileEntityMachine)tile).onNeighborUpdate();
			}
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityLiving, ItemStack itemStack) {
		TileEntity tile = world.getBlockTileEntity(i, j, k);

		if (tile != null) {
			((TileEntityMachine) tile).rotation = PlayerUtil.determine3DOrientation_F(world, i, j, k, entityLiving);
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		TileEntityMachine tile = (TileEntityMachine) world.getBlockTileEntity(x, y, z);
		ForgeDirection sideDir = ForgeDirection.getOrientation(side);
		ForgeDirection front = tile.rotation;
		int meta = world.getBlockMetadata(x, y, z);
		
		if (sideDir == front) {
//			return this.textures[meta][0];
			return Block.dispenser.getBlockTextureFromSide(ForgeDirection.WEST.ordinal());
		} else if (sideDir == front.getOpposite()) {
//			return this.textures[meta][1];
			return Block.stone.getBlockTextureFromSide(0);
		} else {
//			return this.textures[meta][2];
			return Block.glass.getIcon(0, 0);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		ForgeDirection sideDir = ForgeDirection.getOrientation(side);
		
		if (sideDir == ForgeDirection.WEST) {
			return this.textures[meta][0];
		} else if (sideDir == ForgeDirection.EAST) {
			return this.textures[meta][1];
		} else {
			return this.textures[meta][2];
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		this.textures = new Icon[UtilityBlock.values().length][3];
		
		for (UtilityBlock util : UtilityBlock.values()) {
			for (int i=0; i<3; i++) {
				this.textures[util.ordinal()][i] = register.registerIcon(util.getTextures()[i]);
			}
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return UtilityBlock.values()[meta].getTileEntity();
	}
	
	/* IGNORE */
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
