package application.model.unit;

//marc bluemer 
//Mike Donohue

public enum UnitType {
	ARCHER(20, 7, 3, 2, 10, 2),
    ASSASSIN(21, 8, 2, 1, 50, 2),
	CAVILIER(25, 7, 3, 1, 10, 4),
	KNIGHT(28, 5, 5, 1, 5, 2),
	KING(30, 7, 4, 1, 10, 2),
	MAGE(20, 8, 4, 3, 10, 2);
	
	private final int hp;
	private final int attack;
	//Damage will be enemy hp - (unit attack - enemy defense) *2 if crit == true
	//keep attack higher than defense on average
    private final int def;
    private final int attackType; 
    //attackType{1,2}
    //1 = melee unit, 8 squares around unit, other type 1 units will counter attack, type 2 will not
    //2 = ranged unit, 4 squares diagonal to the units and the squares 2 away directly vertical and horizontal from the unit, 
    	//type 1 cant counter attack, type 2 can
    private final int critChance;
    //random number generator from 1 to 100. Crit if rand() < critChance
    private final int maxMove;
    
    UnitType(int hp, int attack, int def, int attackType, int critChance,int maxMove) {
       this.hp = hp;
       this.attack = attack;
       this.def = def;
       this.attackType = attackType;
       this.critChance = critChance;
       this.maxMove = maxMove;
    }
    
    private int hp() { 
    	return hp; }
    private int attack() { 
    	return attack; }
    private int def() { 
    	return def; }
    private int attackType() { 
    	return attackType; }
    private int critChance() { 
    	return critChance; }
    private int maxMove() { 
    	return maxMove; }
    
}




