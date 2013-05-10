package electrodynamics.recipe;

import electrodynamics.api.tool.ToolType;

public enum TableRecipeType {

	SMASH(ToolType.HAMMER),
	SIEVE(ToolType.SIEVE);
	
	public ToolType requiredTool;
	
	private TableRecipeType(ToolType requiredTool) {
		this.requiredTool = requiredTool;
	}
	
}
