package application.ui.unitTile;

import application.Main;
import application.model.tile.UnitTile;
import application.ui.Controller;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UnitTileView {
	
	private UnitTile tile;
	private ImageView i;
	private Rectangle r;
	private Pane viewPane;

	private ImageView unitImage;
	private Rectangle unitHighlighting;
	private Text status, currentHp;

	
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

		
//		r.setFill(Color.TRANSPARENT);
//		r.setStrokeType(StrokeType.OUTSIDE);
//		r.setStroke(Color.TRANSPARENT);
//		r.setStrokeWidth(4);
//		r.setStrokeLineJoin(StrokeLineJoin.ROUND);
//		viewPane.getChildren().addAll(i,r);

		// Add text underneath unit
		status = new Text();
		status.setX(Main.TILE_SIZE*tile.getXCord());
		status.setY(Main.TILE_SIZE*tile.getYCord() + (Main.TILE_SIZE + 15));
		status.setFont(new Font(14));
		
		// Add current hp above unit
		currentHp = new Text();
		currentHp.setX(Main.TILE_SIZE*tile.getXCord());
		currentHp.setY(Main.TILE_SIZE*tile.getYCord());
		currentHp.setFont(new Font(14));
				
		Controller.unitGrid.getChildren().addAll(unitImage,unitHighlighting, status, currentHp);

		
		setHandles();
	}
	
	public void setText(String status, String hp) {
		this.status.setText(status);
		this.currentHp.setText(hp);
	}
	
	public void setImage(Image img) {
		unitImage.setImage(img);
		unitImage.setFitWidth(Main.TILE_SIZE);
		unitImage.setFitHeight(Main.TILE_SIZE);

	}
	
	public void removeImage() {
		unitImage.setImage(null);
	}
	
	private void setHandles() {
		unitHighlighting.setOnMouseClicked(e -> {
			tile.onClick(e);
		});
		unitHighlighting.setOnMouseEntered(e-> {
			tile.showUnitText();
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
