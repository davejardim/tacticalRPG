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

public class UnitTileView extends Rectangle{
	
	private UnitTile tile;
	private Image i;
	
	public UnitTileView(UnitTile tile) {
		this.tile = tile;
		
		this.setX(tile.getXCord()*Main.TILE_SIZE);
		this.setY(tile.getYCord()*Main.TILE_SIZE);
		this.setWidth(Main.TILE_SIZE);
		this.setHeight(Main.TILE_SIZE);
		
		i = new Image("/application/resources/Man_256x256.png");
		Controller.unitGrid.getChildren().add(this);
		
		this.setImage(i);
		this.setStrokeType(StrokeType.OUTSIDE);
		this.setStroke(Color.TRANSPARENT);
		this.setStrokeWidth(4);
		this.setStrokeLineJoin(StrokeLineJoin.ROUND);
		System.out.println(Controller.unitGrid.getChildren());
		
		this.setMouseTransparent(true);
	//	this.mouse
		
		
		setHandles();
	}
	
	public void setImage(Image img) {
		ImagePattern pattern = new ImagePattern(i);
		this.setFill(pattern);
	}
	
	public void removeImage() {
		this.setFill(Color.TRANSPARENT);
	}
	
	private void setHandles() {
		this.setOnMouseClicked(e -> {
			tile.onClick(e);
		});
	}
}
