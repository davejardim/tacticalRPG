package application.ui.unitTile;

import application.Main;
import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.ui.Controller;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UnitTileView {
	
	private UnitTile tile;
	private Pane viewPane;

	private ImageView unitImage;
	private Rectangle unitHighlighting;
	private Text currentHp;

	
	public UnitTileView(UnitTile tile, Pane p) {
		this.tile = tile;
		viewPane = p;
		
		
		
		// Used for highlighting selected units
		unitHighlighting = new Rectangle();
		unitHighlighting.setX(Main.TILE_SIZE*tile.getXCord());
		unitHighlighting.setY(Main.TILE_SIZE*tile.getYCord());
		unitHighlighting.setWidth(Main.TILE_SIZE);
		unitHighlighting.setHeight(Main.TILE_SIZE);
		unitHighlighting.setFill(Color.TRANSPARENT);
		unitHighlighting.setStrokeType(StrokeType.OUTSIDE);
		unitHighlighting.setStroke(Color.TRANSPARENT);
		unitHighlighting.setStrokeWidth(4);
		unitHighlighting.setStrokeLineJoin(StrokeLineJoin.ROUND);
			
		// Holds unit image
		unitImage = new ImageView();
		unitImage.setX(tile.getXCord()*Main.TILE_SIZE);
		unitImage.setY(tile.getYCord()*Main.TILE_SIZE);

		
		// Add current hp above unit
		currentHp = new Text();
		currentHp.setX(Main.TILE_SIZE*tile.getXCord());
		currentHp.setY(Main.TILE_SIZE*tile.getYCord());
		currentHp.setFont(new Font(14));
				
		Controller.unitGrid.getChildren().addAll(unitImage,unitHighlighting, currentHp);

		
		setHandles();
	}
	
	public void setText(String hp) {
		this.currentHp.setText(hp);
	}
	
	public void setImage(Image img) {
		unitImage.setImage(img);
		unitImage.setFitWidth(Main.TILE_SIZE);
		unitImage.setFitHeight(Main.TILE_SIZE);

	}
	
	public void removeImage() {
		this.unitImage.setImage(null);
	}
	
	private void setHandles() {
		unitHighlighting.setOnMouseClicked(e -> {
			tile.onClick(e);
		});
		unitHighlighting.setOnMouseEntered(e->{
			tile.showUnitText();
			
			EnvironmentTile t = Controller.highlightedTile;
			if(t != null){
				t.setMouseOverHighlighted(false);
				Controller.highlightedTile = Controller.currentGame.getEnvironmentTile(tile.getXCord(), tile.getYCord());
				Controller.highlightedTile.setMouseOverHighlighted(true);
			} else{
				Controller.highlightedTile = Controller.currentGame.getEnvironmentTile(tile.getXCord(), tile.getYCord());
			}
		});
		unitHighlighting.setOnMouseExited(e-> {
			tile.hideUnitText();
		});
	}

	public void setSelected(boolean b) {
		
		if(b){
			unitHighlighting.setStroke(Color.DEEPSKYBLUE);
		}
		else{
			unitHighlighting.setStroke(Color.TRANSPARENT);
		}
		
	}

	public Pane getViewPane(){
			return viewPane;
	}
	public void setGrid(Pane p) {
		viewPane = p;
		
	}
}
