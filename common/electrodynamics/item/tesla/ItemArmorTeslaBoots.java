package electrodynamics.item.tesla;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.EDItems;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketJump;

public class ItemArmorTeslaBoots extends ItemArmor {

	private Icon texture;
	
	private long timeChargeStarted;
	
	private boolean active;
	
	public ItemArmorTeslaBoots(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 3);
		setCreativeTab(CreativeTabED.item);
		setMaxStackSize(1);
		setMaxDamage(0);
		
		active = true;
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

}
