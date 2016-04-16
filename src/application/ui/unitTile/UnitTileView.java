package application.ui.unitTile;

import application.Main;
import application.model.tile.UnitTile;
import application.ui.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class UnitTileView {
	
	private UnitTile tile;
	private ImageView i;
	private Rectangle r;
	
	public UnitTileView(UnitTile tile) {
		this.tile = tile;
		
		i = new ImageView();
		
		
		r = new Rectangle();
		r.setX(Main.TILE_SIZE*tile.getXCord());
		r.setY(Main.TILE_SIZE*tile.getYCord());
		r.setWidth(Main.TILE_SIZE);
		r.setHeight(Main.TILE_SIZE);
			
		
		i.setX(tile.getXCord()*Main.TILE_SIZE);
		i.setY(tile.getYCord()*Main.TILE_SIZE);
		//i.setWidth(Main.TILE_SIZE);
		//i.setHeight(Main.TILE_SIZE);
		

		r.setFill(Color.TRANSPARENT);
		r.setStrokeType(StrokeType.OUTSIDE);
		r.setStroke(Color.TRANSPARENT);
		r.setStrokeWidth(4);
		r.setStrokeLineJoin(StrokeLineJoin.ROUND);
		Controller.unitGrid.getChildren().addAll(i,r);
		
		r.setOnMouseClicked(e -> { tile.onClick(e);});
		setHandles();
	}
	
	public void setImage(Image img) {
		i.setImage(img);
		i.setFitWidth(Main.TILE_SIZE);
		i.setFitHeight(Main.TILE_SIZE);

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
		}
		else{
			r.setStroke(Color.TRANSPARENT);
		}
		
	}
}
