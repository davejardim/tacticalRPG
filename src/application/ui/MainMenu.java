package application.ui;

import java.io.IOException;

import application.model.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class MainMenu {

	@FXML GridPane mainMenu;
	
	void initialize()
	{
		System.out.println("hello");
	}
	
	
	@FXML protected void handleNewGameButtonAction (ActionEvent event) throws IOException {
		
		//Level level = new Level(10,10);
		Controller.currentGame = new Game();
		
		mainMenu.setVisible(false);
		
	}
	
	@FXML protected void handleLoadGameButtonAction (ActionEvent event) {
		//Bring up game load screen
		//menuPane.setVisible(false);
	}
	
}