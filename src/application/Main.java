package application;
	
import java.io.IOException;

import application.ui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	//game setup variables
	public static final int LEVEL_WIDTH = 36;
	public static final int LEVEL_HEIGHT = 20;
	public static final int TILE_SIZE = 32;
	public static boolean bypassMenuToDefaultLevel = true;
	public static Scene scene;
	
	public static Controller controller;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {	
		
			
			
			int w = 1152/2;	//Window width
			int h = 640/2;	//Window Height
			
			controller = new Controller();
			
			scene = new Scene(controller.getUILayers());
			controller.addInputControls();
			/*Controller that handles all UI interactions
			* 	...mainly for the Game class to handle the UI stack
			*/
			
			//setup window
			primaryStage.setScene(scene);
			primaryStage.setWidth(w);
			primaryStage.setHeight(h);
			
			//must call show() last to render the setup
			primaryStage.show();
			
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
