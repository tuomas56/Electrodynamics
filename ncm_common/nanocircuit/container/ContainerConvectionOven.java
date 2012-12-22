package nanocircuit.container;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

import nanocircuit.tile.TileConvectionOven;

public class ContainerConvectionOven extends Container
{
    protected TileConvectionOven tileEntity;
    
    public  ContainerConvectionOven(InventoryPlayer inventoryPlayer, TileConvectionOven te)
    {
            tileEntity = te;
            addSlotToContainer(new Slot(tileEntity, 0, 76, 37));
            bindPlayerInventory(inventoryPlayer);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) 
    {
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            if (slotObject != null && slotObject.getHasStack()) 
            {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    if (slot == 0) 
                    {
                            if (!mergeItemStack(stackInSlot, 1,
                                            inventorySlots.size(), true)) 
                            {
                                    return null;
                            }
                    
                    } else if (!mergeItemStack(stackInSlot, 0, 1, false)) 
                    {
                            return null;
                    }

                    if (stackInSlot.stackSize == 0) 
                    {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }
            }

            return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) 
    {
            return tileEntity.isUseableByPlayer(player);
    }


    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) 
    {
            for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                            addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                            8 + j * 18, 84 + i * 18));
                    }
            }

            for (int i = 0; i < 9; i++) {
                    addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
            }
    }
}
