package application.ui.unitTile;

import java.util.ArrayList;

import application.Main;
import application.model.tile.EnvironmentTile;
import application.ui.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class EnvironmentTileView {

		private int xCord, yCord;
		@SuppressWarnings("unused")
		private EnvironmentTile tile;
		private static ImageView i;
		private Rectangle gridBox;
		private Rectangle highlightedMask;
		
		public EnvironmentTileView(EnvironmentTile t,int x, int y){

		int ts = Main.TILE_SIZE;
		gridBox = new Rectangle(x*ts, y*ts, ts, ts);

		gridBox.setFill(Color.TRANSPARENT);
		gridBox.setStroke(Color.DARKGREEN);
		gridBox.setStrokeWidth(1);
		gridBox.setStrokeType(StrokeType.INSIDE);
		
		this.xCord = x*Main.TILE_SIZE;
		this.yCord = y*Main.TILE_SIZE;
		
		i = new ImageView(new Image("/application/resources/grass.png"));
		i.setX(xCord);
		i.setY(yCord);
		i.setScaleX(Main.TILE_SIZE/i.getImage().getWidth());
		i.setScaleY(Main.TILE_SIZE/i.getImage().getHeight());

		
		highlightedMask = new Rectangle(x*ts, y*ts, ts, ts);
		highlightedMask.setFill(Color.LIGHTSKYBLUE);
		highlightedMask.setOpacity(.5);
		highlightedMask.setVisible(false);
		
		Controller.environmentGrid.getChildren().addAll(i,highlightedMask, gridBox);
		
		}

		public void setHighlightedMask(boolean b) {
			highlightedMask.setVisible(b);
		}
		
}
