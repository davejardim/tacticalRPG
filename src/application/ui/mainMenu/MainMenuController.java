package application.ui.mainMenu;

import java.io.IOException;

import application.model.level.Level;
import application.ui.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class MainMenuController {
	
	// For now this just loads a generic level
	// TODO: Should load a game and go into map view
	@FXML protected void handleNewGameButtonAction (ActionEvent event) throws IOException {
		Level level = new Level();
		Scene scene = new Scene(level.getView());
		ScreenControl.changeScene(scene);
		
	}
	
	@FXML protected void handleLoadGameButtonAction (ActionEvent event) {
		//Bring up game load screen
	}
}
