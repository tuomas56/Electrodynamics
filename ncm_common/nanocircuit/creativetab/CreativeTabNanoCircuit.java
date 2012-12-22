package nanocircuit.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;

import nanocircuit.items.ItemManager;

public class CreativeTabNanoCircuit extends CreativeTabs
{
	public CreativeTabNanoCircuit(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {
        return ItemManager.itemPcb.shiftedIndex;
    }

}
