package application.ui;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import application.model.game.Game;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {
	
	private GridPane mainMenu;
	
	
	
	//current game, accessible by all
	public static Game currentGame;
	
	//layers
	public static StackPane UILayers; //contains the panes below
	public static Pane environmentGrid;
	public static AnchorPane overlay;
	public static Pane unitGrid;
	
	
	//local UI variables
	private Location selectedTile1;
	
	private Text infoBarText;
	
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
				
				// 	HUD layer
				overlay = new AnchorPane();
				overlay.setPickOnBounds(false);
				Rectangle r = new Rectangle(10,10,10,30);
				infoBarText = new Text("Hello");
				infoBarText.setFill(Color.BLACK);
				r.setWidth(200);
				r.setOpacity(0.2);
				overlay.getChildren().addAll(r,infoBarText);
				overlay.setBottomAnchor(infoBarText, 3.0);
				overlay.setLeftAnchor(infoBarText, 5.0);
				overlay.setLeftAnchor(r, 0.0);
				overlay.setBottomAnchor(r, 0.0);
				//overlay.setRightAnchor(r, 0.0);
				infoBarText.setFont(new Font(20));
				UILayers.getChildren().add(overlay);
				
				overlay.setMouseTransparent(true);

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
				player1.add(new Unit(6, 10, UnitType.PIKACHU, 1));
				player1.add(new Unit(5, 15, UnitType.PIKACHU, 1));
				player2.add(new Unit(30, 5, UnitType.PIKACHU, 2));
				player2.add(new Unit(24, 8, UnitType.PIKACHU, 2));
				player2.add(new Unit(26, 10, UnitType.PIKACHU, 2));
				
				
				//hides main menu (testing purposes) and instead creates default game
				if(Main.bypassMenuToDefaultLevel){
					mainMenu.setVisible(false);
					currentGame = new Game(player1, player2);
				}	
	}
	
}
