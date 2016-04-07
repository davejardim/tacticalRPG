package application.model.level;

import java.io.IOException;

import application.Main;
import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.ScreenControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Stores all level data and will handling the loading of levels.
 * @author markbluemer
 *
 */
public class Level {
	
	private final int LEVEL_SIZE = 25;
	private final int TILE_SIZE = 800 / LEVEL_SIZE;
	
	private EnvironmentTile[][] environmentGrid;
	private UnitTile[][] unitGrid;
	
	private StackPane view;

	private Unit currentSelectedUnit = null;
	
	public Level() {
		ScreenControl.currentLevel = this;
		generateGrids();
		renderGrid();
	}
	
	public EnvironmentTile getEnvironmentTile(int x, int y) {
		return environmentGrid[x][y];
	}
	
	public UnitTile getUnitTile(int x, int y) {
		return unitGrid[x][y];
	}
	
	public double getTileSize() {
		return this.getTileSize();
	}
	
	public StackPane getView() {
		return this.view;
	}
	
	public void clickHandle(UnitTile tile) {
		if(currentSelectedUnit != null) {
			// Actions for when a unit is clicked
			// If another unit is clicked switch to that one
			if(tile.getUnit() != null && !tile.getUnit().equals(currentSelectedUnit)) {
				clearHighlights();
				setHighlights(tile.getUnit());
			}
			// If valid move then move current unit
			if (isValidMove(tile.getXCord(), tile.getYCord())) {
				moveCurrentUnit(tile.getXCord(), tile.getYCord());
				currentSelectedUnit.setXCord(tile.getXCord());
				currentSelectedUnit.setYCord(tile.getYCord());
				
				// Open after move menu
				
				
				// After moving set current unit to null
				currentSelectedUnit = null;
				clearHighlights();
			} else {
				// Throw some error message
				System.out.println("That is not a valid move");
			}
			// If enemy then attack
		} else {
			// If no unit chosen so far and a unit is clicked then highlight paths
			if(tile.getUnit() != null) {
				currentSelectedUnit = tile.getUnit();
				setHighlights(currentSelectedUnit);
			} 
			// Else for now do nothing
		}
	}
	
	// Move currently selected unit to the specified coordinates
	private void moveCurrentUnit(int x, int y) {
		int curX = currentSelectedUnit.getXCord();
		int curY = currentSelectedUnit.getYCord();
		unitGrid[curX][curY].removeUnit();
		unitGrid[x][y].setUnit(currentSelectedUnit, LEVEL_SIZE);
	}
	
	private void setHighlights(Unit unit) {
		boolean[][] boolGrid = getValidMoves(unit.getXCord(), unit.getYCord(), unit.getTravelDist());
		for (int i = 0; i < LEVEL_SIZE; i++) {
			for (int j = 0; j < LEVEL_SIZE; j++) {
				if (boolGrid[i][j]) {
					environmentGrid[i][j].getView().setFill(Color.GREEN);
					environmentGrid[i][j].getView().setOpacity(.5);
				}
			}
		}
	}
	
	private void clearHighlights() {
		for (int i = 0; i < LEVEL_SIZE; i++) {
			for (int j = 0; j < LEVEL_SIZE; j++) {
				environmentGrid[i][j].getView().setFill(Color.TRANSPARENT);
			}
		}
	}
	
	private boolean[][] getValidMoves(int x, int y, int maxDist) {
		boolean[][] highlightGrid = new boolean[LEVEL_SIZE][LEVEL_SIZE];
		for (int i = 0; i < LEVEL_SIZE; i++) {
			for (int j = 0; j < LEVEL_SIZE; j++) {
				if (findDist(x, y, i, j) <= maxDist) {
					highlightGrid[i][j] = true;
				} else {
					highlightGrid[i][j] = false;
				}
			}
		}
		return highlightGrid;
	}
	
	private boolean isValidMove(int x, int y) {
		if (currentSelectedUnit == null) {
			System.out.println("Error: no unit currently selected");
			return false;
		} else {
			return getValidMoves(currentSelectedUnit.getXCord(), currentSelectedUnit.getYCord(),
					currentSelectedUnit.getTravelDist())[x][y];
		}
	}
	
	private double findDist(int x1, int y1, int x2, int y2) {
		 return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	private void generateGrids() {
		environmentGrid = new EnvironmentTile[LEVEL_SIZE][LEVEL_SIZE];
		unitGrid = new UnitTile[LEVEL_SIZE][LEVEL_SIZE];
		
		// Right now manually generating
		// TODO: Load from file or something
		for (int i = 0; i < LEVEL_SIZE; i++) {
			for (int j = 0; j < LEVEL_SIZE; j++) {
				environmentGrid[i][j] = new EnvironmentTile(i, j, TILE_SIZE);
				unitGrid[i][j] = new UnitTile(i, j, TILE_SIZE);
			}
		}
		
		int mid = LEVEL_SIZE / 2;
		unitGrid[mid][mid] = new UnitTile(mid, mid, TILE_SIZE, UnitType.ARCHER);
	}
	
	private void renderGrid() {
		try {
			this.view = FXMLLoader.load(Main.class.getResource("/application/ui/battleGrid/BattleGrid.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
