package application.model.tile;

import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.Controller;
import application.ui.unitTile.UnitTileView;
import javafx.scene.input.MouseEvent;

public class UnitTile {
	
	private Unit unit;
	private int xCord, yCord;
	private UnitTileView view;
	
	private boolean isSelected = false;
	
	/**
	 * For generating empty unit tiles
	 * @param x
	 * @param y
	 * @param tileSize
	 */
	public UnitTile(int x, int y) {
		this.xCord = x;
		this.yCord = y;
		this.view = new UnitTileView(this);
		this.unit = null;
		
	}
	
	/**
	 * For generating unit tile with unit
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param unitType
	 */
	public UnitTile(int x, int y, UnitType unitType) {
		this(x, y);	
		setUnit(new Unit(this.getXCord(), this.getYCord(), unitType, 0));
		// TODO: Differentiate based on unit type (most likely be done in Unit class)
	}
	
<<<<<<< HEAD
	
=======
>>>>>>> origin/master
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
		view.setImage(unit.getImage());
	}
	
	public void setSelected(boolean b){
		view.setSelected(b);
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
	
	public void onClick(MouseEvent e) {
		System.out.println(xCord + ", " + yCord);
		Controller.currentGame.onClick(this, e);
		
	}

	public boolean isSelected() {
		return isSelected;
	}
	
	public boolean isPassable() {
		//TODO This will eventually need to more thoroughly check passability, including whether unit is on same team etc.
		if (!(unit == null))
			return unit.getCanMove();
		else return true;
	}
}
