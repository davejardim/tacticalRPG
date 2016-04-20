package application.ui;

import java.util.ArrayList;

import application.model.game.CharacterSelection;
import application.model.tile.CharSelectTile;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CharacterSelectionView extends BorderPane
{
	final private int GRID_WIDTH = 3;
	final private int GRID_HEIGHT = 2;
	
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
		
		// Add a Pane for each team
		team1Pane = new GridPane();
		team2Pane = new GridPane();
		team1Pane.setPrefSize(200, 400);
		team2Pane.setPrefSize(200, 400);
	
		// Title	
		Label title = new Label("Pick your Teams: ");
		title.setMinWidth(1000);
		title.setAlignment(Pos.CENTER);
		title.setFont(new Font("Didot Bold", 20));
		
		// Button to start the game
		startGame = new Button("Start Game!");
		startGame.setAlignment(Pos.CENTER);
		startGame.setMinWidth(500);
		startGame.setMinHeight(50);
		
		// Buttons to add to teams
		addToTeam = new Button("Add to current Team");
		addToTeam.setMinHeight(50);
		addToTeam.setMinWidth(200);
		
		// HBox to hold all of these buttons
		HBox buttons = new HBox(10);
		buttons.getChildren().addAll(startGame, addToTeam);
		
		
		// Add everything to the BorderPane
		this.setTop(title);
		this.setLeft(team1Pane);
		this.setRight(team2Pane);
		this.setCenter(charPane);
		this.setBottom(buttons);
		
		populateChars();
		setHandles();
	}
	
	// NOTE: Can easily move this to the model later
	private void populateChars() {
		charChoices = new ArrayList<CharSelectTile>();
		
		charChoices.add(new CharSelectTile("/application/resources/pikachu.png"));
		charChoices.add(new CharSelectTile("/application/resources/pikachu.png"));
		charChoices.add(new CharSelectTile("/application/resources/pikachu.png"));
		charChoices.add(new CharSelectTile("/application/resources/pikachu.png"));
		charChoices.add(new CharSelectTile("/application/resources/pikachu.png"));
		charChoices.add(new CharSelectTile("/application/resources/pikachu.png"));
		
		int inc = 0;
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				charPane.add(charChoices.get(inc++), i, j);
			}
		}
		
		setSelected(charChoices.get(0));
	}
	
	public void addCharToView(CharSelectTile selectedUnit, int team) {
		// TODO: duplicate the selectedUnit somehow
		CharSelectTile newTile = new CharSelectTile("/application/resources/pikachu.png");
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
		addToTeam.setOnMouseClicked(e->{
			//model.addChar();
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
