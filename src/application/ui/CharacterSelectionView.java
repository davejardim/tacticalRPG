package application.ui;

import java.util.ArrayList;

import application.Main;
import application.model.game.CharacterSelection;
import application.model.game.CharacterTeam;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

public class CharacterSelectionView 
{
	CharacterSelection model;
	ArrayList<CharacterTeam> teams;
	public static Pane team1Pane, team2Pane;

	static private int w, h;

	@SuppressWarnings("static-access")
	public CharacterSelectionView( CharacterSelection cs){

		model = cs;
		
		w = Main.TILE_SIZE*model.getGridWidth();
		h = Main.TILE_SIZE*model.getGridHeight();
		
		HBox teamBox = new HBox();
		teamBox.setSpacing(300);
		teamBox.setAlignment(Pos.CENTER);

		team1Pane = new Pane();
		team2Pane = new Pane();
		
		teamBox.getChildren().addAll(team1Pane,team2Pane);
		BorderPane menuBox = new BorderPane();
		Label title = new Label("Pick Your Teams:");
		title.setAlignment(Pos.CENTER);
		title.setFont(new Font("Didot Bold", 22));
		menuBox.setLeft(team1Pane);
		menuBox.setRight(team2Pane);
		Rectangle spacer = new Rectangle(0,0,100,5);
		spacer.setFill(Color.TRANSPARENT);
		menuBox.setCenter(spacer);
		menuBox.getChildren().addAll(teamBox);
		menuBox.setTop(title);
		//menuBox.setAlignment(title, Pos.TOP_CENTER);
		menuBox.setPadding(new Insets(0, 10, 10, 10));
		title.setPadding(new Insets(0, 10, 10 , 120));
		Controller.charSelectionMenu.getChildren().addAll(menuBox);


		Controller.charSelectionMenu.setPrefSize(w,h);
		Controller.charSelectionMenu.setMinSize(w,h);
		Controller.charSelectionMenu.setMaxSize(w,h);


	}

	public static void addTeamSelection(int teamNo){	
		Rectangle r = new Rectangle(0,0,w,h);
		r.setFill(Color.BROWN);
		r.setStroke(Color.BLACK);
		r.setStrokeType(StrokeType.OUTSIDE);
		r.setStrokeWidth(4);

		if(teamNo ==1)
			team1Pane.getChildren().add(r);
		else
			team2Pane.getChildren().add(r);
	}


}
