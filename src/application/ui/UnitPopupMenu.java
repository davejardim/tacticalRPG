package application.ui;

import java.util.ArrayList;
import java.util.Collection;

import application.BattleLoop;
import application.model.unit.Unit;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class UnitPopupMenu extends ContextMenu {

	public UnitPopupMenu(Unit unit, BattleLoop loop) {
		Collection<MenuItem> collection = new ArrayList<MenuItem>();
		// Generate options based on current unit's location
		
		// These currently have no use
		MenuItem potion = new MenuItem("Use Potion");
		
		potion.setOnAction(e-> {
			loop.isMenuOpen = false;
		});
		
		MenuItem stay = new MenuItem("Do nothing");
		
		stay.setOnAction(e-> {
			loop.isMenuOpen = false;
		});
		
		collection.add(potion);
		collection.add(stay);
		
		this.getItems().addAll(collection);
	}
}
