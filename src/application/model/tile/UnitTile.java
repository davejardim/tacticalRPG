package application.model.tile;

import application.model.level.Level;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.ScreenControl;
import application.ui.unitTile.UnitTileView;
import javafx.scene.image.Image;

public class UnitTile {
	
	private Unit unit;
	private int xCord, yCord;
	private UnitTileView view;
	private Level currLevel;
	
	/**
	 * For generating empty unit tiles
	 * @param x
	 * @param y
	 * @param tileSize
	 */
	public UnitTile(int x, int y, double tileSize) {
		this.xCord = x;
		this.yCord = y;
		this.view = new UnitTileView(this, tileSize);
		this.unit = null;
		this.currLevel = ScreenControl.currentLevel;
	}
	
	/**
	 * For generating unit tile with unit
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param unitType
	 */
	public UnitTile(int x, int y, int tileSize, UnitType unitType) {
		this(x, y, tileSize);	
		setUnit(new Unit(this.getXCord(), this.getYCord(), unitType), tileSize);
		// TODO: Differentiate based on unit type (most likely be done in Unit class)
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setUnit(Unit unit, int tileSize) {
		this.unit = unit;
		view.setImage(new Image("/application/resources/Man.png", tileSize, tileSize, false, false));
	}
	
	public void removeUnit() {
		this.unit = null;
		view.removeImage();
	}
	
	public int getXCord() {
		return this.xCord;
	}
	
	public int getYCord() {
		return this.yCord;
	}
	
	public UnitTileView getView() {
		return this.view;
	}
	
	public void onClick() {
		// For now let's just show possible movement spaces
		currLevel.clickHandle(this);
		System.out.println(xCord + ", " + yCord);
	}
}
