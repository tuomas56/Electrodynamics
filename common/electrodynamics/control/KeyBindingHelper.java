package electrodynamics.control;

import java.util.ArrayList;

import net.minecraft.client.settings.KeyBinding;

public class KeyBindingHelper {

	public static ArrayList<KeyBinding> bindingList;
	public static ArrayList<Boolean> isRepeatingList;
	
	public static void addKeyBinding(String name, int value, boolean repeats) {
		if (bindingList == null) {
			bindingList = new ArrayList<KeyBinding>();
		}
		
		if (isRepeatingList == null) {
			isRepeatingList = new ArrayList<Boolean>();
		}
		
		bindingList.add(new KeyBinding(name, value));
		isRepeatingList.add(repeats);
	}
	
	public static KeyBinding[] getBindings() {
		return bindingList.toArray(new KeyBinding[bindingList.size()]);
	}

	public static boolean[] getIsRepeating() {
		boolean[] isRepeating = new boolean[bindingList.size()];
		
		for (int i=0; i<isRepeating.length; i++) {
			isRepeating[i] = isRepeatingList.get(i);
		}
		
		return isRepeating;
	}
	
}
