package nanocircuit.blocks;

import java.util.Random;

import nanocircuit.core.Reference;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;

import nanocircuit.NanoCircuitMod;

public class BlockCleanGlass extends BlockGlass
{
	public BlockCleanGlass(int par1)
	{
		super(par1, 17, Material.glass, false);
		setCreativeTab(NanoCircuitMod.tabsNCM);
	}
	
	@Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
	@Override
	public String getTextureFile() 
	{
		return Reference.BLOCK_TEXTURE;
	}

}
