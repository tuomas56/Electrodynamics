package electrodynamics.block;

import java.util.Map.Entry;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.tileentity.Connection;
import electrodynamics.tileentity.TileEntityWire;

public class BlockWire extends BlockContainer{
	public BlockWire(int id){
		super(id, Material.iron);
		this.setUnlocalizedName("edBlockWire");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntityWire();
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
		
		if(tile instanceof TileEntityWire){
			((TileEntityWire) tile).updateEntity();
		}
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
		TileEntityWire wire = (TileEntityWire) world.getBlockTileEntity(x, y, z);
		
		for(Entry<ForgeDirection, Connection> entry : wire.allConnections().entrySet()){
			System.out.println(String.format("Connection(%s): %s", entry.getKey().name(), isConnected(entry)));
		}
	}
	
	private boolean isConnected(Entry<ForgeDirection, Connection> entry){
		if(entry.getValue() != null){
			return true;
		} else{
			return false;
		}
	}
}