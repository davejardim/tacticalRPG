package application.ui;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import application.model.game.CharacterSelection;
import application.model.game.Game;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {
	
	private GridPane mainMenu;
	
	
	
	//current game, accessible by all
	public static Game currentGame;
	public static CharacterSelection charSelect;
	//layers
	public static StackPane UILayers; //contains the panes below
	public static Pane environmentGrid;
	public static AnchorPane overlay;
	public static Pane unitGrid;
	public static Pane charSelectionMenu;

	
	
	//local UI variables
	private Location selectedTile1;
	
	private Text infoBarText;
	private Button endTurn;
	
	public Controller() {
		
		selectedTile1 = new Location(0,0);
		// 1) builds user interface layer by layer
		buildUIStack();				
	}
	

	@SuppressWarnings("static-access")
	private void buildUIStack(){
		
				//creates ui stack in layers
				UILayers = new StackPane();
				UILayers.setAlignment(Pos.CENTER);
				
							
				// 	EnvironmentGrid (empty)
				environmentGrid = new Pane();
				UILayers.getChildren().add(environmentGrid);
				
				int ts = Main.TILE_SIZE;
				int w = Main.LEVEL_WIDTH;
				int h = Main.LEVEL_HEIGHT;
				// Unit Grid
				unitGrid = new Pane();
				UILayers.getChildren().add(unitGrid);
				unitGrid.setPickOnBounds(false);
				unitGrid.setMinSize(ts*w,ts*h);
				unitGrid.setMaxSize(ts*w,ts*h);
				
				
				
				charSelectionMenu = new StackPane();
				UILayers.getChildren().add(charSelectionMenu);
				charSelect = new CharacterSelection();
				charSelectionMenu.setVisible(false);
				
				
	
				
				// 	HUD layer
				overlay = new AnchorPane();
				overlay.setPickOnBounds(false);
				Rectangle r = new Rectangle(10,10,10,30);
				infoBarText = new Text("Hello");
				infoBarText.setFont(new Font(20));
				infoBarText.setFill(Color.BLACK);
				r.setWidth(200);
				r.setOpacity(0.2);
				endTurn = new Button("End Turn");
				endTurn.setFont(new Font(20));
				endTurn.setOnMouseClicked(e->{
					currentGame.endTurn();
				});
				overlay.getChildren().addAll(r,infoBarText, endTurn);
				overlay.setBottomAnchor(infoBarText, 3.0);
				overlay.setLeftAnchor(infoBarText, 5.0);
				overlay.setLeftAnchor(r, 0.0);
				overlay.setBottomAnchor(r, 0.0);
				overlay.setBottomAnchor(endTurn, 3.0);
				overlay.setRightAnchor(endTurn, 5.0);
				
				
				UILayers.getChildren().add(overlay);
				
				//overlay.setMouseTransparent(true);

				overlay.autosize();
				
				
				
				
				// 3) MainMenu Layer
				//loads and display main menu
				try {
					mainMenu = FXMLLoader.load(getClass().getResource("/application/ui/MainMenu.fxml"));
					UILayers.getChildren().add(mainMenu);
					
				} catch (IOException e) {
					System.out.println("MainMenu.fxml error?");
					e.printStackTrace();
				}
				

				// Auto set player units (testing purposes)
				ArrayList<Unit> player1 = new ArrayList<Unit>();
				ArrayList<Unit> player2 = new ArrayList<Unit>();
				player1.add(new Unit(5, 5, UnitType.KOFFING, 1));
				player1.add(new Unit(5, 7, UnitType.MARIO, 1));
				player1.add(new Unit(5, 15, UnitType.CAP, 1));
				player2.add(new Unit(30, 5, UnitType.LINK, 2));
				player2.add(new Unit(24, 8, UnitType.PIKACHU, 2));
				player2.add(new Unit(26, 10, UnitType.PIKACHU, 2));
				

				
				//hides main menu (testing purposes) and instead creates default game
				if(Main.bypassMenuToDefaultLevel){
					
					mainMenu.setVisible(false);
					
					currentGame = new Game();
					Controller.currentGame.startGame(player1, player2);
				}	
	}
	
}
