package electrodynamics.item.tesla;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.control.IKeyBoundClient;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.EDItems;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketJump;

public class ItemArmorTeslaBoots extends ItemArmor implements IKeyBoundClient {

	private Icon texture;
	
	private long timeChargeStarted;
	
	@SideOnly(Side.CLIENT)
	public boolean active;
	
	public ItemArmorTeslaBoots(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 3);
		setCreativeTab(CreativeTabED.tool);
		setMaxStackSize(1);
		setMaxDamage(0);
		
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			active = false;
		}
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		Item thisArmor = EDItems.itemTeslaBoots;
		return stack.getItem() == thisArmor ? ModInfo.RESOURCES_BASE + "/armor/tesla_1.png" : null;
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) return;
		if (!player.onGround) return;
		if (!active) return;
		
		if (GuiScreen.isCtrlKeyDown() && timeChargeStarted == 0) {
			timeChargeStarted = world.getWorldTime();
		}
		
		if (!(GuiScreen.isCtrlKeyDown()) && timeChargeStarted > 0) {
			long chargeTime = ((world.getWorldTime() - timeChargeStarted) / 20);
			if (chargeTime == 0) chargeTime = 1;
			
			timeChargeStarted = 0;
			
			PacketDispatcher.sendPacketToServer(PacketTypeHandler.fillPacket(new PacketJump(chargeTime)));
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/boots");
	}

	@Override
	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key) {
		if (key.equalsIgnoreCase(ConfigurationSettings.HOVER_MODE_TOGGLE_NAME)) {
			this.active = !active;
			player.addChatMessage("Jump Boost: " + (active == true ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF"));
		}
	}

}
