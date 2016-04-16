package application.model.tile;

import java.util.ArrayList;

import application.ui.unitTile.EnvironmentTileView;

public class EnvironmentTile {

	private EnvironmentTileView view;
	
	
	private static ArrayList<EnvironmentTile> highlightedTiles;
	private boolean selected = false;
	
	public EnvironmentTile(int x, int y) {
		
		view = new EnvironmentTileView(this, x,y);
		
		if(highlightedTiles == null)
			highlightedTiles = new ArrayList<EnvironmentTile>();
		
	}
	
	public void setHighlighted(boolean b) {
			view.setHighlightedMask(b);	
	}
	
	public boolean isSelected(){
		return selected;
	}
}
