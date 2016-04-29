package application.ui;

import java.util.ArrayList;

import application.model.game.CharacterSelection;
import application.model.tile.CharSelectTile;
import application.model.unit.UnitType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CharacterSelectionView extends BorderPane
{
	final private int GRID_WIDTH = 3;
	final private int GRID_HEIGHT = 3;
	
	private GridPane team1Pane, team2Pane;
	private static GridPane charPane;
	private CharacterSelection model;
	private int player1Row, player2Row;
	private Button startGame, addToTeam;
	private CharSelectTile selected;
	private ArrayList<CharSelectTile> charChoices;

	@SuppressWarnings("static-access")
	public CharacterSelectionView(CharacterSelection cs){
		model = cs;
		player1Row = 0;
		player2Row = 0;
		
		this.setWidth(1000);
		this.setHeight(500);
		// GridPane that will hold characters that can be selected
		charPane = new GridPane();
		charPane.setMinHeight(400);
		charPane.setMaxHeight(400);
		charPane.setMinWidth(600);
		charPane.setMaxWidth(600);
		charPane.setAlignment(Pos.CENTER);
		
		// Add a Pane for each team
		team1Pane = new GridPane();
		team2Pane = new GridPane();
		team1Pane.setPrefSize(200, 400);
		team2Pane.setPrefSize(200, 400);
		
		// Title	
		Text title = new Text("Pick your Teams: ");
		title.setFont(new Font("Didot Bold", 32));
		
		// Button to start the game
		startGame = new Button("Start Game!");
		startGame.setFont(new Font("Didot Bold", 20));
		startGame.setAlignment(Pos.CENTER);
		startGame.setMinWidth(500);
		startGame.setMinHeight(50);
		startGame.setDisable(true);
		
		// Buttons to add to teams
		addToTeam = new Button("Add to Team 1");
		addToTeam.setFont(new Font("Didot Bold", 20));
		addToTeam.setMinHeight(50);
		addToTeam.setMinWidth(200);
		
		// HBox to hold all of these buttons
		HBox buttons = new HBox(10);
		buttons.setMinWidth(1000);
		buttons.getChildren().addAll(startGame, addToTeam);
		buttons.setAlignment(Pos.CENTER);
		
		
		// Add everything to the BorderPane
		this.setTop(title);
		this.setLeft(team1Pane);
		this.setRight(team2Pane);
		this.setCenter(charPane);
		this.setBottom(buttons);
		this.setAlignment(title, Pos.CENTER);
		
		populateChars();
		setHandles();
	}
	
	// NOTE: Can easily move this to the model later
	private void populateChars() {
		charChoices = new ArrayList<CharSelectTile>();
		
		charChoices.add(new CharSelectTile(UnitType.LINK));	
		charChoices.add(new CharSelectTile(UnitType.MARIO));	
		charChoices.add(new CharSelectTile(UnitType.MJ));	
		charChoices.add(new CharSelectTile(UnitType.CAP));	
		charChoices.add(new CharSelectTile(UnitType.PIKACHU));	
		charChoices.add(new CharSelectTile(UnitType.KOFFING));	
		charChoices.add(new CharSelectTile(UnitType.BB8));
		charChoices.add(new CharSelectTile(UnitType.MEGA));
		charChoices.add(new CharSelectTile(UnitType.DRUMPF));
		
		
		int inc = 0;
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				charPane.add(charChoices.get(inc++), i, j);
			}
		}
		
		setSelected(charChoices.get(0));
	}
	
	public void switchTeams() {
		if (addToTeam.getText().equals("Add to Team 1")) {
			addToTeam.setText("Add to team 2");
		} else {
			addToTeam.setText("Add to Team 1");
		}
	}
	
	public void switchToDone() {
		addToTeam.setDisable(true);
		addToTeam.setText("Character choice done");
		startGame.setDisable(false);
	}
	
	
	public void addCharToView(CharSelectTile selectedUnit, int team) {
		
		CharSelectTile newTile = new CharSelectTile(selectedUnit.getType());
		if (team == 1) {
			team1Pane.add(newTile, 0, player1Row++);
		} else {
			team2Pane.add(newTile, 0, player2Row++);
		}
	}
	
	private void setSelected(CharSelectTile tile) {
		// Remove effect from current selected
		if (selected != null) {
			selected.setEffect(null);
		}
		
		DropShadow dp = new DropShadow();
		dp.setRadius(100);
		dp.setOffsetX(6.0);
		dp.setOffsetY(4.0);
		dp.setColor(Color.BLACK);
		tile.setEffect(dp);
		selected = tile;
	}
	
	private void setHandles() {
		// Set click handlers for selected char
		for (CharSelectTile tile: charChoices) {
			tile.setOnMouseClicked(e->{
				setSelected(tile);
			});
		}
		
		// Add characters to team
		addToTeam.setOnMouseClicked(e->{
			model.addChar(selected);
		});
		
		// Start the game
		startGame.setOnMouseClicked(e->{
			Controller.getInstance().startGame();
		});
	}

//	public static void addTeamSelection(int teamNo){	
//		Rectangle r = new Rectangle(0,0,w,h);
//		r.setFill(Color.BROWN);
//		r.setStroke(Color.BLACK);
//		r.setStrokeType(StrokeType.OUTSIDE);
//		r.setStrokeWidth(4);
//
//		if(teamNo ==1)
//			team1Pane.getChildren().add(r);
//		else
//			team2Pane.getChildren().add(r);
//	}


}
