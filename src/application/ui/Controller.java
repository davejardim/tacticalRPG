package application.ui;

import java.io.IOException;

import application.Main;
import application.model.game.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
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
		
		// 2) addInputControls executed in Main	
		//
		
		
	}
	

	@SuppressWarnings("static-access")
	private void buildUIStack(){
		
				//creates ui stack in layers
				UILayers = new StackPane();
				UILayers.setAlignment(Pos.CENTER);
				//UILayers.setLayoutY(20);
				//	Background (not in use)
				//Canvas c = new Canvas();
				//c.getGraphicsContext2D().setFill(Color.BLACK);
				//c.getGraphicsContext2D().fill();
				//UILayers.getChildren().add(c);
				
				
				// 	EnvironmentGrid (empty)
				environmentGrid = new Pane();
				UILayers.getChildren().add(environmentGrid);
				
				
				// Unit Grid
				unitGrid = new Pane();
				UILayers.getChildren().add(unitGrid);
				unitGrid.setPickOnBounds(false);
				
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
				
				//hides main menu (testing purposes) and instead creates default game
				if(Main.bypassMenuToDefaultLevel){
					mainMenu.setVisible(false);
					currentGame = new Game();
				}	
	}
	
}
