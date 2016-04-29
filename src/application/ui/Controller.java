package application.ui;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import application.model.game.CharPlacement;
import application.model.game.CharacterSelection;
import application.model.game.Game;
import application.model.tile.EnvironmentTile;
import application.model.unit.Unit;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Let's go ahead and make this a singleton
public class Controller {
	private static Controller instance = null;
	
	private GridPane mainMenu;
	
	//current game, accessible by all
	public static Game currentGame;
	public static EnvironmentTile highlightedTile;
	
	//layers
	public static StackPane UILayers; //contains the panes below
	public static Pane environmentGrid;
	public static AnchorPane overlay;
	public static Pane unitGrid;
	public static CharPlacement charPlacement;
	
	private Text infoBarText;
	private Button endTurn;
	
	private CharacterSelection charSelect;

	
	// Private to prevent instantiation from elsewhere
	private Controller() {
		buildMenu();				
	}
	
	/**
	 * Used to get and instantiate the Controller
	 * @return The current controller instance
	 */
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void buildMenu() {
		//creates ui stack in layers
		UILayers = new StackPane();
		UILayers.setAlignment(Pos.CENTER);
		
		try {
			mainMenu = FXMLLoader.load(getClass().getResource("/application/ui/MainMenu.fxml"));
			UILayers.getChildren().add(mainMenu);
			
		} catch (IOException e) {
			System.out.println("MainMenu.fxml error?");
			e.printStackTrace();
		}
	}
	
	public void buildCharacterSelectionMenu() {
		charSelect = new CharacterSelection();
		UILayers.getChildren().add(charSelect.getView());
	}
	
	public void buildCharacterPlacement() {
		charPlacement = new CharPlacement(charSelect);
	}
	
	public void startGame() {
		buildStage();
		// Close the unit selection menu
		UILayers.getChildren().remove(charSelect.getView());
		
		// Initiate game
		currentGame = new Game();
		buildCharacterPlacement();
	}
	
	public void endGame(int winningTeam) {
		// Prevent user from clicking on anything
		unitGrid.setMouseTransparent(true);
		
		Text endText = new Text("Team " + winningTeam + " has won!");
		endText.setFont(new Font("Didot Bold", 68));
		UILayers.getChildren().add(endText);
	}
	
	public void buildStage() {
		// Environment Grid
		environmentGrid = new Pane();
		environmentGrid.setMinSize(Main.TILE_SIZE*Main.LEVEL_WIDTH, Main.TILE_SIZE*Main.LEVEL_HEIGHT);
		environmentGrid.setMaxSize(Main.TILE_SIZE*Main.LEVEL_WIDTH, Main.TILE_SIZE*Main.LEVEL_HEIGHT);
		
		// Unit Grid
		unitGrid = new Pane();
		unitGrid.setPickOnBounds(false);
		unitGrid.setMinSize(Main.TILE_SIZE*Main.LEVEL_WIDTH, Main.TILE_SIZE*Main.LEVEL_HEIGHT);
		unitGrid.setMaxSize(Main.TILE_SIZE*Main.LEVEL_WIDTH, Main.TILE_SIZE*Main.LEVEL_HEIGHT);
		
		// HUD Layers
		overlay = new AnchorPane();
		overlay.setPickOnBounds(false);
		
		// Info Bar
		Rectangle r = new Rectangle(10, 10, 10, 30);
		r.setWidth(200);
		r.setOpacity(0.2);
		infoBarText = new Text("Hello");
		infoBarText.setFont(new Font(20));
		infoBarText.setFill(Color.BLACK);
		
		// End Turn Button
		endTurn = new Button("End Turn");
		endTurn.setFont(new Font(20));
		endTurn.setOnMouseClicked(e->{
			currentGame.endTurn();
		});
		overlay.getChildren().addAll(r, infoBarText, endTurn);
		AnchorPane.setBottomAnchor(infoBarText, 3.0);
		AnchorPane.setLeftAnchor(infoBarText, 5.0);
		AnchorPane.setLeftAnchor(r, 0.0);
		AnchorPane.setBottomAnchor(r, 0.0);
		AnchorPane.setBottomAnchor(endTurn, 3.0);
		AnchorPane.setRightAnchor(endTurn, 5.0);

		UILayers.getChildren().addAll(environmentGrid, unitGrid, overlay);
	}
	
	public void buildCharPlacement() {
		ArrayList<Unit> team1 = charSelect.getTeam1();
		ArrayList<Unit> team2 = charSelect.getTeam2();
		
		// Create and populate a box for team 1
		HBox team1Box = new HBox();
		for (Unit unit : team1) {
			ImageView temp = new ImageView(unit.getImage());
			temp.setFitHeight(Main.TILE_SIZE);
			temp.setFitWidth(Main.TILE_SIZE);
			temp.setOnDragDetected(e->{
				Dragboard db = temp.startDragAndDrop(TransferMode.ANY);
				
				ClipboardContent content = new ClipboardContent();
			});
			team1Box.getChildren().add(temp);
		}
		
		// Create and populate a box for team 2
		HBox team2Box = new HBox();
		for (Unit unit : team2) {
			ImageView temp = new ImageView(unit.getImage());
			temp.setFitHeight(Main.TILE_SIZE);
			temp.setFitWidth(Main.TILE_SIZE);
			team2Box.getChildren().add(temp);
		}
		
		
	}

	public void addInfoBarText(String text) {
		infoBarText.setText(text);
	}

}
