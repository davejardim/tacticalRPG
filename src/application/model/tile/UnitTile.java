package application.model.tile;

import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.CharacterSelectionView;
import application.ui.Controller;
import application.ui.unitTile.UnitTileView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
	public UnitTile(int x, int y, Pane p) {
		this.xCord = x;
		this.yCord = y;
		this.view = new UnitTileView(this, p);
		this.unit = null;
		
	}
	
	/**
	 * For generating unit tile with unit
	 * @param x
	 * @param y
	 * @param tileSize
	 * @param unitType
	 */
	public UnitTile(int x, int y, Pane pane, UnitType unitType) {
		this(x, y, pane);	
		setUnit(new Unit(this.getXCord(), this.getYCord(), unitType, 0));
		// TODO: Differentiate based on unit type (most likely be done in Unit class)
	}
	
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
		
		if(view.getViewPane().equals(CharacterSelectionView.team1Pane))
			Controller.charSelect.onClick(1, this);
		else if(view.getViewPane().equals(CharacterSelectionView.team2Pane))
			Controller.charSelect.onClick(2, this);
		else
			Controller.currentGame.onClick(this, e);
		
	}

	public boolean isSelected() {
		return isSelected;
	}
	
	public boolean isPassable() {
		//TODO This will eventually need to more thoroughly check passability, including whether unit is on same team etc.
		if (! (unit == null))
			return unit.getCanMove();
		else return true;
	}

	public void setGrid(Pane p) {
		view.setGrid(p);
		
	}
	
	public UnitType getType()
	{
		return unit.getType();
	}
}
