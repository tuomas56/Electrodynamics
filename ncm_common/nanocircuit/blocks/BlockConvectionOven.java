package nanocircuit.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import nanocircuit.NanoCircuitMod;
import nanocircuit.core.Reference;
import nanocircuit.tile.TileConvectionOven;

public class BlockConvectionOven extends BlockContainer
{
	protected BlockConvectionOven(int id)
	{
		super(id, Material.iron);
		setCreativeTab(NanoCircuitMod.tabsNCM);
	}
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) 
    {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity == null || player.isSneaking()) 
            {
                    return false;
            }
            player.openGui(NanoCircuitMod.instance, Reference.GUI_ID.CONVECTION_OVEN, world, x, y, z);
            return true;
    }
   
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) 
    {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
    
    private void dropItems(World world, int x, int y, int z)
    {
    	TileConvectionOven tile = (TileConvectionOven)world.getBlockTileEntity(x, y, z);
    	
    	Random rand = new Random();
    	
        if (tile != null)
        {
            for (int i = 0; i < tile.getSizeInventory(); ++i)
            {
                ItemStack stack = tile.getStackInSlot(i);

                if (stack != null)
                {
                    float randx = rand.nextFloat() * 0.8F + 0.1F;
                    float randy = rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem item;

                    for (float randz = rand.nextFloat() * 0.8F + 0.1F; stack.stackSize > 0; world.spawnEntityInWorld(item))
                    {
                        int amount = rand.nextInt(21) + 10;

                        if (amount > stack.stackSize)
                        {
                            amount = stack.stackSize;
                        }

                        stack.stackSize -= amount;
                        item = new EntityItem(world, (double)((float)x + randx), (double)((float)y + randy), (double)((float)z + randz), new ItemStack(stack.itemID, amount, stack.getItemDamage()));
                        float var15 = 0.05F;
                        item.motionX = (double)((float)rand.nextGaussian() * var15);
                        item.motionY = (double)((float)rand.nextGaussian() * var15 + 0.2F);
                        item.motionZ = (double)((float)rand.nextGaussian() * var15);

                        if (stack.hasTagCompound())
                        {
                            item.func_92014_d().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                        }
                    }
                }
            }
        }
    }
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileConvectionOven();
    }
	
	@Override
    public int getBlockTextureFromSide(int par1)
    {
        switch(par1)
        {
        	//Bottom
        	case 0: 
        		return 251;
        	//Top
        	case 1:
        		return 251;
        	//Sides
        	case 2:
        		return 234;
        	case 3:
        	case 4:
        	case 5:
        		return 235;
        }
        return 235;
    }
	
	@Override
	public String getTextureFile() 
	{
		return Reference.BLOCK_TEXTURE;
	}

}
