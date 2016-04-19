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
		
		// These currently have no use
		MenuItem potion = new MenuItem("Attack");
		
		potion.setOnAction(e-> {
			List<Unit> attackOptions = unit.attackOptions(unitLocs);
			unit.attack(attackOptions.get(0));
			System.out.println(attackOptions.get(0).getHp());
			Controller.currentGame.isMenuOpen = false;
		});
		
		MenuItem stay = new MenuItem("Do nothing");
		
		stay.setOnAction(e-> {
			Controller.currentGame.isMenuOpen = false;
		});
		
		collection.add(potion);
		collection.add(stay);
		
		this.getItems().addAll(collection);
	}
}
