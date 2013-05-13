package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;

public class BlockEggTimer extends BlockContainer {

	public BlockEggTimer(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setCreativeTab(CreativeTabED.block);
	}
	
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
