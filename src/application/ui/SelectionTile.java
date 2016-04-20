package application.ui;

import application.Main;
import application.model.unit.Unit;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class SelectionTile extends StackPane {
	private Unit unit;
	private Rectangle unitHighlighting;
	private ImageView img;
	
	public SelectionTile(Unit unit) {
		this.unit = unit;
		
		buildStack();
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void remove() {
		img.setImage(null);
		unit = null;
		this.setMouseTransparent(true);
	}
	
	public void removeHighlight() {
		unitHighlighting.setStroke(Color.TRANSPARENT);
	}
	
	public void setHighlight() {
		unitHighlighting.setStroke(Color.DEEPSKYBLUE);
	}
	
	private void buildStack(){
		// Highlighting
		unitHighlighting = new Rectangle();
		unitHighlighting.setWidth(Main.TILE_SIZE);
		unitHighlighting.setHeight(Main.TILE_SIZE);
		unitHighlighting.setFill(Color.TRANSPARENT);
		unitHighlighting.setStrokeType(StrokeType.OUTSIDE);
		unitHighlighting.setStroke(Color.TRANSPARENT);
		unitHighlighting.setStrokeWidth(4);
		unitHighlighting.setStrokeLineJoin(StrokeLineJoin.ROUND);
		
		// Image for char
		img = new ImageView(unit.getImage());
		img.setFitHeight(Main.TILE_SIZE);
		img.setFitWidth(Main.TILE_SIZE);
		
		this.getChildren().addAll(unitHighlighting, img);
	}
}
