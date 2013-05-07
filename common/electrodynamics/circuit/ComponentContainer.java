package electrodynamics.circuit;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

public class ComponentContainer extends Component {
	
	//Array of included components
	protected ArrayList <Component> components = new ArrayList <Component>();
	
	//State of internal pins now
	protected boolean pins_now[];
	
	//State of internal pins in next step of simulation
	protected boolean pins_next[];
	
	//Which pin has changed since previous step?
	protected boolean pins_changed[];
	
	public ComponentContainer()
	{
		
	}
	
	@Override
	public void onUpdate(ComponentContainer parent)
	{
		ArrayList <Integer> changedID = new ArrayList <Integer>();
		
		for(int i = 0; i < pins_changed.length; i++)
			if(pins_changed[i])
				changedID.add(i);
		
		for(Component comp : components)
			if(comp.hasInterestingPin(changedID))
				comp.onUpdate(this);
		
		for(int i = 0; i < pins_now.length; i++)
			pins_now[i] = pins_next[i];
	}
	
	public boolean[] readPin(int ids[])
	{
		boolean ret[] = new boolean[ids.length];
		
		for(int i : ids)
			ret[i] = pins_now[ids[i]];
		
		return ret;
	}
	
	public void writePin(int ids[], boolean values[])
	{
		if(ids.length != values.length)
		{
			//throw exception
		}
		
		for(int i : ids)
		{
			pins_next[ids[i]] = values[i];
			if(pins_now[ids[i]] != pins_next[ids[i]])
				pins_changed[ids[i]] = true;
			else
				pins_changed[ids[i]] = false;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		super.writeToNBT(nbt);
		nbt.setInteger("componentAmount", this.components.size());
		
		for(Component comp : components)
			comp.writeToNBT(nbt);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		super.readFromNBT(nbt);
		int i = nbt.getInteger("componentAmount");
		
		//TODO:
		//Somehow use proper component's constructor based on it's type
	}

}
