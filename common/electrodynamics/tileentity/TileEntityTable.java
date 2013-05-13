package electrodynamics.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.client.FXType;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketFX;
import electrodynamics.network.packet.PacketSound;
import electrodynamics.network.packet.PacketTableUpdate;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.recipe.RecipeTable;

public class TileEntityTable extends TileEntity {

	/** Type of table. 0 is Display Table, 1 is Smashing Table */
	public byte type;

	/** Item that was displayed previously. Can be null */
	public ItemStack prevDisplayedItem;
	
	/** Item to be displayed on the display table */
	public ItemStack displayedItem;

	public TileEntityTable() {
		this.type = 0;
	}

	public TileEntityTable(byte type) {
		this.type = type;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setByte("type", type);
		if (displayedItem != null) {
			nbt.setTag("displayedItem", displayedItem.writeToNBT(new NBTTagCompound()));
		}
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		this.type = nbt.getByte("type");
		if (nbt.hasKey("displayedItem")) {
			this.displayedItem = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("displayedItem"));
		}
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 2, nbt);
	}

	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}

	public void setItem(ItemStack item) {
		prevDisplayedItem = displayedItem;
		displayedItem = item;
	}
	
	public ItemStack getItem() {
		return displayedItem;
	}
	
	public void update() {
		if (itemChanged()) {
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.fillPacket(new PacketTableUpdate(xCoord, yCoord, zCoord, this.displayedItem)), this.worldObj.provider.dimensionId);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public boolean itemChanged() {
		if (prevDisplayedItem == null && displayedItem == null) {
			return false;
		}
		
		if (prevDisplayedItem != null && displayedItem == null) {
			return true;
		}
		
		if (prevDisplayedItem == null && displayedItem != null) {
			return true;
		}
		
		if (!ItemStack.areItemStacksEqual(prevDisplayedItem, displayedItem)) {
			return true;
		}
		
		return true;
	}
	
	public boolean hasRecipe(ItemStack tool) {
		if (type == 0 && displayedItem.getItem().itemID == Block.stoneSingleSlab.blockID) {
			return true;
		}
		
		return !(CraftingManager.getInstance().tableManager.getRecipe(displayedItem, tool) == null);
	}
	
	public void handleSmash(EntityPlayer player, ItemStack tool) {
		if (displayedItem != null) {
			if (this.type == 0 && displayedItem.getItem().itemID == Block.stoneSingleSlab.blockID) {
				this.displayedItem = null;
				this.worldObj.setBlock(xCoord, yCoord, zCoord, BlockIDs.BLOCK_TABLE_ID, 1, 2);
			} else if (this.type == 1) {
				RecipeTable recipe = CraftingManager.getInstance().tableManager.getRecipe(displayedItem, tool);
				
				if (recipe != null) {
					if (displayedItem.getItem() instanceof ItemBlock) {
						PacketFX packet = new PacketFX(FXType.BLOCK_BREAK, xCoord, yCoord, zCoord, new int[] {displayedItem.itemID, displayedItem.getItemDamage()});
						PacketDispatcher.sendPacketToAllAround(xCoord, yCoord + 2, zCoord, 64D, this.worldObj.provider.dimensionId, PacketTypeHandler.fillPacket(packet));
					}
					
					setItem(recipe.outputItem);
					tool.damageItem(recipe.hammerDamage, player);
					
					update();
					
					PacketSound sound = new PacketSound("electrodynamics.block.tableSmash", xCoord, yCoord, zCoord, PacketSound.TYPE_SOUND);
					PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 32D, this.worldObj.provider.dimensionId, PacketTypeHandler.fillPacket(sound));
				}
			}
		}
	}

}
