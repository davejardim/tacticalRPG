package application.ui.battleGrid;

import application.ui.ScreenControl;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class BattleGridController {
	
	@FXML
	private StackPane stack;
	private GridPane environmentGrid;
	private GridPane unitGrid;
	private final int GRID_SIZE = 25;
	
	public void initialize() {
		ScreenControl.battleGrid = this;
		buildEnvironmentGrid();
		buildUnitGrid();
		stack.getChildren().addAll(environmentGrid, unitGrid);
	}
	
	private void buildEnvironmentGrid() {
		environmentGrid = new GridPane();
		environmentGrid.setGridLinesVisible(true);
		// Not sure what these do right now -_-
		// https://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column-ids-on-mouse-entered-in-each-cell-of-grid-in
		for (int i = 0 ; i < GRID_SIZE ; i++) {
	        ColumnConstraints colConstraints = new ColumnConstraints();
	        colConstraints.setHgrow(Priority.SOMETIMES);
	        environmentGrid.getColumnConstraints().add(colConstraints);
	    }

	    for (int i = 0 ; i < GRID_SIZE ; i++) {
	        RowConstraints rowConstraints = new RowConstraints();
	        rowConstraints.setVgrow(Priority.SOMETIMES);
	        environmentGrid.getRowConstraints().add(rowConstraints);
	    }
	    
		for (int i = 0; i<GRID_SIZE; i++) {
			for (int j = 0; j<GRID_SIZE; j++) {
				environmentGrid.add(ScreenControl.currentLevel.getEnvironmentTile(i, j).getView(), i, j);
			}
		}
	}
	
	private void buildUnitGrid() {
		unitGrid = new GridPane();
		// Not sure what these do right now -_-
		// https://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column-ids-on-mouse-entered-in-each-cell-of-grid-in
		for (int i = 0 ; i < GRID_SIZE ; i++) {
	        ColumnConstraints colConstraints = new ColumnConstraints();
	        colConstraints.setHgrow(Priority.SOMETIMES);
	        unitGrid.getColumnConstraints().add(colConstraints);
	    }

	    for (int i = 0 ; i < GRID_SIZE ; i++) {
	        RowConstraints rowConstraints = new RowConstraints();
	        rowConstraints.setVgrow(Priority.SOMETIMES);
	        unitGrid.getRowConstraints().add(rowConstraints);
	    }
	    
		for (int i = 0; i<GRID_SIZE; i++) {
			for (int j = 0; j<GRID_SIZE; j++) {
				unitGrid.add(ScreenControl.currentLevel.getUnitTile(i, j).getView(), i, j);
			}
		}
		
	}
}
