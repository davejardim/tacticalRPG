package application.ui.unitTile;

import java.util.ArrayList;

import application.Main;
import application.model.tile.EnvironmentTile;
import application.ui.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnvironmentTileView {

		private int xCord, yCord;
		@SuppressWarnings("unused")
		private EnvironmentTile tile;
		private static ImageView i;
		private Rectangle highlightedMask;
		
		public EnvironmentTileView(EnvironmentTile t,int x, int y){

		tile =  t;
		
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
		
		}

		public void setHighlightedMask(boolean b) {
			highlightedMask.setVisible(b);

			
		}
		
}
