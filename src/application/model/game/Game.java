package application.model.game;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import application.Main;
import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.Animation;
import application.ui.Controller;
import application.ui.SelectionTile;
import application.ui.UnitPopupMenu;
import enviornment.EnvironmentType;
import javafx.scene.input.MouseEvent;


public class Game {

	private Unit currentSelectedUnit = null;
	private static EnvironmentTile[][] environmentGrid;
	private static UnitTile[][] unitGrid;
	private int playerTurn;
	private ArrayList<Unit> player1Chars, player2Chars;
	private boolean isCharPlacement;

	public int xSize;
	public int ySize;	
	public static boolean isMenuOpen;
	
	public String testLevel = 
			"11111111111111111111111111111111" +
			"10000010000000000000000000000011" +
			"10000010000000000000000000000011" +
			"11000100000111001111111000111111" +
			"11000000000111000111000100001001" +
			"10100000000111000000100001110011" +
			"11001000000111000111000101010011" +
			"10001100000100111010100001110001" +
			"11110000000100100010000000000101" +
			"10010010000100001110001100010001" +
			"10110001000011000110001100001001" +
			"10010100111111100000001100010001" +
			"10010000000000100000100000001001" +
			"10011111111110100000100101110001" +
			"10000000000000100000100010000001" +
			"11111111111111111111111111111111";


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
				 addEnv(n,j,EnvironmentType.WALL);
			 }
	     i++;
		 }
	}
	}
	
	public void startCharPlacement() {
		Controller.getInstance().buildCharPlacement();
		highlightCharPlacement();
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
				environmentGrid[x][y]= new EnvironmentTile(x, y, EnvironmentType.GRASS);
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

	private void addEnv(int xCord, int yCord, EnvironmentType t){
		environmentGrid[xCord][yCord] = new EnvironmentTile(xCord, yCord, t);
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
					clearHighlights();
					
					// Open after move menu
					UnitPopupMenu menu = new UnitPopupMenu(currentSelectedUnit, unitGrid, tile.getType());
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
			if (selectedTile != null && selectedTile.getUnit() != null && isValidPlacement(selectedTile.getUnit(), tile)) {
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
					clearHighlights();
					Controller.getInstance().addInfoBarText("Player 1 start");
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
	 * Highlight where the player can place their characters at the beginning of the game
	 */
	private void highlightCharPlacement() {
		for (int i = 0; i < Main.LEVEL_WIDTH; i++) {
			for (int j = 0; j < Main.LEVEL_HEIGHT; j++) {
				if (i < 4 || i > 27) {
					environmentGrid[i][j].setHighlighted(true);
				}
			}
		}
	}
	
	/**
	 * Determine if the given unit can be placed on the given tile
	 * @param unit Unit currently selected for placement on the grid
	 * @param tile Tile that was clicked
	 * @return True if placement can be made; false otherwise
	 */
	private boolean isValidPlacement(Unit unit, UnitTile tile) {
		boolean isInTeamZone;
		if (unit.getTeam() == 1) {
			isInTeamZone = tile.getXCord() < 4;
		} else {
			isInTeamZone = tile.getXCord() > 27;
		}
		
		return isInTeamZone && !environmentGrid[tile.getXCord()][tile.getYCord()].isWall() && tile.getUnit() == null;
	}
	
	/**
	 * Switch the turn to the other player
	 */
	private void switchPlayer() {
		playerTurn = (playerTurn == 1) ? 2 : 1;
		Controller.getInstance().addInfoBarText("Player " + playerTurn + " start");
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
	public void moveUnit(int toX, int toY, Unit unit) {
		int curX = unit.getXCord();
		int curY = unit.getYCord();
		
		unitGrid[curX][curY].removeUnit();
		unitGrid[curX][curY].setSelected(false);
		unitGrid[toX][toY].setUnit(unit);
		unit.setXCord(toX);
		unit.setYCord(toY);
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
	public static boolean[][] getValidMoves(int x, int y, int maxDist) {

		Comparator<int[]> comp = (sq1, sq2) -> (sq1[0] - sq2[0]);
		PriorityQueue<int[]> Q = new PriorityQueue<>(comp);
		int[] node;
		boolean[][] validSquares = new boolean[Main.LEVEL_WIDTH][Main.LEVEL_HEIGHT];
		for (int i = Math.max(x-maxDist, 0); i < Math.min(x+maxDist+1, Main.LEVEL_WIDTH) ; i++)
		{
			for (int j = Math.max(y-maxDist, 0); j < Math.min(y+maxDist+1, Main.LEVEL_HEIGHT) ; j++)
			{
				if (i == x && j == y)
					Q.add(new int[] {0, i, j});
				else if (environmentGrid[i][j].getEnvType().isPassable())
					Q.add(new int[] {10000, i, j});

			}
		}
		
		while (!Q.isEmpty())
		{
			node = Q.remove();
			if (node[0] > maxDist) continue;
			if ((unitGrid[node[1]][node[2]].getUnit() == null) || node[0] == 0) validSquares[node[1]][node[2]] = true;
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

	public void buildWall() {
		
	for (int i = 0; i < Main.LEVEL_HEIGHT; i++)
		{
			addEnv(Main.LEVEL_WIDTH/2, i, EnvironmentType.TRUMPWALL);
		}
	}

	
}
