package application.ui;

import application.Main;
import application.model.unit.Unit;
import javafx.scene.image.ImageView;

public class SelectionTile extends ImageView {
	private Unit unit;
	
	public SelectionTile(Unit unit) {
		this.setImage(unit.getImage());
		this.setFitHeight(Main.TILE_SIZE);
		this.setFitWidth(Main.TILE_SIZE);
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void remove() {
		this.setImage(null);
		this.unit = null;
	}
}
