package nanocircuit.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import nanocircuit.NanoCircuitMod;
import nanocircuit.core.Reference;

public class BlockCleanIron extends Block
{
	public BlockCleanIron(int par1)
	{
		super(par1, 19, Material.iron);
		setCreativeTab(NanoCircuitMod.tabsNCM);
	}

	@Override
	public String getTextureFile() 
	{
		return Reference.BLOCK_TEXTURE;
	}
}
