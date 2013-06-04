package electrodynamics.item;

import java.util.ArrayList;
import java.util.List;

import electrodynamics.util.ItemUtil;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.core.handler.GuiHandler;
import electrodynamics.interfaces.IInventoryItem;
import electrodynamics.inventory.InventoryItem;
import electrodynamics.lib.core.ModInfo;

public class ItemTray extends Item implements IInventoryItem {

	private Icon texture;
	
	public ItemTray(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(0);
		setCreativeTab(CreativeTabED.tool);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List list, boolean show) {
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("Items")) {
			NBTTagList items = stack.stackTagCompound.getTagList("Items");
			List<ItemStack> itemsList = new ArrayList<ItemStack>();
			
			for (int i=0; i<items.tagCount(); i++) {
				NBTTagCompound itemTag = (NBTTagCompound) items.tagAt(i);
				
				if (itemTag != null) {
					ItemStack item = ItemStack.loadItemStackFromNBT(itemTag);
					
					if (item != null) {
						itemsList.add( item );
					}
				}
			}
			itemsList = ItemUtil.trimItemsList( itemsList, true );
			for( ItemStack item : itemsList ) {
				list.add(item.stackSize +"x " + item.getDisplayName());
			}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote && player.isSneaking()) {
			GuiHandler.openGui(player, world, (int)player.posX, (int)player.posY, (int)player.posZ, GuiHandler.GuiType.TRAY);
		}
		
		return stack;
	}

	@Override
	public InventoryItem getInventory(ItemStack stack) {
		if (stack.getItem() instanceof IInventoryItem) {
			return new InventoryItem(9, stack);
		}
		
		return null;
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "tool/sinteringTray");
	}
	
}
