package electrodynamics.circuit;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

public abstract class Component {
	
	//A type of the component
	protected int type;
	
	//Used to request pin state from parent component
	//Used to check if interesting us pin is changed
	protected int pin_id_read[];
	
	//Used to tell parent which his pins he needs to update
	protected int pin_id_write[];
	
	public Component()
	{
		
	}
	
	abstract public void onUpdate(ComponentContainer parent);
	
	public int[] getReadPinID()
	{
		return this.pin_id_read;
	}
	
	public int[] getWritePinID()
	{
		return this.pin_id_write;
	}
	
	public boolean hasInterestingPin(ArrayList <Integer> ID)
	{
		for(int i : this.pin_id_read)
			if(ID.contains(i))
				return true;
		
		return false;
	}
	
	public void writeToNBT(NBTTagCompound nbt) 
	{
		nbt.setInteger("compType", this.type);
		nbt.setIntArray("pinIdRead", this.pin_id_read);
		nbt.setIntArray("pinIdWrite", this.pin_id_write);
	}
	
	public void readFromNBT(NBTTagCompound nbt) 
	{
		this.type = nbt.getInteger("compType");
		this.pin_id_read = nbt.getIntArray("pinIdRead");
		this.pin_id_write = nbt.getIntArray("pinIdWrite");
	}
}
