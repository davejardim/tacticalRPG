package application.model.game;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

import application.Main;
import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.Controller;
import application.ui.SelectionTile;
import application.ui.UnitPopupMenu;
import javafx.scene.input.MouseEvent;


public class Game {

	private Unit currentSelectedUnit = null;
	private EnvironmentTile[][] environmentGrid;
	private UnitTile[][] unitGrid;
	private int playerTurn;
	private ArrayList<Unit> player1Chars, player2Chars;
	private boolean isCharPlacement;

	public int xSize;
	public int ySize;	
	public static boolean isMenuOpen;
	
	public String testLevel = 
			"00000000000111111111111111111111" +
			"00000010000000000000000000000011" +
			"00000010000000000000000000000011" +
			"00000000000111001111111000111111" +
			"00000000000111000000000000000001" +
			"00000000000111000000000000000001" +
			"00000000000111000000000000000001" +
			"00000100000100000000000000000001" +
			"00000000000100000000000000000001" +
			"00010000000100000000001100000001" +
			"00000000000100000000001100000001" +
			"00000100000000000000001100000001" +
			"00000000000000000000000000000001" +
			"00010000000000000000000000000001" +
			"00000000000100000000000000000001" +
			"00000000000111111111111111111111";


	public Game() {
		isMenuOpen = false;
		playerTurn = 1;
		isCharPlacement = true;
		player1Chars = new ArrayList<Unit>();
		player2Chars = new ArrayList<Unit>();
		genGrid(Main.LEVEL_WIDTH,Main.LEVEL_HEIGHT);
		populateWalls(testLevel);
		startCharPlacement();
		
	}
	
	public UnitTile[][] getUnitGrid() {
		return unitGrid;
	}
	//places walls based on a string
	public void populateWalls(String walls){
	int i = 0;
	for (int j = 0; j < 16; j++) {
		 for (int n = 0; n < 32; n++) {
			 if(walls.charAt(i)=='1'){
				 addUnit(n,j,UnitType.WALL);
			 }
	     i++;
		 }
	}
	}
	
	public void startCharPlacement() {
		Controller.getInstance().buildCharPlacement();
	}
	
	public void startGame(ArrayList<Unit> team1, ArrayList<Unit> team2) {
		player1Chars = team1;
		player2Chars = team2;
		isMenuOpen = false;
		playerTurn = 1;
		
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
				unitGrid[x][y]= new UnitTile(x, y, Controller.unitGrid);
			}
		}
	}
	
	

	public void selectUnit(int x, int y) {
		unitGrid[x][y].setSelected(true);
	}

	
	private void addUnit(int xCord, int yCord, UnitType t){

		unitGrid[xCord][yCord] = new UnitTile(xCord, yCord, Controller.unitGrid, t);
	}

	public void onClick(UnitTile tile, MouseEvent e) {
		// Add up front any conditions that should prevent clicking
		if (!isMenuOpen && !isCharPlacement) {
			if (currentSelectedUnit != null) {
				// When a unit is clicked:
				// If another different unit is selected switch to it	
				if (tile.getUnit() != null 
						&& !tile.getUnit().equals(currentSelectedUnit)
						&& isCurrentPlayersUnit(tile.getUnit())
						&& !tile.getUnit().getHasMoved()
						&& tile.getUnit().getCanMove()) 
				{
					unitGrid[currentSelectedUnit.getXCord()][currentSelectedUnit.getYCord()].setSelected(false);
					setSelectedUnit(tile.getUnit());
				} else if (isValidMove(tile.getXCord(), tile.getYCord(), currentSelectedUnit)) {
					moveUnit(tile.getXCord(), tile.getYCord(), currentSelectedUnit);

					// Open after move menu
					UnitPopupMenu menu = new UnitPopupMenu(currentSelectedUnit, unitGrid);
					menu.show(Controller.UILayers, e.getScreenX(), e.getScreenY());
					isMenuOpen = true;

					//After moving set current unit to null
					currentSelectedUnit.setMoved(true);
					currentSelectedUnit = null;

				} else {
					// Throw some error message
					// TODO: Output this into player console
					System.out.println("That is not a valid move");
				}
			} else {
				// If no unit chosen and unit is clicked then highlight paths
				System.out.println("CODE");
				if (tile.getUnit() != null && !tile.getUnit().getHasMoved() 
											&& tile.getUnit().getCanMove()
											&& isCurrentPlayersUnit(tile.getUnit())) {
					setSelectedUnit(tile.getUnit());
				}
			}
		}
		
		if (isCharPlacement) {
			SelectionTile selectedTile = Controller.getInstance().charPlacement.getSelected();
			if (selectedTile.getUnit() != null) {
				if (selectedTile.getUnit().getTeam() == 1) {
					player1Chars.add(selectedTile.getUnit());
				} else {
					player2Chars.add(selectedTile.getUnit());
				}
				moveUnit(tile.getXCord(), tile.getYCord(), selectedTile.getUnit());
				selectedTile.removeHighlight();
				selectedTile.remove();
				if (Controller.getInstance().charPlacement.isDone()) {
					isCharPlacement = false;
				}
			}
		}
	}
	
	public void endTurn() {
		// Start by changing players units hasMoved variable
		if (playerTurn == 1) {
			for (Unit unit : player1Chars) {
				unit.setMoved(false);
			}
		} else {
			for (Unit unit : player2Chars) {
				unit.setMoved(false);
			}
		}
		
		// Clear selected unit, then switch the player
		setSelectedUnit(null);
		switchPlayer();
	}
	
	/**
	 * Switch the turn to the other player
	 */
	private void switchPlayer() {
		playerTurn = (playerTurn == 1) ? 2 : 1;
	}
	
	/**
	 * Given a unit check whether it's current players unit
	 * @param unit Unit being selected
	 * @return If current players unit
	 */
	private boolean isCurrentPlayersUnit(Unit unit) {
		return playerTurn == unit.getTeam();
	}

	/**
	 * Given a unit set it show it's available move positions
	 * @param unit Unit that is being selected
	 */
	public void setSelectedUnit(Unit unit) {
		if (!(currentSelectedUnit == null)) {
			unitGrid[currentSelectedUnit.getXCord()][currentSelectedUnit.getYCord()].setSelected(false);
		}
		currentSelectedUnit = unit;
		clearHighlights();
		if (!(unit == null)) {
			unitGrid[unit.getXCord()][unit.getYCord()].setSelected(true);
			setHighlights(unit);
		}
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
			return getValidMoves(unit.getXCord(), unit.getYCord(), unit.getMaxMove())[x][y];
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
		boolean[][] boolGrid = getValidMoves(unit.getXCord(), unit.getYCord(), unit.getMaxMove());
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
		for (int i = Math.max(x-maxDist, 0); i < Math.min(x+maxDist, Main.LEVEL_WIDTH) ; i++)
		{
			for (int j = Math.max(y-maxDist, 0); j < Math.min(x+maxDist, Main.LEVEL_HEIGHT) ; j++)
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
