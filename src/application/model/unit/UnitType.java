package application.model.unit;

import javafx.scene.image.Image;

//marc bluemer 
//Mike Donohue

public enum UnitType {
	//hp/attack/def/attackType/critChance/maxMove/canMove/Image
	ARCHER(20, 7, 3, 2, 10, 2, true, new Image("/application/resources/Man_256x256.png")),
    ASSASSIN(21, 8, 2, 1, 50, 2, true, new Image("/application/resources/Man_256x256.png")),
	LINK(25, 7, 3, 1, 10, 4, true, new Image("/application/resources/toon_link.gif")),
	MARIO(28, 5, 5, 1, 5, 2, true,new Image("/application/resources/Mario.gif")),
	MJ(30, 7, 4, 1, 10, 2, true, new Image("/application/resources/MJ.gif")),
	CAP(20, 8, 4, 3, 10, 2, true, new Image("/application/resources/Cap.gif")),
	WALL(0,0,0,0,0,0, false, new Image("/application/resources/brick_wall.png")),
	PIKACHU(21, 8, 2, 1, 50, 4, true, new Image("/application/resources/pikachu.gif")),
	KOFFING(30, 4, 6, 1, 10, 2, true, new Image("/application/resources/koffing.gif"));
	
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
    //0 can pass, 1 cant pass
    public final boolean canMove;
    private final Image sprite;
    
    UnitType(int hp, int attack, int def, int attackType, int critChance, int maxMove, boolean canMove, Image sprite) {
       this.hp = hp;
       this.attack = attack;
       this.def = def;
       this.attackType = attackType;
       this.critChance = critChance;
       this.maxMove = maxMove;
       this.sprite = sprite;
       this.canMove = canMove;
       
    }
    
    public int hp() { 
    	return hp; 
    	}
    public int attack() { 
    	return attack; 
    	}
    public int def() { 
    	return def; 
    	}
    public int attackType() { 
    	return attackType; 
    	}
    public int critChance() { 
    	return critChance; 
    	}
    public int maxMove() { 
    	return maxMove; 
    }
    public Image sprite(){
    	return sprite;
    }
    public boolean canMove(){
    	return canMove;
    }
    
}




