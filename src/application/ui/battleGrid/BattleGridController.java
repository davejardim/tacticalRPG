package application.ui.battleGrid;

import java.io.IOException;

import application.Main;
import application.model.level.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BattleGridController {
	
	@FXML
	private StackPane stack;
	
	public static Level currentLevel;
	
	public void initialize() {
		try {
			currentLevel = new Level();
			
			GridPane environmentGrid = FXMLLoader.load(Main.class.getResource("/application/ui/environmentGrid/EnvironmentGrid.fxml"));
			GridPane unitGrid = FXMLLoader.load(Main.class.getResource("/application/ui/unitGrid/UnitGrid.fxml"));
			stack.getChildren().addAll(environmentGrid, unitGrid);
		} catch (LoadException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
