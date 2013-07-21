package electrodynamics.inventory;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SimpleInventory implements IInventory, INBTTagable {


	public final int size;
	public final String name;

	protected final ItemStack[] internalInv;

	public SimpleInventory(int size, String name) {
		this.size = size;
		this.name = name;
		this.internalInv = new ItemStack[size];
	}

	@Override
	public int getSizeInventory() {
		return size;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if( i < 0 || i >= size ) return null;
		return internalInv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int amount) {
		if( i < 0 || i >= size ) return null;

		ItemStack stackInSlot = getStackInSlot( i );
		if( stackInSlot != null ) {
			ItemStack retValue;
			if( amount >= stackInSlot.stackSize ) {
				retValue = stackInSlot.copy();
				internalInv[i] = null;
			} else {
				retValue = stackInSlot.splitStack( amount );
			}
			onInventoryChanged();
			return retValue;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return null; // unsupported
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if( i < 0 || i >= size ) return;
		internalInv[i] = itemstack.copy();
		internalInv[i].stackSize = Math.min( itemstack.stackSize, getInventoryStackLimit() );
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() { }

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() { }

	@Override
	public void closeChest() { }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		NBTTagList list = new NBTTagList();

		for( int i = 0; i < size; i++ ) {
			if( internalInv[i] != null ) {
				NBTTagCompound tempTag = new NBTTagCompound();
				tempTag.setByte( "Slot", (byte) i );
				internalInv[i].writeToNBT( tempTag );
				list.appendTag( tempTag );
			}
		}

		tag.setTag( name, list );
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		if( tag.hasKey( name ) ) {
			NBTTagList items = tag.getTagList( name );

			for( int i = 0; i < items.tagCount(); i++ ) {
				NBTTagCompound tempTag = (NBTTagCompound) items.tagAt( i );

				if( tempTag != null ) {
					byte slotByte = tempTag.getByte( "Slot" );
					internalInv[slotByte] = ItemStack.loadItemStackFromNBT( tempTag );
				}
			}
		}
	}

}
