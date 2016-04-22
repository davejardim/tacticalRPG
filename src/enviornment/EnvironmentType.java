package enviornment;

import javafx.scene.image.Image;

public enum EnvironmentType {
	WALL(new Image("/application/resources/brick_wall.png"), false),
	GRASS(new Image("/application/resources/grass.png"), true);
	
    private final Image sprite;
    private final Boolean passable;
    
    EnvironmentType(Image sprite, Boolean passable) {
       this.sprite = sprite;
       this.passable = passable;
    }
    public Image getSprite(){
    	return sprite;
    } 
    public Boolean isPassable(){
    	return passable;
    } 
}




