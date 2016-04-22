package enviornment;

import javafx.scene.image.Image;

public enum EnviornmentType {
	WALL(new Image("/application/resources/brick_wall.png"));
	
    private final Image sprite;
    
    EnviornmentType(Image sprite) {
       this.sprite = sprite;
    }
    public Image getSprite(){
    	return sprite;
    } 
}




