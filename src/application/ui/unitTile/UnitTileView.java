package application.ui.unitTile;

import application.Main;
import application.model.tile.UnitTile;
import application.ui.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class UnitTileView {
	
	private UnitTile tile;
	private ImageView i;
	private Rectangle r;
	private Pane viewPane;
	
	public UnitTileView(UnitTile tile, Pane p) {
		this.tile = tile;
		viewPane = p;
		
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
		viewPane.getChildren().addAll(i,r);
		
		setHandles();
	}
	
	public void setImage(Image img) {
		i.setImage(img);
		i.setFitWidth(Main.TILE_SIZE);
		i.setFitHeight(Main.TILE_SIZE);

	}
	
	public void removeImage() {
		i.setImage(null);
	}
	
	private void setHandles() {
		r.setOnMouseClicked(e -> {
			tile.onClick(e);
		});
	}

	public void setSelected(boolean b) {
		
		if(b){
			r.setStroke(Color.DEEPSKYBLUE);
		}
		else{
			r.setStroke(Color.TRANSPARENT);
		}
		
	}

	public Pane getViewPane(){
			return viewPane;
	}
	public void setGrid(Pane p) {
		viewPane = p;
		
	}
}
