package application.ui.unitTile;

import application.model.tile.UnitTile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class UnitTileView extends Rectangle {
	
	private UnitTile tile;
	
	public UnitTileView(UnitTile tile, double tileSize) {
		this.tile = tile;
		this.setHeight(tileSize);
		this.setWidth(tileSize);
		this.setFill(Color.TRANSPARENT);
		
		setHandles();
	}
	
	public void setImage(Image img) {
		ImagePattern pattern = new ImagePattern(img);
		this.setFill(pattern);
	}
	
	public void removeImage() {
		this.setFill(Color.TRANSPARENT);
	}
	
	private void setHandles() {
		this.setOnMouseClicked(e -> {
			tile.onClick(e);
		});
	}
}
