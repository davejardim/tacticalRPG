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

	private final int hpTotal;
	private int yCord, xCord, hp, attack, def, attackType, critChance, maxMove, team;
	private Image image;
	private boolean hasMoved, canMove;
	private UnitType type;

	public Unit(int x, int y, UnitType type, int team) {
		this.xCord = x;
		this.yCord = y;
		this.hpTotal = type.hp();
		this.hp = type.hp();
		this.attack = type.attack();
		this.def = type.def();
		this.attackType = type.attackType();
		this.critChance = type.critChance();
		this.maxMove = type.maxMove();
		this.hasMoved = false;
		this.image = type.sprite();
		this.canMove = type.canMove();
		this.team = team;
		this.type = type;
	}
	public int getHpTotal() {
		return hpTotal;
	}
	public int getHp(){
		return hp;
	}
	public int getAttack(){
		return attack;
	}
	public int getDef(){
		return def;
	}
	public int getAttackType(){
		return attackType;
	}
	public int getCritChance(){
		return critChance;
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
	
	public void setHp(int hp){
		this.hp = hp;
	}

	public int getMaxMove() {
		return this.maxMove;
	}

	public boolean getCanMove() {
		return canMove;
	}
	public boolean getHasMoved() {
		return this.hasMoved;
	}

	public void setMoved(boolean b) {
		hasMoved = b;
	}
	
	public UnitType getType() {
		return this.type;
	}
	
	public int getTeam() {
		return team;
	}

	public List<Unit> attackOptions(UnitTile[][] unitLocs) {
		List<Unit> list = new ArrayList<>();
		//if melee unit
		if(this.attackType==1){
			//if up 1
			if(unitLocs[this.xCord+1][this.yCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord+1][this.yCord].getUnit());
			}
			//if down 1
			if(unitLocs[this.xCord-1][this.yCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord-1][this.yCord].getUnit());
			}
			//if 1 right
			if(unitLocs[this.xCord][this.yCord+1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord][this.yCord+1].getUnit());
			}
			//if one left
			if(unitLocs[this.xCord][this.yCord-1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord][this.yCord-1].getUnit());
			}
		}
		//if ranged unit
		if(this.attackType==2){
			//if up right
			if(unitLocs[this.xCord+1][this.yCord+1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord+1][this.yCord+1].getUnit());
			}
			//if up left
			if(unitLocs[this.xCord+1][this.yCord-1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord+1][this.yCord-1].getUnit());
			}
			//if down right
			if(unitLocs[this.xCord-1][this.yCord+1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord-1][this.yCord+1].getUnit());
			}
			//if down left
			if(unitLocs[this.xCord-1][this.yCord-1].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord+1][this.yCord+1].getUnit());
			}
			//if up 2
			if(unitLocs[this.xCord+2][this.yCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord+2][this.yCord].getUnit());
			}
			//if down 2
			if(unitLocs[this.xCord-2][this.yCord].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord-2][this.yCord].getUnit());
			}
			//if 2 right
			if(unitLocs[this.xCord][this.yCord+2].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord][this.yCord+2].getUnit());
			}
			//if 2 left
			if(unitLocs[this.xCord][this.yCord-2].getUnit()==null){
			}
			else{
				list.add(unitLocs[this.xCord][this.yCord-2].getUnit());
			}
		}
		return list;
	}
	
	public void attack(Unit attackee){
		//melee attackers
		if(this.attackType==1){
			if(attackee.getAttackType()==1){
				//attackee takes damage
				attackee.setHp(attackee.getHp()-this.getAttack());
				if(attackee.getHp()==0){
					//death
				}
				else{
					this.hp = this.hp - attackee.getAttack();
				}
			}
			if(attackee.getAttackType()==2){
				attackee.setHp(attackee.getHp() - this.getAttack());
				if(attackee.getHp()==0){
					//death
				}
			}
		}
		//ranged
		if(this.attackType==2){
			if(attackee.getAttackType()==2){
				attackee.setHp(attackee.getHp()-this.getAttack());
				if(attackee.getHp()==0){
					//death
				}
				else{
					this.hp = this.hp - attackee.getAttack();
				}
			}
			if(attackee.getAttackType()==1){
				attackee.setHp(attackee.getHp() - this.getAttack());
				if(attackee.getHp()==0){
					//death
				}
			}
		}	
	}

	private void setUnitStats(UnitType type) {
		switch (type) {
		case ASSASSIN:
			break;
		case ARCHER:
			break;
		case LINK:
			break;
		case MARIO:
			break;
		case MJ:
			break;
		case CAP:
			break;
		case WALL:
			break;
		case PIKACHU:
			break;
		case KOFFING:
			break;
		}

	}
}
