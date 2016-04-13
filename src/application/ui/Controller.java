package application.ui;

import java.io.IOException;

import com.sun.glass.ui.Application;

import application.Main;
import application.model.game.Game;
import application.model.tile.EnvironmentTile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
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
	
	//local UI variables
	private Location currentSelectedTile;
	private Text infoBarText;
	
	public Controller() {
		
		// 1) builds user interface layer by layer
		buildUIStack();
		
		// 2) addInputControls executed in Main	
		//
		
		
	}
	
	public void addInputControls() {

		
		UILayers.setPickOnBounds(false);
		
		
		overlay.setOnKeyTyped(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(currentSelectedTile == null)
					currentSelectedTile = new Location(Controller.currentGame.xSize/2,Controller.currentGame.xSize/2);
				else {
					Location l = currentSelectedTile;
					int xCord = l.getX();
					int yCord = l.getY();
					if(event.getText().equals("w"))
						l.setCoordinate(xCord, yCord - 1);
					if(event.getText().equals("d"))
						l.setCoordinate(xCord + 1, yCord);
					if(event.getText().equals("s"))
						l.setCoordinate(xCord, yCord + 1);
					if(event.getText().equals("a"))
						l.setCoordinate(xCord - 1, yCord);
				}
				infoBarText.setText("(" + currentSelectedTile.getX() + ", " + currentSelectedTile.getY()  + ")");
				//System.out.println(infoBarText.getText());
			//	currentGame.getLocation(currentSelectedTile.getX(), currentSelectedTile.getY());
			}
		});
		
		environmentGrid.setOnMouseMoved(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				int x = (int)event.getX()/Main.TILE_SIZE;
				int y = (int) event.getY()/Main.TILE_SIZE;
				
				if(currentSelectedTile == null)
					currentSelectedTile = new Location(x,y);
				else
					currentSelectedTile.setCoordinate(x,y);
				
				infoBarText.setText("(" + x + ", " + y + ")");
			}
		});
		
		environmentGrid.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
			
			
			//System.out.println(UILayers.getWidth());
			}
			});
		
	}
 

	public StackPane getUILayers(){
		return UILayers;
	}
	public Parent getUIStack() {
		return UILayers;
	}
	
	
	@SuppressWarnings("static-access")
	private void buildUIStack(){
		
		//creates ui stack
				UILayers = new StackPane();
				UILayers.setAlignment(Pos.CENTER);
				//background
				Canvas c = new Canvas();
				c.getGraphicsContext2D().setFill(Color.BLACK);
				c.getGraphicsContext2D().fill();
				//UILayers.getChildren().add(c);
				
				//adds empty environmentGrid to UI stack
				environmentGrid = new Pane();
				UILayers.getChildren().add(environmentGrid);
				
				
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
				
		overlay = new AnchorPane();
		overlay.setPickOnBounds(false);
		Rectangle r = new Rectangle();
		infoBarText = new Text("asd");
		
		overlay.getChildren().addAll(r,infoBarText);
		overlay.setBottomAnchor(infoBarText, 3.0);
		overlay.setLeftAnchor(infoBarText, 5.0);
		infoBarText.setFont(new Font(20));
		UILayers.getChildren().add(overlay);
		
		overlay.autosize();
		
		
	}
	
}



/*Canvas c = new Canvas(100,100);
gameLayers.getChildren().addAll(c);
c.getGraphicsContext2D().setFill(Color.BLACK);
c.getGraphicsContext2D().fillRect(100,100,100,100);			
Text t = new Text("");
AnchorPane at = new AnchorPane(t);
at.setBottomAnchor(t, 8.0);
at.setLeftAnchor(t, 5.0);

t.setTextAlignment(TextAlignment.LEFT);
System.out.println(gameLayers.getChildren());

c.setVisible(true);
gameLayers.getChildren().addAll(at);
*/