package application.model.tile;

import java.util.ArrayList;

import application.model.unit.Unit;
import application.ui.unitTile.EnvironmentTileView;
import enviornment.EnvironmentType;

public class EnvironmentTile {

	private EnvironmentTileView view;
	private Unit unit;
	private int xCord, yCord;
	private EnvironmentType type;
	
	private static ArrayList<EnvironmentTile> highlightedTiles;
	private boolean selected = false;
	
	public EnvironmentTile(int x, int y, EnvironmentType type) {
		this.type = type;
		view = new EnvironmentTileView(this, x,y);
		
		
		if(highlightedTiles == null)
			highlightedTiles = new ArrayList<EnvironmentTile>();
		
	}
	
	public void setHighlighted(boolean b) {
			view.setHighlightedMask(b);	
	}
	
	public void setMouseOverHighlighted(boolean b) {
		view.setMouseOverHighlight(b);
	}
	public EnvironmentType getEnvType(){
		return this.type;
	}
	
	public boolean isSelected(){
		return selected;
	}

	public void setEnvironment(EnvironmentType t) {
		view.setEnvironment(t);
		
	}

	public void removeFromGrid() {
		view.removeFromGrid();
		
	}
	
	public boolean isWall() {
		return type == EnvironmentType.WALL;
	}
}
