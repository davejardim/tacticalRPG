package application.model.tile;

import application.Main;
import application.ui.Controller;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class EnvironmentTile {

	//view - should be moved to separate class eventually
	private int xCord, yCord;
	private ImageView i;
	private Rectangle highlightedMask;
	
	private static EnvironmentTile curSelectedTile;
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
		//empty box for default empty environment location
		//Rectangle r = new Rectangle(x*Main.TILE_SIZE,y*Main.TILE_SIZE,Main.TILE_SIZE, Main.TILE_SIZE);
		//isEmpty = true;
		
		//Controller.environmentGrid.getChildren().add(r);
	}

	public int getXCord() {
		return this.xCord;
	}
	
	public int getYCord() {
		return this.yCord;
	}

	public void setSelected(boolean b) {
		if(b)
		{
			if(curSelectedTile != this){
			if(curSelectedTile != null){
				curSelectedTile.getHighlightMask().setVisible(false);
				highlightedMask.setVisible(true);
			
			}
			curSelectedTile = this;
		}
			
		}
		else highlightedMask.setVisible(false);
			
		
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	private Rectangle getHighlightMask(){
		return highlightedMask;
	}
}
