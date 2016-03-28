package application.model.unit;

/**
 * Stores all information for a unit
 * 
 * @author markbluemer
 *
 */
public class Unit {
	
	private int yCord, xCord;
	
	public Unit(int x, int y, UnitType type) {
		this.xCord = x;
		this.yCord = y;
		setUnitStats(type);
	}
	
	public int getXCord() {
		return this.xCord;
	}
	
	public int getYCord() {
		return this.yCord;
	}
	
	public void setXCord(int x) {
		this.xCord = x;
	}
	
	public void setYCord(int y) {
		this.yCord = y;
	}
	
	private void setUnitStats(UnitType type) {
		switch (type) {
		case SOLDIER:
			break;
		case HORSE:
			break;
		}
			
	}
}
