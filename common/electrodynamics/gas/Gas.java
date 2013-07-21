package electrodynamics.gas;

import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Gas {

	/** Unique identifier for this particular gas */
	public String stringID;
	
	/** Unlocalized name for this gas */
	public String unlocalizedName;
	
	/** Texture used for showing this gas in world/in GUIs/pipes/other implementations */
	@SideOnly(Side.CLIENT)
	public Icon texture;
	
	/** Gas density. Up to modders to implement */
	public float density;
	
	/** If this gas takes form in world, which block should it associate with */
	public int blockID = -1;
	
	public Gas(String stringID) {
		this.stringID = stringID;
		this.unlocalizedName = "gas." + stringID + ".name";
	}
	
	public Gas setIcon(Icon icon) {
		this.texture = icon;
		return this;
	}
	
	public Gas setDensity(float density) {
		this.density = density;
		return this;
	}
	
	public Gas setBlock(int id) {
		this.blockID = id;
		return this;
	}
	
}
