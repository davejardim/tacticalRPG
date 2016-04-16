package application.model.game;

import java.io.IOException;
import java.util.ArrayList;

import application.BattleLoop;
import application.Main;
import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.Controller;
import application.ui.UnitPopupMenu;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Stores all level data and will handling the loading of levels.
 * @author markbluemer
 *
 */
public class Game {

	private Unit currentSelectedUnit = null;
	private EnvironmentTile environmentGrid[][];
	private UnitTile unitGrid[][];
	
	private ArrayList<UnitTile> selectedUnits;
	
	public int xSize;
	public int ySize;
	
	public Game() {
			
		genGrid(Main.LEVEL_WIDTH,Main.LEVEL_HEIGHT);
		Controller.environmentGrid.setMaxWidth(xSize*Main.TILE_SIZE);
		Controller.environmentGrid.setMaxHeight(ySize*Main.TILE_SIZE);
		
		selectedUnits = new ArrayList<UnitTile>();
	}
	
	public EnvironmentTile getEnvironmentTile(int x, int y){
		return environmentGrid[x][y];
		
	}
	private void genGrid(int w, int h){
		
		xSize = w;
		ySize = h;
		
		
		//setup plain green grass environment 
		environmentGrid = new EnvironmentTile[w][h];
		for(int x = 0; x < w; x++){
			for(int y = 0; y < h; y++){
			environmentGrid[x][y]= new EnvironmentTile(x, y);
			}
		}
		
		//setup  & init of unit grid
		unitGrid = new UnitTile[w][h];
		System.out.println(unitGrid.length);
		System.out.println(w + " " + h);
		//add default player
		unitGrid[w/2][h/2] = new UnitTile(w/2,h/2);
		
		
		Controller.UILayers.setAlignment(Pos.CENTER);
	}

	public void selectUnit(int x, int y) {
	
		//if(selectedUnits.isEmpty()))
		if(unitGrid[x][y] != null){
			unitGrid[x][y].setSelected(true);
		}	
	}

		
	
//	public StackPane getView() {
//		return this.view;
//	}
//	
//	/**
//	 * Given a unit set it show it's available move positions
//	 * @param unit Unit that is being selected
//	 */
//	public void setSelectedUnit(Unit unit) {
//		clearHighlights();
//		setHighlights(unit);
//	}
//	
//	/**
//	 * Check if given unit can validly move to given x and y coordinates
//	 * @param x X coordinate to move to 
//	 * @param y Y coordinate to move to 
//	 * @param unit Unit that is being moved
//	 * @return True if unit can be moved, false otherwise
//	 */
//	public boolean isValidMove(int x, int y, Unit unit) {
//		if (unit == null) {
//			System.out.println("Error: no unit currently selected");
//			return false;
//		} else {
//			return getValidMoves(unit.getXCord(), unit.getYCord(),
//					unit.getTravelDist())[x][y];
//		}
//	}
//	
//	/**
//	 * Move given unit to given x and y coordinates
//	 * @param x X coordinate to move to
//	 * @param y	Y coordinate to move to
//	 * @param unit Unit to move
//	 */
//	public void moveUnit(int x, int y, Unit unit) {
//		int curX = unit.getXCord();
//		int curY = unit.getYCord();
//		unitGrid[curX][curY].removeUnit();
//		unitGrid[x][y].setUnit(unit, LEVEL_SIZE);
//		unit.setXCord(x);
//		unit.setYCord(y);
//		clearHighlights();
//	}
//	
//	private void setHighlights(Unit unit) {
//		boolean[][] boolGrid = getValidMoves(unit.getXCord(), unit.getYCord(), unit.getTravelDist());
//		for (int i = 0; i < LEVEL_SIZE; i++) {
//			for (int j = 0; j < LEVEL_SIZE; j++) {
//				if (boolGrid[i][j]) {
//					environmentGrid[i][j].getView().setFill(Color.GREEN);
//					environmentGrid[i][j].getView().setOpacity(.5);
//				}
//			}
//		}
//	}
//	
//	private void clearHighlights() {
//		for (int i = 0; i < LEVEL_SIZE; i++) {
//			for (int j = 0; j < LEVEL_SIZE; j++) {
//				environmentGrid[i][j].getView().setFill(Color.TRANSPARENT);
//			}
//		}
//	}
//	
//	private boolean[][] getValidMoves(int x, int y, int maxDist) {
//		boolean[][] highlightGrid = new boolean[LEVEL_SIZE][LEVEL_SIZE];
//		for (int i = 0; i < LEVEL_SIZE; i++) {
//			for (int j = 0; j < LEVEL_SIZE; j++) {
//				if (findDist(x, y, i, j) <= maxDist
//						&& unitGrid[i][j].getUnit() == null) {
//					highlightGrid[i][j] = true;
//				} else {
//					highlightGrid[i][j] = false;
//				}
//			}
//		}
//		return highlightGrid;
//	}
//	
//	private double findDist(int x1, int y1, int x2, int y2) {
//		 return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//	}
//	
//	
//	private void generateGrids() {
//		environmentGrid = new EnvironmentTile[LEVEL_SIZE][LEVEL_SIZE];
//		unitGrid = new UnitTile[LEVEL_SIZE][LEVEL_SIZE];
//		
//		// Right now manually generating
//		// TODO: Load from file or something
//		for (int i = 0; i < LEVEL_SIZE; i++) {
//			for (int j = 0; j < LEVEL_SIZE; j++) {
//				environmentGrid[i][j] = new EnvironmentTile(i, j, TILE_SIZE);
//				unitGrid[i][j] = new UnitTile(i, j, TILE_SIZE);
//			}
//		}
//		
//		int mid = LEVEL_SIZE / 2;
//
//		unitGrid[mid][mid] = new UnitTile(mid, mid, TILE_SIZE, UnitType.MAGE);
//		unitGrid[0][0] = new UnitTile(0, 0, TILE_SIZE, UnitType.KNIGHT);
//
//	}
//	
//	private void renderGrid() {
//		try {
//			this.view = FXMLLoader.load(Main.class.getResource("/application/ui/battleGrid/BattleGrid.fxml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
}
