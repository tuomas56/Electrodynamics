package electrodynamics.util;

import java.lang.reflect.Method;

import net.minecraft.entity.EntityLiving;
import electrodynamics.Electrodynamics;
import electrodynamics.core.EDLogger;

public class EntityReflectionWrapper {

	/* CONSTANTS */
	public static final String OBF_DROP_ID = "func_70633_aT";
	public static final String DROP_ID = "getDropItemId";
	public static final String OBF_DEATH_SOUND = "func_70673_aS";
	public static final String DEATH_SOUND = "getDeathSound";
	
	private EntityLiving entity;
	
	public EntityReflectionWrapper(EntityLiving entity) {
		this.entity = entity;
	}
	
	public int getMainDropID() {
		try {
			Method dropID = EntityLiving.class.getDeclaredMethod(Electrodynamics.instance.obfuscated == true ? OBF_DROP_ID : DROP_ID, new Class[0]);
			dropID.setAccessible(true);
			return (int) dropID.invoke(this.entity, new Object[0]);
		} catch(Exception ex) {
			EDLogger.warn("Failed to get method " + DROP_ID + " from Entity " + this.entity.getEntityName());
			EDLogger.warn("Reason: " + ex.getLocalizedMessage());
		}
		
		return 0;
	}
	
	public String getDeathSound() {
		try {
			Method deathSound = EntityLiving.class.getDeclaredMethod(Electrodynamics.instance.obfuscated == true ? OBF_DEATH_SOUND : DEATH_SOUND, new Class[0]);
			deathSound.setAccessible(true);
			return (String) deathSound.invoke(this.entity, new Object[0]);
		} catch(Exception ex) {
			EDLogger.warn("Failed to get method " + DEATH_SOUND + " from Entity " + this.entity.getEntityName());
			EDLogger.warn("Reason: " + ex.getLocalizedMessage());
		}
		
		return "";
	}
	
}
