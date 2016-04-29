package application.model.unit;

import application.Main;
import javafx.scene.image.Image;

//marc bluemer 
//Mike Donohue

public enum UnitType {
	
	
	//hp/attack/def/attackType/critChance/maxMove/canMove/Image
	//ARCHER(20, 7, 3, 2, 10, 2, true, new Image("/application/resources/Man_256x256.png")),
    //ASSASSIN(21, 8, 2, 1, 50, 2, true, new Image("/application/resources/Man_256x256.png")),
	LINK(25, 7, 3, 1, 10, 9, true, 
			new Image(Main.playerResource + "0_toon_link.gif"),
			new Image(Main.playerResource + "0_toon_link_icon.png")),
	MARIO(28, 5, 5, 1, 5, 7, true,
			new Image(Main.playerResource + "1_Mario.gif"),
			new Image(Main.playerResource + "1_Mario_icon.png")),
	MJ(30, 7, 4, 1, 10, 3, true, 
			new Image(Main.playerResource + "2_MJ.gif"),
			new Image(Main.playerResource + "2_MJ_icon.jpg")),
	CAP(20, 8, 4, 3, 10, 12, true, 
			new Image(Main.playerResource + "3_Cap.gif"),
			new Image(Main.playerResource + "3_Cap_icon.png")),
	PIKACHU(21, 8, 2, 1, 50, 10, true, 
			new Image(Main.playerResource + "4_pikachu.gif"),
			new Image(Main.playerResource + "4_pikachu_icon.png")),
	KOFFING(30, 4, 6, 1, 10, 12, true, 
			new Image(Main.playerResource + "5_koffing.gif"),
			new Image(Main.playerResource + "5_koffing_icon.png")),
	BB8(1,	1, 1, 1, 1, 15, true, 
			new Image(Main.playerResource + "6_bb8.gif"),
			new Image(Main.playerResource + "6_bb8_icon.jpg")),
	MEGA(10, 10, 10, 17, 3, 500, true, 
			new Image(Main.playerResource + "7_mega_man.gif"),
			new Image(Main.playerResource + "7_mega_man_icon.png"));
	
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
    private final Image icon;
    
    UnitType(int hp, int attack, int def, int attackType, int critChance, int maxMove, boolean canMove, Image sprite, Image icon) {
       this.hp = hp;
       this.attack = attack;
       this.def = def;
       this.attackType = attackType;
       this.critChance = critChance;
       this.maxMove = maxMove;
       this.sprite = sprite;
       this.canMove = canMove;
       this.icon = icon;
       
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
    public Image icon(){
    	return icon;
    }
    public boolean canMove(){
    	return canMove;
    }
    

    
    
  
    
}




