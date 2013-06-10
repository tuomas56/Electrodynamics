package electrodynamics.core.misc;

import electrodynamics.core.lang.EDLanguage;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;

public class DamageSourceBlock extends DamageSource {

	public int x;
	public int y;
	public int z;
	
	public String message;
	
	public DamageSourceBlock() {
		super("grind");
		
		setDamageBypassesArmor();
	}

	public DamageSourceBlock(String message, int x, int y, int z) {
		this();
		
		this.message = message;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String getDeathMessage(EntityLiving entity) {
		String death = "death.attack." + this.damageType;
		return entity.getEntityName() + " " + EDLanguage.getInstance().translate(death);
	}
	
}
