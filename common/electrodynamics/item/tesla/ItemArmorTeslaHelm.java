package electrodynamics.item.tesla;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.control.IKeyBoundClient;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.ItemHandler;
import electrodynamics.lib.ModInfo;

public class ItemArmorTeslaHelm extends ItemArmor implements IKeyBoundClient {

	private Icon texture;
	
	@SideOnly(Side.CLIENT)
	public boolean thermalEnabled = false;
	
	public ItemArmorTeslaHelm(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 0);
		setCreativeTab(CreativeTabED.item);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return stack.getItem() == ItemHandler.itemTeslaHelm ? ModInfo.RESOURCES_BASE + "/armor/tesla_1.png" : null;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/helmet");
	}

	@SideOnly(Side.CLIENT)
    public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY) {
		if (thermalEnabled) {
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glColor4f(50, 0, 140, 0.05F);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.addVertex(0.0D, (double) resolution.getScaledHeight(), -90.0D);
			tessellator.addVertex((double) resolution.getScaledWidth(), (double) resolution.getScaledHeight(), -90.0D);
			tessellator.addVertex((double) resolution.getScaledWidth(), 0.0D, -90.0D);
			tessellator.addVertex(0.0D, 0.0D, -90.0D);
			tessellator.draw();
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key) {
		if (key.equals(ConfigurationSettings.THERMAL_VIEW_TOGGLE_NAME)) {
			if (FMLClientHandler.instance().hasOptifine()) {
				player.addChatMessage(EnumChatFormatting.RED + "OPTIFINE DETECTED: " + EnumChatFormatting.RESET + "The thermal render is currently broken when used with Optifine.");
				player.addChatMessage("This feature will remain disabled.");
				return;
			}
			
			this.thermalEnabled = !thermalEnabled;
			player.addChatMessage("Thermal View: " + (thermalEnabled == true ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF"));
		}
	}
	
}
