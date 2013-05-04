package electrodynamics.circuit.components;

import electrodynamics.circuit.Component;
import electrodynamics.circuit.ComponentContainer;

public class OR_Gate extends Component {
	
	public OR_Gate()
	{
		this.pin_id_read = new int[3];
		this.pin_id_write = new int[1];
	}
	
	public void onUpdate(ComponentContainer parent)
	{
		boolean input_pin[] = parent.readPin(this.pin_id_read);
		boolean output_pin[] = new boolean [1];
		
		for(int i = 0; i < input_pin.length; i++)
			output_pin[0] |= input_pin[i];
		
		parent.writePin(pin_id_write, output_pin);
	}
}
