package electrodynamics.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntitySignalDimmer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSignalDimmer extends BlockContainer{
	public BlockSignalDimmer(int id){
		super(id, Material.circuits);
		this.setUnlocalizedName("edBlockSignalDimmer");
		this.setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntitySignalDimmer();
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
}