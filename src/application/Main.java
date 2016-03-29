package application;
	
import java.io.IOException;

import application.ui.ScreenControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {	
		try {
			GridPane mainMenu = FXMLLoader.load(getClass().getResource("/application/ui/mainMenu/MainMenu.fxml"));	
			
			primaryStage.setScene(new Scene(mainMenu, 850, 850));
			primaryStage.setMaxHeight(850);
			primaryStage.setMinHeight(850);
			primaryStage.setMaxWidth(850);
			primaryStage.setMinWidth(850);
			primaryStage.show();
			ScreenControl.setStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
