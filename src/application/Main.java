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
	public static boolean bypassMenuToDefaultLevel = false;
	public static Scene scene;
	public static final String playerResource = "/application/resources/player/";
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {	
		
			
			
			int w = 1024+64;	//Window width
			int h = 512+64;	//Window Height
			
			// Instantiate controller
			Controller.getInstance();
			

			scene = new Scene(Controller.getInstance().UILayers);
			/*Controller that handles all UI interactions
			* 	...mainly for the Game class to handle the UI stack
			*/
			
			//setup window
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(w);
			primaryStage.setMinHeight(h+32);
			primaryStage.setMaxWidth(w);
			primaryStage.setMaxHeight(h+32);
			
			
			//must call show() last to render the setup
			primaryStage.show();
			
			
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
