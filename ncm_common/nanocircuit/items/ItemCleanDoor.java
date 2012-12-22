package nanocircuit.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import nanocircuit.NanoCircuitMod;
import nanocircuit.blocks.BlockManager;
import nanocircuit.core.Reference;

public class ItemCleanDoor extends ItemDoor
{
	public ItemCleanDoor(int par1)
	{
		super(par1, Material.iron);
		this.iconIndex = 7;
		setCreativeTab(NanoCircuitMod.tabsNCM);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else
        {
            ++par5;
            Block var11 = BlockManager.blockDoor;

            if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
            {
                if (!var11.canPlaceBlockAt(par3World, par4, par5, par6))
                {
                    return false;
                }
                else
                {
                    int var12 = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(par3World, par4, par5, par6, var12, var11);
                    --par1ItemStack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
	
	@Override
	public String getTextureFile() 
	{
		return Reference.ITEM_TEXTURE;
	}

}
