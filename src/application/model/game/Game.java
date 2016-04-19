package application.model.game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import application.Main;
import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.Controller;
import application.ui.UnitPopupMenu;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;

/**
 * Stores all level data and will handling the loading of levels.
 * @author markbluemer
 *
 */
public class Game {

	private Unit currentSelectedUnit = null;
	private EnvironmentTile[][] environmentGrid;
	private UnitTile[][] unitGrid;

	public int xSize;
	public int ySize;	
	public static boolean isMenuOpen, isPlayerTurn;


	public Game() {

		genGrid(Main.LEVEL_WIDTH,Main.LEVEL_HEIGHT);
		Controller.environmentGrid.setMaxWidth(xSize*Main.TILE_SIZE);
		Controller.environmentGrid.setMaxHeight(ySize*Main.TILE_SIZE);

		//add default player
		addUnit(4,4, UnitType.PIKACHU);
		addUnit(4,5, UnitType.WALL);

		isMenuOpen = false;
		isPlayerTurn = true;


	}

	public EnvironmentTile getEnvironmentTile(int x, int y){
		if( x < 0 || x == xSize || y < 0 || y >= ySize)
			return null;
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
		for(int x = 0; x < w; x++){
			for(int y = 0; y < h; y++){
				unitGrid[x][y]= new UnitTile(x, y);
			}
		}
		System.out.println(unitGrid.length);
		System.out.println(w + " " + h);


		Controller.UILayers.setAlignment(Pos.CENTER);
	}

	public void selectUnit(int x, int y) {
		unitGrid[x][y].setSelected(true);
	}

	private void addUnit(int xCord, int yCord, UnitType t){

		unitGrid[xCord][yCord] = new UnitTile(xCord, yCord, t);
	}

	public void onClick(UnitTile tile, MouseEvent e) {
		// Add up front any conditions that should prevent clicking
		if (!isMenuOpen && isPlayerTurn) {
			if (currentSelectedUnit != null) {
				// When a unit is clicked:
				// If another different unit is selected switch to it	
				if (tile.getUnit() != null 
						&& !tile.getUnit().equals(currentSelectedUnit)
						&& !tile.getUnit().getHasMoved()
						&& tile.getUnit().getCanMove()) {
					unitGrid[currentSelectedUnit.getXCord()][currentSelectedUnit.getYCord()].setSelected(false);
					tile.setSelected(true);
					setSelectedUnit(tile.getUnit());
					tile.setSelected(true);
				} else if (isValidMove(tile.getXCord(), tile.getYCord(), currentSelectedUnit)) {
					moveUnit(tile.getXCord(), tile.getYCord(), currentSelectedUnit);

					// Open after move menu
					UnitPopupMenu menu = new UnitPopupMenu(currentSelectedUnit);
					menu.show(Controller.UILayers, e.getScreenX(), e.getScreenY());
					isMenuOpen = true;

					//After moving set current unit to null
					currentSelectedUnit.switchMoved();
					currentSelectedUnit = null;

				} else {
					// Throw some error message
					// TODO: Output this into player console
					System.out.println("That is not a valid move");
				}
			} else {
				// If no unit chosen and unit is clicked then highlight paths
				System.out.println("CODE");
				if (tile.getUnit() != null && !tile.getUnit().getHasMoved() && tile.getUnit().getCanMove()) {
					setSelectedUnit(tile.getUnit());
					tile.setSelected(true);
				}
			}
		}
	}

	/**
	 * Given a unit set it show it's available move positions
	 * @param unit Unit that is being selected
	 */
	public void setSelectedUnit(Unit unit) {
		currentSelectedUnit = unit;
		clearHighlights();
		setHighlights(unit);
	}

	/**
	 * Check if given unit can validly move to given x and y coordinates
	 * @param x X coordinate to move to 
	 * @param y Y coordinate to move to 
	 * @param unit Unit that is being moved
	 * @return True if unit can be moved, false otherwise
	 */
	public boolean isValidMove(int x, int y, Unit unit) {
		if (unit == null) {
			System.out.println("Error: no unit currently selected");
			return false;
		} else {
			return getValidMoves(unit.getXCord(), unit.getYCord(), unit.getTravelDist())[x][y];
		}
	}

	/**
	 * Move given unit to given x and y coordinates
	 * @param x X coordinate to move to
	 * @param y	Y coordinate to move to
	 * @param unit Unit to move
	 */
	public void moveUnit(int x, int y, Unit unit) {
		int curX = unit.getXCord();
		int curY = unit.getYCord();
		unitGrid[curX][curY].removeUnit();
		unitGrid[curX][curY].setSelected(false);
		unitGrid[x][y].setUnit(unit);
		unit.setXCord(x);
		unit.setYCord(y);
		clearHighlights();
	}

	private void setHighlights(Unit unit) {
		boolean[][] boolGrid = getValidMoves(unit.getXCord(), unit.getYCord(), unit.getTravelDist());
		for (int i = 0; i < Main.LEVEL_WIDTH; i++) {
			for (int j = 0; j < Main.LEVEL_HEIGHT; j++) {
				if (boolGrid[i][j]) {
					environmentGrid[i][j].setHighlighted(true);
				}
			}
		}
	}

	private void clearHighlights() {
		for (int i = 0; i < Main.LEVEL_WIDTH; i++) {
			for (int j = 0; j < Main.LEVEL_HEIGHT; j++) {
				environmentGrid[i][j].setHighlighted(false);
			}
		}
	}
	
	// Uses Dijkstra's to find valid paths given a certain distance
	private boolean[][] getValidMoves(int x, int y, int maxDist) {

		Comparator<int[]> comp = (sq1, sq2) -> (sq1[0] - sq2[0]);
		PriorityQueue<int[]> Q = new PriorityQueue<>(comp);
		int[] node;
		boolean[][] validSquares = new boolean[Main.LEVEL_WIDTH][Main.LEVEL_HEIGHT];
		for (int i = Math.max(x-maxDist, 0); i <= Math.min(x+maxDist, Main.LEVEL_WIDTH) ; i++)
		{
			for (int j = Math.max(y-maxDist, 0); j <= Math.min(x+maxDist, Main.LEVEL_HEIGHT) ; j++)
			{
				if (i == x && j == y)
					Q.add(new int[] {0, i, j});
				else if (unitGrid[i][j].isPassable())
					Q.add(new int[] {10000, i, j});

			}
		}
		
		while (!Q.isEmpty())
		{
			node = Q.remove();
			if (node[0] > maxDist) continue;
			if (0 != node[0]) validSquares[node[1]][node[2]] = true;
			ArrayList<int[]> nodeNeighbours = new ArrayList<>();
			for (int[] element : Q)
			{
				//Top Neighbour
				if (element[1] == node[1] && element[2] == node[2] + 1)
				{
					if (element[0] > node[0] +1) {
						element[0] = node[0] + 1;
						nodeNeighbours.add(element);
					}
				}
				//Right Neighbour
				else if (element[1] == node[1] + 1 && element[2] == node[2])
				{
					if (element[0] > node[0] +1) {
						element[0] = node[0] + 1;
						nodeNeighbours.add(element);
					
					}
				}
				//Bottom Neighbour
				else if (element[1] == node[1] && element[2] == node[2] - 1)
				{
					if (element[0] > node[0] +1) {
						element[0] = node[0] + 1;
						nodeNeighbours.add(element);
					}
				}
				//Left Neighbour
				else if (element[1] == node[1] - 1 && element[2] == node[2])
				{
					if (element[0] > node[0] +1) {
						element[0] = node[0] + 1;
						nodeNeighbours.add(element);
					}
				}
			}
			//Resort neighbours back into priority queue:
			for (int[] neighbour : nodeNeighbours)
			{
				Q.remove(neighbour);
				Q.add(neighbour);
			}
		}
		return validSquares;
	}
}
