package nanocircuit.items;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import nanocircuit.core.Reference;

public class ItemNCOre extends ItemBlock
{
    public ItemNCOre(int i)
    {
        super(i);
        setHasSubtypes(true);
        setItemName("ncOre");
    }

    public int getPlacedBlockMetadata(int i)
    {
        return i;
    }

    public int getMetadata(int i)
    {
        return i;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case Reference.BLOCK_MAGNETITE_META:
                return "tile.oreMagnetite";

            case Reference.BLOCK_NICKEL_META:
                return "tile.oreNickel";
        }

        throw new IndexOutOfBoundsException();
    }
}
