package application.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.model.tile.UnitTile;
import application.model.unit.Unit;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class UnitPopupMenu extends ContextMenu {

	public UnitPopupMenu(Unit unit, UnitTile[][] unitLocs) {
		Collection<MenuItem> collection = new ArrayList<MenuItem>();
		// Generate options based on current unit's location
		
		// Find all attack options
		ArrayList<Unit> attackOptions = unit.attackOptions(unitLocs);
			for (Unit u : attackOptions) {
				if (unit.getTeam() != u.getTeam()) {
				MenuItem newAttack = new MenuItem("Attack " + u.getType().toString());
				newAttack.setOnAction(e-> {
					unit.attack(u);
					Controller.getInstance().setInfoBarText(unit.getType().toString() + " attacked " +
																u.getType().toString() + " for " + unit.getAttack() + " HP!");
					Controller.currentGame.isMenuOpen = false;
				});
				collection.add(newAttack);
			}
		}
		
		MenuItem stay = new MenuItem("Do nothing");
		
		stay.setOnAction(e-> {
			Controller.currentGame.isMenuOpen = false;
		});
		
		collection.add(stay);
		
		this.getItems().addAll(collection);
	}
}
