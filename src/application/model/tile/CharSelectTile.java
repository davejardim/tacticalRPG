package application.model.tile;

import application.Main;
import application.model.unit.UnitType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharSelectTile extends ImageView {
	private UnitType type;
	
	public CharSelectTile(String imgURL) {
		Image i = new Image(imgURL);		
		this.setImage(i);

	
	}
	
	public CharSelectTile(UnitType ut){
		this.setImage(ut.icon());
		this.type = ut;
		this.setFitWidth(100);
		this.setFitHeight(100);
		
	}
	
	public UnitType getType() {
		return type;
	}
}
