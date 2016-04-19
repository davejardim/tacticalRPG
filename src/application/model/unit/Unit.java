package application.model.unit;

import java.util.ArrayList;
import java.util.List;

import application.model.tile.UnitTile;
import javafx.scene.image.Image;

/**
 * Stores all information for a unit
 * 
 * @author markbluemer
 *
 */
public class Unit {

	private int yCord, xCord, travelDist, hp, attack, def, attackType, critChance, maxMove, canMove;
	private Image image;
	private boolean hasMoved;
	private UnitType type;

	public Unit(int x, int y, UnitType type, int team) {
		this.xCord = x;
		this.yCord = y;
		this.travelDist = 5; // TODO: Shouldn't be hard set
		this.hp = type.hp();
		this.attack = type.attack();
		this.def = type.def();
		this.attackType = type.attackType();
		this.critChance = type.critChance();
		this.maxMove = type.maxMove();
		this.hasMoved = false;
		this.image = type.sprite();
		this.canMove = type.canMove();
		this.type = type;
	}

	public Image getImage() {
		return this.image;
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

	public boolean getCanMove() {
		return canMove == 1;
	}
	public boolean getHasMoved() {
		return this.hasMoved;
	}

	public void switchMoved() {
		hasMoved = !hasMoved;
	}

	public List<UnitTile> attack(UnitTile[][] unitLocs) {
		List<UnitTile> list = new ArrayList<>();
		//if melee unit
		if(this.attackType==1){
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
		if(this.attackType==2){
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

	public UnitType getType(){
		return type;
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
		case PIKACHU:
			break;
		case WALL:
			break;
		default:
			break;
		}

	}
}
