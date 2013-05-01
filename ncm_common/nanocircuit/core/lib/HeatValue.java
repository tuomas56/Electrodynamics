package nanocircuit.core.lib;

public enum HeatValue {

	COLD(0, 0, 0),
	NORMAL(0, 0, 0),
	HOT(0, 0, 0);
	
	public int r;
	public int g;
	public int b;
	
	private HeatValue(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
}
