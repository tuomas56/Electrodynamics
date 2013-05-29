package electrodynamics.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.item.Ingot;
import electrodynamics.util.GLColor;

public class ItemIngot extends Item {

	private Icon[] textures;
	
	/** RGBA color definitions for ingots. Custom ingots first, then vanilla iron ingot, then vanilla gold ingot */
	public static int[][] ingotColors;
	
	static {
		//TODO Ingot colors + actual gold color for gold ingot
		ingotColors = new int[Ingot.values().length + 2][];
		
		// Iron Ingot
		ingotColors[Ingot.values().length] = new int[] {255, 255, 255, 255};
		// Gold Ingot
		ingotColors[Ingot.values().length + 1] = new int[] {255, 122, 122, 255};
	}
	
	public ItemIngot(int id) {
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabED.resource);
	}
	
	public static boolean isIngot(ItemStack stack) {
		return (stack.getItem() == EDItems.itemIngot) || (stack.getItem() == Item.ingotGold) || (stack.getItem() == Item.ingotIron);
	}
	
	public static GLColor getColorForIngot(ItemStack stack) {
		int[] colors = null;
		
		if (stack.getItem() == EDItems.itemIngot) {
			colors = ingotColors[stack.getItemDamage()];
		} else if (stack.getItem() == Item.ingotIron) {
			colors = ingotColors[Ingot.values().length];
		} else if (stack.getItem() == Item.ingotGold) {
			colors = ingotColors[Ingot.values().length + 1];
		}

		if (colors != null) {
			return new GLColor(colors[0], colors[1], colors[2], colors[3]);
		} else {
			return new GLColor(255, 255, 255, 255);
		}
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return textures[damage];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Ingot.get(stack.getItemDamage()).unlocalizedName + ".name";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (Ingot ingot : Ingot.values()) {
			list.add(new ItemStack(id, 1, ingot.ordinal()));
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[Ingot.values().length];
		
		for (int i=0; i<textures.length; i++) {
			textures[i] = register.registerIcon(Ingot.get(i).getTextureFile());
		}
	}
	
}
