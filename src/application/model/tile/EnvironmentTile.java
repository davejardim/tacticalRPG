package application.model.tile;

import java.util.ArrayList;

import application.Main;
import application.ui.Controller;
import application.ui.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnvironmentTile {

	//view - should be moved to separate class eventually
	private int xCord, yCord;
	private static ImageView i;
	private Rectangle highlightedMask;
	
	private static ArrayList<EnvironmentTile> highlightedTiles;
	private boolean selected = false;
	
	public EnvironmentTile(int x, int y) {
		this.xCord = x*Main.TILE_SIZE;
		this.yCord = y*Main.TILE_SIZE;
		
		i = new ImageView(new Image("/application/resources/grass.png"));
		i.setX(xCord);
		i.setY(yCord);
		i.setScaleX(Main.TILE_SIZE/i.getImage().getWidth());
		i.setScaleY(Main.TILE_SIZE/i.getImage().getHeight());
		Controller.environmentGrid.getChildren().add(i);
		
		highlightedMask = new Rectangle(x*Main.TILE_SIZE, y*Main.TILE_SIZE, Main.TILE_SIZE, Main.TILE_SIZE);
		highlightedMask.setFill(Color.LIGHTSKYBLUE);
		highlightedMask.setOpacity(.5);
		highlightedMask.setVisible(false);
		Controller.environmentGrid.getChildren().add(highlightedMask);
		
		if(highlightedTiles == null)
			highlightedTiles = new ArrayList<EnvironmentTile>();
	}

	public int getXCord() {
		return this.xCord;
	}
	
	public int getYCord() {
		return this.yCord;
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
			highlightedMask.setVisible(b);
		
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	private Rectangle getHighlightMask(){
		return highlightedMask;
	}
}
