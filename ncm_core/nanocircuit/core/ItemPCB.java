package nanocircuit.core;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import nanocircuit.NanoCircuitCore;
import nanocircuit.core.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class ItemPCB extends Item
{
	public ItemPCB(int i)
	{
		super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(NanoCircuitCore.tabsNCM);
	}
	
	@Override
	public Icon getIconFromDamage(int meta)
	{
		//return 8 + meta;
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(new ItemStack(par1, 1, 0));
    }
	
	@Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case Reference.PCB_META.BASIC_PCB:
                return "item.basicPCB";
        }

        throw new IndexOutOfBoundsException();
    }
	
	// Texture: Reference.ITEM_TEXTURE;

}
