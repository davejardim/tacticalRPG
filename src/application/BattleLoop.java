package application;

import application.model.level.Level;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.ui.UnitPopupMenu;
import javafx.scene.input.MouseEvent;

/**
 * Handles the loop of everything that occurs during the battle.
 * This is a singleton
 * 
 * @author markbluemer
 *
 */
public class BattleLoop {
	private static BattleLoop instance = null;
	
	private Level level;
	
	private Unit currentSelectedUnit = null;
	
	public boolean isMenuOpen = false;
	private boolean isGameOver, isPlayerTurn;
	
	protected BattleLoop(Level level) {
		this.level = level;
		
		isGameOver = false;
		isPlayerTurn = true; // Always start with player turn
	}
	
	/**
	 * Used to instantiate the battle loop
	 * @param level
	 */
	public static void createBattleLoop(Level level) {
		if (instance == null) {
			instance = new BattleLoop(level);
		}
	}
	
	/**
	 * Used to grab the current BattleLoop
	 * @return
	 */
	public static BattleLoop getInstance() {
		return instance;
	}
	
	/**
	 * Determines what occurs when a click is captured
	 * @param tile UnitTile that was clicked.
	 * @param e MouseEvent that defines the click
	 */
	public void clickHandle(UnitTile tile, MouseEvent e) {
		// Add up front any conditions that should prevent clicking
		if (!isMenuOpen && isPlayerTurn) {
			if (currentSelectedUnit != null) {
				// When a unit is clicked:
				// If another different unit is selected switch to it
				if (tile.getUnit() != null 
						&& !tile.getUnit().equals(currentSelectedUnit)
						&& !tile.getUnit().getHasMoved()) {
					currentSelectedUnit = tile.getUnit();
					level.setSelectedUnit(currentSelectedUnit);
				} else if (level.isValidMove(tile.getXCord(), tile.getYCord(), currentSelectedUnit)) {
					level.moveUnit(tile.getXCord(), tile.getYCord(), currentSelectedUnit);
					
					// Open after move menu
					UnitPopupMenu menu = new UnitPopupMenu(currentSelectedUnit, this);
					menu.show(level.getView(), e.getScreenX(), e.getScreenY());
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
				if (tile.getUnit() != null && !tile.getUnit().getHasMoved()) {
					currentSelectedUnit = tile.getUnit();
					level.setSelectedUnit(currentSelectedUnit);
				}
			}
		}
	}
}
