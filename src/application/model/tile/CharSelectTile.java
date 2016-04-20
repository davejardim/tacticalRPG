package application.model.tile;

import application.model.unit.UnitType;
import javafx.scene.image.ImageView;

public class CharSelectTile extends ImageView {
	private UnitType type = UnitType.PIKACHU;
	
	public CharSelectTile(String imgURL) {
		super(imgURL);
	}
	
	public UnitType getType() {
		return type;
	}
}
