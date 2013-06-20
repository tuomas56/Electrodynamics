package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileConnection;
import electrodynamics.tileentity.TileEntityRedWire;

public class BlockWire extends BlockContainer{
	public BlockWire(int id){
		super(id, Material.circuits);
		this.setUnlocalizedName("edBlockWire");
		this.setCreativeTab(CreativeTabED.block);
		setBlockBounds(0F, 0F, 0F, 1F, .15F, 1F);
	}

	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntityRedWire();
	}
	
	@Override
	public int getRenderType(){
		return RenderingRegistry.getNextAvailableRenderId();
	}

	
	@Override
	public int getRenderBlockPass(){
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId){
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if(tile instanceof TileConnection){
			((TileConnection) tile).updateEntity();
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int j, float k, float l, float m){
		TileConnection wire = (TileConnection) world.getBlockTileEntity(x, y, z);
		
		System.out.println("Orientation: " + this.determineOrientation(world, x, y, z, player));
		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack stack){
		int rotation = determineOrientation(world, x, y, z, living);
		
		world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
		
		((TileEntityRedWire) world.getBlockTileEntity(x, y, z)).setOrientation(rotation);
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int par5){
		TileConnection wire = (TileConnection) blockAccess.getBlockTileEntity(x, y, z);
		
		if (wire instanceof TileEntityRedWire) {
			if(wire.isPowered()){
				return 15;
			} else{
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
		TileEntityRedWire wire = (TileEntityRedWire) world.getBlockTileEntity(x, y, z);
	}
	
	private int determineOrientation(World world, int x, int y, int z, EntityLiving player){
		if(MathHelper.abs((float) player.posX - (float) x) < 2.0F && MathHelper.abs((float) player.posZ - (float) z) < 2.0F){
			double d = player.posY + 1.82D - (double) player.yOffset;
			
			if(d - (double) y > 2.0D){
				return 1;
			}
			
			if((double) y - d > 0.0D){
				return 0;
			}
		}
		
		int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F /360.0F) + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
	}
}