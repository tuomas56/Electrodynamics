package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
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
	
}