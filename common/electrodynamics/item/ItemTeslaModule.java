package electrodynamics.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.api.tool.ITeslaModule;
import electrodynamics.core.CreativeTabED;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.item.TeslaModule;
import electrodynamics.util.StringUtil;

public class ItemTeslaModule extends Item implements ITeslaModule {

	private Icon[] textures;
	
	public ItemTeslaModule(int id) {
		super(id);
		setMaxStackSize(1);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabED.tool);
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return textures[damage];
	}
	
	@Override
	public String getModuleName(ItemStack stack) {
		return EDLanguage.getInstance().translate(TeslaModule.get(stack.getItemDamage()).unlocalizedName);
	}

	@Override
	public String getModuleDescription(ItemStack stack) {
		return EDLanguage.getInstance().translate(TeslaModule.get(stack.getItemDamage()).moduleDescription);
	}

	@Override
	public int[] validArmorTypes(ItemStack stack) {
		return TeslaModule.get(stack.getItemDamage()).validArmorTypes;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		TeslaModule.get(itemStack.getItemDamage()).teslaLogic.onArmorTick(world, player, itemStack);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		if (GuiScreen.isShiftKeyDown()) {
			List<String> strings = StringUtil.splitStringAfterNChars(EDLanguage.getInstance().translate(TeslaModule.get(stack.getItemDamage()).moduleDescription), 25);
			for (String string : strings) {
				list.add(string);
			}
		} else {
			list.add("Press SHIFT to see description");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (TeslaModule module : TeslaModule.values()) {
			list.add(module.toItemStack());
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.textures = new Icon[TeslaModule.values().length];
		
		for (TeslaModule module : TeslaModule.values()) {
			this.textures[module.ordinal()] = register.registerIcon(module.getTextureFile());
		}
	}

}
