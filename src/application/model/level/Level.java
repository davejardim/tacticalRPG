package application.model.level;

import application.model.tile.EnvironmentTile;
import application.model.tile.UnitTile;
import application.model.unit.UnitType;

/**
 * Stores all level data and will handling the loading of levels.
 * @author markbluemer
 *
 */
public class Level {
	
	private final int LEVEL_SIZE = 25;
	private final double TILE_SIZE = 800 / LEVEL_SIZE;
	
	private EnvironmentTile[][] environmentGrid;
	private UnitTile[][] unitGrid;
	
	public Level() {
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
		unitGrid[mid][mid] = new UnitTile(mid, mid, TILE_SIZE, UnitType.SOLDIER);
	}
	
	public EnvironmentTile getEnvironmentTile(int x, int y) {
		return environmentGrid[x][y];
	}
	
	public UnitTile getUnitTile(int x, int y) {
		return unitGrid[x][y];
	}
}
