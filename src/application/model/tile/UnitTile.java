package application.model.tile;

import application.model.unit.Unit;
import application.model.unit.UnitType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class UnitTile extends Tile {
	
	private Unit unit;
	
	/**
	 * For generating empty unit tiles
	 * @param x
	 * @param y
	 * @param tileSize
	 */
	public UnitTile(int x, int y, double tileSize) {
		super(x, y, tileSize);
		setClickHandler();
	}
	
	/**
	 * For generating unit tile with unit
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param unitType
	 */
	public UnitTile(int x, int y, double tileSize, UnitType unitType) {
		super(x, y, tileSize);
		
		unit = new Unit(this.getXCord(), this.getYCord(), unitType);
		
		ImagePattern img = new ImagePattern(new Image("/application/resources/Man.png"));
		this.setFill(img);
		setClickHandler();
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	private void setClickHandler() {
		this.setOnMouseClicked(e -> {
			this.setFill(Color.RED);
			System.out.println(this.getXCord() + ", " + this.getYCord());
		});
	}
}
