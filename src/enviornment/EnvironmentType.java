package enviornment;

import javafx.scene.image.Image;

public enum EnvironmentType {
	WALL(new Image("/application/resources/brick_wall.png")),
	GRASS(new Image("/application/resources/grass.png"));
	
    private final Image sprite;
    
    EnvironmentType(Image sprite) {
       this.sprite = sprite;
    }
    public Image getSprite(){
    	return sprite;
    } 
}




