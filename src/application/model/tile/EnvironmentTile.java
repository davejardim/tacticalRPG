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


	//highlights
	public static void setHighlighted(Location l1, Location l2){
		
		for(EnvironmentTile e : highlightedTiles){
			e.setHighlighted(false);
		}
		highlightedTiles.clear();
		
		for(int i = l1.getX(); i <= l2.getX(); i++)
			for(int j = l2.getY(); j <= l2.getY(); j++)
			{
				if(Controller.currentGame.getEnvironmentTile(i, j) != null){
					EnvironmentTile e = Controller.currentGame.getEnvironmentTile(i, j);
					highlightedTiles.add(e);
					e.setHighlighted(true);
				}
			}
		
		
	}
	
	public void setHighlighted(boolean b) {
			view.setHighlightedMask(true);
		
	}
	
	public boolean isSelected(){
		return selected;
	}
}
