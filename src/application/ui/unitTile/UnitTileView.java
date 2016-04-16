package application.ui.unitTile;

import application.Main;
import application.model.tile.UnitTile;
import application.ui.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class UnitTileView {
	
	private UnitTile tile;
	private ImageView i;
	private Rectangle r;
	
	private int xCord;
	private int yCord;
	
	public UnitTileView(UnitTile tile) {
		this.tile = tile;
		
		i = new ImageView();
		r = new Rectangle();
			
		
		i.setX(tile.getXCord()*Main.TILE_SIZE);
		i.setY(tile.getYCord()*Main.TILE_SIZE);
		//i.setWidth(Main.TILE_SIZE);
		//i.setHeight(Main.TILE_SIZE);
		

		
		Controller.unitGrid.getChildren().add(i);

		r.setStrokeType(StrokeType.OUTSIDE);
		r.setStroke(Color.TRANSPARENT);
		r.setStrokeWidth(4);
		r.setStrokeLineJoin(StrokeLineJoin.ROUND);
		
		setHandles();
	}
	
	public void setImage(Image img) {
		i.setImage(img);
		i.setScaleX(Main.TILE_SIZE/i.getImage().getWidth());
		i.setScaleY(Main.TILE_SIZE/i.getImage().getHeight());
		//i.setScaleX(Main.TILE_SIZE/i.getImage().getWidth());
		//i.setScaleY(Main.TILE_SIZE/i.getImage().getHeight());
	}
	
	public void removeImage() {
		
	}
	
	private void setHandles() {
		i.setOnMouseClicked(e -> {
			tile.onClick(e);
		});
	}

	public void setSelected(boolean b) {
		
		if(!tile.isSelected()){
			r.setStroke(Color.DEEPSKYBLUE);
			tile.setSelected(true);
		}
		else{
			r.setStroke(Color.TRANSPARENT);
			tile.setSelected(false);
		}
		
	}
}
