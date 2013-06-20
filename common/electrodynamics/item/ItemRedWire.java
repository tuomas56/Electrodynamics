package electrodynamics.item;

import net.minecraft.item.ItemBlock;

public class ItemRedWire extends ItemBlock{
	public ItemRedWire(int id){
		super(id);
	}
	
	@Override
	public int getMetadata(int i){
		return 7;
	}
}