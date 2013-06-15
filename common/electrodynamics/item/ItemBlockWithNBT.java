package electrodynamics.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.ItemIDs;
import electrodynamics.util.StringUtil;

// Technically a hidden item. While it has an ID, it will mimic the ItemBlock of whatever block is
// assigned to it. It's sole purpose is to allow blocks out of this mod's control to preserve TileEntity
// NBT data

public class ItemBlockWithNBT extends Item {

	public ItemBlockWithNBT(int id) {
		super(id);
		setMaxStackSize(64);
		setMaxDamage(0);
	}
	
	public static ItemStack generate(int id, int meta, NBTTagCompound nbt) {
		ItemStack stack = new ItemStack(ItemIDs.ITEM_NBT_BLOCK_ID, 1, 0);
		encode(stack, id, meta, nbt);
		return stack;
	}
	
	public static String getBlockName(ItemStack stack) {
		if (stack.getTagCompound() == null) {
			return null;
		}
		
		if (stack.getTagCompound().getCompoundTag("block") == null) {
			return null;
		}
		
		ItemStack block = getStoredBlock(stack);
		
		return block != null ? block.getDisplayName() : null;
	}
	
	public static ItemStack getStoredBlock(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("block");
		
		if (nbt.hasKey("bid") && nbt.hasKey("bmeta")) {
			int id = nbt.getInteger("bid");
			int meta = nbt.getInteger("bmeta");
			
			return new ItemStack(id, 1, meta);
		}
		
		return null;
	}
	
	/** Decode an item nbt stack to get the block data as well as the TileEntiy data if it exists. CAN RETURN NULL!
	 * @return Array of data. 0 = id, 1 = meta, 2 = TileEntity NBT if exists (can be null)
	 */
	public static Object[] decode(ItemStack stack) {
		if (stack == null) {
			return null;
		}
		
		if (stack.getTagCompound() == null) {
			return null;
		}
		
		if (stack.getTagCompound().getCompoundTag("block") == null) {
			return null;
		}
		
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt != null && nbt.hasKey("bid") && nbt.hasKey("bmeta")) {
			int id = nbt.getInteger("bid");
			int meta = nbt.getInteger("bmeta");
			NBTTagCompound data = null;
			
			if (nbt.hasKey("bdata")) {
				data = nbt.getCompoundTag("bdata");
			}
			
			return new Object[] {id, meta, data};
		}
		
		return null;
	}
	
	public static void encode(ItemStack stack, int id, int meta, NBTTagCompound teNBT) {
		if (stack == null) {
			return;
		}
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		NBTTagCompound nbt = stack.getTagCompound();
		NBTTagCompound block = new NBTTagCompound();
		nbt.setInteger("bid", id);
		nbt.setInteger("bmeta", meta);
		
		if (teNBT != null) {
			nbt.setCompoundTag("bdata", teNBT);
		}
		
		nbt.setCompoundTag("block", block);
		stack.setTagCompound(nbt);
	}
	
	@Override
	public String getItemDisplayName(ItemStack stack) {
		String blockName = getBlockName(stack);
		
		return blockName != null ? ItemBlockWithNBT.getBlockName(stack) + "(NBT)" : "Unnamed";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		if (GuiScreen.isShiftKeyDown()) {
			List<String> strings = StringUtil.splitStringAfterNChars(EDLanguage.getInstance().translate(Strings.ITEM_NBT_HELP_DESC), 35);
			for (String string : strings) {
				list.add(string);
			}
		} else {
			list.add(EDLanguage.getInstance().translate(Strings.ITEM_NBT_HELP_MESSAGE));
		}
	}
	
}
