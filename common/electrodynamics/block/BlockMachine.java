package electrodynamics.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.core.EDLogger;
import electrodynamics.lib.block.Machine;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.tileentity.machine.TileEntityMachine;
import electrodynamics.tileentity.structure.TileEntityStructure;
import electrodynamics.util.PlayerUtil;

public class BlockMachine extends BlockContainer {
	
	public Icon[] textures;

	public BlockMachine(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setCreativeTab(CreativeTabED.block);
	}

	public int damageDropped(int damage) {
		return damage;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityLiving, ItemStack itemStack) {
		TileEntity tile = world.getBlockTileEntity(i, j, k);

		if (tile != null) {
			((TileEntityMachine) tile).rotation = PlayerUtil.determine3DOrientation_F(world, i, j, k, entityLiving);
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		try {
			return Machine.values()[meta].tileEntity.newInstance();
		} catch (Exception ex) {
			EDLogger.warn("Failed to create TileEntity for machine " + Machine.values()[meta].unlocalizedName);
			return null;
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		if (world.isRemote) return true;
		
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (tile instanceof TileEntityMachine) {
			((TileEntityMachine)tile).onBlockActivated(player);
			return true;
		}
		
		return false;
	}
	
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (tile instanceof TileEntityMachine) {
			((TileEntityMachine)tile).onBlockBreak();
		}
		
		super.breakBlock(world, x, y, z, id, meta);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (Machine machine : Machine.values()) {
			list.add(new ItemStack(id, 1, machine.ordinal()));
		}
	}
	
	/* IGNORE */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Machine.values().length];
		for (int i = 0; i < Machine.values().length; i++) {
			textures[i] = registry.registerIcon(Machine.get(i).getTextureFile());
		}
	}
	
	@Override
	public Icon getIcon(int id, int meta) {
		return textures[meta];
	}
	
	public int idPicked(World world, int x, int y, int z) {
		return this.blockID;
	}

	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	
}
