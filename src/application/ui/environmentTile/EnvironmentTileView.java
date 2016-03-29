package application.ui.environmentTile;

import application.model.tile.EnvironmentTile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EnvironmentTileView  extends Rectangle{
	
	private EnvironmentTile tile;
	
	public EnvironmentTileView(EnvironmentTile tile, double tileSize) {
		this.tile = tile;
		this.setHeight(tileSize);
		this.setWidth(tileSize);
		this.setFill(Color.TRANSPARENT);
	}
	
	public void setImage(Image img) {
		ImagePattern pattern = new ImagePattern(img);
		this.setFill(pattern);
	}
}
