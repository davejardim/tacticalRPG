package application.model.tile;

import application.model.unit.UnitType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharSelectTile extends ImageView {
	private UnitType type = UnitType.PIKACHU;
	
	public CharSelectTile(String imgURL) {
		this.setImage(new Image(imgURL));
	}
	
	public UnitType getType() {
		return type;
	}
}
