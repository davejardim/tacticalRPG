package application.model.unit;

/**
 * Stores all information for a unit
 * 
 * @author markbluemer
 *
 */
public class Unit {
	
	private int yCord, xCord, travelDist;
	
	public Unit(int x, int y, UnitType type) {
		this.xCord = x;
		this.yCord = y;
		this.travelDist = 5; // TODO: Shouldn't be hard set
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
	
	public int getTravelDist() {
		return this.travelDist;
	}
	public int attack() {
		
		return this.xCord;
	}
	
	private void setUnitStats(UnitType type) {
		switch (type) {
		case ASSASSIN:
			break;
		case ARCHER:
			break;
		case CAVILIER:
			break;
		case KNIGHT:
			break;
		case KING:
			break;
		case MAGE:
			break;
		}
			
	}
}
