package application.ui;

import application.model.level.Level;
import application.ui.battleGrid.BattleGridController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenControl {
	public static Stage stage;
	public static Level currentLevel;
	
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
}
