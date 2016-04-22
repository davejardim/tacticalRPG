package application.model.tile;

import java.util.ArrayList;

import application.model.unit.Unit;
import application.ui.unitTile.EnvironmentTileView;
import enviornment.Environment;
import enviornment.EnvironmentType;

public class EnvironmentTile {

	private EnvironmentTileView view;
	private Unit unit;
	private int xCord, yCord;
	private EnvironmentType type;
	
	private static ArrayList<EnvironmentTile> highlightedTiles;
	private boolean selected = false;
	
	public EnvironmentTile(int x, int y, EnvironmentType type) {
		view = new EnvironmentTileView(this, x,y);
		this.type = type;
		
		if(highlightedTiles == null)
			highlightedTiles = new ArrayList<EnvironmentTile>();
		
	}
	
	public void setHighlighted(boolean b) {
			view.setHighlightedMask(b);	
	}
	public EnvironmentType getEnvType(){
		return this.type;
	}
	
	public boolean isSelected(){
		return selected;
	}
}
