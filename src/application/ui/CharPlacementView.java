package application.ui;

import java.util.ArrayList;

import application.model.game.CharPlacement;
import application.model.unit.Unit;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CharPlacementView {
	private CharPlacement model;
	
	public CharPlacementView(CharPlacement model) {
		this.model = model;
		buildView();
	}
	
	private void buildView() {
		ArrayList<Unit> team1 = model.getSelectedChars().getTeam1();
		ArrayList<Unit> team2 = model.getSelectedChars().getTeam2();
		
		// Create and populate a box for team 1
		HBox team1Box = new HBox();
		for (Unit unit : team1) {
			SelectionTile temp = new SelectionTile(unit);
			setHandle(temp);
			team1Box.getChildren().add(temp);
		}
		
		// Create and populate a box for team 2
		HBox team2Box = new HBox();
		for (Unit unit : team2) {
			SelectionTile temp = new SelectionTile(unit);
			setHandle(temp);
			team2Box.getChildren().add(temp);
		}
		
		AnchorPane.setTopAnchor(team1Box, 0.0);
		AnchorPane.setLeftAnchor(team1Box, 0.0);
		AnchorPane.setTopAnchor(team2Box, 0.0);
		AnchorPane.setRightAnchor(team2Box, 0.0);
		Controller.getInstance().overlay.getChildren().addAll(team1Box, team2Box);
	}
	
	public void setHandle(SelectionTile tile) {
		tile.setOnMouseClicked(e->{
			model.onClick(tile);
		});
	}
}
