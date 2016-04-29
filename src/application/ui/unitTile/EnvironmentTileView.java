package application.ui.unitTile;

import application.Main;
import application.model.tile.EnvironmentTile;
import application.ui.Controller;
import enviornment.EnvironmentType;
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
		private Rectangle highlightedMask, mouseOverHighlight;
		
		public EnvironmentTileView(EnvironmentTile t, int x, int y){
			
		int ts = Main.TILE_SIZE;
		gridBox = new Rectangle(x*ts, y*ts, ts, ts);

		gridBox.setFill(Color.TRANSPARENT);
		gridBox.setStroke(Color.DARKGREEN);
		gridBox.setStrokeWidth(1);
		gridBox.setStrokeType(StrokeType.INSIDE);
		
		this.xCord = x*Main.TILE_SIZE;
		this.yCord = y*Main.TILE_SIZE;
		
		i = new ImageView(t.getEnvType().getSprite());
		i.setFitWidth(Main.TILE_SIZE);
		i.setFitHeight(Main.TILE_SIZE);
		i.setX(xCord);
		i.setY(yCord);
		//i.setScaleX(Main.TILE_SIZE/i.getImage().getWidth());
		//i.setScaleY(Main.TILE_SIZE/i.getImage().getHeight());

		
		highlightedMask = new Rectangle(x*ts, y*ts, ts, ts);
		highlightedMask.setFill(Color.LIGHTSKYBLUE);
		highlightedMask.setOpacity(.5);
		highlightedMask.setVisible(false);
		

		mouseOverHighlight = new Rectangle(x*ts, y*ts, ts, ts);
		mouseOverHighlight.setFill(Color.INDIANRED);
		mouseOverHighlight.setOpacity(.5);
		mouseOverHighlight.setVisible(false);
		
		Controller.environmentGrid.getChildren().addAll(i,highlightedMask, mouseOverHighlight, gridBox);

		}

		public void setHighlightedMask(boolean b) {
			highlightedMask.setVisible(b);
		}
		
		public void setMouseOverHighlight(boolean b) {
			mouseOverHighlight.setVisible(b);
		}

		public void setEnvironment(EnvironmentType t) {
			i.setImage(t.getSprite());
			
		}

		public void removeFromGrid() {
		
			
		}
		
}
