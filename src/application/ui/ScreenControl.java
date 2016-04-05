package application.ui;

import application.model.level.Level;
import application.ui.battleGrid.BattleGridController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenControl {
	private static Stage stage;
	private static Level currentLevel;
	
	public static BattleGridController battleGrid;
	
	public static void setStage (Stage s) {
		stage = s;
	}
	
	public static Scene getScene() {
		return stage.getScene();
	}
	
	public static void changeScene(Scene s) {
		stage.setScene(s);
	}
	
	public static void setCurrentLevel(Level level) {
		currentLevel = level;
	}
	
	public static Level getCurrentLevel(){
		return currentLevel;
	}
}
