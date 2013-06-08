package electrodynamics.tileentity;

import electrodynamics.inventory.InventoryItem;
import electrodynamics.item.EDItems;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.recipe.RecipeKiln;
import electrodynamics.util.InventoryUtil;
import electrodynamics.util.ItemUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TileEntityBasicKiln extends TileEntityMachine {

	public final float ROTATIONAL_MAX = 1.5F;
	public float doorAngle = 0;
	public boolean open = false;

	public int fuelLevel = 0;
	public boolean burning = false;
	public int currentCookTime;
	public int totalCookTime;

	public InventoryItem trayInventory;


	@Override
	public void updateEntityClient() {
		if( open && doorAngle <= ROTATIONAL_MAX ) {
			doorAngle += 0.2F;
		} else if( !open && doorAngle > 0 ) {
			doorAngle -= 0.2F;
		}

		if( doorAngle < 0 )
			doorAngle = 0;
		if( doorAngle > ROTATIONAL_MAX )
			doorAngle = ROTATIONAL_MAX;
	}

	@Override
	public void updateEntityServer() {
		if( fuelLevel > 0 ) {
			--this.fuelLevel;

			if( open ) return;

			if( totalCookTime > 0 ) {
				if( trayInventory == null ) {
					totalCookTime = 0;
				} else {
					if( currentCookTime == 0 ) {
						RecipeKiln recipe = getCurrentRecipe();

						if( recipe != null ) {
							doProcess( recipe );

							this.totalCookTime = 0;
							this.currentCookTime = 0;
							sendTrayUpdate();
							return;
						}
					} else {
						--currentCookTime;
					}
				}
			} else {
				if( trayInventory != null ) {
					RecipeKiln recipe = getCurrentRecipe();
					if( recipe != null ) {
						this.totalCookTime = this.currentCookTime = recipe.processingTime;
						return;
					}
				}
			}

		} else if( burning ) {
			burning = false;
			sendBurningUpdate();
		}
	}

	@Override
	public void onBlockBreak() {
		if( trayInventory != null )
			InventoryUtil.ejectItem( worldObj, xCoord, yCoord, zCoord, ForgeDirection.UP, trayInventory.parent.copy(), new Random() );
	}

	@Override
	public void onBlockActivated(EntityPlayer player) {
		if( player.isSneaking() ) { // open and close the door.
			this.open = !this.open;
			sendOpenUpdate();
			return;
		}

		if( !open ) return;

		ItemStack playerHeldItem = player.getHeldItem();
		if( playerHeldItem != null ) {
			if( ItemUtil.getFuelValue( playerHeldItem ) > 0 ) { // refuel
				this.fuelLevel += ItemUtil.getFuelValue( playerHeldItem );
				--playerHeldItem.stackSize;
				if( playerHeldItem.itemID == Item.bucketLava.itemID ) { // return the empty bucket when using lava as fuel.
					ItemStack bucket = new ItemStack( Item.bucketEmpty, 1 );
					if( playerHeldItem.stackSize == 0 )
						player.setCurrentItemOrArmor( 0, bucket );
					else
						player.inventory.addItemStackToInventory( bucket );
				}
				this.burning = true;
				sendBurningUpdate();
				((EntityPlayerMP) player).updateHeldItem();

			} else if( trayInventory == null && playerHeldItem.getItem() == EDItems.itemTrayKiln ) { // place tray

				this.trayInventory = new InventoryItem( 4, playerHeldItem.copy(), 16 );
				--playerHeldItem.stackSize;

				sendTrayUpdate();
				((EntityPlayerMP) player).updateHeldItem();
			}

		} else if( this.trayInventory != null ) { // remove tray
			player.setCurrentItemOrArmor( 0, trayInventory.parent.copy() );

			this.trayInventory = null;
			this.currentCookTime = 0;
			this.totalCookTime = 0;

			sendTrayUpdate();
			((EntityPlayerMP) player).updateHeldItem();
		}
	}

	// ----- Internal functionality -----

	private RecipeKiln getCurrentRecipe() {
		List<ItemStack> items = Arrays.asList( this.trayInventory.inventory );
		return CraftingManager.getInstance().kilnManager.getKilnRecipe( items );
	}

	private void doProcess(RecipeKiln recipe) {
		// Remove ingredients from the tray inventory
		ItemUtil.removeItemsFromArray( recipe.getInputs(), trayInventory.inventory );

		// Give the outputs
		for( ItemStack output : recipe.getOutputs() ) {
			InventoryUtil.addToInventory( trayInventory, output.copy() );
		}
	}


	// ----- NBT and sync packets -----

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT( tag );
		tag.setBoolean( "open", open );
		tag.setInteger( "fuelLevel", fuelLevel );
		if( this.trayInventory != null ) {
			this.trayInventory.writeToNBT( tag );
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT( tag );
		open = tag.getBoolean( "open" );
		this.fuelLevel = tag.getInteger( "fuelLevel" );
		this.burning = this.fuelLevel > 0;
		if( tag.hasKey( "Items" ) ) {
			this.trayInventory = new InventoryItem( 4, new ItemStack( EDItems.itemTrayKiln ), 16 );
			this.trayInventory.readFromNBT( tag );
		}
	}

	@Override
	public void getDescriptionForClient(NBTTagCompound nbt) {
		super.getDescriptionForClient( nbt );
		nbt.setBoolean( "open", open );
		nbt.setBoolean( "burning", burning );
		nbt.setInteger( "fuelLevel", fuelLevel );
		if( this.trayInventory != null ) {
			this.trayInventory.writeToNBT( nbt );
		}
	}

	@Override
	public void onDescriptionPacket(NBTTagCompound nbt) { // load
		super.onDescriptionPacket( nbt );
		this.open = nbt.getBoolean( "open" );
		this.burning = nbt.getBoolean( "burning" );
		this.fuelLevel = nbt.getInteger( "fuelLevel" );
		if( nbt.hasKey( "Items" ) ) {
			this.trayInventory = new InventoryItem( 4, new ItemStack( EDItems.itemTrayKiln ), 16 );
			this.trayInventory.readFromNBT( nbt );
		} else {
			this.trayInventory = null;
		}
	}

	@Override
	public void onUpdatePacket(NBTTagCompound nbt) { // update
		super.onUpdatePacket( nbt );
		if( nbt.hasKey( "open" ) ) {
			this.open = nbt.getBoolean( "open" );
		}
		if( nbt.hasKey( "burning" ) ) {
			this.burning = nbt.getBoolean( "burning" );
		}
		if( nbt.hasKey( "noTray" ) ) {
			this.trayInventory = null;
		}
		if( nbt.hasKey( "Items" ) ) {
			this.trayInventory = new InventoryItem( 4, new ItemStack( EDItems.itemTrayKiln ), 16 );
			this.trayInventory.readFromNBT( nbt );
		}
	}

	private void sendOpenUpdate() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean( "open", this.open );
		sendUpdatePacket( nbt );
	}

	private void sendBurningUpdate() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean( "burning", this.burning );

		sendUpdatePacket( nbt );
	}

	private void sendTrayUpdate() {
		NBTTagCompound nbt = new NBTTagCompound();
		if( this.trayInventory != null )
			this.trayInventory.writeToNBT( nbt );
		else
			nbt.setBoolean( "noTray", true );

		sendUpdatePacket( nbt );
	}

}
