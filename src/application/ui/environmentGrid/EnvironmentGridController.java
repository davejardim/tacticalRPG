package application.ui.environmentGrid;

import application.ui.ScreenControl;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class EnvironmentGridController {
	
	// Constant for now, figure out how to move this to the BattleGridController
	private final int GRID_SIZE = 25;
	
	@FXML
	private GridPane grid;
	
	public void initialize() {
		
		// Not sure what these do right now -_-
		// https://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column-ids-on-mouse-entered-in-each-cell-of-grid-in
		for (int i = 0 ; i < GRID_SIZE ; i++) {
	        ColumnConstraints colConstraints = new ColumnConstraints();
	        colConstraints.setHgrow(Priority.SOMETIMES);
	        grid.getColumnConstraints().add(colConstraints);
	    }

	    for (int i = 0 ; i < GRID_SIZE ; i++) {
	        RowConstraints rowConstraints = new RowConstraints();
	        rowConstraints.setVgrow(Priority.SOMETIMES);
	        grid.getRowConstraints().add(rowConstraints);
	    }
	    
		for (int i = 0; i<GRID_SIZE; i++) {
			for (int j = 0; j<GRID_SIZE; j++) {
				addPane(i, j);
			}
		}
	}
	
	private void addPane(int col, int row) {
		grid.add(ScreenControl.getCurrentLevel().getEnvironmentTile(col, row).getView(), col, row);
		
	}
}
