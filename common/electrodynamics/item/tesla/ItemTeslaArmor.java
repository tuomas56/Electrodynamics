package electrodynamics.item.tesla;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemTeslaArmor extends ItemArmor {

	public ArmorType armorType;
	
	public Icon texture;
	
	public ItemTeslaArmor(int id, int armorType) {
		super(id, EnumArmorMaterial.IRON, 2, armorType);
		setCreativeTab(CreativeTabED.tool);
		setMaxStackSize(1);
		setMaxDamage(0);
		
		this.armorType = ArmorType.values()[armorType];
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
	
}
