package application.model.unit;

import java.util.ArrayList;
import java.util.List;

import application.model.tile.UnitTile;

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
	public List<UnitTile> attack(UnitTile[][] unitLocs) {
		List<UnitTile> list = new ArrayList<>();
		//if melee unit
		if(this.getType()==1){
			//if up 1
			if(unitLocs[this.yCord+1][this.xCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord+1][this.xCord]);
			}
			//if down 1
			if(unitLocs[this.yCord-1][this.xCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord-1][this.xCord]);
			}
			//if 1 right
			if(unitLocs[this.yCord][this.xCord+1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord][this.xCord+1]);
			}
			//if one left
			if(unitLocs[this.yCord][this.xCord-1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord][this.xCord-1]);
			}
		}
		//if ranged unit
		if(this.getType()==2){
			//if up right
			if(unitLocs[this.yCord+1][this.xCord+1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord+1][this.xCord+1]);
			}
			//if up left
			if(unitLocs[this.yCord+1][this.xCord-1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord+1][this.xCord-1]);
			}
			//if down right
			if(unitLocs[this.yCord-1][this.xCord+1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord-1][this.xCord+1]);
			}
			//if down left
			if(unitLocs[this.yCord-1][this.xCord-1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord+1][this.xCord+1]);
			}
			//if up 2
			if(unitLocs[this.yCord+2][this.xCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord+2][this.xCord]);
			}
			//if down 2
			if(unitLocs[this.yCord-2][this.xCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord-2][this.xCord]);
			}
			//if 2 right
			if(unitLocs[this.yCord][this.xCord+2].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord][this.xCord+2]);
			}
			//if 2 left
			if(unitLocs[this.yCord][this.xCord-2].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.yCord][this.xCord-2]);
			}
		}
		return list;
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
