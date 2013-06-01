package electrodynamics.item.elmag;

import java.util.List;

import electrodynamics.api.tool.IElMagModule;
import electrodynamics.core.CreativeTabED;
import electrodynamics.core.handler.GuiHandler;
import electrodynamics.interfaces.IInventoryItem;
import electrodynamics.inventory.InventoryItem;
import electrodynamics.lib.core.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemElMagArmor extends ItemArmor implements IInventoryItem {

	public ArmorType armorType;
	
	public Icon texture;
	
	public ItemElMagArmor(int id, int armorType) {
		super(id, EnumArmorMaterial.IRON, 2, armorType);
		setCreativeTab(CreativeTabED.tool);
		setMaxStackSize(1);
		setMaxDamage(0);
		
		this.armorType = ArmorType.values()[armorType];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		InventoryItem inv = this.getInventory(stack);
		
		if (inv != null && inv.getStackInSlot(0) != null) {
			list.add(((IElMagModule)inv.getStackInSlot(0).getItem()).getModuleName(stack));
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				GuiHandler.openGui(player, world, (int)player.posX, (int)player.posY, (int)player.posZ, GuiHandler.GuiType.TESLA_MODULE);
			} else {
				int pos = EntityLiving.getArmorPosition(stack) - 1;
				ItemStack currArmor = player.getCurrentArmor(pos);
				
				if (currArmor == null) {
					player.setCurrentItemOrArmor(pos, stack.copy());
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}
		}
		
		return stack;
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		InventoryItem inv = this.getInventory(itemStack);
		
		if (inv != null && inv.getStackInSlot(0) != null) {
			((IElMagModule)inv.getStackInSlot(0).getItem()).onArmorTick(world, player, itemStack);
		}
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return ModInfo.RESOURCES_BASE + "/armor/" + armorType.renderFile;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/" + armorType.textureFile);
	}
	
	public enum ArmorType {
		HELM("helmet", "tesla_1.png"),
		CHEST("chestplate", "tesla_1.png"),
		LEGS("leggings", "tesla_2.png"),
		BOOTS("boots", "tesla_1.png");
		
		public String textureFile;
		public String renderFile;
		
		private ArmorType(String textureFile, String renderFile) {
			this.textureFile = textureFile;
			this.renderFile = renderFile;
		}
	}

	@Override
	public InventoryItem getInventory(ItemStack stack) {
		return new InventoryItem(2, stack);
	}
	
}
