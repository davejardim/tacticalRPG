package application;
	
import application.ui.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	//game setup variables
	public static final int LEVEL_WIDTH = 32;
	public static final int LEVEL_HEIGHT = 16;
	public static final int TILE_SIZE = 32;
	public static boolean bypassMenuToDefaultLevel = true;
	public static Scene scene;
	
	public static Controller controller;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {	
		
			
			
			int w = 1024;	//Window width
			int h = 512+50;	//Window Height
			
			controller = new Controller();
			

			scene = new Scene(controller.UILayers);
			
			/*Controller that handles all UI interactions
			* 	...mainly for the Game class to handle the UI stack
			*/
			
			//setup window
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(w);
			primaryStage.setMinHeight(h);
			primaryStage.setMaxWidth(w);
			primaryStage.setMaxHeight(h);
			
			
			//must call show() last to render the setup
			primaryStage.show();
			
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
