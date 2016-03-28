package application.model.tile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
	
	private final int TILE_SIZE = 10;
	private int xCord, yCord;
	
	public Tile(int x, int y, double tileSize) {
		this.xCord = x;
		this.yCord = y;
		this.setHeight(tileSize);
		this.setWidth(tileSize);
		this.setFill(Color.TRANSPARENT);
	}
	
	public int getXCord() {
		return this.xCord;
	}
	
	public int getYCord() {
		return this.yCord;
	}
	
	public void setXCord(int x) {
		this.xCord = x;
	}
	
	public void setYCord(int y) {
		this.yCord = y;
	}
}
