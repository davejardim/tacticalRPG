package application.model.tile;

import java.util.ArrayList;

import application.Main;
import application.ui.Controller;
import application.ui.Location;
import application.ui.unitTile.EnvironmentTileView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
			view.setHighlightedMask(true);	
	}
	
	public boolean isSelected(){
		return selected;
	}
}
