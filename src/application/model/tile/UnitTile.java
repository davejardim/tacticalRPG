package application.model.tile;

import application.model.game.Game;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.unitTile.UnitTileView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class UnitTile {
	
	private Unit unit;
	private int xCord, yCord;
	private UnitTileView view;
	private Game currLevel;
	
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
	public UnitTile(int x, int y, int tileSize, UnitType unitType) {
		this(x, y);	
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
	
	public void setSelected(boolean b){

		if(!isSelected){
			view.setStroke(Color.DEEPSKYBLUE);
			isSelected = true;
		}
		else{
			view.setStroke(Color.TRANSPARENT);
			isSelected = false;
		}
		

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
	}
}
